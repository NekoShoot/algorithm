import java.util.*;
import java.io.*;

// ABCDCDAB

public class Main {
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("./src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int cnt = 0;

        // 1. 처음 들어온 소가 쌍이 되는 소 보다 빨리 나가야 함
        // 2. 쌍이 되는 소 안에 다른 쌍이 있을 수 있음
        String memo = st.nextToken();

        int[] visited = new int[26];

        char[] cows = memo.toCharArray();

        for(int i = 0; i < cows.length - 1; i++) {
            // 완전탐색
            // 1. 한 소가 들어오는 걸 고름
            char cowIn = cows[i];

            if(visited[cowIn - '0'-17] == 2) continue;

            for(int v = 0; v < 26; v++) {
                if(visited[v] == 1) {
                    visited[v] = 0;
                    break;
                }
            }

            // 2. 그 소가 나가는 idx를 확인
            int idx = -1;
            for(int j = i + 1; j < cows.length; j++) {
                char cowOut = cows[j];
                if(cowIn == cowOut) {
                    idx = j;
                    break;
                }
            }

            // 3. 그 소가 들어오고 나가는 사이에 다른 소들 중에서 쌍이 되는 소가 있는 지 확인
            out: for(int c = i + 1; c < idx; c++) {
                char cowPair = cows[c]; // 너가 짝꿍이니?

                if(visited[cowPair - '0'-17] == 2) continue;
                if(visited[cowPair - '0'-17] == 0 && c == idx - 1) {
                    cnt++;
                    visited[cowPair - '0'-17] = 2; // 짝꿍임
                    visited[cowIn - '0'-17] = 2;
                    break;
                }

                for(int d = c + 1; d < idx; d++) {
                    char cowPair2 = cows[d];
                    if(cowPair == cowPair2) {
                        visited[cowPair - '0'-17] = 1;
                        visited[cowPair2 - '0'-17] = 1;
                        break; // 먼저 나갔으면 짝꿍이 아니구나
                    }



                    if(d == idx - 1 && visited[cowPair - '0'-17] == 0) {
                        cnt++; // 끝까지 봤는데도 먼저 나가지 않다니 너가 짝꿍이구나
                        visited[cowPair - '0'-17] = 2; // 짝꿍임
                        visited[cowIn - '0'-17] = 2;
                        break out;
                    }

                }

            }


        }

        System.out.println(cnt);

    }
}