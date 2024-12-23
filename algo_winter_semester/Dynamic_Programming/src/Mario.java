import java.util.Arrays;

public class Mario {
    static int[] line = new int[]{0, 1, 50, 1, -1, 1, 3, -5,  1, -15, 0, 100, -1, -1};
    static int[] dp = new int[14];

    static int gogo(int n) {
        if(n == 0) return 0;
        if(n <0) return -999;

        for(int i = 8; i <= n; i++) {
            int case7 = dp[i-7] + line[i];
            int case2 = dp[i-2] + line[i];

            dp[i] = Math.max(case7, case2);
        }

        return dp[n];
    }

    public static void main(String[] args) {
        Arrays.fill(dp, -999);

        dp[0] = 0;
        dp[2] = line[2];
        dp[4] = line[2] + line[4];
        dp[6] = line[2] + line[4] + line[6];
        dp[7] = line[7];

        int ans = gogo(13);

        System.out.println(Arrays.toString(dp));
        System.out.println(ans);
    }
}
