package 너비우선탐색;

import java.util.*;
import java.io.*;

public class BFS {
	static int V, E; // 노드, 간선 개수
	static List<Integer>[] adj; // 인접 리스트
	static boolean[] visited; // 방문 배열
	static String input = "7 9\r\n" + "1 2\r\n" + "1 3\r\n" + "1 6\r\n" + "2 4\r\n" + "2 7\r\n" + "3 4\r\n" + "4 7\r\n" + "5 6\r\n" + "5 7";
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(input);
		
		V = sc.nextInt();
		E = sc.nextInt();
		
		adj = new ArrayList[V+1]; // 배열만 만듬, 안에 리스트 생성하지 않았음
		for(int i = 1; i <= V; i++) { // 1번 노드부터 시작
			adj[i] = new ArrayList<>(); // 그래서 만들어줌
		}
		visited = new boolean[V+1];
		
		// 무방향 그래프 간선 연결
		for(int i = 0; i < E; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			
			adj[a].add(b);
			adj[b].add(a);
		}
		
		
		bfs(1);
		
	} // main
	
	static void bfs(int v) {
		Queue<Integer> q = new LinkedList<>();
		
		q.offer(v); // 시작 노드 큐에 넣기
		visited[v] = true; // 방문 체크
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			System.out.println(cur);
			
			for(int adjNode : adj[cur]) {
				if(!visited[adjNode]) {
					q.offer(adjNode);
					visited[adjNode] = true;
				}
			}
		}
	}
}
