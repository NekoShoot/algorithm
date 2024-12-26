public class nth {
    static int[] members = {225, 220, 160, 160, 160, 100};

    int binarySearch(int target) {
        int left = 0;
        int right = members.length - 1;

        int ans = -1;

        while(left <= right) {
            int mid = (left + right) / 2;

            if(members[target] >= members[mid]) {
                right = mid - 1;
                ans = mid;
            }
            else left = mid + 1;
        }

        return ans;
    }

    public static void main(String[] args) {

    }
}
