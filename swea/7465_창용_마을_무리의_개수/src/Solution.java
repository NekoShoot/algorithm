import java.util.*;
import java.io.*;

// 창용 마을 무리의 개수
public class Solution {
	static int[] p; // 대표자들 저장할 배열
	
	static int findSet(int x) {
		if(x != p[x]) p[x] = findSet(p[x]); // x가 포함된 집단의 대표자로 바꾸기(다음부터 찾기 편하게)
		return p[x]; // 대표자 반환
	}

	static void union(int x, int y) {
		p[y] = x; // y집합의 대표자가 x가 된다 == x집합 안에 y집합이 들어간다.
	}
	
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();		
		
		for(int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt(); // 사람 명수(노드)
			int M = sc.nextInt(); // 사람 관계 수(간선)
			
			p = new int[N+1]; // p배열 초기화(1부터 시작)
			for(int x = 1; x <= N; x++) {
				p[x] = x; // makeSet(x)
			}			
			
			// 사람 관계 수
			for(int e = 0; e < M; e++) {
				int first = sc.nextInt();
				int second = sc.nextInt();
				
				int p1 = findSet(first); // first 집합의 대표자
				int p2 = findSet(second); // second 집합의 대표자
				
				if(p1 != p2) union(p1, p2); // 사이클이 없으면					
				
			} // 집합 관계 정리 끝				
						
			// 마지막 관계 정리
			for(int x = 1; x <= N; x++) {
				findSet(x);
			}
			
			System.out.println(Arrays.toString(p));
			
			// 무리 개수 체크
			Set<Integer> set = new HashSet<>();
			for(int i = 1; i <= N; i++) {
				set.add(p[i]);
			}
			
			int answer = set.size();
			
			System.out.printf("#%d %d\n", tc, answer);
		} // tc

	}
}
