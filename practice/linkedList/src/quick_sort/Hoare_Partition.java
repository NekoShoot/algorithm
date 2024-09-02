package quick_sort;

public class Hoare_Partition {
	static int[] arr = { 7, 5, 13, 2, 79, 12, 35, 42 };
	static int N = arr.length; // 배열의 길이
	
	public static void main(String[] args) {
		
	}
	
	// 분할
	static void quickSort(int left, int right) {
		
	}
	
	// 정복
	// pivot은 배열 가장 왼쪽
	static int hoare_partition(int left, int right) {
		int L = left + 1; // pivot을 제외한 배열에서 가장 왼쪽 값
		int R = right; // 배열의 가장 오른쪽 값
		
		int pivot = arr[left];
		
		while(L <= R) {
			// L은 정방향으로 pivot보다 큰 값
			while(L <= R && arr[L] <= pivot) {
				
			}
			
			// R은 역방향으로 pivot보다 작은 값
		}
		
		return R;
	}
}
