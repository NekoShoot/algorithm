import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

class Node {
    int n;
    int price;

    public Node(int n, int price) {
        this.n = n;
        this.price = price;
    }
}

public class Main {
    ArrayList<Node>[] aList = new ArrayList[5];
    static final int INF = Integer.MAX_VALUE - 100;

    void init() {
        aList[0] = new ArrayList<>(Arrays.asList(new Node(1, 10), new Node(2, 30)));
        aList[1] = new ArrayList<>(Arrays.asList(new Node(2, 10), new Node(3, 40)));
        aList[2] = new ArrayList<>(Arrays.asList(new Node(3, 10)));
        aList[3] = new ArrayList<>();
    }

    void dijkstra() {
        int[] bestPrices = new int[5];
        Arrays.fill(bestPrices, INF);
        bestPrices[0] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.price - b.price);

        pq.offer(new Node(0, 0));

        while(!pq.isEmpty()) {
            Node now = pq.poll();

            if(now.price > bestPrices[now.n]) continue;

            for(Node next : aList[now.n]) {
                if(bestPrices[next.n] > now.price + next.price) {
                    bestPrices[next.n] = now.price + next.price;
                    pq.offer(new Node(next.n, bestPrices[next.n]));
                }
            }
        }

        for(int bp : bestPrices) {
            System.out.println(bp);
        }
    }

    void solution() {
        init();
        dijkstra();
    }

    public static void main(String[] args) {
        new Main().solution();
    }
}
