package neet150.array_and_hashing;

/*
Given an integer array nums, return an array output where output[i] is the product of all the elements of nums except nums[i].

Each product is guaranteed to fit in a 32-bit integer.

Follow-up: Could you solve it in O(n) time without using the division operation?

Example 1:

Input: nums = [1,2,4,6]

Output: [48,24,12,8]
Example 2:

Input: nums = [-1,0,1,2,3]

Output: [0,-6,0,0,0]
Constraints:

2 <= nums.length <= 1000
-20 <= nums[i] <= 20
 */
public class ProductOfArrayExceptSelf {
    class Solution {
        public int[] productExceptSelf(int[] nums) {
            int n = nums.length;
            int[] prefix = new int[n];
            int[] suffix = new int[n];
            int[] res = new int[n];

            prefix[0] = nums[0];
            for (int i = 1; i < n; i++) {
                prefix[i] = prefix[i - 1] * nums[i];
            }
            suffix[n - 1] = nums[n - 1];
            for (int i = n - 2; i >= 0; i--) {
                suffix[i] = suffix[i + 1] * nums[i];
            }

            for (int i = 0; i < n; i++) {
                int pre = (i - 1) >= 0 ? prefix[i - 1] : 1;
                int suf = (i + 1) >= n ? 1 : suffix[i + 1];
                res[i] = pre * suf;
            }
            return res;
        }
    }

}
