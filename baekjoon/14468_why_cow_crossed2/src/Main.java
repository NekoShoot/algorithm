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
        char[] cows = memo.toCharArray();
        for(int i = 0; i < cows.length - 1; i++) {
            // 완전탐색
            // 1. 한 소가 들어오는 걸 고름
            // 2. 그 소가 나가는 idx를 확인
            // 3. 그 소가 들어오고 나가는 사이에 다른 소들 중에서 쌍이 되는 소가 있는 지 확인
            char cowIn = cows[i];




        }



    }
}