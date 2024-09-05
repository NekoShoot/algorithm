import java.util.*;
import java.io.*;

public class Solution {
	static List<Node>[] adjList; // 인접 리스트
	static double[][] islands; // 섬 정보 저장
	static double E; // 환경부담세율
	
	static class Node implements Comparable<Node> {
		// 섬 index
		int depart;
		int arrive;
		double burden; // 환경 부담금
				
		@Override
		public int compareTo(Solution.Node o) {		
			return Double.compare(this.burden, o.burden);
		}
		
		public Node(int d, int a, double b) {
			depart = d;
			arrive = a;
			burden = b;
		}
	} // Node class
		
	
	// 환경부담금 계산
	// d: depart index, a: arrive index
	static double calcBurden(int d, int a) {
		double dx = islands[d][0];
		double dy = islands[d][1];
		
		double ax = islands[a][0];
		double ay = islands[a][1];
		
		double result;
		double ySub = Math.abs(dy - ay);
		double xSub = Math.abs(dx - ax);
		if(dx == ax) result = ySub;
		else if(dy == ay) result = xSub;
		else result = Math.sqrt(Math.pow(xSub,2) + Math.pow(ySub,2)); 
						
		//TODO 0.0 -> burden으로 변경
		return Math.pow(result, 2) * E;
	}
	
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		for(int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt();
			adjList = new ArrayList[N]; // 0번 섬부터 시작, 인접 리스트
			islands = new double[N][2]; // 섬 초기화 0: x, 1: y
			
			sc.nextLine(); // 개행문자 제거
			
			String xStr = sc.nextLine().trim();
			String yStr = sc.nextLine().trim();	
						
			E = sc.nextDouble(); // 환경 부담 세율
											
			for(int i = 0; i < N; i++) {
				adjList[i] = new ArrayList<>(); // 초기화
				
				double x = Double.valueOf(xStr.split(" ")[i]);
				double y = Double.valueOf(yStr.split(" ")[i]);
				islands[i][0] = x;
				islands[i][1] = y;
			}
			
			// 모든 섬들에 대해 터널 계산해보기
			for(int i = 0; i < N; i++) {
				for(int j = i+1; j< N; j++) {
					int a = i;
					int b = j;
					double w = calcBurden(a, b);
										
					// 무방향
					adjList[a].add(new Node(a, b, w));
					adjList[b].add(new Node(b, a, w));
				}
			} // 터널 정보 저장 끝
			
			// 프림 알고리즘
			PriorityQueue<Node> pq = new PriorityQueue<>();
			boolean[] visited = new boolean[N];
			
			// 1. 시작 노드 선택
			int start = 0;						
			// 방문 체크
			visited[start] = true;
			// 인접 노드들 큐에 넣기
			pq.addAll(adjList[start]);			
			
			double ans = 0.0;
			int pick = 1;
			
			// 2. 전체 노드 순회
			while(pick != N) { // 전체 노드를 순회
				// 큐에서 하나 뽑고(최솟값) 그게 방문하지 않았던 노드를 향하고 있으면 가중치 더해주기 
				Node e = pq.poll();
				if(visited[e.arrive]) continue; // 이미 방문했던 노드면 pass
				
				ans += e.burden;				
				// 방문 체크
				visited[e.arrive] = true;
				
 				// 그 노드의 인접 노드들을 전부 큐에 넣기
				pq.addAll(adjList[e.arrive]);
				
				pick++; // 뽑은 개수
			}
						
			ans = Math.round(ans);
			System.out.printf("#%d %.0f\n", tc, ans);
		} // tc
	}
}