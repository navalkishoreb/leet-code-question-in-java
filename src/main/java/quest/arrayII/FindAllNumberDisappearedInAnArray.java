package quest.arrayII;

/**
 * Given an array nums of n integers where nums[i] is in the range [1, n], return an array of all the integers in the range [1, n] that do not appear in nums.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [4,3,2,7,8,2,3,1]
 * Output: [5,6]
 * Example 2:
 * <p>
 * Input: nums = [1,1]
 * Output: [2]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * n == nums.length
 * 1 <= n <= 10^5
 * 1 <= nums[i] <= n
 * <p>
 * <p>
 * Follow up: Could you do it without extra space and in O(n) runtime? You may assume the returned list does not count as extra space.
 */

import java.util.List;
import java.util.ArrayList;

public class FindAllNumberDisappearedInAnArray {

    class Solution {
        public List<Integer> findDisappearedNumbers(int[] nums) {
            int[] data = new int[nums.length + 1];
            List<Integer> res = new ArrayList<>();

            for (int num : nums) {
                data[num]++;
            }

            for (int i = 1; i < nums.length + 1; i++) {
                if (data[i] == 0) {
                    res.add(i);
                }
            }
            return res;
        }
    }

    class Solution2 {
        // without extra space
        public List<Integer> findDisappearedNumbers(int[] nums) {
            List<Integer> res = new ArrayList<>();

            for (int i = 0; i < nums.length; i++) {
                int index = Math.abs(nums[i]) - 1;
                if (nums[index] > 0) {
                    nums[index] = -nums[index];
                }
            }

            for (int i = 0; i < nums.length; i++) {
                if (nums[i] > 0) {
                    res.add(i + 1);
                }
            }
            return res;
        }
    }
}
