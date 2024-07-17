import java.io.FileInputStream;
import java.util.Scanner;

public class Main {
	// 리모컨 버튼은 0~9와 +, -.
	// +를 누르면 보고 있는 채널에서 +1
	// -를 누르면 보고 있는 채널에서 -1
		// 채널 0에서 -를 누르면 채널 변하지 않음
	// 채널은 무한대만큼 있음
	// 현재 채널 100
	
	// 첫째줄 이동하려고 하는 채널 target = N (0<= N <= 500,000)
	// 둘째줄 고장난 버튼의 개수 M (0 <= M <= 10)
	// 셋째 줄에 고장난 버튼이 주어짐. 중복 x
	
	// N으로 이동하기 위해 버튼을 최소 몇 번 눌러야 하는지?
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./input.txt"));
		Scanner sc = new Scanner(System.in);
		
		// 임의의 테스트 케이스 개수 T
		int T = sc.nextInt();
		// 테스트 케이스 횟수 만큼 반복
		//for (int test_case = 1; test_case < T + 1; test_case++) {
			// 타겟 채널
			int target = sc.nextInt();
			// 고장난 버튼 개수
			int cntBroken = sc.nextInt();
			// 고장난 버튼들
			int[] brokenButtons = new int[cntBroken];
			// 최종 답 저장할 변수 생성
			int sum = 0;		
			
			
			// cntBorken이 없으면(고장난 게 없으면) target 채널 개수만 카운트해서 sum으로 출력
			
			
			for (int i = 0; i < cntBroken; i++) {
				brokenButtons[i] = sc.nextInt();
			}	
						
			// 1. target과 가장 가까운 가능한 채널 = nearest를 누른다 (개수 세야됨)
				// a. available button 배열 저장
			int[] ableButtons = new int[(10 - cntBroken)];
			
			// broken -> isBroken true로 바꿔서 continue로 쳐내고 나머지만 넣기!			
			int countBs = 0;
			for (int i = 0; i < 10; i++) {
				boolean isBroken = false;
				for(int j = 0; j < cntBroken; j++) {
					if (i != brokenButtons[j]) {
						continue;
					} else if (i == brokenButtons[j]){
						isBroken = true;
						countBs++;						
					}					
				}
				
				if (isBroken) {
					continue;
				} else {					
					ableButtons[(i - countBs)] = i;			
				}			
								
			}						
			
				// b. target의 가장 높은 자리수의 숫자와 가장 가까운(뺐을 때 절대값이 가장 적은) 숫자를 배열에서 탐색
			String nearest = "";
			
			
			// log10 활용해서 자릿수 세기 
			int digitNumber = (int) (Math.log10(target) + 1);
			int digit = digitNumber;
			// c. target의 이하 자리수의 숫자와 가장 가까운 숫자 탐색(반복)
			for (int i = 0; i < digitNumber; i++) {				
				int tenDigits = (int) Math.pow(10, (digit - 1));
				// target을 10^digit으로 나누고 남은 몫
				int digitCut = (int) target / tenDigits;			
				

				// 가장 가까운 수가 ableButtons에 없다면 오류처리
				
				
				//  각 ableButtons의 값들을 몫에서 빼면서 최소값 찾기
				int min = Math.abs(digitCut - ableButtons[0]);
				int minIdx = 0;
				for (int j = 1; j < ableButtons.length; j++) {
					int sub = Math.abs(digitCut - ableButtons[j]);
					
					if (sub < min) {
						min = sub;
						minIdx = j;
					}
				}	
				
				// 가장 가까운 숫자 저장
				nearest += minIdx + "";		
				
				// 
							
				// 이하 자리수 탐색을 위해 가장 높은 자리수 제거 + digit 초기화
				if (target != 0) {
					target = target % tenDigits;
					digit = (int) (Math.log10(target) + 1);
				}
				
			
			}			
						
						
				// d. 도출된 nearest의 자리수 계산 -> sum에 더하기
			
				
			
			// 2. abs(target - nearest) = sub 개수를 1에 필요했던 개수에 더한다.
			
			
			
		//}
		
		
		
		
	}

}
