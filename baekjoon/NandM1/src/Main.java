import java.sql.Array;
import java.util.*;
import java.io.*;

public class Main {
    /*
    자연수 N과 M
    1~N까지 자연수 중에서 중복 없이 M개를 고른 길이가 M인 수열을 모두 구하는 프로그램 작성
     */

    static int N;
    static int M;

    static int[] arr;
    static int[] visited;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        // 순열을 구하되 M개가 넘으면 cut하는 방식으로 백트래킹
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        sb  = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[M];
        visited = new int[N+1];

        recursive(0);

        String ans = sb.toString().trim();
        System.out.println(ans);
    }

    // idx: 결과 배열에 저장할 위치
    static void recursive(int idx) {
        // 기저 조건
        if(idx >= M) {
            for(int i = 0; i < arr.length; i++) {
                sb.append(arr[i] + " ");
            }
            sb.deleteCharAt(sb.length()-1);
            sb.append("\n");
            return;
        }

        // 재귀 부분
        for(int i = 1; i <= N; i++) {
            if(visited[i] == 1) continue; // 중복이면 cut

            // 원소 사용
            arr[idx] = i;
            visited[i] = 1;
            recursive(idx+1); // 다음 자리 판단

            // 사용하지 않을 경우
            visited[i] = 0;
        }
    }
}
