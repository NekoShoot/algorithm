import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		/*
		 * 
		 */
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int test_case = 1; test_case <= T; test_case++) {
			int N = sc.nextInt(); // N명
			int M = sc.nextInt(); // M초			
			int K = sc.nextInt(); // K개
			
			int[] times = new int[N];
			// 시간 받기 
			for(int i = 0; i < N; i++) {
				// 삽입 정렬
				int data = sc.nextInt();
				
				if(i == 0) times[i] = data;
				else if(i >= 1) { // 첫번째 원소는 자동으로 정렬, 2번째 원소부터
					int j;
					// 역방향 정렬 && 앞 값이 내 값보다 크면 swop
					for(j = i-1; j >= 0 && times[j] > data; j--) {
						times[j + 1] = times[j];
					}
					
					times[j + 1] = data;
				} 
				
			}
					
			// 0초에 사람 오는 경우
			// 같은 시간에 여러 명의 사람이 오는 경우
			// 
			
			System.out.println(Arrays.toString(times));
		}
		

	}

}
