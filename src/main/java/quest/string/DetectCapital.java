package quest.string;

/**
 * We define the usage of capitals in a word to be right when one of the following cases holds:
 * <p>
 * All letters in this word are capitals, like "USA".
 * All letters in this word are not capitals, like "leetcode".
 * Only the first letter in this word is capital, like "Google".
 * Given a string word, return true if the usage of capitals in it is right.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: word = "USA"
 * Output: true
 * Example 2:
 * <p>
 * Input: word = "FlaG"
 * Output: false
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= word.length <= 100
 * word consists of lowercase and uppercase English letters.
 */
public class DetectCapital {
    class Solution {
        public boolean detectCapitalUse(String word) {
            int upperCount = 0;
            for (char c : word.toCharArray()) {
                if (Character.isUpperCase(c)) {
                    upperCount++;
                }
            }

            return upperCount == word.length()
                    || upperCount == 0
                    || upperCount == 1 && Character.isUpperCase(word.charAt(0));
        }

    }
}
