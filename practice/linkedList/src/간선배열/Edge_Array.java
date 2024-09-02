package 간선배열;

import java.util.*;
import java.io.*;

public class Edge_Array {
	static class Edge {
		int A, B, W; // 시작, 끝, 가중치
		
		public Edge(int a, int b, int w) {
			A = a;
			B = b;
			W = w;
			
		}
	}
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int V = sc.nextInt(); // 정점의 개수
		int E = sc.nextInt(); // 간선의 개수
		
		// 1번 객체 생성 후 객체 배열
		Edge[] edges = new Edge[E];
		
		for(int i = 0; i < E; i++) {
			int A = sc.nextInt();
			int B = sc.nextInt();
			int W = sc.nextInt();
		
			edges[i] = new Edge(A, B, W);
		}
		
		
		// 2번 객체 생성 후 객체 동적 배열
		List<Edge> edgesArr = new ArrayList<>();
		for(int i = 0; i < E; i++) {
			int A = sc.nextInt();
			int B = sc.nextInt();
			int W = sc.nextInt();
			edgesArr.add(new Edge(A, B, W));
		}
		
		
		// 3번 객체 x
		int[][] edgesArr2 = new int[E][3];
		for(int i = 0; i < E; i++) {
			int A = sc.nextInt();
			int B = sc.nextInt();
			int W = sc.nextInt();
			
			edgesArr2[i][0] = A;
			edgesArr2[i][1] = B;
			edgesArr2[i][2] = W;
		}
		
		
	}
}
