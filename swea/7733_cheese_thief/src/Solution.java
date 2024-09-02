import java.io.*;
import java.util.*;

public class Solution {
		static int[][] cheese; // 치즈 NxN
		static int N; // 치즈 크기
		static boolean[][] visited; // 방문 체크
		static int[] dr = { -1, 1, 0, 0 }; // 델타 배열
		static int[] dc = { 0, 0, -1, 1 }; // 상 하 좌 우
		static int cnt; // 덩어리 개수 세는 변수
		static int result; // 결과를 저장할 변수
	
		
	static void deltaDfs(int r, int c) {
		// 기저 조건
		// 델타 탐색을 더 이상 할 수 없을 때
		
		// 재귀 부분
		for(int d = 0; d < 4; d++) { // 델타 탐색
			// 배열 범위 안에서만 && 방문한 적 없을 때만
			if(r + dr[d] >= 0 && r + dr[d] < N 
					&& c + dc[d] >= 0 && c + dc[d] < N
					&& !visited[r + dr[d]][c + dc[d]]) 
			{				
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				visited[nr][nc] = true;
				deltaDfs(nr, nc);		
				return; // cnt 중복을 막기 위해
			}
		}
		
		cnt++; // 재귀 조건을 만족하지 못하는 상태에서 반복이 끝나면 덩어리 하나 탐색 끝, 한 개 짜리라도 덩어리 하나임
		return;
	}
		
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt(); // 테케 T		
		
		for(int tc = 1; tc <= T; tc++) {
			N = sc.nextInt(); // N 초기화
			cheese = new int[N][N]; // cheese 초기화
			visited = new boolean[N][N]; // visited 초기화
			result = 0; // result 초기화
			
			// 최대 치즈 맛을 저장할 변수
			int maxFlavor = 0;

			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					cheese[i][j] = sc.nextInt(); // 치즈 맛 채우기
					if(cheese[i][j] > maxFlavor) maxFlavor = cheese[i][j];
				}
			} 
			
			// x번째 날이 최대 맛이 될때까지 반복
			for(int x = 1; x <= maxFlavor; x++) {
				cnt = 0; // cnt 초기화
				
				
				for(int r = 0; r < N; r++) {
					for(int c = 0; c < N; c++) {
						if(cheese[r][c] == x) visited[r][c] = true; // 요정이 갉아 먹은 곳은 패스
					}
				} 				
				
				for(int r = 0; r < N; r++) {
					for(int c = 0; c < N; c++) {
						if(visited[r][c]) continue; // 방문한 적 있으면 패스
						deltaDfs(r, c); // 재귀
						
					} // c
				} // r
				
				// x번째 날에 얻은 덩어리 총개수의 최댓값 비교
				if(cnt > result) result = cnt;
				
			} // x			
			
			
			System.out.printf("#%d %d\n", tc, result);
		} // tc
	}
}