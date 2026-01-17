package leetcode;

/**
 * Given two strings word1 and word2, return the minimum number of operations required to convert word1 to word2.
 * <p>
 * You have the following three operations permitted on a word:
 * <p>
 * Insert a character
 * Delete a character
 * Replace a character
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: word1 = "horse", word2 = "ros"
 * Output: 3
 * Explanation:
 * horse -> rorse (replace 'h' with 'r')
 * rorse -> rose (remove 'r')
 * rose -> ros (remove 'e')
 * Example 2:
 * <p>
 * Input: word1 = "intention", word2 = "execution"
 * Output: 5
 * Explanation:
 * intention -> inention (remove 't')
 * inention -> enention (replace 'i' with 'e')
 * enention -> exention (replace 'n' with 'x')
 * exention -> exection (replace 'n' with 'c')
 * exection -> execution (insert 'u')
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 0 <= word1.length, word2.length <= 500
 * word1 and word2 consist of lowercase English letters.
 */
public class EditDistance {
    class Solution {
        public int minDistance(String word1, String word2) {
            int m = word1.length();
            int n = word2.length();

            int[][] dp = new int[m + 1][n + 1];

            for (int i = 0; i <= m; i++) {
                dp[i][0] = i;
            }
            for (int i = 0; i <= n; i++) {
                dp[0][i] = i;
            }

            for (int i = 1; i <= m; i++) {
                for (int j = 1; j <= n; j++) {
                    if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                        dp[i][j] = dp[i - 1][j - 1];
                    } else {
                        // int del = 1 + dp[i-1][j];
                        // int insert = 1 + dp[i][j-1];
                        // int replace = 1 + dp[i-1][j-1];
                        // dp[i][j] = Math.min(Math.min(del,insert),replace);
                        dp[i][j] = 1 + Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]);
                    }
                }
            }
            return dp[m][n];

        }
    }
}
