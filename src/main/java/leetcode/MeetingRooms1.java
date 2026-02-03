package leetcode;

import java.util.Comparator;
import java.util.List;

/**
 * Given an array of meeting time interval objects consisting of start and end times [[start_1,end_1],[start_2,end_2],...] (start_i < end_i),
 * determine if a person could add all meetings to their schedule without any conflicts.
 * <p>
 * Note: (0,8),(8,10) is not considered a conflict at 8
 * <p>
 * Example 1:
 * <p>
 * Input: intervals = [(0,30),(5,10),(15,20)]
 * <p>
 * Output: false
 * Explanation:
 * <p>
 * (0,30) and (5,10) will conflict
 * (0,30) and (15,20) will conflict
 * Example 2:
 * <p>
 * Input: intervals = [(5,8),(9,15)]
 * <p>
 * Output: true
 * Constraints:
 * <p>
 * 0 <= intervals.length <= 500
 * 0 <= intervals[i].start < intervals[i].end <= 1,000,000
 */
public class MeetingRooms1 {
    /**
     * Definition of Interval:
     * public class Interval {
     * public int start, end;
     * public Interval(int start, int end) {
     * this.start = start;
     * this.end = end;
     * }
     * }
     */

    class Solution {
        public boolean canAttendMeetings(List<Interval> intervals) {
            if (intervals.isEmpty()) return true;
            intervals.sort(Comparator.comparingInt((Interval interval) -> interval.start));
            Interval prev = intervals.getFirst();
            for (int i = 1; i < intervals.size(); i++) {
                Interval interval = intervals.get(i);
                if (interval.start < prev.end) {
                    return false;
                } else {
                    prev = interval;
                }
            }
            return true;

        }
    }

}
