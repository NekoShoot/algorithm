/*
월 화 수 목 금 토 일
8 9 10 11 12 13 14
1 2 3 4 5 6 7 0

% 7 == 6 || % 7 == 0 <- 주말

parse(): % 100 -> 분
        / 100 -> 시

check(): 
if( (startday + i) % 7 != 0 && != 6)
    parse(timelogs[i]) - parse(schedules[i]) <= 10
    return true;
    
else return false;   
*/

class Solution {
    public int solution(int[] schedules, int[][] timelogs, int startday) {
        int answer = 0;
        
        int n = schedules.length; // 직원 n 명
        
        
empl:     for(int i = 0; i < n; i++) {
            for(int j = 0; j < 7; j++) {
                if(!check(startday + j, schedules[i], timelogs[i][j])) continue empl;
                if(j == 6) answer++;
            }        
            
        }
        
        
        return answer;
    }
    
    // 시간 파싱
    int parsing(int time) {
        int hour = time / 100;
        int minute = time % 100;        
        
        return hour * 60 + minute; // 분으로 변환해서 반환
    }
    
    // 지각 여부 체크
    boolean check(int day, int schedule, int timelog) {
        
        
        if(day % 7 != 6 && day % 7 != 0) { // 주말이 아닌 경우
            if(parsing(timelog) - parsing(schedule) <= 10) { // 희망보다 10분 이하 
                
                return true; // 정상 출근
                
                
            } else return false; // 지각
            
        }
        
        
        return true; // 주말은 영향 x
    }
}