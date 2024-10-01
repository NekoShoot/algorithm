import java.util.*;
import java.io.*;

public class Main {
    static int minClick; // 버튼 최소 횟수

    static int N; // 이동하려고 하는 채널
    static int M; // 고장난 버튼 개수
    static int[] broken;

    static String validLine(BufferedReader br) throws IOException {
        String line;
        while((line = br.readLine()) != null) {
            line = line.trim();
            if(!line.isEmpty()) {
                return line;
            }
        }
        return null;
    } // Valid Line Check

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("./src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String tmpLine = validLine(br); // 자릿수 계산을 위해 String으로 임시 저장
        N = Integer.parseInt(tmpLine);
        int startCh = 100; // 지금 수빈이가 보고 있는 채널

        if(N == startCh) { // 지금 보고 있는 채널과 같으면 0
            minClick = 0;
            System.out.println(minClick);
            return;
        }

        String line = validLine(br);
        M = Integer.parseInt(line);
        broken = new int[M];

        if(M == 0) {
            // 고장난 것이 없으면 N의 자릿수 or +-클릭만 한 것 중 작은 게 정답
            minClick = Math.min(tmpLine.length(), Math.abs(N - startCh));
            System.out.println(minClick);
            return;
        } else if(M == 10) { // 모두 고장났으면 +나 -로 밖에 못감
            minClick = Math.abs(N - 100);
            System.out.println(minClick);
            return;
        }

        line = validLine(br);
        StringTokenizer st = new StringTokenizer(line, " ");
        for(int i = 0; i < M; i++) {
            broken[i] = Integer.parseInt(st.nextToken());
        } // 고장난 채널들 채우기

        // Brute Force
        int targetCh = N;
        // 1. N에서 고장난게 없는 경우, -를 하는 경우와 +를 하는 경우로 나눈다.
            // 1-1. N에 고장난 버튼이 없는 경우 N의 자릿수가 정답 return
        if(isPossible(targetCh)) {
            minClick = tmpLine.length();
            System.out.println(minClick);
            return;
        }
            // 1-2. -를 하는 경우
            // 1-3. +를 하는 경우
        // 2. 그 중에서 고장난 버튼이 있는 지 검사한다.
        // 3. 없으면 그 숫자의 자릿수를 계산하고 거기에 N과의 차이를 더해서 정답으로 return
        // * -를 하는 경우에는 0미만으로 떨어지지 않도록 주의

        int tmpUnder = goUnderTargetCh(targetCh);
        System.out.println(tmpUnder);
        int tmpUpper = goUpperTargetCh(targetCh);
        System.out.println(tmpUpper);
        int tmpClick = Math.min(tmpUpper, tmpUnder);
        System.out.println(tmpClick);
        minClick = Math.min(tmpClick, Math.abs(N - startCh));

        // 41% 틀림
        // 무슨 반례?

        System.out.println(minClick);
    } // main

    // 가능한지 검사하는 메서드 (고장난게 없는지)
    static boolean isPossible(int channel) {
        String strCh = String.valueOf(channel);
        for(int i = 0; i < strCh.length(); i++) {
            int c = strCh.charAt(i) - '0';
            
            for(int j = 0; j < M; j++) {
                if(c == broken[j]) return false; // 고장난 게 있으면
            }
        } // O(6 * 9) == O(54)

        return true;
    } // Check if there is any broken button in channel

    // N에서 -로 가는 경우
    static int goUnderTargetCh(int targetCh) {
        while(targetCh >= 0) {
            targetCh--; // 이미 targetCh은 검사한 후
            if(isPossible(targetCh)) {
                int click = String.valueOf(targetCh).length(); // 자릿수 대입
                click += Math.abs(N - targetCh); // - 개수 더하기
                return click; // 가능
            }
        }
        return Integer.MAX_VALUE; // 0까지 내려갔는데도 불가능
    } // Go Under Target Channel

    static int goUpperTargetCh(int targetCh) {
        while(targetCh < 1000000) { // 9를 제외한 모든 숫자가 고장난 경우 최대 999999까지 가야됨
            targetCh++;
            if(isPossible(targetCh)) {
                int click = String.valueOf(targetCh).length(); // 자릿수 대입
                click += Math.abs(N - targetCh); // - 개수 더하기
                return click; // 가능
            }
        }
        return Integer.MAX_VALUE;
    }
    
}
