/**
 * Problem: Minimum Operations to Write Letter Y in a square Grid
 * Where n is always odd number.
 * Link: https://leetcode.com/problems/minimum-operations-to-write-the-letter-y-on-a-grid/
 * Matrix has 0s, 1s, 2s. Final output can have only two numbers.
 * Eg: All 1s as Y and 2s as non-Y. Similarly other combinations.
 * 
 * Intuition:
 * 1. Count each number in Y area and non-Y area.
 * 2. For each combination of two numbers, calculate the number of operations needed to convert the grid to that combination.
 * 3. The operations needed are the total number of cells in the grid minus the number of cells that are already in the Y area and the number of cells that are in the non-Y area.
 * 
 * Time Complexity: O(n^2)
 * Space Complexity: O(1)
 * 
 */
public class MinOperationLetterYGrid {
    public int minimumOperationsToWriteY(int[][] grid) {
        int n = grid.length;

        int[] yArea = new int[3];
        int[] nonYArea = new int[3];

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                boolean isDiagTopLeft = (i == j && i <= n / 2);
                boolean isDiagTopRight = (i + j == n - 1 && i <= n / 2);
                boolean isVerticalBottom = (j == n / 2 && i >= n / 2);

                if(isDiagTopLeft || isDiagTopRight || isVerticalBottom) yArea[grid[i][j]]++;
                else nonYArea[grid[i][j]]++;
            }
        }

        int minOperations = n * n;
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                if(i != j){
                    int operations = n * n - yArea[i] - nonYArea[j];
                    minOperations = Math.min(minOperations, operations);
                }
            }
        }

        return minOperations;
    }
}
