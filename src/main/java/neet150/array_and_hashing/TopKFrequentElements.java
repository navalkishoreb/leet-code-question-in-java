package neet150.array_and_hashing;

import java.util.*;

/*
Given an integer array nums and an integer k, return the k most frequent elements within the array.

The test cases are generated such that the answer is always unique.

You may return the output in any order.

Example 1:

Input: nums = [1,2,2,3,3,3], k = 2

Output: [2,3]
Example 2:

Input: nums = [7,7], k = 1

Output: [7]
Constraints:

1 <= nums.length <= 10^4.
-1000 <= nums[i] <= 1000
1 <= k <= number of distinct elements in nums.
 */
public class TopKFrequentElements {
    class Node {
        private int key;
        private int count;

        public Node(int key, int count) {
            this.key = key;
            this.count = count;
        }

        public int getKey() {
            return this.key;
        }

        public int getCount() {
            return this.count;
        }
    }

    class Solution {
        public int[] topKFrequent(int[] nums, int k) {
            Queue<Node> minHeap = new PriorityQueue<>((a, b) -> Integer.compare(a.getCount(), b.getCount()));
            Map<Integer, Integer> freqMap = new HashMap<>();
            for (int n : nums) {
                freqMap.put(n, freqMap.getOrDefault(n, 0) + 1);
            }
            for (Map.Entry<Integer, Integer> e : freqMap.entrySet()) {
                minHeap.offer(new Node(e.getKey(), e.getValue()));
                if (minHeap.size() > k) {
                    minHeap.poll();
                }
            }
            int[] res = new int[k];
            int i = 0;
            for (Node node : minHeap) {
                res[i++] = node.getKey();
            }
            return res;
        }

    }

    class Solution2 {
        public int[] topKFrequent(int[] nums, int k) {

            Map<Integer, Integer> freqMap = new HashMap<>();
            for (int n : nums) {
                freqMap.put(n, freqMap.getOrDefault(n, 0) + 1);
            }
            Queue<Integer> minHeap = new PriorityQueue<>(Comparator.comparingInt(freqMap::get));
            for (Integer key : freqMap.keySet()) {
                minHeap.offer(key);
                if (minHeap.size() > k) {
                    minHeap.poll();
                }
            }
            int[] res = new int[k];
            for (int i = k - 1; i >= 0; i--) {
                res[i] = minHeap.poll();
            }
            return res;
        }

    }

}
