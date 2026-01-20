package leetcode;


/**
 * Given an array of intervals intervals where intervals[i] = [starti, endi], return the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.
 * <p>
 * Note that intervals which only touch at a point are non-overlapping. For example, [1, 2] and [2, 3] are non-overlapping.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: intervals = [[1,2],[2,3],[3,4],[1,3]]
 * Output: 1
 * Explanation: [1,3] can be removed and the rest of the intervals are non-overlapping.
 * Example 2:
 * <p>
 * Input: intervals = [[1,2],[1,2],[1,2]]
 * Output: 2
 * Explanation: You need to remove two [1,2] to make the rest of the intervals non-overlapping.
 * Example 3:
 * <p>
 * Input: intervals = [[1,2],[2,3]]
 * Output: 0
 * Explanation: You don't need to remove any of the intervals since they're already non-overlapping.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= intervals.length <= 105
 * intervals[i].length == 2
 * -5 * 10^4 <= starti < endi <= 5 * 10^4
 */

import java.util.Arrays;
import java.util.Comparator;

public class NonOverlappingIntervals {
    class Solution {
        public int eraseOverlapIntervals(int[][] intervals) {

            int n = intervals.length;
            Arrays.sort(intervals, Comparator.comparingInt((int[] a) -> a[1]));
            // for(int i = 0; i < n; i++){
            //     System.out.print("["+intervals[i][0] +","+intervals[i][1]+"]");
            // }

            int overlapCount = 0;
            int[] prev = intervals[0];
            for (int i = 1; i < n; i++) {
                int[] current = intervals[i];
                if (prev[1] <= current[0]) {
                    // no overlap
                    // update prev to current
                    prev = current;

                } else {
                    // don't update prev
                    // current is ignored
                    overlapCount++;
                }

            }

            return overlapCount;

        }
    }
}
