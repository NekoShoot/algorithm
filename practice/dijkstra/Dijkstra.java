package dijkstra;

import java.util.*;
import java.io.*;

public class Dijkstra {
	static class Node {
		int V, W;
		
		public Node(int V, int W) {
			this.V = V;
			this.W = W;
		}
	}
	
	static final int INF = Integer.MAX_VALUE;
	static int V, E;
	static List<Node>[] adjList; // 인접 리스트
	static int[] dist; // 거리 배열
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(input);
		
		V = sc.nextInt();
		E = sc.nextInt();
		
		adjList = new ArrayList[V];
		for(int i = 0; i < V; i++) {
			adjList[i] = new ArrayList<>();
		}
		
		dist = new int[V];
		Arrays.fill(dist, INF);
		// === 초기화 끝 ===
		
		for(int i = 0; i < E; i++) {
			int A = sc.nextInt();
			int B = sc.nextInt();
			int W = sc.nextInt();
			
			adjList[A].add(new Node(B, W));			
			
		} // 입력 완료
		
		dijkstra(0);
		System.out.println(Arrays.toString(dist));
		
	}
	
	private static void dijkstra(int st) {
		boolean[] visited = new boolean[V];
		
		dist[st] = 0; // 시작 노드까지의 거리는 0으로 초기화
		// 어떻게 반복을 할 지는 문제를 보고 결정!
		for(int i = 0; i < V-1; i++) {
			int min = INF;
			int idx = -1;
			
			for(int j = 0; j < V; j++) {
				if(!visited[j] && min > dist[j]) {
					min = dist[j];
					idx = j;
				}
			}
			
			// 방문하지 못한 노드는? 시작 노드로부터 갈 수 없다!
			if(idx == -1) break;
			
			// 방문 체크
			visited[idx] = true; // -> idx 노드를 뽑았음! == 시작 노드부터 idx까지 최단 거리 구해짐
			
			// 갱신하는 부분만 프림과 다르다
			for(Node node : adjList[idx]) {
				if(!visited[node.V] && dist[node.V] > (dist[idx] + node.W)) { // <- 조건문이 다잌인지 프림인지를 결정지음
					// 시작 -> 뽑은 노드의 인접 노드까지의 거리 저장해둔 게 작냐 vs. 시작 -> 뽑은 노드 -> 인접 노드 거리가 작냐
					dist[node.V] = dist[idx] + node.W;
				}
			}
			
			
		}
	} 
	
	
	static String input = "6 11\r\n"
			+ "0 1 4\r\n"
			+ "0 2 2\r\n"
			+ "0 5 25\r\n"
			+ "1 3 8\r\n"
			+ "1 4 7\r\n"
			+ "2 1 1\r\n"
			+ "2 4 4\r\n"
			+ "3 0 3\r\n"
			+ "3 5 6\r\n"
			+ "4 3 5\r\n"
			+ "4 5 12\r\n"
			+ "";
}
