import java.io.*;
import java.util.*;

public class Solution {
    static class Block {
        int r, c, dist;

        public Block(int r, int c, int dist) {
            this.r = r;
            this.c = c;
            this.dist = dist;
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("./src/input.txt"));
        Scanner sc = new Scanner(System.in);

        int T = 10;
        // TODO 1 => T
        for(int tc = 1; tc <= T; tc++) {
            tc = sc.nextInt();
            int ans = 0;

            int[][] maze = new int[16][16];

            int startR = -1;
            int startC = -1;

            int endR = -1;
            int endC = -1;

            for(int i = 0; i < 16; i++) {
                char[] chars = sc.next().trim().toCharArray();

                for(int j = 0; j < 16; j++) {
                    maze[i][j] = chars[j] - '0';
                    if(maze[i][j] == 2) {
                        startR = i;
                        startC = j;
                    } else if(maze[i][j] == 3) {
                        endR = i;
                        endC = j;
                    }
                }
            } // 미로 채우기

            // 델타 배열 상 하 좌 우
            int[] dr = new int[]{-1, 1, 0, 0};
            int[] dc = new int[]{0, 0, -1, 1};

            // BFS로 길찾기
            int dist = -1;
            Block start = new Block(startR, startC, dist);

            Queue<Block> branch = new LinkedList<>(); // 다음 갈 블럭을 탐색할 분기점

            // 방문 배열 쓰지 않고 -1로 덮어씌워서 해도 될까? -> 될 거 같다.
            maze[start.r][start.c] = dist; // 방문 체크
            branch.offer(start);

            // 큐가 빌 때까지 반복 -> 기저조건 == 미로 도착지점일 때
            while(!branch.isEmpty()) {
                int size = branch.size();
                for(int i = 0; i < size; i++) { // 큐 크기 만큼 돌리기
                    Block curr = branch.poll();
                    // 델타 탐색
                    for(int d = 0; d < 4; d++) {
                        int nr = curr.r + dr[d];
                        int nc = curr.c + dc[d];

                        if(nr >= 0 && nr < 16 && nc >= 0 && nc < 16) { // 미로 범위 내
                            if(maze[nr][nc] == 0) {
                                Block next = new Block(nr, nc, dist);
                                maze[nr][nc] = dist; // 방문 및 거리 체크
                                branch.offer(next);
                            } else if(maze[nr][nc] == 3) { // 도착지점이면
                                maze[nr][nc] = dist;
                                ans = 1;
                            }
                        }
                    } // delta
                } // queue

                dist--;
            } // while
//            for(int i = 0; i < 16; i++) {
//                System.out.println(Arrays.toString(maze[i]));
//            }

//            System.out.println(Math.abs(maze[endR][endC]));
            System.out.printf("#%d %d\n", tc, ans);

        } //tc

    } // main
}
