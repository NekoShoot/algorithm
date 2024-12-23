import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int recurCnt;
    static int dpCnt;

    static int recursive_fibo(int n) {
        if(n == 0) return 0;
        else if(n == 1 || n == 2) {
            recurCnt++;
            return 1;
        }
        else {
            int first = recursive_fibo(n-1);
            int second = recursive_fibo(n-2);

            return first + second;
        }
    }

    static int dp_fibo(int n) {
        int[] dp = new int[41];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 1;

        if(0 <= n && n <= 2) return dp[n];
        else {
            for(int i  = 3; i <=n; i++) {
                dp[i] = dp[i-1] + dp[i-2];

                dpCnt++;
            }
        }

        return dp[n];
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());

        int a = recursive_fibo(n);
        int b = dp_fibo(n);

        System.out.println(recurCnt + " " + dpCnt);
    }
}
