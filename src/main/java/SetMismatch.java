/***
 * You have a set of integers s, which originally contains all the numbers from 1 to n. Unfortunately, due to some error, one of the numbers in s got duplicated to another number in the set, which results in repetition of one number and loss of another number.
 *
 * You are given an integer array nums representing the data status of this set after the error.
 *
 * Find the number that occurs twice and the number that is missing and return them in the form of an array.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,2,4]
 * Output: [2,3]
 * Example 2:
 *
 * Input: nums = [1,1]
 * Output: [1,2]
 *
 *
 * Constraints:
 *
 * 2 <= nums.length <= 104
 * 1 <= nums[i] <= 104
 *
 * Seen this question in a real interview before?
 * 1/5
 * Yes
 * No
 */
public class SetMismatch {
}

class Solution1 {
    public int[] findErrorNums(int[] nums) {
        // with O(n) space
        int[] expected = new int[nums.length+1];
        for (int i = 1; i < nums.length+1; i ++){
            expected[i] = 1;
        }
        int duplicate = -1;
        for (int i = 0; i < nums.length; i ++){
            if (expected[nums[i]] == 1){
                expected[nums[i]] = 0;
            }else{
                duplicate = nums[i];
            }
        }
        int missing = -1;
        for (int i = 1; i < nums.length + 1; i++){
            if (expected[i] == 1){
                missing = i;
                break;
            }
        }
        return new int[]{duplicate, missing};

    }
}
class Solution2 {
    // with not extra space
    public int[] findErrorNums(int[] nums) {
        int duplicate = -1;
        int missing = -1;

        for (int i = 0; i < nums.length; i++) {
            int index = Math.abs(nums[i]) - 1;
            if (nums[index] < 0) {
                duplicate = index + 1;
            } else {
                nums[index] = -nums[index];
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                missing = i + 1;
                break;
            }
        }
        return new int[] { duplicate, missing };

    }
}
