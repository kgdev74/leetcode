
public class solution3689 {
    public static long maxTotalValue(int[] nums, int k) {
        int maxVal = Integer.MIN_VALUE;
        int minVal = Integer.MAX_VALUE;

        for (int num : nums) {
            maxVal = Math.max(maxVal, num);
            minVal = Math.min(minVal, num);
        }

        return 1L * k * (maxVal - minVal);
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 2};
        int k = 2;

        System.out.println(maxTotalValue(nums, k)); // Output: 4
    }
}