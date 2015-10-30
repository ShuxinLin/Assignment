/* This module is SMP-safe */

/* Standard headers for LKMs */
#include <linux/kernel.h>
#include <linux/module.h>  
#include <linux/wait.h> 
#include <linux/time.h>
/* We also need the ability to put ourselves to sleep 
 * and wake up later */
#include <linux/sched.h>
#include <linux/kernel_stat.h>
#include <linux/delay.h>

/*define the standard of busy time which is set to 1 for this module*/
#define BUSY_STANDARD_TIME  1

/* This is to store the current time */
static struct timespec my_time;

/* This is used by cleanup, to prevent the module from 
 * being unloaded while the kernel thread is still active */
static DECLARE_WAIT_QUEUE_HEAD(WaitQ);

/* This is the flag of wait_event */
static int condition = 0;

/* signal to thread */
static int please_clock_off = 0;

static struct task_struct* p = &init_task;

/* This is to store the pid of busy process */
static int busy_proc = 1;

/* This is to store the time of keyboard interrupts*/
static int interrupt_time = 0;

static int timer = 0;

MODULE_LICENSE("GPL");

/* This function is to traverse the task_list and suspend the busy process */
void check_busy(){

 for(p = &init_task; (p = next_task(p))!= &init_task;){
     if(cputime_to_secs(p->stime)>BUSY_STANDARD_TIME){    //the first requirement of busy process
     do_posix_clock_monotonic_gettime(&my_time); 	//get the current time
          if((p->stime)*10 > my_time.tv_sec - p->start_time.tv_sec)   //the second requirement of busy process
          {
	   busy_proc=p->pid;
           kill_proc(busy_proc,SIGSTOP,1);	//signal to suspend for 60s
           printk("Process is suspended\n");
	   timer = 0;
 	   break;	 // prevent more than one busy process
	  }
      }
   }
}

void check_timer()
{
	if(timer == 6)
	 { kill_proc(busy_proc,SIGCONT,1);  //signal to wake up the busy process
	  printk("Process is waked up\n");
	  timer = 7;
	}
	else timer++;  
}
/* This is the routine that is run by the kernel thread we
   start when the modulue is loaded */
static int worker_routine(void *irrelevant)
{
  /* drop userspace stuff */
  daemonize("relaxer");
  printk("Your worker is working\n");
  /* now do the work */
  while ( 1 ) {
    
    /* If cleanup wants us to die */
    if (please_clock_off) {
      printk("Your worker is clocking off\n");
      condition=1;          //set the condition to be 1
      complete_and_exit(NULL,0);    // terminate the kernel thread 
    }
    else {
      printk("Hello, this is your worker.\n");
      /* schedule timeout will busy wait unless we say otherwise */
      /* sleep for 10 seconds */
      set_current_state(TASK_INTERRUPTIBLE);
      schedule_timeout(10*HZ);

      if (interrupt_time<kstat_irqs(1)) { //If one key is pressed
      	 check_busy();  
         interrupt_time = kstat_irqs(1); 
      } 
         check_timer(); 
   }
}
}

/* Initialize the module - start kernel thread */
int init_module()
{
  kernel_thread(worker_routine,NULL,0);
  return 0;
}

/* Cleanup */
void cleanup_module()
{
  please_clock_off = 1;
  /* this 12 second sleep simulates the effect of having an SMP
     system, where the worker thread might be executing at the same
     time as rmmod */
  set_current_state(TASK_UNINTERRUPTIBLE);
  schedule_timeout(12*HZ);
  wait_event(WaitQ,condition);
}
