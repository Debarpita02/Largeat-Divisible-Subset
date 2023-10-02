import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        int n = nums.length;
        if (n == 0) return new ArrayList<>();

        Arrays.sort(nums);

        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        int maxSize = 1;
        int maxIndex = 0;

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] % nums[j] == 0) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            if (dp[i] > maxSize) {
                maxSize = dp[i];
                maxIndex = i;
            }
        }

        List<Integer> result = new ArrayList<>();
        int currentSize = maxSize;
        int currentElement = nums[maxIndex];

        for (int i = maxIndex; i >= 0; i--) {
            if (currentSize == dp[i] && currentElement % nums[i] == 0) {
                result.add(nums[i]);
                currentSize--;
                currentElement = nums[i];
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums1 = {1, 2, 3};
        System.out.println(solution.largestDivisibleSubset(nums1)); // Output: [1, 2] or [1, 3]
        int[] nums2 = {1, 2, 4, 8};
        System.out.println(solution.largestDivisibleSubset(nums2)); // Output: [1, 2, 4, 8]
    }
}
