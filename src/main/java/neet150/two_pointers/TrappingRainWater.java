package neet150.two_pointers;

/*
You are given an array of non-negative integers height which represent an elevation map. Each value height[i] represents the height of a bar, which has a width of 1.

Return the maximum area of water that can be trapped between the bars.

Example 1:



Input: height = [0,2,0,3,1,0,1,3,2,1]

Output: 9
Constraints:

1 <= height.length <= 1000
0 <= height[i] <= 1000

 */

public class TrappingRainWater {

    class Solution {
        public int trap(int[] height) {
            int n = height.length;
            int[] leftMax = new int[n];
            int[] rightMax = new int[n];

            leftMax[0] = height[0];
            for (int i = 1; i < n; i++) {
                leftMax[i] = Math.max(leftMax[i - 1], height[i]);
            }

            rightMax[n - 1] = height[n - 1];
            for (int i = n - 2; i >= 0; i--) {
                rightMax[i] = Math.max(rightMax[i + 1], height[i]);
            }

            int water = 0;
            for (int i = 0; i < n; i++) {
                int current = Math.min(leftMax[i], rightMax[i]) - height[i];
                water += current;
            }
            return water;
        }
    }

    class Solution2 {
        public int trap(int[] height) {
            int n = height.length;

            int water = 0;
            int left = 0;
            int right = n - 1;
            int leftMax = height[0];
            int rightMax = height[n - 1];

            while (left < right) {
                if (height[left] < height[right]) {
                    if (height[left] >= leftMax) {
                        leftMax = height[left];
                    } else {
                        water += leftMax - height[left];
                    }
                    left++;
                } else {
                    if (height[right] >= rightMax) {
                        rightMax = height[right];
                    } else {
                        water += rightMax - height[right];
                    }
                    right--;
                }

            }

            return water;
        }
    }

}
