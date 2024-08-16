package 신입사원_분반교육;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/신입사원_분반교육/input.txt"));
		
		/*
		 * score
		 * two 이상 -> 우수 분반
		 * one이상 two미만 -> 보통
		 * one 미만 -> 부진
		 * 
		 * 최소 인원 이상, 최대 이원 이하 
		 * 
		 * 학생이 가장 많은 분반과 적은 분반의 학생 수 차이의 최솟값은?
		 * 최소 최대를 만족하는 score one, score two가 없다면 -1 출력
		 * 
		 * TC -> 테케 개수
		 * N min max -> 학생 수, 최소, 최대
		 * 5 <= N <= 1000
		 * 1 <= 어학 점수 <= 100
		 */

		/*
		 * score one과 two가 임의의 숫자
		 * min 우수 분반을 만족할 수 있는 학생의 점수 중 최솟값 == score2,
		 * min 보통 분반을 만족할 수 있는 학생의 점수 중 최댓값 == score1 
		 * 
		 * 학생 수 차이의 최솟값 -> 가장 골고루 학생들을 분배하는 방법
		 * 
		 * 1. 테케 개수, 테케 개수만큼 반복, 학생 수, 최소인원, 최대인원 받기
		 * 2. 학생 수 만큼 반복, 각 학생의 점수 배열에 저장
		 */
		
		/*
		 * 단순 구현으로 가볼까?
		 * 1. 누적합 배열 만들기(내림차순) (100-i)
		 * 2. min값보다 크거나 같은 순간 i를 score2에 저장, 그 누적값을 stud2에 저장
		 * 3. min값에 stud2를 더하고 그 값보다 크거나 같은 순간 i를 score1에 저장, 그 누적값을 stud1에 저장
		 * 4. i == 100인 곳의 값이 max + stud1보다 크면 continue, 작으면 그 값을 stud0에 저장
		 * 5. stud0 = stud0 - stud1; stud1 = stud1 - stud2;
		 * 6. 3 값중 가장 큰 값과 가장 작은 값 찾아서 빼고 그 값을 배열에 저장(배열 크기는 max - min)
		 * 7. 반복 끝난 후 배열 오름차순으로 정렬
		 * 8. 배열[0] 출력
		 */
		Scanner sc = new Scanner(System.in);
		int TC = sc.nextInt(); // 테케 개수
		for(int test_case = 1; test_case <= TC; test_case++) {
			int N = sc.nextInt(); // 학생 수
			int min = sc.nextInt(); // 최소 인원
			int max = sc.nextInt(); // 최대 인원
			
			// 학생 수 탐색			
			int minSub = Integer.MAX_VALUE;
			
			int[] scoreArr = new int[101]; // 최대 100점
			// 학생들 점수 받기
			for(int i = 0; i < N; i++) {
				int score = sc.nextInt();
				scoreArr[100-score]++; // 내림차순 카운팅 배열
			}
			
			// 누적합 배열로 만들기
			for(int i = 1; i < 101; i++) {
				scoreArr[i] += scoreArr[i-1];
			}

			// 전체 탐색
			for(int k = 0; k < max-min; k++) {
				int score2 = -1;			
				int stud2 = -1;		

				// 1. score2 stud2 
				for(int i = 0; i < 101; i++) {
					if(scoreArr[i] >= min) {
						score2 = 100 - (i);
						stud2 = scoreArr[i];
						break;
					}				
				}

				int score1 = -1;
				int stud1 = -1;			
				// 2. score1 stud1
				for(int i = score2+1; i < 101; i++) {
					if(scoreArr[i] - stud2 >= min) {
						score1 = 100 - i;
						stud1 = scoreArr[i] - stud2;
						break;
					}
				}

				int score0 = -1;
				int stud0 = -1;
				// 3. socre0 stud0
				for(int i = score1+1; i < 101; i++) {
					if(scoreArr[i] - (stud1+stud2) >= min) {
						score0 = 100 - i;
						stud0 = scoreArr[100] - (stud1+stud2);
						break;
					}
				}

				// max 넘으면 불가능
				if(stud0 > max) continue;
				// k가 0인데 stud0이 min보다 작으면 불가능
				if(k == 0 && stud0 < min) {
					minSub = -1;
					break;
				}
					
				// 학생수는 stud에 저장돼있음
				// 최소 최대 값 찾기
				int studTmpMin = Math.min(stud2, stud1);
				int studMin = Math.min(studTmpMin, stud0);
				int studTmpMax = Math.max(stud2, stud1);
				int studMax = Math.max(studTmpMax, stud0);

				// 가장 많은 분반과 적은 분반 차이 값
				int sub = studMax - studMin;
				if(sub < minSub) minSub = sub;
			}

			System.out.printf("#%d %d\n", test_case, minSub);
		}		
	}

}
