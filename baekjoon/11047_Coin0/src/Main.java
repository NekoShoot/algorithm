import java.io.*;
import java.util.*;

public class Main {
    /*
    동전 N 종류 매우 많이 갖고 있음
    가치 합 K
    -> 필요한 동전 개수의 최솟값은?

    -- 첫줄에 N, K --
    1 <= N <= 10
    1 <= K <= 100,000,000

    이후 N개 줄에 A_i가 오름차순으로 주어짐
    1 <= A_i <= 1,000,000
    A_1 = 1, i >= 2인 경우에 A_i는 A_i-1의 배수
     */

    public static void main(String[] args) throws IOException {
        // 결국 A_1 ~ A_n-1은 A_n의 약수
        // 이 약수의 합으로 이루어져 있다면, K를 넘지 않는 가장 큰 약수부터 빼가면 됨
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken()); // 코인 종류
        int K = Integer.parseInt(st.nextToken()); // 목표 합

        int ans = 0;

        // 코인 값 담기
        int[] coinArr = new int[N];
        for(int i = 0; i < N; i++) {
            String coin = br.readLine().trim();
            coinArr[i] = Integer.parseInt(coin);
        }

        // K를 넘지 않는 가장 큰 A_N의 약수부터 빼가기
        int idx = N-1;
        while(idx >= 0) {
            // 1. K가 idx 동전 가치 보다 크면
            if(K >= coinArr[idx]) {
                K -= coinArr[idx];
                ans++;
            } else {
                idx--;
            }
        }

        System.out.println(ans);
    }
}
