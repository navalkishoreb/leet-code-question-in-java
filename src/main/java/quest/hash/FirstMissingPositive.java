package quest.hash;

/**
 * Given an unsorted integer array nums. Return the smallest positive integer that is not present in nums.
 * <p>
 * You must implement an algorithm that runs in O(n) time and uses O(1) auxiliary space.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,0]
 * Output: 3
 * Explanation: The numbers in the range [1,2] are all in the array.
 * Example 2:
 * <p>
 * Input: nums = [3,4,-1,1]
 * Output: 2
 * Explanation: 1 is in the array but 2 is missing.
 * Example 3:
 * <p>
 * Input: nums = [7,8,9,11,12]
 * Output: 1
 * Explanation: The smallest positive integer 1 is missing.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 1605
 * -2^31 <= nums[i] <= 2^31 - 1
 */
public class FirstMissingPositive {
    class Solution {
        public int firstMissingPositive(int[] nums) {
            int n = nums.length;
            for (int i = 0; i < nums.length; i++) {
                // objective to place value i at (i-1)th index
                while (nums[i] >= 1 && nums[i] <= n && nums[nums[i] - 1] != nums[i]) {
                    swap(nums, i, nums[i] - 1);
                }

            }
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] != i + 1) {
                    return i + 1;
                }
            }
            return n + 1;
        }

        private void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }
}
