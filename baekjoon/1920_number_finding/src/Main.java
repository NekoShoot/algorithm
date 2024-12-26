import java.util.*;
import java.io.*;

/*
1트 <<<< 시간 초과
2트 <<<< 가지치기 했는데도 시간초과
더 줄일 수 있는 부분 찾기
 */

public class Main {
    static int[] An;

    // n, n개의 정수
    // m, m개의 수
    // -2^^31 ~ 2^^31
    // m개의 수들이 A[n] 안에 존재하는 지 확인
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim()); // target 정수 개수

        StringTokenizer st = new StringTokenizer(br.readLine());
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        An = new int[n];
        for(int i = 0; i < n; i++) {
            An[i] = Integer.parseInt(st.nextToken());
        } // 배열 입력, 이 부분은 무조건 n 수행 해야됨. O(n)
        Arrays.sort(An); // 이분 탐색을 위한 오름차순 정렬, 무조건 수행해야 됨 O(logn)

        // m 받고 m마다 이분탐색 메소드 돌리기
        int m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

//        int[] Am = new int[m];
//        for(int i = 0; i < m; i++) {
//            int targetNum = Integer.parseInt(st.nextToken());
//
//            if(targetNum > An[An.length-1]) {
//                sb.append
//            }
//        }

        for(int i = 0; i < m; i++) { // 여기가 문제
            int Am = Integer.parseInt(st.nextToken());

            if(Am > An[An.length-1]) {
                bw.write("0\n");
                continue;
            } // 가지치기 1

            if(Am < An[0]) {
                bw.write("0\n");
                continue;
            } // 가지치기 2

            int ans = binarySearch(Am);
            bw.write(ans + "\n");
        }

        bw.flush();
        bw.close();
    }

    static int binarySearch(int m) {
        int ans = 0;

        int left = 0; // 탐색 시작 인덱스
        int right = An.length - 1; // 끝 인덱스

        while(left <= right) {
//            int mid = (left/2) + (right/2); // int 범위 넘지 않게 미리 나누기
            // 위 방법은 의도한 바대로 동작하지 않음. <<<<< 시간초과의 주범

            int mid = left + (right - left) / 2; // 이 방법이 안전함

            // m이 mid와 같으면 정답 출력
            if(An[mid] == m) return 1;

            // mid가 m보다 크면 오른쪽은 정답이 아님
            if(An[mid] > m) right = mid - 1;
            else left = mid + 1; // mid가 m보다 작으면 왼쪽은 정답이 아님
        }

        return ans;
    }
}
