import java.util.*;

// 총 호출 15,000회 이하
class UserSolution {
    static List<Integer> adjSumIdx;
    static int[] table;
    static int start; // 카드 리스트에서 가장 왼쪽을 가리키는 인덱스
    static int end;
    static int joker; // 조커 값

    void init(int mJoker, int mNumbers[]) {
        joker = mJoker % 20; // joker 값 초기화

        // 한 쪽 방향으로 최대 5만개까지 카드가 놓여짐. 최악의 경우 5만5개까지
        // ==> 10만10개가 최대 허용 범위
        table = new int[100010];
        start = table.length / 2;

        for(int i = 0; i < mNumbers.length; i++) {
            table[start+i] = mNumbers[i]; // 첫 5장 놓기
        }

        end = start + mNumbers.length;
    }

    // 최대 10,000회 호출 ->
    void putCards(int mDir, int mNumbers[]) {
        switch (mDir) {
            case 0 ->  { // 왼쪽에 놓아야 하는 경우
                for(int i = mNumbers.length-1; i >= 0 ; i--) {
                    table[start-i] = mNumbers[i];
                }
                start -= mNumbers.length;
            }
            case 1 -> { // 오른쪽에 놓아야 하는 경우
                for(int i = 0; i < mNumbers.length; i++) {
                    table[end+i] = mNumbers[i];
                }
                end += mNumbers.length;
            }
        }
    }

    // 4개 카드 합 % 20 -> 1~19만 가능
    // mNum -> 기준 숫자(4개 합이 같아야 하는 목표 숫자)
    // 완탐 기준 최악의 경우 50005 * 4 * 50003번 돌아야 함
    int findNumber(int mNum, int mNth, int ret[]) {
        adjSumIdx = new ArrayList<>();

        // 완탐
        for(int i = start; i <= end - 3; i++) {

            int curr = table[i] % 20;
            if(curr == -1) curr = joker;

            // 인접한 오른쪽 3장 더하기
            for(int j = 1; j <= 3; j ++) {
                int next = table[i + j];
                if(next == -1) next = joker;
                curr += next % 20;
            }

            // curr이 sum이 됨
            if(curr % 20 == mNum) {
                adjSumIdx.add(i); // 그 값을 만족하는 인덱스를 저장(어차피 10만 배열 안에서 절대값이라 putCards에 영향x)
            }
            
            if(adjSumIdx.size() == mNth) { // 조건에 맞는 카드를 찾은 경우

                int idx = adjSumIdx.get(mNth-1);

//                System.out.println("joker => " + joker);
//                System.out.println("mNum => " + mNum);
//                System.out.println("mNth => " + mNth);
//                System.out.println("start => " + table[idx]);


                for(int k = 0; k < ret.length; k++) {
                    ret[k] = table[idx + k];
                }

//                System.out.println("ret => " + Arrays.toString(ret));

                return 1;
            }
        } // 모든 카드 확인 끝

        return 0; // 조건에 맞는 카드를 찾을 수 없는 경우
    }
    
    // 1~30 조커 값 변경
    void changeJoker(int mValue) {
        joker = mValue % 20;
    }
}