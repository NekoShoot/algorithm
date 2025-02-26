//TIP 코드를 <b>실행</b>하려면 <shortcut actionId="Run"/>을(를) 누르거나
// 에디터 여백에 있는 <icon src="AllIcons.Actions.Execute"/> 아이콘을 클릭하세요.
// 3개의 시계
// x는 최대 480분 == 8시간
// x분 빠른 시계
// 정확한 시계
// x분 느린 시계

import java.util.*;
import java.io.*;

public class Main {
    static int[][] watches;
    static int[] watchMinutes;

    public static void main(String[] args) throws IOException {
        // 테케 받기
        System.setIn(new FileInputStream("./src/input.txt"));


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken()); // 테케 < 100

        for(int tc = 0; tc < T; tc++) {
            watches = new int[3][2]; // 초기화
            watchMinutes = new int[3];
            st = new StringTokenizer(br.readLine());

            // 시계 시간 저장
            for(int i = 0; i < 3; i++) {
                parse(st.nextToken(), i);
            }

            // 오름차순으로 정렬
            Arrays.sort(watchMinutes);

            int correct = calculate();
            String answer = "";
            if(correct == -1) answer = "Look at the sun"; // 불가능
            else {
                answer = output(correct / 60, correct % 60); // 정답 형태로 가공
            }










        }

    }

    // 시간 파싱
    static void parse(String time, int idx) {
        int hour = Integer.parseInt(time.split(":")[0]);
        int minute = Integer.parseInt(time.split(":")[1]);

        watches[idx][0] = hour;
        watches[idx][1] = minute;

        watchMinutes[idx] = hour * 60 + minute;
    }

    static int convert(int hour, int minute) {
        return hour * 60 + minute;
    }

    // 3가지 시간 비교
    static int calculate() {
        int a = watchMinutes[0], b = watchMinutes[1], c = watchMinutes[2];
        // 1개는 x <= 480 범위 내에서 더 빠른 시간 (8시간)
        // 1개는 x분 더 느린 시간

        // 1 ~ 12시, 0~59분
        // 12시 -> 720분
        // 5 - 7시간 => 10시
        // 12 4 8 <- 12도, 4도, 8도 정답이 될 수 있기 때문에 impossible

        System.out.println(a  + " " + b + " " + c);
        /*
        300 720 600
        719 750 61
        720 240 480
         */

        // 정답을 알 수 없는 경우
        if(b - a == c - b && c - b == 4) return -1;

        // a가 정확한 시간일 경우
        // 1. c - a가 맞는 시간일 경우
        // 2. b - a가 맞는 시간일 경우

        // b가 정확한 시간일 경우


        // c가 정확한 시간일 경우


        return 0;
    }



    // 출력 답안 양식
    static String output(int hour, int minute) {
        StringBuilder sb = new StringBuilder();
        sb.append("The correct time is ");
        sb.append(hour).append(":");
        if(minute < 10) {
            sb.append(0).append(minute);
        } else {
            sb.append(minute);
        }

        return sb.toString();
    }
}