package leetcode;

/**
 * You are given an integer eventTime denoting the duration of an event, where the event occurs from time t = 0 to time t = eventTime.
 * <p>
 * You are also given two integer arrays startTime and endTime, each of length n. These represent the start and end time of n non-overlapping meetings, where the ith meeting occurs during the time [startTime[i], endTime[i]].
 * <p>
 * You can reschedule at most k meetings by moving their start time while maintaining the same duration, to maximize the longest continuous period of free time during the event.
 * <p>
 * The relative order of all the meetings should stay the same and they should remain non-overlapping.
 * <p>
 * Return the maximum amount of free time possible after rearranging the meetings.
 * <p>
 * Note that the meetings can not be rescheduled to a time outside the event.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: eventTime = 5, k = 1, startTime = [1,3], endTime = [2,5]
 * <p>
 * Output: 2
 * <p>
 * Explanation:
 * <p>
 * <p>
 * <p>
 * Reschedule the meeting at [1, 2] to [2, 3], leaving no meetings during the time [0, 2].
 * <p>
 * Example 2:
 * <p>
 * Input: eventTime = 10, k = 1, startTime = [0,2,9], endTime = [1,4,10]
 * <p>
 * Output: 6
 * <p>
 * Explanation:
 * <p>
 * <p>
 * <p>
 * Reschedule the meeting at [2, 4] to [1, 3], leaving no meetings during the time [3, 9].
 * <p>
 * Example 3:
 * <p>
 * Input: eventTime = 5, k = 2, startTime = [0,1,2,3,4], endTime = [1,2,3,4,5]
 * <p>
 * Output: 0
 * <p>
 * Explanation:
 * <p>
 * There is no time during the event not occupied by meetings.
 * <p>
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= eventTime <= 109
 * n == startTime.length == endTime.length
 * 2 <= n <= 105
 * 1 <= k <= n
 * 0 <= startTime[i] < endTime[i] <= eventTime
 * endTime[i] <= startTime[i + 1] where i lies in the range [0, n - 2].
 */
public class RescheduleMeetingForMaximumFreeTime1 {
    class Solution {
        public int maxFreeTime(int eventTime, int k, int[] startTime, int[] endTime) {
            int n = startTime.length;
            int[] gap = new int[n + 1];
            gap[0] = startTime[0];
            for (int i = 1; i < n; i++) {
                gap[i] = startTime[i] - endTime[i - 1];
            }
            gap[n] = eventTime - endTime[n - 1];

            int kSum = 0;
            for (int i = 0; i <= k; i++) {
                kSum += gap[i];
            }

            int maxKsum = kSum;

            for (int i = 1; i < n + 1 - k; i++) {
                kSum = kSum + gap[i + k] - gap[i - 1];
                maxKsum = Math.max(maxKsum, kSum);
            }

            return maxKsum;
        }
    }
}
