package neet150.array_and_hashing;

import java.util.ArrayList;
import java.util.List;

/*
Design an algorithm to encode a list of strings to a string. The encoded string is then sent over the network and is decoded back to the original list of strings.

Machine 1 (sender) has the function:

string encode(vector<string> strs) {
    // ... your code
    return encoded_string;
}
Machine 2 (receiver) has the function:

vector<string> decode(string s) {
    //... your code
    return strs;
}
So Machine 1 does:

string encoded_string = encode(strs);
and Machine 2 does:

vector<string> strs2 = decode(encoded_string);
strs2 in Machine 2 should be the same as strs in Machine 1.

Implement the encode and decode methods.

Example 1:

Input: dummy_input = ["Hello","World"]

Output: ["Hello","World"]

Explanation:
Machine 1:
Codec encoder = new Codec();
String msg = encoder.encode(strs);
Machine 1 ---msg---> Machine 2

Machine 2:
Codec decoder = new Codec();
String[] strs = decoder.decode(msg);
Example 2:

Input: dummy_input = [""]

Output: [""]

Constraints:

0 <= strs.length < 100
0 <= strs[i].length < 200
strs[i] contains any possible characters out of 256 valid ASCII characters.

Follow up: Could you write a generalized algorithm to work on any possible set of characters?
 */
public class EncodeDecodeStrings {
    class Solution {
        private final char delimiter = '$';
        private final char escape = '|';

        public String encode(List<String> strs) {
            StringBuilder builder = new StringBuilder();
            for (String str : strs) {
                for (char c : str.toCharArray()) {
                    if (c == escape || c == delimiter) {
                        builder.append(escape);
                    }
                    builder.append(c);
                }
                builder.append(delimiter);
            }

            return builder.toString();

        }

        public List<String> decode(String str) {
            List<String> res = new ArrayList<>();
            StringBuilder builder = new StringBuilder();
            char[] data = str.toCharArray();
            int n = data.length;
            for (int i = 0; i < data.length; i++) {
                char c = data[i];
                if (c == escape && i + 1 < n) {
                    i++;
                    builder.append(data[i]);
                } else if (c == delimiter) {
                    res.add(builder.toString());
                    builder.setLength(0);
                } else {
                    builder.append(c);
                }

            }
            return res;
        }
    }

}
