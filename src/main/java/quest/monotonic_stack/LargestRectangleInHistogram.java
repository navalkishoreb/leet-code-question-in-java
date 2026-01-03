package quest.monotonic_stack;

/**
 * Given an array of integers heights representing the histogram's bar height where the width of each bar is 1,
 * return the area of the largest rectangle in the histogram.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * Input: heights = [2,1,5,6,2,3]
 * Output: 10
 * Explanation: The above is a histogram where width of each bar is 1.
 * The largest rectangle is shown in the red area, which has an area = 10 units.
 * Example 2:
 * <p>
 * <p>
 * Input: heights = [2,4]
 * Output: 4
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= heights.length <= 105
 * 0 <= heights[i] <= 104
 */
import java.util.ArrayDeque;
import java.util.Deque;
public class LargestRectangleInHistogram {
    class Solution {
        public int largestRectangleArea(int[] heights) {
            int maxArea = 0;
            Deque<Integer> stack = new ArrayDeque<>();
            for (int rightBoundary = 0; rightBoundary <= heights.length; rightBoundary++) {
                int currentHeight = (rightBoundary == heights.length) ? 0 : heights[rightBoundary];
                while (!stack.isEmpty() && currentHeight < heights[stack.peek()]) {
                    int barConsidered = stack.pop();
                    int height = heights[barConsidered];
                    int leftBoundary = stack.isEmpty() ? -1 : stack.peek();
                    int width = rightBoundary - leftBoundary - 1;
                    int area = height * width;
                    maxArea = Math.max(maxArea, area);
                }
                stack.push(rightBoundary);
            }
            return maxArea;
        }
    }
}
