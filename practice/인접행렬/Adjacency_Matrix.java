package 인접행렬;

import java.util.Scanner;

public class Adjacency_Matrix {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int V = sc.nextInt(); // 정점의 개수
		int E = sc.nextInt(); // 간선의 개수
		
		int[][] adjArr = new int[V][V];
		
		for(int i = 0; i < E; i++) {
			int a = sc.nextInt(); // 진출 차수
			int b = sc.nextInt(); // 진입 차수 
			adjArr[a][b] = 1; // 유향 그래프면 이 방향만
			adjArr[b][a] = 1; // 무향 그래프이면 상호 연결이므로	
		}
		
		
	}
}
