public class Main {
    static int[] arr = new int[1000];

    static int get_fibo(int n) {
        for(int i = 2; i < n+1; i++) {
            arr[i] = arr[i-2] + arr[i-1];
        }

        return arr[n];
    }

    public static void main(String[] args) {
        arr[0] = 0;
        arr[1] = 1;

        System.out.println(get_fibo(15));
    }
}
