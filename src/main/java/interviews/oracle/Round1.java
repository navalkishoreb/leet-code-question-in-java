package interviews.oracle;

import java.util.HashSet;
import java.util.Scanner;

public class Round1 {
    record Result(String s, int length) {
    }

    static Result longestUniqueSubstring(String input) {
        char[] array = input.toCharArray();
        int n = array.length;
        HashSet<Character> set = new HashSet<>();
        int left = 0;
        int maxLen = 0;
        Result result = null;
        for (int right = 0; right < n; right++) {
            char ch = array[right];
            if (!set.isEmpty() && set.contains(ch)) {
                if (set.size() > maxLen) {
                    result = new Result(input.substring(left, right), set.size());
                    maxLen = set.size();
                }
                while (!set.isEmpty() && set.contains(ch)) {
                    char previous = array[left];
                    set.remove(previous);
                    left++;
                }
            } else {
                set.add(array[right]);
            }
        }


        return result == null ? new Result("", 0) : result;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String input = in.next();

        Result res = longestUniqueSubstring(input);
        System.out.println(res);
    }
}

