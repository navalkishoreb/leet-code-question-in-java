package quest.prefix_sum;

/**
 * You are given an integer array nums.
 * You can choose exactly one index (0-indexed) and remove the element.
 * Notice that the index of the elements may change after the removal.
 * <p>
 * For example, if nums = [6,1,7,4,1]:
 * <p>
 * Choosing to remove index 1 results in nums = [6,7,4,1].
 * Choosing to remove index 2 results in nums = [6,1,4,1].
 * Choosing to remove index 4 results in nums = [6,1,7,4].
 * An array is fair if the sum of the odd-indexed values equals the sum of the even-indexed values.
 * <p>
 * Return the number of indices that you could choose such that after the removal, nums is fair.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [2,1,6,4]
 * Output: 1
 * Explanation:
 * Remove index 0: [1,6,4] -> Even sum: 1 + 4 = 5. Odd sum: 6. Not fair.
 * Remove index 1: [2,6,4] -> Even sum: 2 + 4 = 6. Odd sum: 6. Fair.
 * Remove index 2: [2,1,4] -> Even sum: 2 + 4 = 6. Odd sum: 1. Not fair.
 * Remove index 3: [2,1,6] -> Even sum: 2 + 6 = 8. Odd sum: 1. Not fair.
 * There is 1 index that you can remove to make nums fair.
 * Example 2:
 * <p>
 * Input: nums = [1,1,1]
 * Output: 3
 * Explanation: You can remove any index and the remaining array is fair.
 * Example 3:
 * <p>
 * Input: nums = [1,2,3]
 * Output: 0
 * Explanation: You cannot make a fair array after removing any index.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 104
 *
 */
public class WayToMakeFairArray {
    class Solution {
        public int waysToMakeFair(int[] nums) {
            long totalOdd = 0;
            long totalEven = 0;
            for (int i = 0; i < nums.length; i++) {
                if (i % 2 == 0) {
                    totalEven += nums[i];
                } else {
                    totalOdd += nums[i];
                }
            }

            long prefixEven = 0;
            long prefixOdd = 0;
            int count = 0;
            for (int i = 0; i < nums.length; i++) {
                // we are trying to remove this ith element
            /*
            prefixEven
            → even indices before i

            (totalOdd - prefixOdd)
            → odd indices after i
            → they shift → become even

            If i is odd
            → nums[i] was counted in totalOdd
            → must subtract it
            */
                long newTotalEven = prefixEven + (totalOdd - prefixOdd);
                long newTotalOdd = prefixOdd + (totalEven - prefixEven);
                if (i % 2 == 0) {
                    newTotalOdd -= nums[i];
                    prefixEven += nums[i];
                } else {
                    newTotalEven -= nums[i];
                    prefixOdd += nums[i];
                }

                if (newTotalEven == newTotalOdd) count++;

            }
            return count;

        }
    }
}
