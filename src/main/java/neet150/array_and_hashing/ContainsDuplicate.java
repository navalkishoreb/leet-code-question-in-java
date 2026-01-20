package neet150.array_and_hashing;

import java.util.HashSet;
import java.util.Set;

/**
 * Given an integer array nums, return true if any value appears more than once in the array, otherwise return false.
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1, 2, 3, 3]
 * <p>
 * Output: true
 * <p>
 * Example 2:
 * <p>
 * Input: nums = [1, 2, 3, 4]
 * <p>
 * Output: false
 */
public class ContainsDuplicate {
    class Solution {
        public boolean hasDuplicate(int[] nums) {
            Set<Integer> set = new HashSet();
            for (int n : nums) {
                set.add(n);
            }
            return set.size() != nums.length;
        }
    }
}
