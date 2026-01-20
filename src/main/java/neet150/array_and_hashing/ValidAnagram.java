package neet150.array_and_hashing;

import java.util.HashMap;
import java.util.Map;

/**
 * Given two strings s and t, return true if the two strings are anagrams of each other, otherwise return false.
 * <p>
 * An anagram is a string that contains the exact same characters as another string, but the order of the characters can be different.
 * <p>
 * Example 1:
 * <p>
 * Input: s = "racecar", t = "carrace"
 * <p>
 * Output: true
 * Example 2:
 * <p>
 * Input: s = "jar", t = "jam"
 * <p>
 * Output: false
 * Constraints:
 * <p>
 * s and t consist of lowercase English letters.
 */
public class ValidAnagram {
    class Solution {
        public boolean isAnagram(String s, String t) {
            if (s.length() != t.length()) {
                return false;
            }
            Map<Character, Integer> map = new HashMap<>();
            int n = s.length();
            for (int i = 0; i < n; i++) {
                char c = s.charAt(i);
                map.put(c, map.getOrDefault(c, 0) + 1);
            }
            for (int i = 0; i < n; i++) {
                char c = t.charAt(i);
                if (!map.containsKey(c)) {
                    return false;
                }
                map.put(c, map.get(c) - 1);
                if (map.get(c) < 0) return false;
            }
            return true;
        }
    }

}
