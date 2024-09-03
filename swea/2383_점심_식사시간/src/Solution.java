
import java.util.*;
import java.io.*;

public class Solution {
	static int N; // 방 한 변의 길이
	static int[][] map; // 방
	static int[][] pInfo; // 사람
	static int pCnt; // 사람 명수
	static int[][] stairs; // 계단
	
	static ArrayDeque<Integer> stair1Entrance; // 1번 계단 입구 큐
	static ArrayDeque<Integer> stair1; // 1번 계단 위 큐
	static ArrayDeque<Integer> stair2Entrance; // 2번 계단 입구 큐
	static ArrayDeque<Integer> stair2; // 2번 계단 위 큐
	
	
	// 어떤 계단 입구로 가는지 고르는 부분
	// idx: 사람 인덱스, 1부터 시작
	static void chooseStair(int idx) {
		// 기저 조건
		// 모든 사람을 다 보았을 때
		if(idx > pCnt) {
			// 해야할 작업들
			// goingDownStair()
			
			System.out.println("1번 입구 => " + stair1Entrance);
			System.out.println("2번 입구 => " + stair2Entrance);
			System.out.println("");
			
			// 1번 계단 queue 비우기(나중에 마지막 뽑을 때 필요)
			if(!stair1.isEmpty()) {
				for(int i = 0; i < stair1.size(); i++) {
					stair1.poll();
				}
			}
			
			// 2번 계단 queue 비우기(나중에 마지막 뽑을 때 필요)
			if(!stair2.isEmpty()) {
				for(int i = 0; i < stair2.size(); i++) {
					stair2.poll();
				}
			}	
			
			return;
		}
				
		// 재귀 부분
		int time;
		// 1. 1번 계단을 고른다.
		// 이동 시간(분) = | PR - SR | + | PC - SC |
		time = Math.abs(pInfo[idx][0] - stairs[0][0]) + Math.abs(pInfo[idx][1] - stairs[0][1]);  
		pInfo[idx][2] = time;
		stair1Entrance.offer(idx);
		chooseStair(idx+1);
		
		// 롤백		
		
		// 여기까지는 의도대로 동작.
		// TODO 2번 queue에 중복이 생기는 문제 해결 <- 재귀에서 노드를 체크하는 방식이 원래 의도한 바와 다름
		// 예를 들어, 1~4를 1번 계단에, 5~6을 2번 계단에 보내는 경우에, 
		// "5를 2번에, 6을 1번에 보내는 경우"에 이미 5번을 큐에 넣어 버려서 6만 큐에 넣게 됨.
		
		// 2. 2번 계단을 고른다.
		time = Math.abs(pInfo[idx][0] - stairs[1][0]) + Math.abs(pInfo[idx][1] - stairs[1][1]);  
		pInfo[idx][2] = time;  
		stair2Entrance.offer(stair1Entrance.pollLast());
		chooseStair(idx+1);
		
		
		
	}
	
	// 계단 내려가는 부분
	static void goingDownStair() {
		
	}
	
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		Scanner sc = new Scanner(System.in);
		
		// 1. T, N, map, queue 4개
		int T = sc.nextInt();
		// TODO 반복 최댓값 1 => T로 변경
		for(int tc = 1; tc <= 1; tc++) {
			N = sc.nextInt();
			map = new int[N][N];
			pInfo = new int[11][3]; // 0: row, 1: col, 2: time to stair entrance
			stairs = new int[2][2];
			
			Arrays.fill(stairs[0], -1); // 1번 계단 판별을 위해 초기화
			
			// 계단 큐 초기화
			stair1Entrance = new ArrayDeque<>();
			stair1 = new ArrayDeque<>();
			stair2Entrance = new ArrayDeque<>();
			stair2 = new ArrayDeque<>();
			
			// 방 정보
			pCnt = 0; // 사람 명 수 세기			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					map[i][j] = sc.nextInt();
					
					if(map[i][j] == 1) { // 사람 저장
						pCnt++;
						pInfo[pCnt][0] = i;
						pInfo[pCnt][1] = j;
					} else if(map[i][j] > 1) { // 계단 저장
						if(stairs[0][0] == -1) { // 1번 계단
							stairs[0][0] = i;
							stairs[0][1] = j;
						} else { // 2번 계단
							stairs[1][0] = i;
							stairs[1][1] = j;
						}
					}
				}
			} // 방 정보 처리 끝
						
			System.out.println("=====방 정보=====");
			for(int i = 0; i < N; i++) {
				System.out.println(Arrays.toString(map[i]));
			}			
			
			System.out.println("=====사람 정보=====");
			for(int i = 1; i <= pCnt; i++) {
				System.out.println(Arrays.toString(pInfo[i]));
			}			
			
			System.out.println("=====계단 정보=====");
			for(int i = 0; i <= 1; i++) {
				System.out.println(Arrays.toString(stairs[i]));
			}			
			System.out.println("=================");
			
			chooseStair(1);
			
		} //tc 종료	
	} // main 종료
}
