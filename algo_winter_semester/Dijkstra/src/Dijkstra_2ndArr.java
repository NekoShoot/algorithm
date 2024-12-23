import java.util.Arrays;
import java.util.PriorityQueue;

class Node2 {
    int x;
    int y;
    int price;

    public Node2(int x, int y, int price) {
        this.x = x;
        this.y = y;
        this. price = price;
    }
}

public class Dijkstra_2ndArr {
    static final int INF = Integer.MAX_VALUE - 999;

    static int[][] maze = {
            {0, 3, 5, 2},
            {-1, -1, 1, -1},
            {2, 4, 3, 2},
            {6, -1, 2, 1},
            {0, 1, 2, 1}
    };

    static PriorityQueue<Node2> pq = new PriorityQueue<>((a, b) -> a.price - b.price);
    static int[][] bestPrice = new int[5][4];


    static void dijkstra2() {
        int[] dx = {0, 0, -1, 1};
        int[] dy = {1, -1, 0, 0};

        pq.offer(new Node2(0, 0, 0));

        while(!pq.isEmpty()) {
            Node2 current = pq.poll();

            if(current.price > bestPrice[current.y][current.x]) continue;

            for(int d = 0; d < 4; d++) {
                int nx = current.x + dx[d];
                int ny = current.y + dy[d];

                if(nx < 0 || ny < 0 || nx >= 4 || ny >= 5) continue;
                if(maze[ny][nx] == -1) continue;

                if(bestPrice[ny][nx] > current.price + maze[ny][nx]) {
                    bestPrice[ny][nx] = current.price + maze[ny][nx];
                    pq.offer(new Node2(nx, ny, bestPrice[ny][nx]));
                }
            }

        }

    }

    public static void main(String[] args) {
        for (int[] rows : bestPrice) {
            Arrays.fill(rows, INF);
        }

        dijkstra2();
        System.out.println(Arrays.deepToString(bestPrice));

    }
}
