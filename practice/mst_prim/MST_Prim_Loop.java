package mst_prim;

import java.util.*;
import java.io.*;

public class MST_Prim_Loop {
	static final int INF = Integer.MAX_VALUE; // 초기화 할 때 부여할 값
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(input); 
		
		int V = sc.nextInt(); // 정점의 번호(0번부터 시작)
		int E = sc.nextInt(); // 간선의 수
		
		// 인접 행렬 방식으로 해보자!
		int[][] adjArr = new int[V][V];
		
		for(int i = 0; i < E; i++) {
			int A = sc.nextInt();
			int B = sc.nextInt();
			int W = sc.nextInt();
			
			adjArr[A][B] = adjArr[B][A] = W; // 무향 그래프
		} // 그래프 입력 끝
		
		// 방문 체크
		boolean[] visited = new boolean[V]; 
		int[] p = new int[V]; // 부모 노드 저장(어디서 왔는감?)
		int[] distance = new int[V]; // key(가중치 값)
		
		// 프림 알고리즘
		// 1. 초기화
		for(int i = 0; i < V; i++) {
			p[i] = -1; // 0부터 노드 번호가 시작이므로
			distance[i] = INF; // 최소값을 찾아야 하니까 어마어마하게 큰 수로 초기화
		}
		
		// 2. 시작 노드 결정
		distance[0] = 0;
		
		//3. 가중치 배열을 돌면서 가장 값이 낮은 것을 골라서 방문 체크 + 갱신
		for(int i = 0 ; i < V-1; i++) { // V-1번 도나 V번 도나 똑같음(마지막 값이 알아서 갱신됨)
			int min = INF; // 가중치 중 최소값
			int idx = -1; // 인덱스(최소값을 가져온)
			
			// 방문하지 않았으면서 && 가장 작은 가중치 가져오기
			for(int j = 0; j < V; j++) {
				if(!visited[j] && distance[j] < min) {
					min = distance[j];
					idx = j;
				}
			} // 반복문 종료되면 idx는 이번에 뽑은 애로 바뀜(가장 작은 가중치를 가진)
			
			// 방문 처리
			visited[idx] = true;
			
			// 방문하지 않았고 && 갱신할 수 있으면 갱신하기
			for(int j = 0; j < V; j++) {
				// 인접해있으면 && 가중치가 갱신 가능하면
				if(!visited[j] && adjArr[idx][j] != 0 && distance[j] > adjArr[idx][j]) { 
					distance[j] = adjArr[idx][j];
					p[j] = idx; // 어디에서 왔는지 기록
				}
			}
		} // 반복 완전 종료
		
		int ans = 0;
		for(int i = 0; i < V; i++) {
			ans += distance[i];
		}
		
		System.out.println(ans);
		
	} // main
	
	
	
	static String input = "7 11\r\n 0 1 32\r\n 0 2 31\r\n 0 5 60\r\n 0 6 51\r\n 1 2 21\r\n 2 4 46\r\n 2 6 25\r\n 3 4 34\r\n 3 5 18\r\n 4 5 40\r\n 4 6 51";
}
