package day01;

import java.util.Arrays;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.IOException;

public class Test {
	public static void main(String[] args) {
		try {
			System.setIn(new FileInputStream("./input.txt")); 
			Scanner sc = new Scanner(System.in);
			
			int T = sc.nextInt();
			for(int test_case = 1; test_case <= T; test_case++) {
				
				
				int[] arr = new int[10];
				
				int sum = 0;
				int max = 0;
				int min = 10000 * 10 + 1; // 모든 최대값의 합 보다 1 큰 값
				
				for(int i = 0; i < 10; i++) {
					arr[i] = sc.nextInt();
					
					if(arr[i] > max) { // max 저장
						max = arr[i];
					} 
					
					if(arr[i] < min) {
						min = arr[i];
					}			
									
				}
				
				if(max == min) {
					System.out.printf("#%d %d\n", test_case, 0);
					continue;
				}
					
				for(int i = 0; i < 10; i++) {
					if(arr[i] == min || arr[i] == max) {
						continue;
						
					} else {
						sum += arr[i];						
						
					}
					
				}
				
				int result;
				double divided = (double) sum / 8;
				double underPoint = (divided * 10) % 10;
				if(underPoint >= 5) {
					result = (sum / 8) + 1;
				} else {
					result = (sum / 8);
				}
					
				System.out.printf("#%d %d\n", test_case, result);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}		
		
	}

}
