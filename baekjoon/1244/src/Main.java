import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./input.txt"));
		/* 알고리즘 구상
		 * 핵심 조건: 학생 성별이 남성이냐 여성이냐
		 * 스위치 개수 n <= 100
		 * 스위치 한 줄의 최대 개수 20
		 * 
		 * case 1. 남성일 경우 (gender == 1)
		 * 받은 숫자 x일 때, 스위치를 조작할 수 있는 개수 == n / x (몫)
		 * 반복문(for)을 통해 스위치를 저장한 배열 속에서 x의 배수 - 1번째(인덱스) 스위치들을 확인
		 * 0이면 1로, 1이면 0으로
		 * 
		 * case 2. 여성일 경우 (gender == 2)
		 * 받은 숫자 y일 때, 스위치를 저장한 배열 속에서 y-1번째(인덱스) 위치로 이동
		 * y-1이 20으로 나누어 떨어지는지(y-1 % 20 == 0) 검사
		 * 반복문(while)을 통해 -방향과 +방향으로 1씩 탐색하며 스위치 상태 비교
		 * 더 이상 같지 않으면 즉시 종료,
		 * 탐색한 위치 중 하나가 20으로 나누어 떨어지면서 좌우대칭을 만족하면 cnt를 올리지 않고 종료
		 * 그 때 탐색한 모든 위치의 스위치의 상태를 0이면 1로, 1이면 0으로
		 * (반복문을 돌릴 때 반복 횟수를 저장할 값을 만들기 cnt = 1; -> 
		 * 이걸 활용해서 인덱스 조절해서 탐색하고 -> 
		 * 멈췄을 때 횟수를 확인해서 y-1-cnt ~ y-1+cnt 범위 사이에 있는 스위치들 상태 바꾸기)
		 */ 		  
		
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		
		// 길이라 인덱스가 아님!!!
		int switchRows = n < 20 ? n : 20;
		// 20이면 1줄, 21이면 2줄, 99면 5줄, 100이면 5줄		
		int switchCols = ((n - 1) / 20) + 1;					
				
		// 23이면 (2, 1)
		// 스위치 상태 저장
		int[][] switchArr = new int[switchCols][switchRows];
out:	for(int i = 0; i < switchCols; i++) {
			for(int j = 0; j < switchRows; j++) {
				if(i == switchCols - 1 && j >= (n % 20)) {					
					break out;
				} 
				
				switchArr[i][j] = sc.nextInt();	
			}
		}		
		
		int studentNum = sc.nextInt();		
		
		// 학생 수 만큼 반복 시행
		for(int i = 0; i < studentNum; i++) {			
			int gender = sc.nextInt();			
			
			// case 1. 남자일 때
			if(gender == 1) {
				// 타겟 스위치 위치
				int targetSwitch = sc.nextInt();
				
				// 스위치 조작 개수
				int numberOfChangingSwitch = n / targetSwitch;	
				
				// 타겟 스위치 위치의 배수를 만들면서 스위치 상태 확인 후 변경!
				for(int j = 1; j < numberOfChangingSwitch + 1; j++) {
					int multiplied = j * targetSwitch;					
					// 스위치 위치를 rows, cols로 가공		
					// 인덱스라 위에 switchRows,Cols와는 다름!
					// 1(0, 0) 20(0, 19) 21(1, 0) 99(4, 18) 100(4, 19)
					int targetRows = ((multiplied - 1) / 20);
					int targetCols = multiplied <= 20 ? multiplied - 1 : (multiplied - 1) % 20;
																			
					// 0이면 1로 1이면 0으로
					if(switchArr[targetRows][targetCols] == 0) switchArr[targetRows][targetCols] = 1;	
						else if(switchArr[targetRows][targetCols] == 1) switchArr[targetRows][targetCols] = 0;
				}			
							
				System.out.println("남자의 결과는");
				System.out.println(Arrays.toString(switchArr[0]));
				
			} else if(gender == 2) { 
			//case 2. 여자일 때
				int targetSwitch = sc.nextInt(); 				
				
				// 1(0, 0) 20(0, 19) 21(1, 0) 99(4, 18) 100(4, 19)
				int targetRows = ((targetSwitch - 1) / 20);
				int targetCols = targetSwitch <= 20 ? targetSwitch - 1 : (targetSwitch - 1) % 20;				
								
				System.out.println("여자 동작 중");
				System.out.println(targetRows + ", " + targetCols);
				
				// target이 0번째거나 19번째 인덱스이면 종료
				if(targetCols == 0 || targetCols == 19) {
					if(switchArr[targetRows][targetCols] == 0) switchArr[targetRows][targetCols] = 1;
						else if(switchArr[targetRows][targetCols] == 1) switchArr[targetRows][targetCols] = 0;					
					continue;
				}								
				
				int cnt = 1;
				while (true) {
					int moveBack = switchArr[targetRows][targetCols - cnt];
					int moveForward = switchArr[targetRows][targetCols + cnt];
					
					// 좌우대칭이면
					if(moveBack == moveForward) {
						// targetCols - cnt == 0 || targetCols + cnt == 19일때
						// targetCols - cnt ~ targetCols + cnt 탐색 후 스위치 바꾸고 종료
						int sub = 2 * cnt + 1; // 정지했을 때의 좌우대칭인 전체 스위치 개수
						if(moveBack == 0 || moveForward == 19) {
							for(int j = 0; j < sub; j++) {
								if(switchArr[targetRows][targetCols - cnt + j] == 0) switchArr[targetRows][targetCols - cnt + j] = 1;
									else if(switchArr[targetRows][targetCols - cnt + j] == 1) switchArr[targetRows][targetCols - cnt + j] = 0;
							}			
							
							break;
						}
						
						cnt++;
						continue;
						
					} else { //좌우대칭이 아니면 target부터 targetCols - (cnt - 1) ~ targetCols + (cnt - 1)까지 탐색 후 스위치 바꾸고 종료 
						int sub = 2 * (cnt - 1) + 1; // 정지했을 때의 좌우대칭인 전체 스위치 개수
						for(int j = 0; j < sub; j++) {
							if(switchArr[targetRows][targetCols - (cnt - 1) + j] == 0) switchArr[targetRows][targetCols - (cnt - 1) + j] = 1;
								else if(switchArr[targetRows][targetCols - (cnt - 1) + j] == 1) switchArr[targetRows][targetCols - (cnt - 1) + j] = 0;
						}					
												
						break;
					}
					
				}
				
				
				
			}				
				
			
		}
		System.out.println("최종 스위치 상태는");
		System.out.println(Arrays.toString(switchArr[0]));		
		
		
out:	for(int i = 0; i < switchCols; i++) {
			for(int j = 0; j < switchRows; j++) {
				if(i == switchCols - 1 && j >= (n % 20)) {					
					break out;
				} 
				
				if(j < switchRows - 1) {				
					System.out.print(switchArr[i][j] + " ");					
				} else if(j == switchRows - 1 && i < switchCols - 1) {					
					System.out.print(switchArr[i][j]);
					System.out.println("");
				} else {
					System.out.print(switchArr[i][j]);
				}
					
			}
		}		



	}

}
