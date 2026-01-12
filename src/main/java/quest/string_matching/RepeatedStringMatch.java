package quest.string_matching;

/**
 * Given two strings a and b, return the minimum number of times you should repeat string a so that string b is a substring of it. If it is impossible for b​​​​​​ to be a substring of a after repeating it, return -1.
 * <p>
 * Notice: string "abc" repeated 0 times is "", repeated 1 time is "abc" and repeated 2 times is "abcabc".
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: a = "abcd", b = "cdabcdab"
 * Output: 3
 * Explanation: We return 3 because by repeating a three times "abcdabcdabcd", b is a substring of it.
 * Example 2:
 * <p>
 * Input: a = "a", b = "aa"
 * Output: 2
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= a.length, b.length <= 10^4
 * a and b consist of lowercase English letters.
 */
public class RepeatedStringMatch {
    class Solution {
        public int repeatedStringMatch(String a, String b) {

            //int count = (int)Math.ceil((double)b.length() / a.length());
            int count = (b.length() + a.length() - 1) / a.length();
            String repeat = a.repeat(count);
            if (repeat.contains(b)) {
                return count;
            }
            repeat = repeat + a;
            count++;
            if (repeat.contains(b)) {
                return count;
            }
            return -1;

        }
    }
}
