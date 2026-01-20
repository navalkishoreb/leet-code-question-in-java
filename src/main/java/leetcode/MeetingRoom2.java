package leetcode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Given an array of meeting time interval objects consisting of start and end times [[start_1,end_1],[start_2,end_2],...] (start_i < end_i), find the minimum number of days required to schedule all meetings without any conflicts.
 * <p>
 * Note: (0,8),(8,10) is not considered a conflict at 8.
 * <p>
 * Example 1:
 * <p>
 * Input: intervals = [(0,40),(5,10),(15,20)]
 * <p>
 * Output: 2
 * Explanation:
 * day1: (0,40)
 * day2: (5,10),(15,20)
 * <p>
 * Example 2:
 * <p>
 * Input: intervals = [(4,9)]
 * <p>
 * Output: 1
 * Constraints:
 * <p>
 * 0 <= intervals.length <= 500
 * 0 <= intervals[i].start < intervals[i].end <= 1,000,000
 */
public class MeetingRoom2 {
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

    class Node {
        private final int state;
        private final int time;

        public Node(int state, int time) {
            this.state = state;
            this.time = time;
        }

        public int getTime() {
            return this.time;
        }

        public int getState() {
            return this.state;
        }
    }

    class Solution {
        public int minMeetingRooms(List<Interval> intervals) {
            int n = intervals.size();
            List<Node> data = new ArrayList<>();
            for (Interval interval : intervals) {
                data.add(new Node(1, interval.start));
                data.add(new Node(0, interval.end));
            }

            data.sort(Comparator.comparing(Node::getTime).thenComparing(Node::getState));
            int countActiveMeetings = 0;
            int maxActiveMeetins = 0;
            for (Node node : data) {
                if (node.getState() == 0) {
                    countActiveMeetings--;
                } else {
                    countActiveMeetings++;
                    maxActiveMeetins = Math.max(maxActiveMeetins, countActiveMeetings);

                }
            }
            return maxActiveMeetins;

        }
    }

}
