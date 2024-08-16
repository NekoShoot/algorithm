package 삼성시_버스노선;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("./src/삼성시_버스노선/input.txt"));
        /*
            1~5000 버스 정류장 번호
            노선 N개
            i번 -> A_i이상 B_i 이하 모든 정류장
            P개 정류장에 대해 각 정류장에 몇 개의 버스 노선이 다니는 지 구하기

            - 테케 T
            - 각 테케 첫 줄 정수 N (1 ≤ N ≤ 500)
            - N개 줄의 i번째 줄 정수 A_i, B_I(1 ≤ 둘 다 ≤ 5,000)  사이 공백 있음
            - 다음 줄 P (1 ≤ P ≤ 500)
            - 다음 P개의 줄의 j번째 줄 C_j(1 ≤ C_j ≤ 5,000)

            - P개의 정수 공백 하나로 구분해 출력
            - j번째 정수는 C_j번 버스 정류장을 지나는 버스 노선의 개수
         */
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        int T = sc.nextInt(); // 테케 개수
        for(int test_case = 1; test_case <= T; test_case++) {
            int N = sc.nextInt(); // 버스 노선 개수
            int[] busStations = new int[5001]; // 버스 노선을 지나는 정류장들

            // A_i B_i -> 버스 노선
            for(int i = 0; i < N; i++) {
                int Ai = sc.nextInt();
                int Bi = sc.nextInt();

                // Ai이상 Bi이하인 모든 정류장을 다님
                for(int j = Ai-1; j <= Bi-1; j++) {
                    busStations[j]++;                    
                }
            }

            int P = sc.nextInt(); // 버스 정류장 개수
            for(int j = 0; j < P; j++) {
                int Cj = sc.nextInt(); // 버스 정류장 번호
                sb.append(busStations[Cj - 1]).append(" ");

            }

            sb.deleteCharAt(sb.length()-1);
            String result = sb.toString();
            System.out.printf("#%d %s\n", test_case, result);
            sb.delete(0, sb.length());
        }

    }
}
