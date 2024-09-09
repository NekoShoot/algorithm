package topological_sort;

import java.io.*;
import java.util.*;

public class Topological_Sort_Stack {
	public static String[] cook = { "", "재료구매", "양념장만들기", "고기재우기", "고기손질", "제육볶음만들기", "식사", "뒷정리", "채소손질", "밥하기" };
	static int V; // 노드 개수
	static int E; // 간선 개수
	static int[][] adjArr;
	static int[] degree;
	static boolean[] visited;
	static Stack<Integer> ans; // 정답을 담을 스택
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./topological_sort/input.txt"));
		Scanner sc = new Scanner(System.in);
		
		V = sc.nextInt();
		E = sc.nextInt();
		
		// 인접 행렬
		adjArr = new int[V+1][V+1];
		degree = new int[V+1]; // 진입 차수
		visited = new boolean[V+1]; // 방문 배열
		ans = new Stack<>(); // 정답 스택 초기화
		
		for(int i = 0; i < E; i++) {
			int A = sc.nextInt(); // depart(out)
			int B = sc.nextInt(); // arrive(in)
			
			// 방향 있음
			adjArr[A][B] = 1; // 가중치 없음
			degree[B]++; // 진입 차수 증가
		} // 입력 완료
		
		for(int i = 1; i <= V; i++) {
			if(degree[i] == 0) {
				dfs(i);
			}
		} // 위상 정렬 완료
		
		StringBuilder sb = new StringBuilder();
		while(!ans.isEmpty()) {
			sb.append(cook[ans.pop()] + " -> "); // 위상 정렬 수행 순서
		}
		
		sb.delete(sb.length()-4, sb.length()-1);
		sb.append("\n");
		String result = sb.toString();
		System.out.println(result);
	}
	
	private static void dfs(int curr) {
		visited[curr] = true; // 방문 체크
		// 선행 조건이 여러 개일 경우, 그걸 다 해야 작업을 수행하므로 
		// 방문 체크를 했다고 해서 바로 정답에 넣는 것이 아님
		
		for(int i = 1; i <= V; i++) { // 인접한 모든 노드 탐색(인접 행렬이라)
			if(adjArr[curr][i] == 1 && !visited[i]) { // 간선 있고 방문 안했다면
				dfs(i);
			}
		}
		
		// 재귀 반복이 끝난 후(간선이 연결되어 있는 모든 노드 탐색함)
		ans.push(curr);
		
	}
}
