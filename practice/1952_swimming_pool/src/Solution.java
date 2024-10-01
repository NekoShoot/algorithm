import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("./src/input.txt"));
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        // TODO 1 -> T
        for(int tc = 1; tc <= T; tc++) {
            int[] prices = new int[4]; // 0: 1일, 1: 1달, 2: 3달, 3: 1년
            for(int i = 0; i < 4; i++) {
                prices[i] = sc.nextInt();
            } // 이용권 가격들

            int[] year = new int[13]; // 달마다 계획한 이용 일수
            for(int i = 1; i <= 12; i++) {
                year[i] = sc.nextInt();
            } // 12개월 이용 계획

            int[] dp = new int[15]; // 각 개월의 최소 비용 선택(1년짜리 빼고)

            int ans; // 1년을 쓴 가격을 기준으로 생각

            for(int i = 1; i <= 14; i++) {
                if(i <= 12) {
                    // 이번 달에 1일 이용권으로 이용하는 경우
                    int dayCase = dp[i-1] + (prices[0] * year[i]);
                    // 이번 달에 1달 이용권을 이용하는 경우
                    int monthCase = dp[i-1] + prices[1];
                    // 이번 달이 3달 이용권 만료 달인 경우
                    int quarterCase = 9999; // 최대 범위를 넘는 임시 값
                    if (i >= 3) {
                        quarterCase = dp[i-3] + prices[2];
                    }

                    int tmpMin = Math.min(dayCase, monthCase);
                    dp[i] = Math.min(tmpMin, quarterCase);
                }
                else { // 11월과 12월에 3개월 치를 사는 경우 고려
                    dp[12] = Math.min(dp[12], dp[i-3] + prices[2]);
                }
            } // dp 배열 저장 끝

//            System.out.println(Arrays.toString(prices));
//            System.out.println(Arrays.toString(year));
//            System.out.println(Arrays.toString(dp));

            // dp[12]와 ans 중에서 작은 값이 정답
            ans = Math.min(prices[3], dp[12]);

            System.out.printf("#%d %d\n", tc, ans);

        } // tc
    } // main
}
