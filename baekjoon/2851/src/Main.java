import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int sum = 0;
		int[] mushrooms = new int[10];
		for(int i = 0; i < 10; i++) {
			int height = sc.nextInt();
			mushrooms[i] = height;
			sum += height;
		}
		
		sum -= 100;
		
		for(int i = 0; i < 9; i++) {
			int befMush = 0;
			int aftMush = mushrooms[i];			
			
			if((100-befMush) >= (100-aftMush)) {
				break;
			}
			
			
		}
	}

}
