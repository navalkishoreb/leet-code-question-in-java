package quest.heap;

/**
 * You are given two integer arrays nums1 and nums2 sorted in non-decreasing order and an integer k.
 * <p>
 * Define a pair (u, v) which consists of one element from the first array and one element from the second array.
 * <p>
 * Return the k pairs (u1, v1), (u2, v2), ..., (uk, vk) with the smallest sums.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
 * Output: [[1,2],[1,4],[1,6]]
 * Explanation: The first 3 pairs are returned from the sequence: [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
 * Example 2:
 * <p>
 * Input: nums1 = [1,1,2], nums2 = [1,2,3], k = 2
 * Output: [[1,1],[1,1]]
 * Explanation: The first 2 pairs are returned from the sequence: [1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums1.length, nums2.length <= 105
 * -109 <= nums1[i], nums2[i] <= 109
 * nums1 and nums2 both are sorted in non-decreasing order.
 * 1 <= k <= 104
 * k <= nums1.length * nums2.length
 */


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class FindKPairsWithSmallestSum {
    class Solution {
        public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
            PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt((int[] a) -> a[2]));
            List<List<Integer>> res = new ArrayList<>();

            for (int i = 0; i < nums1.length; i++) {
                minHeap.add(new int[]{i, 0, nums1[i] + nums2[0]});
            }
            int i = 0;
            while (i != k) {
                int[] data = minHeap.poll();
                if(data == null) break;
                int u = data[0];
                int v = data[1];
                res.add(List.of(nums1[u], nums2[v]));
                if (v + 1 < nums2.length) {
                    minHeap.add(new int[]{u, v + 1, nums1[u] + nums2[v + 1]});
                }
                i++;

            }
            return res;

        }
    }
}
