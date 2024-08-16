package 스위치;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/스위치/input.txt"));
		/*
		 * 남자 -> 자기 배수 번호의 스위치 변경
		 * 여자 -> 자기 번호 스위치 중심으로 좌우 대칭인 가장 큰 범위 변경(항상 홀수 개수)
		 * 스위치 마지막 상태출력
		 * 
		 * 첫 줄 스위치 개수 t t <= 100 양의 정수
		 * 둘째 줄 스위치 상태 사이사이 공백
		 * 셋째 줄 학생 수 n <= 100 양의 정수
		 * 넷째~마지막 학생 성별, 공백 후 받은 번호 k(스위치 개수 이하의 양의 정수)
		 * 
		 * 한 줄에 20개씩 스위치 출력, 스위치 사이에 공백
		 * 
		 * 1. 스위치 개수와 스위치 받아서 1차원 배열에 저장
		 * 2. 학생수 n 받고 그 만큼 반복
		 * 3. 반복문 안에 if문으로 남학생과 여학생 경우 가르기
		 * 4. 남학생 경우 -> N/k 번 반복, k * i들 스위치 바꾸기
		 * 5. 여학생 경우 -> cnt; k-cnt와 k+cnt 비교해서 달라질때까지 
		 * && k-cnt >= 0, k+cnt <= arr.length()-1 범위 안에 있는 때까지 비교 후 바꾸기
		 */
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		int t = sc.nextInt(); // 스위치 개수
		int[] switchArr = new int[t];
		for(int i = 0; i < t; i++) {
			switchArr[i] = sc.nextInt();
		}
		
		int n = sc.nextInt(); // 학생 수
		// 스위치 조작
		for(int i = 0; i < n; i++) {
			int gender = sc.nextInt();
			
			if(gender == 1) { // 남학생
				int k = sc.nextInt(); // 받은 번호
				
				for(int j = 1; j <= t/k; j++) {
					int targetNumber = (k * j)-1;
					
					// 스위치 조작
					if(switchArr[targetNumber] == 1) switchArr[targetNumber] = 0;
					else switchArr[targetNumber] = 1;
					
				}
				
			}
					
			else { // 여학생
				// 5. 여학생 경우 -> cnt; k-cnt와 k+cnt 비교해서 달라질때까지 
				// && k-cnt >= 0, k+cnt <= arr.length()-1 범위 안에 있는 때까지 
				// 비교 후 바꾸기
				int k = sc.nextInt(); // 받은 번호
				int target = k - 1; // target index
				
				// 받은 번호가 배열 끝이면 그 번호만 바꾸고 끝
				if(k == 0 || k == t-1) {
					if(switchArr[target] == 1) switchArr[target] = 0;
					else switchArr[target] = 1;
					continue;
				}
				
				int cnt = 0;
				while(0 <= target-cnt && target+cnt <= t-1 // 배열 범위 안에서 좌우 대칭이면
						&& switchArr[target-cnt] == switchArr[target+cnt]) {					
					cnt++; // 좌우 대칭 개수 세기
				}
				
				// target-cnt부터 target+cnt까지 바꿔줘야됨
				// -> 2*cnt + 1개
				for(int j = -cnt+1; j <= cnt-1; j++) {
					if(switchArr[target+j] == 1) switchArr[target+j] = 0;
					else switchArr[target+j] = 1;
				}
				
			}	

			
		}
		
		for(int i = 1; i <= t; i++) {
			if(i%20 == 0) sb.append(switchArr[i-1] + "\n");
			else sb.append(switchArr[i-1] + " ");
		}
		
		String result = sb.toString();
		System.out.printf(result);
	}

}
