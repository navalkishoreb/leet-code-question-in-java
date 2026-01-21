package neet150.two_pointers;

/*
Given a string s, return true if it is a palindrome, otherwise return false.

A palindrome is a string that reads the same forward and backward. It is also case-insensitive and ignores all non-alphanumeric characters.

Note: Alphanumeric characters consist of letters (A-Z, a-z) and numbers (0-9).

Example 1:

Input: s = "Was it a car or a cat I saw?"

Output: true
Explanation: After considering only alphanumerical characters we have "wasitacaroracatisaw", which is a palindrome.

Example 2:

Input: s = "tab a cat"

Output: false
Explanation: "tabacat" is not a palindrome.

Constraints:

1 <= s.length <= 1000
s is made up of only printable ASCII characters.

 */
public class ValidPalindrome {
    class Solution {
        public boolean isPalindrome(String s) {
            s = s.toLowerCase();
            char[] str = s.toCharArray();
            StringBuilder builder = new StringBuilder();
            for (char c : str) {
                if (Character.isLetterOrDigit(c)) {
                    builder.append(c);
                }
            }
            String original = builder.toString();
            int i = 0;
            int j = original.length() - 1;
            while (i < j) {
                if (original.charAt(i) == original.charAt(j)) {
                    i++;
                    j--;
                } else {
                    return false;
                }
            }
            return true;
        }
    }

    class Solution2 {
        public boolean isPalindrome(String s) {
            int left = 0;
            int right= s.length() -1;
            while(left < right){
                char lchar = s.charAt(left);
                char rchar = s.charAt(right);
                if(!Character.isLetterOrDigit(lchar)){
                    left++;
                    continue;
                }
                if(!Character.isLetterOrDigit(rchar)){
                    right--;
                    continue;
                }
                if(Character.toLowerCase(lchar)!=Character.toLowerCase(rchar)){
                    return false;
                }
                left++;
                right--;
            }
            return true;

        }
    }


}
