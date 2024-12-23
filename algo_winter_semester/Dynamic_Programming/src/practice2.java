public class practice2 {
    static int[] fiboArr = new int[1000];

    static int get_fibo(int n) {
        if(n == 0) return 0;
        if(n == 1) return 1;

        if(fiboArr[n] != 0) {
            return fiboArr[n];
        }

        int a = get_fibo(n-2);
        int b = get_fibo(n-1);
        fiboArr[n] = a + b;

        return a + b;
    }


    public static void main(String[] args) {
        fiboArr[0] = 0;
        fiboArr[1] = 1;


        int ans = get_fibo(45);
        System.out.println(ans);
    }
}
