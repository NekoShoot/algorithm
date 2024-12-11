import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 9184번: 신나는 함수 실행
public class Main {
    static int[][][] dpArr = new int[21][21][21];

    public static void main(String[] args) throws IOException {

        /*
        제약
        -50 ≤ a, b, c ≤ 50

        a, b, c 정수 입력, 한 줄에 하나씩
         */
//        System.setIn(new FileInputStream("./input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // dp 배열 초기값 설정
        for (int i = 0; i <= 20; i++) {
            for (int j = 0; j <= 20; j++) {
                for (int k = 0; k <= 20; k++) {
                    if (i == 0 || j == 0 || k == 0) {
                        dpArr[i][j][k] = 1;
                    }
                }
            }
        }

        // 배열 값 채우기
        for (int i = 1; i <= 20; i++) {
            for (int j = 1; j <= 20; j++) {
                for (int k = 1; k <= 20; k++) {
                    fillDpArr(i, j, k);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        while(true) {
            String line = validLine(br);

            StringTokenizer st = new StringTokenizer(line, " ");

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            // 끝이면
            if (a == -1 && b == -1 && c == -1) {
                break;
            }

            // 1번 조건
            if (a <= 0 || b <= 0 || c <= 0) {
                sb.append("w(").append(a).append(", ")
                        .append(b).append(", ")
                        .append(c).append(") = ")
                        .append(1).append("\n");
            } else {
                // 2번 조건
                if (a > 20 || b > 20 || c > 20) {
                    sb.append("w(").append(a).append(", ")
                            .append(b).append(", ")
                            .append(c).append(") = ")
                            .append(dpArr[20][20][20]).append("\n");
                } else {
                    sb.append("w(").append(a).append(", ")
                            .append(b).append(", ")
                            .append(c).append(") = ")
                            .append(dpArr[a][b][c]).append("\n");
                }
            }
        }

        String result = sb.toString().trim();
        System.out.println(result);
    }

    static void fillDpArr(int a, int b, int c) {
        if(a < b && b < c) {
            dpArr[a][b][c] = dpArr[a][b][c-1] + dpArr[a][b-1][c-1] - dpArr[a][b-1][c];
        } else {
            dpArr[a][b][c] = dpArr[a-1][b][c] + dpArr[a-1][b-1][c] + dpArr[a-1][b][c-1] - dpArr[a-1][b-1][c-1];
        }
    }

    static String validLine(BufferedReader br) throws IOException {
        String line;
        while((line = br.readLine()) != null) {
            line = line.trim();

            if(!line.isEmpty()) {
                return line;
            }
        }
        return null;
    }
}
