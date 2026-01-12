package quest.string;

/**
 * You are given a personal information string s, representing either an email address or a phone number. Return the masked personal information using the below rules.
 * <p>
 * Email address:
 * <p>
 * An email address is:
 * <p>
 * A name consisting of uppercase and lowercase English letters, followed by
 * The '@' symbol, followed by
 * The domain consisting of uppercase and lowercase English letters with a dot '.' somewhere in the middle (not the first or last character).
 * To mask an email:
 * <p>
 * The uppercase letters in the name and domain must be converted to lowercase letters.
 * The middle letters of the name (i.e., all but the first and last letters) must be replaced by 5 asterisks "*****".
 * Phone number:
 * <p>
 * A phone number is formatted as follows:
 * <p>
 * The phone number contains 10-13 digits.
 * The last 10 digits make up the local number.
 * The remaining 0-3 digits, in the beginning, make up the country code.
 * Separation characters from the set {'+', '-', '(', ')', ' '} separate the above digits in some way.
 * To mask a phone number:
 * <p>
 * Remove all separation characters.
 * The masked phone number should have the form:
 * "***-***-XXXX" if the country code has 0 digits.
 * "+*-***-***-XXXX" if the country code has 1 digit.
 * "+**-***-***-XXXX" if the country code has 2 digits.
 * "+***-***-***-XXXX" if the country code has 3 digits.
 * "XXXX" is the last 4 digits of the local number.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "LeetCode@LeetCode.com"
 * Output: "l*****e@leetcode.com"
 * Explanation: s is an email address.
 * The name and domain are converted to lowercase, and the middle of the name is replaced by 5 asterisks.
 * Example 2:
 * <p>
 * Input: s = "AB@qq.com"
 * Output: "a*****b@qq.com"
 * Explanation: s is an email address.
 * The name and domain are converted to lowercase, and the middle of the name is replaced by 5 asterisks.
 * Note that even though "ab" is 2 characters, it still must have 5 asterisks in the middle.
 * Example 3:
 * <p>
 * Input: s = "1(234)567-890"
 * Output: "***-***-7890"
 * Explanation: s is a phone number.
 * There are 10 digits, so the local number is 10 digits and the country code is 0 digits.
 * Thus, the resulting masked number is "***-***-7890".
 * <p>
 * <p>
 * Constraints:
 * <p>
 * s is either a valid email or a phone number.
 * If s is an email:
 * 8 <= s.length <= 40
 * s consists of uppercase and lowercase English letters and exactly one '@' symbol and '.' symbol.
 * If s is a phone number:
 * 10 <= s.length <= 20
 * s consists of digits, spaces, and the symbols '(', ')', '-', and '+'.
 */
public class MaskingPersonalInformation {
    class Solution {
        public String maskPII(String s) {
            if (s.contains("@")) {
                return maskEmail(s);
            } else {
                return maskPhone(s);
            }

        }
        private String maskEmail(String s) {
            s = s.toLowerCase();
            int at = s.indexOf('@');
            char first = s.charAt(0);
            char last = s.charAt(at - 1);

            return first + "*".repeat(5) + last + s.substring(at);
        }

        private String maskPhone(String s) {
            StringBuilder digits = new StringBuilder();
            for (char c : s.toCharArray()) {
                if (Character.isDigit(c)) {
                    digits.append(c);
                }
            }

            int n = digits.length();
            String local = "***-***-" + digits.substring(n - 4);

            if (n == 10) {
                return local;
            }

            return "+" + "*".repeat(n - 10) + "-" + local;
        }

    }

}
