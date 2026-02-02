package neet150.sliding_window;

/*
Given two strings s and t, return the shortest substring of s such that every character in t, including duplicates, is present in the substring. If such a substring does not exist, return an empty string "".

You may assume that the correct output is always unique.

Example 1:

Input: s = "OUZODYXAZV", t = "XYZ"

Output: "YXAZ"
Explanation: "YXAZ" is the shortest substring that includes "X", "Y", and "Z" from string t.

Example 2:

Input: s = "xyz", t = "xyz"

Output: "xyz"
Example 3:

Input: s = "x", t = "xy"

Output: ""
Constraints:

1 <= s.length <= 1000
1 <= t.length <= 1000
s and t consist of uppercase and lowercase English letters.
*/
public class MinimumWindowSubstring {
    class Solution {
        public String minWindow(String s, String t) {
            int[] need = new int[128];

            for (char c : t.toCharArray()) {
                need[c]++;
            }

            int required = t.length();
            int left = 0;
            int minLen = Integer.MAX_VALUE;
            int start = 0;

            for (int right = 0; right < s.length(); right++) {
                char c = s.charAt(right);

                // if this required char
                if (need[c] > 0) {
                    required--;
                }
                need[c]--;

                while (required == 0) {
                    if (right - left + 1 < minLen) {
                        minLen = right - left + 1;
                        start = left;
                    }

                    char p = s.charAt(left);
                    need[p]++;
                    if (need[p] > 0) {
                        required++;
                    }
                    left++;
                }
            }

            return minLen == Integer.MAX_VALUE ? "" : s.substring(start, start + minLen);

        }
    }

}
