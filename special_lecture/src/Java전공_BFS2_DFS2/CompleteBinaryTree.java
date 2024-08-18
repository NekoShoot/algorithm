package Java전공_BFS2_DFS2;

import java.util.ArrayDeque;
import java.util.Queue;

public class CompleteBinaryTree<E> {
    private Object[] nodes;
    private int lastIndex;
    private final int MAX_SIZE;

    public CompleteBinaryTree(int maxSize) {
        super();
        MAX_SIZE = maxSize;
        nodes = new Object[MAX_SIZE+1];
    }

    public boolean isFull() {
        return lastIndex == MAX_SIZE; // true면 가득 참
    }

    public void add(E e) {
        if(isFull()) throw new RuntimeException("트리가 노드로 가득 찼습니다.");
        nodes[++lastIndex] = e;
    }

    public void bfs() {
        // 1. 순서를 관리할 큐 준비
//        Queue<Integer> queue = new ArrayDeque<>(); // index만 넣으면 됨! 순서만 알면 되기 때문에
        Queue<int[]> queue = new ArrayDeque<>(); // 탐색이 될 대상과 너비까지 큐에 넣기
        // 2. 처음 탐색의 대상이 될 노드를 큐에 넣기
        queue.offer(new int [] {1, 0}); // 루트노드 인덱스에 넣기

        // 3. 큐에 저장돼있는 탐색 대상들을 차례대로 꺼내어 방문
        while(!queue.isEmpty()) {
            // 4. 탐색 대상 꺼내기
            int info[] = queue.poll();
            int cur = info[0];
            int breadth = info[1];

            // 아래 순서는 전위 탐색 V -> L -> R
            // 5. 탐색 대상 탐색하기 (대상으로 해야할 작업들 수행)
            System.out.print(nodes[cur]+"("+ breadth + ")" + "\t"); // \t 탭

            // 6. 탐색 대상과 관계를 맺고 있는 대상들을 다음에 방문하기 위해 순서를 결정하는 큐에 넣기
            if(cur * 2 <= lastIndex) queue.offer(new int [] {cur * 2, breadth+1}); // 왼쪽 자식 체크
            if(cur*2 +1 <= lastIndex) queue.offer(new int [] {cur * 2+1, breadth+1}); // 오른쪽 자식 체크
        }

    }

    public void dfsByPreorder(int cur) {
        // 아래 순서는 전위 탐색 V -> L -> R
        // 1. 탐색 대상 탐색하기 (대상 작업 수행)
        System.out.print(nodes[cur]+ "\t"); // \t 탭

        // 2. 탐색 대상과 관계를 맺고 있는 대상들을 다음에 방문하기 위해 재귀 탐색

        // 6. 탐색 대상과 관계를 맺고 있는 대상들을 다음에 방문하기 위해 순서를 결정하는 큐에 넣기
        if(cur * 2 <= lastIndex) dfsByPreorder(cur*2); // 왼쪽 자식 체크
        if(cur*2 +1 <= lastIndex) dfsByPreorder((cur*2+1)); // 오른쪽 자식 체크


    }

    public static void main(String[] args) {
        String[] names = {"김", "이", "조", "박", "방", "규"};
        CompleteBinaryTree<String> tree = new CompleteBinaryTree<>(names.length);
        for(String name : names) {
            tree.add(name);
        }

        System.out.println("=============bfs=============");
        tree.bfs();
        System.out.println("\n=============dfs=============");
        tree.dfsByPreorder(1); // 루트노드
    }
}
