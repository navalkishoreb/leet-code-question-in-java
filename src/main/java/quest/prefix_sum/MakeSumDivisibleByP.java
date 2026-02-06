package quest.prefix_sum;

import java.util.HashMap;
import java.util.Map;

/**
 * Given an array of positive integers nums, remove the smallest subarray (possibly empty) such that the sum of the remaining elements is divisible by p.
 * It is not allowed to remove the whole array.
 * <p>
 * Return the length of the smallest subarray that you need to remove, or -1 if it's impossible.
 * <p>
 * A subarray is defined as a contiguous block of elements in the array.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [3,1,4,2], p = 6
 * Output: 1
 * Explanation: The sum of the elements in nums is 10, which is not divisible by 6.
 * We can remove the subarray [4], and the sum of the remaining elements is 6, which is divisible by 6.
 * Example 2:
 * <p>
 * Input: nums = [6,3,5,2], p = 9
 * Output: 2
 * Explanation: We cannot remove a single element to get a sum divisible by 9.
 * The best way is to remove the subarray [5,2], leaving us with [6,3] with sum 9.
 * Example 3:
 * <p>
 * Input: nums = [1,2,3], p = 3
 * Output: 0
 * Explanation: Here the sum is 6. which is already divisible by 3. Thus we do not need to remove anything.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^9
 * 1 <= p <= 10^9
 */
public class MakeSumDivisibleByP {
    class Solution {
        public int minSubarray(int[] nums, int p) {
            // need long sum overflow
            // [1000000000,1000000000,1000000000], p =3
            long sum = 0;
            for (int n : nums) {
                sum += n;
            }
            int rem = (int) (sum % p);
            if (rem == 0) {
                // no removal required
                // nums = [3, 6], p =3
                // nums = [5, 5, 5, 5], p = 5
                return 0;
            }
            long prefixSum = 0;
            Map<Integer, Integer> map = new HashMap<>();
            // add base case when subarray can start from 0
            // nums = [4,1,2], p = 3
            map.put(0, -1);
            int ans = nums.length;
            for (int i = 0; i < nums.length; i++) {
                prefixSum += nums[i];
                int mod = (int) (prefixSum % p);
                // cannot use math.abs becasue of cyclic nature
                // -2 rem should give p-2 value
                int need = (mod - rem + p) % p;
                if (map.containsKey(need)) {
                    ans = Math.min(ans, i - map.get(need));
                }
                // what happens when mod is duplicate ?
                // we are overwriting because we want j to be closer to i
                // to get the shortest array
                // nums = [1, 2, 3, 4], p = 3
                map.put(mod, i);
            }
            // p larger than whole sum
            // nums = [1, 2], p = 4
            //
            // rem = 3 % 4 = 3
            // the only array with mode 3 is whole array
            // nums = [5], p = 7
            return ans == nums.length ? -1 : ans;
        }
    }
}
