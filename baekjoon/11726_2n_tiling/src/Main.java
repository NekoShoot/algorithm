import java.util.*;
import java.io.*;

public class Main {
    // br의 유효 줄만 input으로 받기
    static String validateLine(BufferedReader br) throws IOException {
        String line;
        while((line = br.readLine()) != null) {
            line = line.trim();
            if(!line.isEmpty()) {
                return line;
            }
        }
        return null;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = validateLine(br);
        int n = Integer.parseInt(line);

        int[] dp = new int[n+1];
        dp[1] = 1;
        if(n >= 2) dp[2] = 2;

        if(n >= 3) {
            for (int i = 3; i <= n; i++) {
                // 1x2와 2x1의 조합으로 분할
                // dp[i-1] -> 1x2를 선택해서 마지막 칸을 채우기
                // dp[i-2] -> 2x1를 선택해서 마지막 칸을 채우기
                dp[i] = ((dp[i - 1] % 10007) + (dp[i - 2] % 10007)) % 10007;
                // (A + B) % M == ((A % M) + (B % M)) % M
            } // dp 배열 채우기
        }
        int ans = dp[n];
        System.out.println(ans);
    } // main
}
