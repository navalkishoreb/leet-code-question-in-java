package quest.string_matching;

/**
 * Given a string s, check if it can be constructed by taking a substring of it and appending multiple copies of the substring together.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "abab"
 * Output: true
 * Explanation: It is the substring "ab" twice.
 * Example 2:
 * <p>
 * Input: s = "aba"
 * Output: false
 * Example 3:
 * <p>
 * Input: s = "abcabcabcabc"
 * Output: true
 * Explanation: It is the substring "abc" four times or the substring "abcabc" twice.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= s.length <= 104
 * s consists of lowercase English letters.
 */
public class RepeatedSubstringPattern {
    class Solution {
        public boolean repeatedSubstringPattern(String s) {
            String repeat = s + s;
            String trim = repeat.substring(1, repeat.length() - 1);
            return trim.contains(s);
        }
    }
}
