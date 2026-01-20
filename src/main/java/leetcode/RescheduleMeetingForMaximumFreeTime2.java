package leetcode;

/**
 * You are given an integer eventTime denoting the duration of an event. You are also given two integer arrays startTime and endTime, each of length n.
 * <p>
 * These represent the start and end times of n non-overlapping meetings that occur during the event between time t = 0 and time t = eventTime, where the ith meeting occurs during the time [startTime[i], endTime[i]].
 * <p>
 * You can reschedule at most one meeting by moving its start time while maintaining the same duration, such that the meetings remain non-overlapping, to maximize the longest continuous period of free time during the event.
 * <p>
 * Return the maximum amount of free time possible after rearranging the meetings.
 * <p>
 * Note that the meetings can not be rescheduled to a time outside the event and they should remain non-overlapping.
 * <p>
 * Note: In this version, it is valid for the relative ordering of the meetings to change after rescheduling one meeting.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: eventTime = 5, startTime = [1,3], endTime = [2,5]
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
 * Input: eventTime = 10, startTime = [0,7,9], endTime = [1,8,10]
 * <p>
 * Output: 7
 * <p>
 * Explanation:
 * <p>
 * <p>
 * <p>
 * Reschedule the meeting at [0, 1] to [8, 9], leaving no meetings during the time [0, 7].
 * <p>
 * Example 3:
 * <p>
 * Input: eventTime = 10, startTime = [0,3,7,9], endTime = [1,4,8,10]
 * <p>
 * Output: 6
 * <p>
 * Explanation:
 * <p>
 * <p>
 * <p>
 * Reschedule the meeting at [3, 4] to [8, 9], leaving no meetings during the time [1, 7].
 * <p>
 * Example 4:
 * <p>
 * Input: eventTime = 5, startTime = [0,1,2,3,4], endTime = [1,2,3,4,5]
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
 * 0 <= startTime[i] < endTime[i] <= eventTime
 * endTime[i] <= startTime[i + 1] where i lies in the range [0, n - 2].
 */
public class RescheduleMeetingForMaximumFreeTime2 {
    class Solution {
        public int maxFreeTime(int eventTime, int[] startTime, int[] endTime) {
            int n = startTime.length;
            int[] gaps = new int[n + 1];

            gaps[0] = startTime[0];
            for (int i = 1; i < n; i++) {
                gaps[i] = startTime[i] - endTime[i - 1];
            }
            gaps[n] = eventTime - endTime[n - 1];

            int[] dur = new int[n];
            for (int i = 0; i < n; i++) {
                dur[i] = endTime[i] - startTime[i];
            }

            int[] prefixMax = new int[n + 1];
            int[] suffixMax = new int[n + 1];

            prefixMax[0] = gaps[0];
            for (int i = 1; i <= n; i++) {
                prefixMax[i] = Math.max(prefixMax[i - 1], gaps[i]);
            }

            suffixMax[n] = gaps[n];
            for (int i = n - 1; i >= 0; i--) {
                suffixMax[i] = Math.max(suffixMax[i + 1], gaps[i]);
            }
            int maxGap = prefixMax[n];
            for (int i = 0; i < n; i++) {
                int otherGap = 0;
                int mergedGap = gaps[i] + dur[i] + gaps[i + 1];
                if (i > 0)
                    otherGap = Math.max(otherGap, prefixMax[i - 1]);
                if (i + 2 <= n)
                    otherGap = Math.max(otherGap, suffixMax[i + 2]);
                if (otherGap >= dur[i]) {
                    // Can place meeting elsewhere - get full merged gap
                    maxGap = Math.max(maxGap, mergedGap);
                } else {
                    // Must place back in merged gap - only get gaps[i] + gaps[i+1]
                    maxGap = Math.max(maxGap, gaps[i] + gaps[i + 1]);
                }
            }
            return maxGap;

        }
    }
}
