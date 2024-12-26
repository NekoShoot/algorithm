import java.util.*;
import java.io.*;

public class Main {
    // 첫줄 숫자카드 개수 N <=50만
    // 둘줄 n개의 숫자 카드 정수
    // -천만 ~ +천만
    // 셋줄 M <= 50만
    // 넷줄 M개 정수 -천만 ~ +천만

    // binarySearch
    // 1. target 보다 작은 값 중 가장 큰 위치 찾기(거기서 +1하면 target 넘버의 시작 idx)
    //      while left < right
    //      a. target <= mid -> right = mid - 1;
    //      b. target > mid -> left = mid;
    //      c. start = right + 1;
    // 2. target 보다 큰 값 중 가장 작은 위치 찾기(거기서 -1하면 target 넘버의 마지막 idx)
    //      a. target < mid -> right = mid;
    //      b. target >= mid -> left = mid + 1;
    //      c. end = left - 1;

    static int[] cardArr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine().trim()); // 숫자카드 개수
        cardArr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < N; i++) {
            cardArr[i] = Integer.parseInt(st.nextToken());
        } // 숫자 카드 입력
        Arrays.sort(cardArr); // 이분탐색을 위한 정렬

//        System.out.println(Arrays.toString(cardArr));

        int M = Integer.parseInt(br.readLine()); // target
        st = new StringTokenizer(br.readLine());

        // st 각 토큰에 대해 반복문 M만큼 돌려서 binarySearch
        for(int i = 0; i < M; i++) {
            int target = Integer.parseInt(st.nextToken());

            int start = bsStart(target); // target과 같은
            if(start == -1) {
                sb.append("0 ");
                continue;
            }

            int end = bsEnd(target);
            if(end == -1){
                sb.append("0 ");
                continue;
            }

            // ans + 1 해야 정답 개수 나옴
            int ans = 1 + (end - start);

//            System.out.println("start => " + start);
//            System.out.println("end => " + end);

            sb.append(ans).append(" ");
        }

        String answer = sb.toString().trim();
        System.out.print(answer);
    }

    static int bsStart(int target) {
        // 예외 처리
        if(target == cardArr[0]) return 0;
        if(target < cardArr[0]) return -1;

        int start = 1; // target보다 작은 수 중 가장 큰 수의 idx에서 +1을 해야 시작 idx가 나옴

        int left = 0;
        int right = cardArr.length-1;

        while(left <= right) {
            int mid = left + (right - left) / 2;

            if(cardArr[mid] >= target) right = mid - 1;
            else left = mid + 1;

        } // 이분 탐색

        // 반복문 종료 후 right 더해주기(target보다 작은 수 중 가장 큰 수의 idx)
        start += right;

//        System.out.println("start => " + start);
//        System.out.println(right);

        // 없을 경우 처리
        if(start >= cardArr.length || target != cardArr[start]) return -1;

        return start;
    }

    static int bsEnd(int target) {
        if(target == cardArr[cardArr.length-1]) return cardArr.length-1;
        if(target > cardArr[cardArr.length-1]) return -1;

        int end = -1; // target보다 큰 수 중 가장 작은 수의 idx에서 -1을 해야 마지막 idx가 나옴

        int left = 0;
        int right = cardArr.length - 1;

        while(left <= right) {
            int mid = left + (right - left) / 2;

            if(cardArr[mid] <= target) left = mid + 1;
            else right = mid - 1;
        }

        // 반복문 종료 후 left 더해주기
        end += left;

//        System.out.println("end => " + end);
        if(end < 0 || target != cardArr[end]) return -1;
        return end;
    }
}
