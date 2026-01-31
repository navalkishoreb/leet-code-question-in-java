package neet150.two_pointers;

/*
You are given an integer array heights where heights[i] represents the height of the ith bar.

You may choose any two bars to form a container. Return the maximum amount of water a container can store.

Example 1:



Input: height = [1,7,2,5,4,7,3,6]

Output: 36
Example 2:

Input: height = [2,2,2]

Output: 4
Constraints:

2 <= height.length <= 1000
0 <= height[i] <= 1000

*/
public class ContainerWithMostWater {
    class Solution {
        public int maxArea(int[] heights) {
            int i = 0;
            int j = heights.length - 1;
            int maxArea = 0;
            while (i < j) {
                int h = Math.min(heights[i], heights[j]);
                int w = j - i;
                int area = h * w;
                maxArea = Math.max(maxArea, area);
                if (heights[i] < heights[j]) {
                    i++;
                } else {
                    j--;
                }
            }
            return maxArea;
        }
    }

}
