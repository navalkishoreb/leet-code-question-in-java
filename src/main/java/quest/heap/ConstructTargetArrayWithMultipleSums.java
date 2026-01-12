package quest.heap;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * You are given an array target of n integers. From a starting array arr consisting of n 1's, you may perform the following procedure :
 * <p>
 * let x be the sum of all elements currently in your array.
 * choose index i, such that 0 <= i < n and set the value of arr at index i to x.
 * You may repeat this procedure as many times as needed.
 * Return true if it is possible to construct the target array from arr, otherwise, return false.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: target = [9,3,5]
 * Output: true
 * Explanation: Start with arr = [1, 1, 1]
 * [1, 1, 1], sum = 3 choose index 1
 * [1, 3, 1], sum = 5 choose index 2
 * [1, 3, 5], sum = 9 choose index 0
 * [9, 3, 5] Done
 * Example 2:
 * <p>
 * Input: target = [1,1,1,2]
 * Output: false
 * Explanation: Impossible to create target array from [1,1,1,1].
 * Example 3:
 * <p>
 * Input: target = [8,5]
 * Output: true
 * <p>
 * <p>
 * Constraints:
 * <p>
 Constraints:

 n == target.length
 1 <= n <= 5 * 104
 1 <= target[i] <= 10^9
 */
public class ConstructTargetArrayWithMultipleSums {
    class Solution {
        public boolean isPossible(int[] target) {
            // “Can we repeatedly reduce the largest element by subtracting the sum of all others, until all elements become 1?”
            PriorityQueue<Long> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
            // sum needs to be long as each element can be 10^9 and can lead to int overflow
            long sum = 0;
            long prev;
            for (int i = 0; i < target.length; i++) {
                maxHeap.offer((long)target[i]);
                sum += target[i];
            }

            while (true) {
                long max = maxHeap.poll();
                long rest = sum - max;
                // target = [1, 1, 1, 1]
                // target = [1, 8]
                // target = [1, 1000000000]
                // rest == 1 for quicker exit
                if (max == 1 || rest == 1) {
                    return true;
                }
                // target = [1, 1, 1, 6]
                // target = [2]
                if (max <= rest || rest == 0) {
                    return false;
                }
                prev = (int) max % rest;
                if (prev == 0) {
                    prev = rest;
                }
                maxHeap.offer(prev);
                sum = rest + prev;
            }

        }
    }
}
