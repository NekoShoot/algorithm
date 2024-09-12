import java.util.Scanner;

public class DP01_fibonacci {
    static int[] memo;

    static {
        memo = new int[1000];
        memo[0] = 0;
        memo[1] = 1;
    }
    

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        double start;
        double end;

        start = System.nanoTime();
        System.out.println(fibonnachi(N));
        end = System.nanoTime();
        System.out.println("재귀 => " + (int) (end - start));

        start = System.nanoTime();
        fiboMemo(N);
        System.out.println(memo[N]);
        end = System.nanoTime();
        System.out.println("Memo => " + (int) (end - start));

        start = System.nanoTime();
        System.out.println(fiboDP(N));
        end = System.nanoTime();
        System.out.println("DP => " + (int) (end - start));
    }

    // dp
    public static int fiboDP(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for(int i = 2; i <= n; i++) {
            dp[i] = dp[i-2] + dp[i-1];
        }
        return dp[n];
    }

    // 메모이제이션
    public static int fiboMemo(int n) {
        // 메모를 통해서 중복 계산 제외
        if(n > 2 && memo[n] == 0)  memo[n] = fibonnachi(n-1) + fibonnachi(n-2);
        return memo[n];
    }

    // 재귀
    static int fibonnachi(int n) {
        // 기저 조건
        if(n < 2) return n;
        return fibonnachi(n-1) + fibonnachi(n-2);
    }
}
