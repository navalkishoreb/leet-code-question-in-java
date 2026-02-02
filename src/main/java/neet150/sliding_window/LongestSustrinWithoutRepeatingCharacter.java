package neet150.sliding_window;

import java.util.HashSet;

/*
Given a string s, find the length of the longest substring without duplicate characters.

A substring is a contiguous sequence of characters within a string.

Example 1:

Input: s = "zxyzxyz"

Output: 3
Explanation: The string "xyz" is the longest without duplicate characters.

Example 2:

Input: s = "xxxx"

Output: 1
Constraints:

0 <= s.length <= 1000
s may consist of printable ASCII characters.

 */
public class LongestSustrinWithoutRepeatingCharacter {
    class Solution {
        public int lengthOfLongestSubstring(String s) {
            int left = 0;
            int right = 0;
            HashSet<Character> set = new HashSet<>();
            int max = 0;
            while (left <= right && right < s.length()) {
                char c = s.charAt(right);

                while (set.contains(c)) {
                    char p = s.charAt(left);
                    set.remove(p);
                    left++;
                }
                set.add(c);
                max = Math.max(max, right - left + 1);
                // max = Math.max(max, set.size());
                right++;
            }
            return max;
        }
    }
}
