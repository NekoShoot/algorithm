package 깊이우선탐색;

import java.util.Scanner;

public class DFS {
	static int V, E;
	static int[][] adj; // 인접 행렬
	static boolean[] visited;
	static String input = "7 9\r\n" + "1 2\r\n" + "1 3\r\n" + "1 6\r\n" + "2 4\r\n" + "2 7\r\n" + "3 4\r\n" + "4 7\r\n" + "5 6\r\n" + "5 7";
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(input);	 
		
		V = sc.nextInt(); // 노드 개수
		E = sc.nextInt(); // 간선 개수
		
		adj = new int[V+1][V+1];
		visited = new boolean[V+1]; // 초기화
		
		for(int i = 0; i < E; i++) {
			int A = sc.nextInt();
			int B = sc.nextInt();
			adj[A][B] = adj[B][A] = 1; // 무향 그래프
			
		} // 간선 입력
		
		
		dfs(1);
	} // main
	
	static void dfs(int v) {
		visited[v] = true;
		System.out.println(v);
		
		// 나와 인접하면서 방문하지 않은 노드 방문
		for(int i = 1; i <= V; i++) {
			if(!visited[i] && adj[v][i] == 1) {
				dfs(i);
			}
		}
	}
}
