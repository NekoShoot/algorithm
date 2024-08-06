import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		/*
		1. 길이 L+1의 배열 생성
		2. N번 반복문을 돌면서 P~K에 i에 해당하는 번호 넣기
			- 만약 해당하는 칸이 0이 아니라면 continue;
		3. K-P 값을 비교해서 최대값 저장
		4. 배열 전체 탐색하면서 미리 생성한 카운팅 배열에 각 i의 개수를 저장
		5. N 길이의 카운팅 배열을 탐색하며 가장 높은 값을 가지는 인덱스 반환
		6. 3.에서 저장했던 최대값을 가지는 i 반환
		7. 출력
		*/
		Scanner sc = new Scanner(System.in);
		int L = sc.nextInt(); // 롤케잌 길이
		int N = sc.nextInt(); // 사람 수
		
		int[] rollcake = new int[L+1]; // 롤케잌 배열 생성
		
		int expectedMax = -1;
		int expectedIdx = 0;
		for(int i = 1; i <= N; i++) {
			int P = sc.nextInt();
			int K = sc.nextInt();
			int sub = K - P;
			
			if(sub > expectedMax) {
				expectedMax = sub; // 가장 많이 받을 걸로 예상한 최대값 저장
				expectedIdx = i;
			}
			
			for(int j = P; j <= K; j++) { // P~K 범위 적기
				if(rollcake[j] == 0) rollcake[j] = i; // 그 칸을 아무도 안 찍었다면 내 거
			}			
			
		}
		
		int[] cnt = new int[N+1]; // 카운팅 배열
		for(int i = 0; i < L+1; i++) {
			cnt[rollcake[i]]++; // rollcake에 써져있는 숫자 개수 세기
		}
		
		int max = -1; // 실제 최대값 저장할 변수
		int idx = -1; // 그 사람 번호
		for(int i = 1; i <= N; i++) {
			if(cnt[i] > max) {
				max = cnt[i];
				idx = i;
			}
		}
		
		System.out.printf("%s\n%s", expectedIdx, idx);
		
	}

}
