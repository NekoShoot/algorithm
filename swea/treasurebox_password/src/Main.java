import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("./src/input.txt"));
        /*
        1. Array 활용해서 숫자 삽입
        2. enqueue(dequeue())로 회전
        3. hashSet으로 중복제거
        4. 16 -> 10진수로 변경
        5. 내림차순 정렬
        6. K번째 출력
         */
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt(); // 테케 개수
        for(int test_case = 1; test_case <= T; test_case++) {
            int N = sc.nextInt(); // 숫자 총 개수
            int K = sc.nextInt(); // K번째 수
            String numbers = sc.next(); // 숫자들(공백 없음)

            int numPerLine = N/4; // 한 변당 할당되는 숫자 개수
            char[] numberArr = new char[N];

            for(int i = 0; i < N; i++) {
                char number = numbers.charAt(i);
                numberArr[i] = number;
            }

//           Set<Integer> numberSet = new HashSet<>(); // 숫자 집합
            Set<String> strSet = new HashSet<>();
//            List<Integer> tenDigitNumbers = new ArrayList<>();
            String numStr = "";
            for(int i = 0; i < N/4; i++) { // 뚜껑 회전하기(시계방향 -> 맨 뒤 인자를 빼서 맨 앞으로
                for(int j = 0; j < N; j++) { // 한 변씩 읽어서 숫자 만들기
                    if(j != 0 && j % (numPerLine) == 0) {
//                        int tenDigit = sixteenToTenDigit(numStr);
//                        numberSet.add(tenDigit);
                        strSet.add(numStr);
                        numStr = "";
                    }

                    numStr += numberArr[((N-i) + j) % N]; // 시계방향 회전할 때마다 -1씩, 숫자 읽을 땐 +1씩 해줘야하니까

                    if(j == N-1) {
//                        int tenDigit = sixteenToTenDigit(numStr);
//                        numberSet.add(tenDigit);
                        strSet.add(numStr);
                        numStr = "";
                    }
                }
            }

            // 중복 수동 제거
//            for(int i = tenDigitNumbers.size()-1; i >= 1; i--) {
//                int current = tenDigitNumbers.get(i);
//                for(int j = i-1; j >= 0; j--) { // 역방향 순회
//                    int next = tenDigitNumbers.get(j);
//                    if(current == next) tenDigitNumbers.remove(j);
//                }
//            }

            // reverse sort
            // List<Integer>라서 Collections.sort(list, Comparator.reverseOrder()) 사용 가능!
//            Collections.sort(tenDigitNumbers, Comparator.reverseOrder());
//            int result = tenDigitNumbers.get(K-1);


            // set을 arr로 변경
            String[] tenDigitNumStr = strSet.toArray(new String[0]);
            int[] tenDigitNumbers = new int[tenDigitNumStr.length];
            for(int i = 0; i < tenDigitNumStr.length; i++) {
                tenDigitNumbers[i] = sixteenToTenDigit(tenDigitNumStr[i]);
            }

            reverseSort(tenDigitNumbers);
//            System.out.println(Arrays.toString(tenDigitNumbers));

//            Integer[] tenDigitArr = numberSet.toArray(new Integer[0]);
            // Integer class라서 Comparator.reverseOrder()를 쓸 수 있겠네??
//            Arrays.sort(tenDigitArr, Comparator.reverseOrder()); // 내림차순 정렬

//            int[] tenDigitNumbers = new int[tenDigitArr.length];
//            for(int i = 0; i < tenDigitArr.length; i++) {
//                tenDigitNumbers[i] = tenDigitArr[i].intValue();
//            }

//            System.out.println(Arrays.toString(tenDigitNumbers));

            int result = tenDigitNumbers[K-1];
            System.out.printf("#%d %d\n", test_case, result);

        }
    }

    static void reverseSort(int[] arr) {
        // 선택 정렬
        // 최대값의 idx 찾기
        for(int i = 0; i < arr.length; i++) {
            int maxIdx = i;
            for(int j = i+1; j < arr.length; j++) {
                if(arr[maxIdx] < arr[j]) maxIdx = j;
            }

            // swop
            int tmp = arr[i];
            arr[i] = arr[maxIdx];
            arr[maxIdx] = tmp;
        }
    }

    // A: 10, B: 11, C: 12, D: 13, E: 14
    static int sixteenToTenDigit(String number) {
        int result = 0; // 10진수로 변경된 최종 결과값 저장

        // 역방향 순회로 곱해서 더해주기
        for(int i = number.length()-1; i >= 0; i--) {
            char num = number.charAt(i);
            // 0, 1 ,2 ... length-1
            int multiple = (int) Math.pow(16, (number.length()-1) - i);
            if('0' <= num && num <= '9') {
                int tenDigitNum = (num - '0') * multiple;
                result += tenDigitNum;

            } else if(num == 'A') {
                result += 10 * multiple;

            } else if(num == 'B') {
                result += 11 * multiple;

            } else if(num == 'C') {
                result += 12 * multiple;

            } else if(num == 'D') {
                result += 13 * multiple;

            } else if(num == 'E') {
                result += 14 * multiple;

            } else if(num == 'F') {
                result += 15 * multiple;
            }

        }
        
        return result;
    }

}