package neet150.array_and_hashing;

import java.util.HashSet;
import java.util.Set;

/*
You are given a 9 x 9 Sudoku board board. A Sudoku board is valid if the following rules are followed:

Each row must contain the digits 1-9 without duplicates.
Each column must contain the digits 1-9 without duplicates.
Each of the nine 3 x 3 sub-boxes of the grid must contain the digits 1-9 without duplicates.
Return true if the Sudoku board is valid, otherwise return false

Note: A board does not need to be full or be solvable to be valid.

Example 1:



Input: board =
[["1","2",".",".","3",".",".",".","."],
 ["4",".",".","5",".",".",".",".","."],
 [".","9","8",".",".",".",".",".","3"],
 ["5",".",".",".","6",".",".",".","4"],
 [".",".",".","8",".","3",".",".","5"],
 ["7",".",".",".","2",".",".",".","6"],
 [".",".",".",".",".",".","2",".","."],
 [".",".",".","4","1","9",".",".","8"],
 [".",".",".",".","8",".",".","7","9"]]

Output: true
Example 2:

Input: board =
[["1","2",".",".","3",".",".",".","."],
 ["4",".",".","5",".",".",".",".","."],
 [".","9","1",".",".",".",".",".","3"],
 ["5",".",".",".","6",".",".",".","4"],
 [".",".",".","8",".","3",".",".","5"],
 ["7",".",".",".","2",".",".",".","6"],
 [".",".",".",".",".",".","2",".","."],
 [".",".",".","4","1","9",".",".","8"],
 [".",".",".",".","8",".",".","7","9"]]

Output: false
Explanation: There are two 1's in the top-left 3x3 sub-box.

Constraints:

board.length == 9
board[i].length == 9
board[i][j] is a digit 1-9 or '.'.

 */
public class ValidSudoku {
    class Solution {
        Set<Character>[] rows = new HashSet[9];
        Set<Character>[] cols = new HashSet[9];
        Set<Character>[] box = new HashSet[9];

        public boolean isValidSudoku(char[][] board) {

            for (int i = 0; i < 9; i++) {
                rows[i] = new HashSet<>();
                cols[i] = new HashSet<>();
                box[i] = new HashSet<>();
            }

            for (int i = 0; i < 9; i++) {

                for (int j = 0; j < 9; j++) {
                    char c = board[i][j];
                    if (c == '.') {
                        continue;
                    }
                    int boxIndex = (i / 3) * 3 + (j / 3);

                    if (doesAlreadyExists(c, i, j, boxIndex)) {
                        return false;
                    }

                    rows[i].add(c);
                    cols[j].add(c);
                    box[boxIndex].add(c);
                }
            }
            return true;
        }

        private boolean doesAlreadyExists(char c, int row, int col, int boxIndex) {
            return rows[row].contains(c) || cols[col].contains(c) || box[boxIndex].contains(c);

        }
    }

}
