import java.util.*;
import java.io.*;

public class Solution {
	static double[] p; // 대표값 저장하는 배열
	static double E; // 환경부담 세율(input중 제일 마지막에 들어옴)
	
	// x를 원소로 가지는 서로소 집합의 대표값 찾기
	static double findSet(double x) {		
		if(x != p[(int) x]) p[(int) x] = findSet(p[(int) x]);
		return p[(int) x];
	}
	
	// x와 y 서로소 집합 병합
	static void union(double x, double y) {		
		p[(int) y] = x; // y의 대표값을 x로 == y가 x 집합의 부분집합이 된다
	}
	
	// first섬과 second섬 사이의 해저터널 환경부담금 계산
	static double calcBurden(double[][] islands, int f, int s) {
		double result;
		
		double[] first = islands[f];
		double[] second = islands[s];
		
		double xSub = Math.abs(first[0] - second[0]);
		double ySub = Math.abs(first[1] - second[1]);
		
		if(first[0] == second[0]) result = ySub; // x가 같으면 터널 길이는 y의 차
		else if(first[1] == second[1]) result = xSub; // y가 같으면 터널 길이는 x의 차
		else result = Math.sqrt(Math.pow(xSub, 2) + Math.pow(ySub, 2)); // 피타고라스 정리 * 2
		return Math.pow(result, 2) * E; // 환경 부담금 = E(환경 부담 세율) * (L(해저터널 길이)**2 )
	}	
	
	// main
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./src/input.txt"));
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
				
		for(int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt(); // 섬의 수
			double[][] islands = new double[N][2]; // 섬 정보를 저장할 배열. 0번 섬부터
			p = new double[N];			
			
			for(int x = 0; x < N; x++) {
				islands[x][0] = sc.nextDouble(); // 섬의 x값 받기 
			}
			
			for(int y = 0; y < N; y++) {
				islands[y][1] = sc.nextDouble(); // 섬의 y값 받기
			}
			
			E = sc.nextDouble(); // 환경 부담 세율
			
			// 신장 트리 만들기(모든 섬 간에 해저터널 경우의 수 다 구해보기)
			// 1 <-> 2를 만들면 2 <-> 1을 만들지 않아도 됨(양방향)
			// 1 <-> 2를 만들면 2 <-> 1을 만들지 않아도 됨(양방향)
			// 환경 부담금 == 가중치
			int eCnt = 0; // 해저터널 개수 => 중복 없으면 `(N-1)!`			
			
			for(int i = 0; i < N; i++) {		
				p[i] = i; // 각각의 섬을 대표로 하는 서로소 집합 생성(makeSet(i))
				
				for(int j = i+1; j < N; j++) {
					eCnt++; 					
				}
			}
						
			// i: 간선 순서 (i, 0): 출발 섬 (i, 1): 도착 섬 (i, 2): 가중치
			double[][] tunnels = new double[eCnt][3]; // 터널 저장
			
			int idx = 0;
			for(int i = 0; i < N; i++) {
				for(int j = i+1; j < N; j++) {
					double weight = calcBurden(islands, i, j);					
					tunnels[idx][0] = i; // 출발 섬
					tunnels[idx][1] = j; // 도착 섬
					tunnels[idx][2] = weight; // 환경부담금	
					idx++;
				}
			}
									
			// 크루스칼로 최소 신장 트리 구하기	
			// 1. 간선 배열 가중치 기준으로 오름차순 정렬
			Arrays.sort(tunnels, new Comparator<double[]>() {
				@Override
				public int compare(double[] o1, double[] o2) {
					return Double.compare(o1[2], o2[2]); 
				}
			});
			
			
			// 2. N-1개의 해저터널 건설			
			double answer = 0; // 환경 부담금 총합
			int construct = 0; // 건설한 해저터널 개수			
			
			for(int i = 0; i < eCnt; i++) {
				double depart = tunnels[i][0];
				double arrive = tunnels[i][1];
				double weight = tunnels[i][2];
								
				// x와 y가 각자 서로소 집합의 대표값일 때				
				double pd = findSet(depart);				
				double pa = findSet(arrive);				
				
				if(pd != pa) { // // x와 y가 서로 다를 때(사이클 방지)
					union(pd, pa);
					answer += weight;
					construct++;
				}
				
				if(construct == N-1) break;
			}
			
			// 소수점 첫째 자리에서 반올림
			answer = Math.round(answer);			
			
			System.out.printf("#%d %.0f\n", tc, answer);
			
		} // tc
		
	}
}
