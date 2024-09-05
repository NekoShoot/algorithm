package mst_prim;

import java.util.*;

import mst_prim.MST_Prim_PriorityQueue.Edge;

import java.io.*;

public class MST_Prim_PriorityQueue {
	static class Edge implements Comparable<Edge>{
		int A;
		int B;
		int W;
						
		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.W, o.W);
		}

		Edge(int A, int B, int W) {
			this.A = A;
			this.B = B;
			this.W = W;
		}
	}
	
	static final int INF = Integer.MAX_VALUE; // 초기화 할 때 부여할 값
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(input); 
		
		int V = sc.nextInt(); // 정점의 번호(0번부터 시작)
		int E = sc.nextInt(); // 간선의 수
				
		
		// 인접 리스트 방식으로 해보자!
		List<Edge>[] adjList = new ArrayList[V];
		
		for(int i = 0; i < V; i++) {
			adjList[i] = new ArrayList<>();
		}		
		
		for(int i = 0; i < E; i++) {
			int A = sc.nextInt();
			int B = sc.nextInt();
			int W = sc.nextInt();
			
			adjList[A].add(new Edge(A, B, W));
			adjList[B].add(new Edge(B, A, W));
		} // 그래프 입력 끝
		
		// 방문 체크
		boolean[] visited = new boolean[V]; 
		
		PriorityQueue<Edge> pq = new PriorityQueue<>(); // 뽑은 간선의 가중치를 저장할 우선순위 큐
		visited[0] = true;
		
		int ans = 0; // 정답
		int pick = 1; // 뽑은 개수
		
		// 프림 알고리즘
		// 아래 3개는 다 똑같이 동작함!
//		for(int i = 0; i < adjList[0].size(); i++) pq.add(adjList[0].get(i));		
//		for(Edge e : adjList[0]) pq.add(e);		
		pq.addAll(adjList[0]);
		
		while(pick != V) { // 마지막까지 뽑으면 stop
			Edge e = pq.poll();
			if(visited[e.B]) continue; // 이미 뽑았던 도착 지점이라면 pass
			
			ans += e.W; // 거리 추가
			visited[e.B] = true;
			pick++;
			
			pq.addAll(adjList[e.B]);
		}
		
		System.out.println(ans);
		
	} // main
	
	
	
	static String input = "7 11\r\n 0 1 32\r\n 0 2 31\r\n 0 5 60\r\n 0 6 51\r\n 1 2 21\r\n 2 4 46\r\n 2 6 25\r\n 3 4 34\r\n 3 5 18\r\n 4 5 40\r\n 4 6 51";
}
