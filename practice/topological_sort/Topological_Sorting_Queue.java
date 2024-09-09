package topological_sort;

import java.util.*;
import java.io.*;

public class Topological_Sorting_Queue {
	public static String[] cook = { "", "재료구매", "양념장만들기", "고기재우기", "고기손질", "제육볶음만들기", "식사", "뒷정리", "채소손질", "밥하기" };
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./topological_sort/input.txt"));
		Scanner sc = new Scanner(System.in);
		int V = sc.nextInt(); // 노드 개수
		int E = sc.nextInt(); // 간선 개수
		
		// 인접 행렬
		int[][] adjArr = new int[V+1][V+1];
		int[] degree = new int[V+1]; // 진입 차수
		
		for(int i = 0; i < E; i++) {
			int A = sc.nextInt(); // depart(out)
			int B = sc.nextInt(); // arrive(in)
			
			// 방향 있음
			adjArr[A][B] = 1; // 가중치 없음
			degree[B]++; // 진입 차수 증가
		} // 입력 완료
		
		// 위상 정렬 Queue		
		Queue<Integer> queue = new LinkedList<>();
		
		// 1. 진입 차수가 0인 노드를 전부 삽입(정방향이건 역방향이건 상관 없음)
		for(int i = V; i >= 1; i--) {
			if(degree[i] == 0) {
				queue.add(i);
			}
		}
		
		StringBuilder sb = new StringBuilder();
		// 2. queue가 공백 상태가 될 때까지 반복
		while(!queue.isEmpty()) {
			// 큐에서 노드 뽑기
			int curr = queue.poll();
			sb.append(cook[curr] + " -> "); // 위상 정렬 수행 순서
			
			// 뽑은 노드와 연결된 간선 제거
			for(int i = 1; i <= V; i++) {
				if(adjArr[curr][i] == 1) { // 연결되어 있는 노드
					adjArr[curr][i] = 0; // 간선 제거
					degree[i]--; // in 노드 기준 진입차수 낮추기
					// 새로 진입차수가 0이 된 노드 큐에 넣기
					if(degree[i] == 0) queue.offer(i);
				}
			}		
			
		}
		
		sb.delete(sb.length()-4, sb.length()-1);
		sb.append("\n");
		String result = sb.toString();
		System.out.println(result);
	}
	
	

}
