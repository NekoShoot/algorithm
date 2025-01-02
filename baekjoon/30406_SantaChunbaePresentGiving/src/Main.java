import java.util.*;
import java.io.*;

public class Main {
    static int[] presentArr;
    static int[] cntArr;

    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine().trim());
        presentArr = new int[N];
        cntArr = new int[4];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            int satisfaction = Integer.parseInt(st.nextToken());
            presentArr[i] = satisfaction;

            switch(satisfaction) {
                case 0 -> cntArr[0]++;
                case 1 -> cntArr[1]++;
                case 2 -> cntArr[2]++;
                case 3 -> cntArr[3]++;
            }

        }

        // 1. 0과 3을 페어링
        pairing(0, 3);

        // 2. 1과 2를 페어링
        pairing(1, 2);

        // 3. 남는 것들 중 서로 일치하지 않는 것끼리 페어링({01, 02, 13, 23} 만 남음)
        // 3-1. 0이 남았으면 먼저 2를 보고 그 다음에 1을 페어링
        if(cntArr[0] != 0) {
            pairing(0, 2);

            if(cntArr[0] != 0) {
                pairing(0, 1);
            }
        }
        // 3-2 0이 없으면 1,2를 3과 페어링
        else {
            if(cntArr[1] != 0) {
                pairing(1, 3);
            }

            if(cntArr[2] != 0) {
                pairing(2, 3);
            }
        }

        System.out.println(answer);

    }

    // XOR 연산 메소드
    static void pairing(int a, int b) {
        int pairCnt = Math.min(cntArr[a], cntArr[b]); // 페어 개수
        cntArr[a] -= pairCnt; // 페어한 애들 개수 빼주기
        cntArr[b] -= pairCnt;
        answer += pairCnt * (a ^ b); // XOR한 값어치 만큼 더해주기
    }
}
