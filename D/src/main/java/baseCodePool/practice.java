package baseCodePool;

import java.util.Scanner;

public class practice {
public static void main(String[] args)
{
	Scanner sc= new Scanner(System.in);
	System.out.println("Enter your knowledge marks");
	int know =sc.nextInt();
	int uptothemark =5;
		mymethods();
	
}

private static void mymethods() {
	
	int[][] myNumbers = { {1, 2, 3, 4}, {5, 6, 7} };
	for (int i = 0; i < myNumbers.length; ++i) {
	      for(int j = 0; j < myNumbers[i].length; ++j) {
	    	  if (myNumbers[i][j]>=5 && myNumbers[i][j]<7 ){
	    		  System.out.println(myNumbers[i][j]);
	    		 
	    	  }
	    	  
	      }
	     
	    }
	
}
}
