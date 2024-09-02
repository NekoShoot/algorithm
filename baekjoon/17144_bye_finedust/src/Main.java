
import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		Scanner sc = new Scanner(System.in);
		
		int R = sc.nextInt(); // 방 row 길이
		int C = sc.nextInt(); // 방 column 길이
		int T = sc.nextInt(); // T초
		
		// 공기 청정기 2행의 위치
		int upper = -1;
		int lower = -1;
		
		int[][] room = new int[R][C];		
		for(int r = 0; r < R; r++) {
			for(int c = 0; c < C; c++) {
				room[r][c] = sc.nextInt(); // 미세먼지 측정!
				
				if(room[r][c] == -1) {
					if(upper == -1) upper = r;
					else lower = r;
				}
			}
		}
		
		int[][] mirror = new int[R][C]; // 미세먼지 동시 확산을 위한 미러 룸	

		
		// 미세먼지 델타
		// 상 우 하 좌
		int[] dr = { -1, 0, 1, 0 };
		int[] dc = { 0, 1, 0, -1 };
		
		// 공기 청정기 델타
		// 하 우 상 좌
		int[] pr = { 1, 0, -1, 0 };
		int[] pc = { 0, 1, 0, -1 };

		
		// T초 동안 진행 
		for(int t = 1; t <= T; t++) {
			for(int r = 0; r < R; r++) { // 미러링
				mirror[r] = Arrays.copyOf(room[r], room[r].length);
			}
			
			// 1. 미세먼지 확산
			for(int r = 0; r < R; r++) {
				for(int c = 0; c < C; c++) {
					if(room[r][c] == -1) continue; // 공기청정기 제외
					
					int spread = room[r][c] / 5; // 확산되는 양
					for(int d = 0; d < 4; d++) {
						int nr = r + dr[d];
						int nc = c + dc[d];
						
						// 범위 안에서 공기청정기가 아니면
						if(nr >= 0 && nr < R
						&& nc >= 0 && nc < C
						&& room[nr][nc] != -1) {
							// 확산(앞에 바뀐 것에 관계 없이 spread값이 원래의 값을 기준으로 정해짐)
							mirror[nr][nc] += spread; 
							mirror[r][c] -= spread;
						}
						
					} // 델타 배열
										
				} // c
			} // r
			
			// 미러를 다시 진짜에 반영
			for(int r = 0; r < R; r++) {
				for(int c = 0; c < C; c++) {
					room[r][c] = mirror[r][c];
				}
			}
			
//			System.out.println("확산");
//			for(int r = 0; r < R; r++) {
//				System.out.println(Arrays.toString(room[r]));
//			}
			
			// =============== clear ===============
			
			// 2. 공기 청정기 작동 TODO -1이 옮겨지는 거 같으니 그거 해결
			// 위쪽

			// 바람은 1로 부는 것에서 시작하지만, 역방향 델타 탐색으로 구현
			int p = 0; // 공기가 밀어낸 미세먼지들을 탐색할 델타
			
			// 역방향으로 탐색하므로 상 -> 우 -> 하 -> 좌 순으로 탐색 <- 미세먼지 델타와 같음
			int nr = upper - 1; // 공기청정기 위쪽 시작 row
			int nc = 0; // 공기청정기 위쪽 시작 column			
			
			while(nr != upper || nc != 0) { // nr과 nc가 공기청정기 위쪽으로 돌아오면
				// 범위 밖으로 나가면 p++
				if(nr + dr[p] < 0 || nc + dc[p] > C-1 || nr+dr[p] > upper) p++;
								
				// 바로 이전 칸을 옮겨오기
				if(room[nr + dr[p]][nc + dc[p]] != -1) room[nr][nc] = room[nr + dr[p]][nc + dc[p]];
				else room[nr][nc] = 0;
				
				nr += dr[p];
				nc += dc[p];				
			} // upper
			
//			System.out.println("공청 윗방향");
//			for(int r = 0; r < R; r++) {
//				System.out.println(Arrays.toString(room[r]));
//			}
			
			
			// ======= clear ==========
			
			// 아래쪽
			p = 0;
			
			// 역방향 탐색하므로 하 -> 우 -> 상 -> 좌
			int lr = lower + 1;
			int lc = 0;
			
			while(lr != lower || lc != 0) { // nr과 nc가 공기청정기 위쪽으로 돌아오면
				// 범위 밖으로 나가면 p++
				if(lr + pr[p] > R-1 || lc + pc[p] > C-1 || lr+pr[p] < lower) p++;
				
				
				// 바로 이전 칸을 옮겨오기
				if(room[lr + pr[p]][lc + pc[p]] != -1) room[lr][lc] = room[lr + pr[p]][lc + pc[p]];
				else room[lr][lc] = 0;
				
				lr += pr[p];
				lc += pc[p];				
			} // lower
 
//			System.out.println("아랫방향");
//			for(int r = 0; r < R; r++) {
//				System.out.println(Arrays.toString(room[r]));
//			}
			
			
		} // t		
		 
		

		// 집 안에 남은 미세먼지 총량 구하기
		int sum = 0;
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				sum += room[i][j];
			}
		}
		
		System.out.println(sum + 2); // 공기청정기 값 빼주기
	}
}
