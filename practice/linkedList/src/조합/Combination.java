package 조합;

public class Combination {
	static String[] materials = { "sangchu", "patty", "tomato", "cheese" };
	static int N, R; // 전체 재료의 수 N, 내가 뽑고 싶은 재료의 수 R
	static String[] sel; // 뽑은 재료들을 저장할 배열
	
	public static void main(String[] args) {
		N = 4;
		R = 2;
		sel = new String[R];
	}
	
	
	static void recursive_combination(int idx, int sidx) {
		// 기저 조건
		if(sidx == R) return;
		
		if(idx >= N) return;
		
		// 재귀 부분
		// 해당 재료를 사용
		sel[sidx] = materials[idx];
		recursive_combination(idx+1, sidx+1);
		
		// 해당 재료를 사용하지 않음
		sel[sidx] = null;
		recursive_combination(idx+1, sidx);
	}
}
