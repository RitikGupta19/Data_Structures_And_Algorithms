/**
 * Problem: Rotate a matrix by 90 degrees anti-clockwise.
 * Link: https://www.naukri.com/code360/problems/rotate-matrix-by-90-degrees_981261?leftPanelTabValue=PROBLEM
 * 
 * Approach:
 * 1. Transpose the matrix (swap rows with columns).
 * 2. Reverse each column of the transposed matrix.
 * 
 * Time Complexity: O(n^2)
 * Space Complexity: O(1)
 */
public class RotateArrayBy90AntiClockwise {
    public static ArrayList<ArrayList<Integer>> rotateMatrix(ArrayList<ArrayList<Integer>> matrix){
		// Write your code here.
		int n = matrix.size();
		int m = matrix.get(0).size();

		for(int i = 0 ; i < n; i++){
			for(int j = i; j < m ; j++){
				int temp = matrix.get(i).get(j);
				matrix.get(i).set(j, matrix.get(j).get(i));
				matrix.get(j).set(i, temp);
			}
		}

		for(int c = 0; c < m; c++){
			for(int r = 0; r < n / 2; r++){
				int temp = matrix.get(r).get(c);
				matrix.get(r).set(c, matrix.get(n - 1 - r).get(c));
				matrix.get(n - 1 - r).set(c, temp);
			}
		}

		return matrix;
	}
}
