package 병합정렬;

import java.util.Arrays;

public class Merge_Sort {
	static int[] arr = {7, 1, 3, 13, 25, 9};
	static int[] sorted = new int[arr.length];
	
	// 분할
	// left: 왼쪽 배열의 시작점, right: 오른쪽 배열의 끝점
	static void mergeSort(int left, int right) {
		if(left < right) {
			int mid = (left + right) / 2;
			mergeSort(left, mid); // 논리적 왼쪽 배열 분할
			mergeSort(mid+1, right); // 논리적 오른쪽 배열 분할
			merge(left, mid, right);
		}
	}
	
	
	// 병합
	static void merge(int left, int mid, int right) {
		int L = left; // 왼쪽 시작점
		int R = mid+1; // 오른쪽 시작점
		
		int idx = left; // 새 배열에서 왼쪽부터 채워넣을 인덱스
		
		while(L <= mid && R <= right) {
			if(arr[L] <= arr[R]) {
				sorted[idx++] = arr[L++];
			} else {
				sorted[idx++] = arr[R++];
			}			
		}
		
		if(L <= mid) {
			for(int i = L; i <= mid; i++) {
				sorted[idx++] = arr[i];
			}
		} else {
			for(int i = R; i <= right; i++) {
				sorted[idx++] = arr[i];
			}
		}	
				
		for(int i = left; i <= right; i++) {
			arr[i] = sorted[i];
		}
			
	}
	
	public static void main(String[] args) {
		mergeSort(0, arr.length-1);
		System.out.println(Arrays.toString(arr));
	}
}
