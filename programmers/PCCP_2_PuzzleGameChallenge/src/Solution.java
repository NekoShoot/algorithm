import java.util.*;
import java.io.*;

class Solution {
    /*
    n개의 퍼즐
    퍼즐 난이도 diff
    현재 퍼즐 소요 시간 time_cur
    이전 퍼즐 소요 시간 time_prev
    숙련도 level
    ====================
    diff <= level이면 time_cur만큼 시간 사용 -> 해결
    diff > level이면 diff - level번 틀림 -> 틀릴 때마다 time_cur만큼 시간 사용, time_prev만큼 시간 사용
    *** 이전 퍼즐을 다시 풀 때는 이전 퍼즐의 난이도에 상관없이 틀리지 않음
    diff - level번 이후엔 time_cur 만큼 시간을 사용해 퍼즐 해결
    
    전체 제한 시간 limit
    limit 안에 퍼즐을 모두 해결하기 위한 숙련도(level)의 최솟값????
    diff, level, time_cur > 0
        
    1 ~ 100000 (diff 최댓값)범위에서 이분탐색
    첫 번째 퍼즐을 풀 때에는 틀리지 않는다고 가정(이전 퍼즐이 없기 때문에)
    */

    public int solution(int[] diffs, int[] times, long limit) {
        int answer = 0;

        answer += binarySearch(diffs, times, limit);

        return answer;
    }

    // binarySearch Logic: return answer level 1 ~ 100000
    int binarySearch(int[] diffs, int[] times, long limit) {
        int left = 1;
        int right = 100000;

        while(left <= right) {
            int mid = left + (right - left) / 2;

            // mid가 가능한 level 범위를 갈라줄 중간값
            if(puzzleSolving(mid, diffs, times, limit)) { // 가능하면, 오른쪽은 전부 정답이 아님
                right = mid - 1;
            } else { // 불가능 하면, 왼쪽은 전부 정답이 아님
                left = mid + 1;
            }
        }

        return left;
    }


    // Time Adding Logic(puzzle solving time)
    boolean puzzleSolving(int level, int[] diffs, int[] times, long limit) {
        long time = 0L;

        // 퍼즐 하나씩 풀어보기
        for(int i = 0; i < diffs.length; i++) {
            if(diffs[i] <= level) {
                time += times[i];
            } else {
                int errorTimes = (diffs[i] - level);
                int time_prev = times[i-1];
                time += errorTimes * (time_prev + times[i]) + times[i];
            }
        } // 퍼즐 풀이 종료

        if(time <= limit) return true;
        else return false;
    }
}