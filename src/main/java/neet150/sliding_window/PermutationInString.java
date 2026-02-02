package neet150.sliding_window;

/*
You are given two strings s1 and s2.

Return true if s2 contains a permutation of s1, or false otherwise. That means if a permutation of s1 exists as a substring of s2, then return true.

Both strings only contain lowercase letters.

Example 1:

Input: s1 = "abc", s2 = "lecabee"

Output: true
Explanation: The substring "cab" is a permutation of "abc" and is present in "lecabee".

Example 2:

Input: s1 = "abc", s2 = "lecaabee"

Output: false
Constraints:

1 <= s1.length, s2.length <= 1000

*/
public class PermutationInString {

    class Solution {
        public boolean checkInclusion(String s1, String s2) {
            int n1 = s1.length();
            int n2 = s2.length();

            int[] freq1 = new int[26];
            int[] freq2 = new int[26];

            // permuatation means order does not matter
            // for a given window of length n1
            //
            // match the frequency

            // calculate frequency
            for (int i = 0; i < n1; i++) {
                char c = s1.charAt(i);
                freq1[c - 'a']++;
            }

            int left = 0;
            for (int right = 0; right < n2; right++) {
                char c = s2.charAt(right);
                freq2[c - 'a']++;

                if (right - left + 1 > n1) {
                    // window is larger then required
                    // reduce window
                    // reduce freq2
                    char p = s2.charAt(left);
                    freq2[p - 'a']--;
                    left++;
                }

                if (right - left + 1 == n1 && match(freq1, freq2)) {
                    return true;
                }
            }
            return false;
        }

        // O(26) --> constant
        private boolean match(int[] freq1, int[] freq2) {
            for (int i = 0; i < freq1.length; i++) {
                if (freq1[i] != freq2[i]) {
                    return false;
                }
            }
            return true;
        }
    }

}
