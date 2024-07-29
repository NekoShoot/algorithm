package day01;

import java.util.Scanner;

public class FindMaxNumber {
	 public static void main(String args[]) throws Exception
	    {
	        Scanner sc = new Scanner(System.in);
	        int T;
	        T=sc.nextInt();
	 
	        for(int test_case = 1; test_case <= T; test_case++)
	        {
	            int result = 0;
	             
	            for(int i = 0; i < 10; i++) {
	                int tmp = sc.nextInt();
	                if(tmp > result) result = tmp;             
	            }
	             
	            System.out.printf("#%d %s\n", test_case, result);
	        }
	    }
}
