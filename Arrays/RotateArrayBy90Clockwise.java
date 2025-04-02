/**
 * Rotate a 2D array by 90 degrees clockwise.
 * Link: https://leetcode.com/problems/rotate-image/
 * 
 * Approach:
 * 1. Transpose the matrix (swap rows with columns).
 * 2. Reverse each row of the transposed matrix.
 * 
 * Time Complexity: O(n^2)
 * Space Complexity: O(1)
 */
public class RotateArrayBy90Clockwise {
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;

        for(int i = 0; i < n; i++){
            for(int j = i; j < m; j++){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        for(int i = 0; i < n; i++){
            for(int j = 0 ; j < m / 2; j++){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][m - 1 - j];
                matrix[i][m - 1 - j] = temp;
            }
        }
        
    }
}
