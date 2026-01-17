package leetcode;

/**
 * Given a 2D grid consists of 0s (land) and 1s (water).  An island is a maximal 4-directionally connected group of 0s and a closed island is an island totally (all left, top, right, bottom) surrounded by 1s.
 * <p>
 * Return the number of closed islands.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * <p>
 * <p>
 * Input: grid = [[1,1,1,1,1,1,1,0],[1,0,0,0,0,1,1,0],[1,0,1,0,1,1,1,0],[1,0,0,0,0,1,0,1],[1,1,1,1,1,1,1,0]]
 * Output: 2
 * Explanation:
 * Islands in gray are closed because they are completely surrounded by water (group of 1s).
 * Example 2:
 * <p>
 * <p>
 * <p>
 * Input: grid = [[0,0,1,0,0],[0,1,0,1,0],[0,1,1,1,0]]
 * Output: 1
 * Example 3:
 * <p>
 * Input: grid = [[1,1,1,1,1,1,1],
 * [1,0,0,0,0,0,1],
 * [1,0,1,1,1,0,1],
 * [1,0,1,0,1,0,1],
 * [1,0,1,1,1,0,1],
 * [1,0,0,0,0,0,1],
 * [1,1,1,1,1,1,1]]
 * Output: 2
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= grid.length, grid[0].length <= 100
 * 0 <= grid[i][j] <=1
 */
public class ClosedIslands {
    class Solution {
        private int[][] grid;
        private int m;
        private int n;

        public int closedIsland(int[][] grid) {
            this.grid = grid;
            m = grid.length;
            n = grid[0].length;
            // 1 --> water
            // 0 --> land
            // flood boundary islands
            for (int i = 0; i < m; i++) {
                if (grid[i][0] == 0) {
                    floodWater(i, 0);
                }
                if (grid[i][n - 1] == 0) {
                    floodWater(i, n - 1);
                }
            }

            for (int i = 0; i < n; i++) {
                if (grid[0][i] == 0) {
                    floodWater(0, i);
                }
                if (grid[m - 1][i] == 0) {
                    floodWater(m - 1, i);
                }
            }
            // what remains now
            int count = 0;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == 0) {
                        count++;
                        // an island can be made of more than 1 cell
                        // all connected zeroes should be filled up
                        floodWater(i, j);
                    }
                }
            }


            return count;
        }

        private void floodWater(int i, int j) {
            if (i < 0 || j < 0 || i >= m || j >= n || grid[i][j] == 1) {
                return;
            }

            grid[i][j] = 1;
            floodWater(i - 1, j);
            floodWater(i, j - 1);
            floodWater(i + 1, j);
            floodWater(i, j + 1);
        }
    }
}
