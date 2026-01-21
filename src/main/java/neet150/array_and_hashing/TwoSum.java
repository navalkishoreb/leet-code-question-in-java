package neet150.array_and_hashing;

/*
  Given an array of integers nums and an integer target, return the indices i and j such that nums[i] + nums[j] == target and i != j.
  <p>
  You may assume that every input has exactly one pair of indices i and j that satisfy the condition.
  <p>
  Return the answer with the smaller index first.
  <p>
  Example 1:
  <p>
  Input:
  nums = [3,4,5,6], target = 7
  <p>
  Output: [0,1]
  Explanation: nums[0] + nums[1] == 7, so we return [0, 1].
  <p>
  Example 2:
  <p>
  Input: nums = [4,5,6], target = 10
  <p>
  Output: [0,2]
  Example 3:
  <p>
  Input: nums = [5,5], target = 10
  <p>
  Output: [0,1]
  Constraints:
  <p>
  2 <= nums.length <= 1000
  -10,000,000 <= nums[i] <= 10,000,000
  -10,000,000 <= target <= 10,000,000
  Only one valid answer exists.
 */

import java.util.HashMap;
import java.util.Map;

public class TwoSum {
    class Solution {
        public int[] twoSum(int[] nums, int target) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                int complement = target - nums[i];
                if (map.containsKey(complement)) {
                    return new int[]{map.get(complement), i};
                }
                map.put(nums[i], i);
            }
            return new int[]{-1, -1};
        }
    }

}
