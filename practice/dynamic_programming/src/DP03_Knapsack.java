import java.util.Scanner;

public class DP03_Knapsack {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt(); // 물건의 개수
        int W = sc.nextInt(); // 배낭의 무게

        int[] weights = new int[N+1];
        int[] cost = new int[N+1];

        for(int i = 1; i <= N; i++) {
            weights[i] = sc.nextInt();
            cost[i] = sc.nextInt();
        }

        int[][] dp = new int[N+1][W+1];

        // 각 물건은 1개씩만
        for(int i = 1; i <= N; i++) {
            for(int w = 0; w <= W; w++) { // w는 임시 무게
                // 물건의 무게가 임시 무게보다 작으면 선택할지 고민
                if(weights[i] <= w) {
                    dp[i][w] = Math.max(dp[i-1][w - weights[i]] + cost[i], dp[i-1][w]);
                } else dp[i][w] = dp[i-1][w];
            }
        } // 물건 고려
    }
}
