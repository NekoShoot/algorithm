package 인접리스트;

import java.util.*;
import java.io.*;

public class Adjacency_List {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int V = sc.nextInt(); // 정점의 개수
		int E = sc.nextInt(); // 간선의 개수
		
		// arrayList를 원소로 가지는 배열
		List<Integer>[] adjList = new ArrayList[V];
		for(int i = 0; i < V; i++) {
			adjList[i] = new ArrayList<>();
		}
				
		for(int i = 0; i < E; i++) {
			int A = sc.nextInt();
			int B = sc.nextInt();
			
			adjList[A].add(B);
			adjList[B].add(A);
		}
	}
}
