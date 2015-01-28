/* Part1:Build a new Shell */
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <sys/wait.h>
#include <sys/types.h>

/* Define the max length of command and path */
#define MAX_CMD  512
#define MAX_PATH 512
 
/* This is to process the command */
void process_cmd(char *cmd, char *arg) {
    if (strcmp(cmd, "cd") == 0) {   /* cd - change directory */
        if (arg == NULL){ /* If no argument is given,return HOME environment */
            chdir("/home");
        } 
        else chdir(arg);
    }

    else if (strncasecmp(a,"exit",4) == 0) {  /* exit - exit the shell */  
            if(a[4]=='[' && a[6]==']') /* If an argument n is given,n should be return. */
                exit(a[5]);
            else exit(0);
            }
    else { /* command is not an internal command */
        pid_t pid;
        if ((pid = fork()) < 0) { /* If fail to create a process */
            perror("Failed to fork()");
            exit(1);
        }
        else if (pid == 0) { /* If child process is forked */
        	char *arg1 = strtok(NULL, " "); /* Assume there are four arguments */
		    char *arg2 = strtok(NULL, " ");
		    char *arg3 = strtok(NULL, " ");
            if (execlp(cmd, cmd,arg,arg1,arg2,arg3,(char*)0) == -1) {
                printf("Not valid\n"); /* If execution is failed */
                exit(0);
            }
        }
        else if (pid > 0) { /* father process */
            waitpid(pid, NULL, 0);
        }
    }
}

/* Register a signal handler for Ctrl-C */
void ctrlC(int sig)  
{  
    fflush(stdin);
    //printf( "I will be printed immediately");
}  
 
int main(){
    char line[MAX_CMD];
    char path[MAX_PATH];
    signal(SIGINT, ctrlC);  /* If Ctrl-C is captured */ 
    while (strcmp(line, "exit") != 0) {
        printf("Myshell>%s$ ", getcwd(path, MAX_PATH));
        fgets(line, MAX_CMD, stdin); /* read the command line */
        *strchr(line, '\n') = '\0';
        if (line[0] == '\0') continue;
        char *cmd = strtok(line, " "); /* state the executable name */
        char *arg = strtok(NULL, " "); /* state the arguments */
        process_cmd(cmd, arg);
    }
    return 0;
}
