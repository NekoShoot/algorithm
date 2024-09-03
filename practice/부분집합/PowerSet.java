package 부분집합;

public class PowerSet {
	static String[] materials = { "danmuji", "ham", "oi", "sugeumchi" };
	static int N = 4; // 재료 개수
	static int[] sel = new int[N];
	
	public static void main(String[] args) {
		// 공집합부터 전체집합까지
		// 비트마스킹
		
		for(int i = 0; i < (1 << N); i++) {
			String tmp = "";
			
			for(int j = 0; j < N; j++) {
				if((i & (1 << j)) != 0) {
					tmp += materials[j] + "";
				}
			}			
		}
	}
}
