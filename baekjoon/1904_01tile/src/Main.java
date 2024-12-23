import java.util.*;
import java.io.*;

public class Main {
    // 00과 1을 이용해 길이가 n인 수열의 가짓수 구하기
    // n=1 -> 1 (1개)
    // n=2 -> 11 / 00 (2개)
    // n=3 -> 111 100 001 / (3개)
    // n=4 -> 1111 1001 1100 0011 / 0000 (5개)
    // n=5 -> 11111 11001 10011 11100 00111 / 00100 10000 00001 (8개)
    // n=6 -> 111111 111001 110011 100111 111100 001111 100100 001001 110000 100001 000011 /
    // 001100 000000 (13개)

    // dp[n] = dp[n-1] + dp[n-2]

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());

        int[] dp = new int[1000001]; // 입력의 최대까지 커버 가능하게
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;

        if(n >= 3) {
            for (int i = 3; i <= n; i++) {
                dp[i] = ((dp[i-1] % 15746) + (dp[i-2] % 15746))% 15746; // % 분할 적용으로 정답!
            }
        }

        int ans = dp[n];

        System.out.println(ans);


    }
}
