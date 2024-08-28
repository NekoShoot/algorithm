import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		/*
		 * 평범한 배낭 문제	 
		 */
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt(); // 물품 개수
		int K = sc.nextInt(); // 버틸 수 있는 무게(가방 최대 무게)
		
		int[][] luggage = new int[N][2];
		for(int i = 0; i < N; i++) {
			luggage[i][0] = sc.nextInt(); // W
			luggage[i][1] = sc.nextInt(); // V
		}
		
		int cnt = N-1;
		int[] cache = new int[N]; // DP를 위한 배열
		int max = 0; // 최대값을 저장할 변수
		for(int i = 0; i < (int) Math.pow(2, N); i++) {
			int sumW = 0;
			int sumV = 0;
			
			for(int j = 0; j < N; j++) {
				if(sumW > K) { // 무게 초과하면 다음 경우의 수로					
					break;
				}
				
				sumW += cache[j] * luggage[j][0]; // 무게
				sumV += cache[j] * luggage[j][1]; // 가치
			}
			
			if(sumW <= K && sumV > max) max = sumV; // 최댓값 저장
			
			// 다음 경우의 수로
			while(cnt >= 0) {
				if(cache[cnt] == 1) {
					cache[cnt] = 0;
					cnt--;
				}
				else {
					cache[cnt] = 1; // 처음 0 만나면 1로 바꾸고
					cnt = N-1; // cnt 초기화
					break;
				}				
			}			
		}
		
		System.out.println(max);
	}
}
