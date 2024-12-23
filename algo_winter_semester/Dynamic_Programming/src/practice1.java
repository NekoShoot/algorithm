public class practice1 {
    static int[] fiboArr = new int[1000];

    static int get_fibo(int n) {
        if(n == 0) return 0;
        if(n == 1) return 1;

        for(int i = 2; i <= n; i++) {
            fiboArr[i] = fiboArr[i-2] + fiboArr[i-1];
        }

        return fiboArr[n];
    }

    public static void main(String[] args) {
        fiboArr[0] = 0;
        fiboArr[1] = 1;

        int ans = get_fibo(45);
        System.out.println(ans);
    }
}
