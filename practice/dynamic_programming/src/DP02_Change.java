import java.util.Scanner;

public class DP02_Change {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int change = sc.nextInt(); // 거스름돈
        int[] dp = new int[change+1]; // 0개로 초기화
        
        // 1, 4, 6원 고민 -> Bottom-Up 방식
        for(int i = 1; i <= change; i++) {
            // i: 거스름돈의 액수 (1원, 2원 ... 하나씩 구해서 결국 8원의 최적해 구하기
            int minCnt = Integer.MAX_VALUE; //
            
            minCnt = Math.min(minCnt, dp[i-1]+1); // 1원 짜리를 선택
            if(i >= 4) minCnt = Math.min(minCnt, dp[i-4] + 1); // 4원짜리를 선택
            if(i >= 6) minCnt = Math.min(minCnt, dp[i-6] + 1); // 6원짜리를 선택
            dp[i] = minCnt;
        } // 동전의 종류 고민 끝

        System.out.println(dp[change]);
    }
}
