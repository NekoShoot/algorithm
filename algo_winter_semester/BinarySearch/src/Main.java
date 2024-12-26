public class Main {
    static int arr[] = {1, 2, 5, 9, 15, 50, 150, 230};

    static int bs(int target) {
        int left = 0;
        int right = arr.length - 1;

        while(left <= right) {
            int middle = (left + right) / 2;

            // 정답 찾음!
            if(arr[middle] == target) return middle;

            // 정답이 큰 쪽 이라면
            if(arr[middle] < target) left = middle + 1;
            else right = middle - 1;
        }

        return - 1;
    }

    public static void main(String[] args) {

    }
}
