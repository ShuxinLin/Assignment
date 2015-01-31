import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
/**
 * An implementation of Line Formatting Algorithms for ADS
 */

public class LineFormat {

    /** `len' is the fixed line length - "L" in the coursework handout. */
    static int len=80;


    /** The method below will implement the dynamic programming 
     * algorithm for the case when we sum the squares of the trailing
     * space, for every line INCLUDING the final one.  The input is a
     * list of integers, representing the lengths of the words in
     * sequence. The output is another (probably shorter) array of
     * integers, describing the position of the line breaks in the
     * optimal formatting.
     * I've commented out the declaration because I was running 
     * tests on the file-handling methods */

      	public static int[] dynamicLFall (int[] wordLengths){

	int n = wordLengths.length;

	// cost[i][j] stores the sum of squares of leftover space 
	//at the end of the lines when one line contains words i,...,j(i<=j).
	int[][] cost = new int[n+1][n+1];
	
	//construct cost[i][j] by using the defination of e(i,j)
	for(int i=1; i<=n; i++){
		cost[i][i] = len-wordLengths[i-1];
		for(int j=i+1; j<=n; j++){
			cost[i][j] = cost[i][j-1]-wordLengths[j-1]-1;
			}
		}
	
	//update cost[][]
	for(int i=1; i<=n; i++){	
		for(int j=i; j<=n; j++){
			if(cost[i][j] >= 0){
				cost[i][j] = cost[i][j]*cost[i][j];}
			else{			//if the cost is less than 0, set cost infinite value			
				cost[i][j] = Integer.MAX_VALUE;
				}
			}
		}
		
	// array ss[i] stores the optimal vopt(l[i])
	int[] ss = new int[n+1];

	//breakpoints[i] stores the optimal k when we stores words k+1,...,i 
	//in the last line and the rest are in the upper lines.
	int[] breakpoint = new int[n+1];

	breakpoint[0]= ss[0] = 0;
	for(int j=1; j<=n; j++){
		ss[j] = Integer.MAX_VALUE; 	//initialize ss[]
		for(int i=1; i<=j; i++){
			if( ss[i-1]+cost[i][j]<ss[j] && cost[i][j]!=Integer.MAX_VALUE 
				&& ss[i-1]!=Integer.MAX_VALUE){
					ss[j] = ss[i-1] + cost[i][j];	//calculate ss[] by using the definiton ss(l;i)
					breakpoint[j] = i-1;	
				}
			}
		}
	
	//list opt() store the reversal sequence of breakpoints
	List<Integer> opt = new ArrayList<Integer>();
	int i=n;
	//add breakpoints to opt()
	while(i!=0){opt.add(i); i=breakpoint[i];}
	int[] opt_array = new int[opt.size()];
	for (int j = 0; j < opt.size(); j++) {opt_array[j] = opt.get(j);}
	//reverse the opt_array to get the actual opt_array
	for(int k=0,j=opt_array.length-1;k<j;k++,j--){  
            	int temp=opt_array[k];  
            	opt_array[k]=opt_array[j];  
           	opt_array[j]=temp;  
        }  

	//for (int k = 0; k < opt_array.length; k++)  {System.out.println(opt_array[k]);}
	return opt_array;
		
	}

    /** The method below will exploit the 'dynamicLFall' function
     * to calculate the optimum sequence of line breaks, if our
     * optimisation criterion is instead the sum of squares of all
     * trailing spaces EXCEPT the final one.  The input is a list
     * of integers, representing the lengths of the words in
     * sequence. The output is another (shorter) array of integers,
     * describing the position of the line breaks in the optimal
     * formatting.
     * I've commented out the declaration because I was running 
     * tests on the file-handling methods */

     public static int[] dynamicLFallbutlast (int[] wordLengths){

	int n = wordLengths.length;
	// cost[i][j] stores the sum of squares of leftover space
	//at the end of the lines when one line contains words i,...,j(i<=j).
	int[][] cost = new int[n+1][n+1];

	//construct cost[i][j] by using the defination of e(i,j)
	for(int i=1; i<=n; i++){
		cost[i][i] = len-wordLengths[i-1];
		for(int j=i+1; j<=n; j++){
			cost[i][j] = cost[i][j-1]-wordLengths[j-1]-1;
			}
		}

	//update cost[][]
	for(int i=1; i<=n; i++){	
		for(int j=i; j<=n; j++){
			if(cost[i][j] >= 0)	
				{cost[i][j] = cost[i][j]*cost[i][j];}
			else{				//if the cost is less than 0, set cost infinite value		
				cost[i][j] = Integer.MAX_VALUE;
				}
			}
		}
		

	// array ss[i] stores the optimal vopt(l[i])
	int[] ss = new int[n+1];

	//breakpoints[i] stores the optimal k when we stores words k+1,...,i 
	//in the last line and the rest are in the upper lines.
	int[] breakpoint = new int[n+1];

	breakpoint[0]= ss[0] = 0;
	for(int j=1; j<=n; j++){
		ss[j] = Integer.MAX_VALUE; 	//initialize ss[]
		for(int i=1; i<=j; i++){
			if( ss[i-1]+cost[i][j]<ss[j] && cost[i][j]!=Integer.MAX_VALUE 
				&& ss[i-1]!=Integer.MAX_VALUE){
					ss[j] = ss[i-1] + cost[i][j];	//calculate ss[] by using the definiton ss(l;i)
					breakpoint[j] = i-1;	
				}
			}
		}

	int tmp=ss[n];
	//initialize the last-but-one breakpoint
	int last_but_one_bp=n;

	//find the last-but-one breakpoint to satisfy the ss*(l;i) 
	for (int j=n-1; j>0; j--){
		if(cost[j+1][n]!=Integer.MAX_VALUE && ss[j]<tmp) 
			last_but_one_bp=j;
			}
	//System.out.println(last_but_one_bp);

	//list opt*() store the reversal sequence of breakpoints	
	List<Integer> opt_butlast = new ArrayList<Integer>();

	//add breakpoints to opt*()
	opt_butlast.add(n);
	int i=last_but_one_bp;
	while(i!=0){opt_butlast.add(i); i=breakpoint[i];}
	int[] opt_butlast_array = new int[opt_butlast.size()];
	for (int j = 0; j < opt_butlast.size(); j++) {opt_butlast_array[j] = opt_butlast.get(j);}

	//reverse the opt_butlast_array to get the actual opt_butlast_array
	for(int k=0,j=opt_butlast_array.length-1;k<j;k++,j--){  
            	int temp=opt_butlast_array[k];  
            	opt_butlast_array[k]=opt_butlast_array[j];  
           	opt_butlast_array[j]=temp;  
        	}  
	  
	//for (int k = 0; k < opt_array.length; k++)   {System.out.println(opt_array[k]);}
	return opt_butlast_array;
		
	}




 

    /** The method below will implement the simple greedy
     * algorithm for computing a line formatting.  We do not 
     * expect the sum-of-squares to be optimal for the outputs
     * of this algorithm.  The input is a list of integers,
     * representing the lengths of the words in sequence. The
     * output is another (shorter) array of
     * integers, describing the position of the line breaks
     * in the greedy formatting. 
     * I've commented out the declaration because I was running 
     * tests on the file-handling methods */

     public static int[] greedyLF (int[] wordLengths){
	int n = wordLengths.length;
	int size =0;

	//list breakpoint() store the sequence of breakpoints
	List<Integer> breakpoint = new ArrayList<Integer>();

	//add breakpoints to breakpoint()
	for (int i =0; i<n;i++){
		if (len-size<wordLengths[i]){
			breakpoint.add(i);
			size=wordLengths[i]+1;
			}
		else {
			size = size + wordLengths[i]+1;
			}
		}
	breakpoint.add(n);

	//copy each value of list to the new breakpoint array B
	int[] B = new int[breakpoint.size()];
	for (int j = 0; j < breakpoint.size(); j++) {
		B[j] = breakpoint.get(j);
		}

	//for (int k = 0; k < B.length; k++)   {System.out.println(B[k]);}
	return B;

      } 


    /** You might want to implement the method below, which takes
     * as input (1) a list of word lengths, and (2) a list of indices
     * called breakPoints (describing a chosen line formatting, and
     * probably generated by one of our 3 algorithms), and computes 
     * the ss value of the formatting in terms of sums-of-squares 
     * of trailing whitespace, for all of the lines including the 
     * final one.
     * The method might be useful in the testing stage.  Up to you
     * though.... */

     public static int ssAll (int[] wordLengths, int[] breakPoints){
	int n = wordLengths.length;
	int m = breakPoints.length;
	int e = 0;
	int ssALL=0;
	
	//calculate ss(l;i)
	for(int i=0;i<m-1;i++){
		int single_len=0;
		{for (int j=breakPoints[i]+1;j<=breakPoints[i+1];j++){
			single_len= single_len+wordLengths[j-1];
			}
		//e is e(i,j)
		e=len-single_len-breakPoints[i+1]+breakPoints[i]+1;
		if (e<0){	//if e<0, the breakpoints sequence must be wrong
		System.out.println("breakpoints are wrong");
		return -1;
		}
		else {ssALL=ssALL+e*e;}
		}
	}

	//because the first word cannot be found in the loop by using index search,
	//we calculate the e of the first line alone.
	int first_line=0;
	for(int i=0;i<breakPoints[0];i++){first_line=first_line+wordLengths[i];}
	int e0=len-first_line-breakPoints[0]+1;

	return ssALL+e0*e0;

     } 


    /** You might want to implement the method below, which takes
     * as input (1) a list of word lengths, and (2) a list of indices
     * called breakPoints (describing a chosen line formatting, and
     * probably generated by one of our 3 algorithms), and computes 
     * the ss value of the formatting in terms of sums-of-squares 
     * of trailing whitespaces, for all of the lines except the 
     * last one.
     * The method might be useful in the testing stage.  Up to you
     * though.... */

     public static int ssAllbutlast (int[] wordLengths, int[] breakPoints){

	int n = wordLengths.length;
	int m = breakPoints.length;
	int e = 0;
	int ssALL=0;
	
	//calculate ss*(l;i)
	for(int i=0;i<m-2;i++){int single_len=0;
	{for (int j=breakPoints[i]+1;j<=breakPoints[i+1];j++)
		{single_len= single_len+wordLengths[j-1];}
		//e is e(i,j)
		e=len-single_len-breakPoints[i+1]+breakPoints[i]+1;
		if (e<0){	//if e<0, the breakpoints sequence must be wrong
		System.out.println("breakpoints are wrong");
		return -1;
		}
		else {ssALL=ssALL+e*e;}
		}
	}

	//because the first word cannot be found in the loop by using index search,
	//we calculate the e of the first line alone.
	int first_line=0;
	for(int i=0;i<breakPoints[0];i++){first_line=first_line+wordLengths[i];}
	int e0=len-first_line-breakPoints[0]+1;

	return ssALL+e0*e0;
    
     } 


    /** A method to read the list of words in a given file "fileName"
     * and return these words in order as as list of strings. */

    public static String[] readWordsFile(String fileName) {
	ArrayList<String> list = new ArrayList<String>();
	try {
	    File file = new File(fileName);
	    Scanner scanner = new Scanner(file);
	    while (scanner.hasNext()) {
		list.add(scanner.next());
	    }
	    scanner.close();
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	}
	return (String[]) list.toArray(new String[1]);
     }

    public static void printWords(String[] words) {
	int i;
	int l = words.length;
	    for (i = 0; i < l; i++) {
		System.out.println(words[i]);
	    }}

    /** The method below is a very simple method to print a list of 
     * words to a file according to a list of breakpoints.  
     * I EXPECT, but don't check, that the breakpoints are in 
     * increasing order, AND that the last entry of the breakPoints 
     * array is equal to words.length.
     * If the last entry of breakPoints array is LESS than the length
     * of the words array, not all words will be printed.   */
    
    public static void printWordsFile(String[] words, int[] breakPoints, 
	String filename) {
	int i,j;
	int l = words.length;
	int b = breakPoints.length;
	FileOutputStream fileout;
	try {
	    fileout = new FileOutputStream(filename);
	    if (b == 0) {
		System.err.println ("Bad input to printWordsFile");
	    } else {
		for (j = 0; (j < l)&&(j < breakPoints[0]-1); j++) {
		    	new PrintStream(fileout).print(words[j]);
			new PrintStream(fileout).print(" ");
		}
		if (breakPoints[0]-1 < l) {
		    new PrintStream(fileout).print(words[breakPoints[0]-1]);
		}
		new PrintStream(fileout).println("");		
		for (i = 1; i < b; i++) {
		    for (j = breakPoints[i-1]; (j < l)&&(j<breakPoints[i]-1); 
			 j++) {
			new PrintStream(fileout).print(words[j]);
			new PrintStream(fileout).print(" ");
		    }
		    if (breakPoints[i]-1 < l) {
			new PrintStream(fileout).print(words[breakPoints[i]-1]);
		    }
		    new PrintStream(fileout).println("");	
		}
	    }
	    fileout.close();
        }
	catch (IOException e) 
	    {			
		System.err.println ("Unable to write to file");
		System.exit(-1);
	    }
    }


	/**
	 * @see java.lang.Object#toString()
	 */
    /**	public String toString() {
     *         StringBuffer buffer = new StringBuffer();
     *          SkipListNode node = head;
     *
     *	while (node.next(0) != tail) {
     *                  node = node.next(0);
     *                  buffer.append(node.getKey() + " ");
     *                  for (int i = 0; i < node.height() + 1; i++) {
     *                           buffer.append("*");
     *                  }
     *                   buffer.append("\n");
     *	    }
     *	return buffer.toString();
     *}
     */

	/*
	 * You should write a main method to run tests on your
	 * Line Formatting methods. 
	 * 
	 * Here I have just written some commands to test the 
	 * file handling little methods I wrote. You can delete 
	 * all these, it's just an example.  -- Mary
	 */



    public static void main(String args[])
    {

	int[] l=new int[160];

	//set flag1 to calculate the time ss1-ss0<=0
	int flag1=0;

	//set flag2 to calculate the time ss2-ss3<=0
	int flag2=0;

	//set flag3 to calculate the time opt(l) is equal to opt*(l)
	int flag3=0;

	int ss_avg_diff=0;
	int ss_max_diff=0;
	int ssbutlast_avg_diff=0;
	int ssbutlast_max_diff=0;

	for(int k=0;k<100;k++){		//generate 100 random wordlength arrays
		for(int i=0;i<160;i++){
			l[i]=(int) (Math.random()*6+2);	//the value is between 2 and 7
			}

		int[] bp= new int[dynamicLFall(l).length];
		int[] bp1= new int [greedyLF(l).length];
		int[] bp2= new int [dynamicLFallbutlast(l).length];

		//return the breakpoints array for three algorithms
		bp=dynamicLFall(l);
		bp1=greedyLF(l);
		bp2=dynamicLFallbutlast(l);

		//ss0 is the vopt(l) 
		int ss0 =ssAll (l, bp);

		//ss1 is the vopt(l) 
		int ss1 =ssAll (l, bp1);

		//ss2 is the vopt*(l) 
		int ss2 =ssAllbutlast (l, bp2);

		//ss3 is the vopt*(l) 
		int ss3 =ssAllbutlast (l, bp1);

		ss_avg_diff=ss_avg_diff+ss1-ss0;
		if(ss_max_diff<ss1-ss0) ss_max_diff=ss1-ss0;
		if(ss1-ss0<=0) flag1=flag1+1;
		if(bp2==bp) flag3=flag3+1;

		ssbutlast_avg_diff=ssbutlast_avg_diff+ss2-ss3;
		if(ss2-ss3<=0) flag2=flag2+1;
		if(ssbutlast_max_diff<ss2-ss3) ssbutlast_max_diff=ss2-ss3;}

		System.out.format("The avarage difference between ss when applying greedyLF or dynamicLFall is %d \n", ss_avg_diff/100);  
		System.out.format("The avarage difference between ss* when applying dynamicLFallbutlast or greedyLF is %d \n", ssbutlast_avg_diff/100);
		System.out.format("The max difference between ss when applying greedyLF or dynamicLFall is %d \n", ss_max_diff);
		System.out.format("The max difference between ss* when applying greedyLF or dynamicLFallbutlast is %d \n", ssbutlast_max_diff);
		System.out.format("The frequency when ss applying greedyLF is less than or equal to that of dynamicLFall is %.2f \n",flag1/100.0);
		System.out.format("The frequency when ss* applying dynamicLFallbutlast is less than or equal to that of greedyLF is %.2f \n",flag2/100.0);
		System.out.format("The frequency with which opt is different to opt* is %.2f \n",1-flag3/100.0);

	}
}
