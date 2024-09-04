package mst_kruskal;

import java.util.*;

public class MST_Kruskal {
	static int[] p; // 대표자를 저장할 배열
	
	static class Edge implements Comparable<Edge>{
		int A, B, W;		
		
		public Edge(int a, int b, int w) {
			super();
			A = a;
			B = b;
			W = w;
		}

		@Override
		public String toString() {
			return "Edge [A=" + A + ", B=" + B + ", W=" + W + "]";
		}		
		
		// 가중치 기준으로 정렬하기 위해 
		@Override
		public int compareTo(Edge o) {
			return this.W - o.W;
		}
	}
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(input);
		
		int V = sc.nextInt(); // 정점 개수
		int E = sc.nextInt(); // 간선 개수
		
		Edge[] edges = new Edge[E];
				
		
		for(int i = 0; i < E; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			int w = sc.nextInt();
			
			edges[i] = new Edge(a, b, w);
		}
			
		// 크루스칼 1. 가중치 순으로 정렬
		
		/*
		 * Arrays.sort(edges, new Comparator<Edge>() {
		 * 	@Override
		 * 	public int compare(MST_Kruskal.Edge o1, 
		 * }
		 */
		Arrays.sort(edges); // compareTo Overriding
		
		// 크루스칼 2. V-1개의 간선을 뽑기(사이클이 발생하지 않게)
		// 상호 배타 집합 == 서로소 집합 == 유니온 파인드
		p = new int[V];
		
		// make-set을 통해 전체 노드 각각 자신을 대표로 만드는 작업 수행
		for(int i = 0; i < V; i++) {// 0번 노드부터 시작하기 때문에 반복문 0부터 시작
			//makeSet(i);
			p[i] = i; // 자기자신을 인덱스로 하는 곳의 대표를 자기자신을 넣어줌
		}
		
		int ans = 0; // 최소 비용을 저장하기 위한 변수
		int pick = 0; // 내가 뽑은 간선의 개수
		
		for(int i = 0; i < E; i++) {
			int x = edges[i].A;
			int y = edges[i].B;
			int w = edges[i].W;
			
//			// 사이클 발생하는 지 검사 -> union과의 중복 계산 발생
//			if(findSet(x) != findSet(y)) { // x와 y의 간선이 있는데 서로의 대표가 다르면 사이클이 아니라는 뜻
//				union(x, y);
//				ans += w;
//				pick++;
//			}
			
			// 메소드 호출 횟수를 줄일 수 있음
			int px = findSet(edges[i].A);
			int py = findSet(edges[i].B);
			if(px != py) {
				union(px, py);
				ans += edges[i].W;
				pick++;
			}
			
			
			if(pick == V-1) break; // 간선 개수 v-1개 뽑으면 break (사이클 방지)
		}
		
//		for(Edge e : edges) System.out.println(e);
		System.out.println(ans);
		
	}
	
	static void makeSet(int x) {
		p[x] = x;
 	}
	
	static int findSet(int x) {
		// path compression
		if(x != p[x]) p[x] = findSet(p[x]); // 재귀를 통해 대표자를 찾고, 그 집합 안에 있는 요소들에 전부 대표자 대입
		return p[x];
	}
	
	static void union(int x, int y) {
		// x와 y가 대표자이어야 함
		if(x == findSet(x) && y == findSet(y))
			p[findSet(y)] = findSet(x); // y 집합의 대표자를 x로 지정함 == x집합에 y집합을 넣기
		
		p[y] = x; // 중복 계산을 없애기 위해 대표를 바꿔주면?
	}
	
	static String input = "7 11\r\n 0 1 32\r\n 0 2 31\r\n 0 5 60\r\n 0 6 51\r\n 1 2 21\r\n 2 4 46\r\n 2 6 25\r\n 3 4 34\r\n 3 5 18\r\n 4 5 40\r\n 4 6 51";
}




