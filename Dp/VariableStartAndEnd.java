/*
 * The problem is advancement of the last problem that is "TriangleDp.java".
 * Here we have to find the maximum path sum from top to bottom.
 * Start point can be any element in the first row. (variable)
 * End point can be any element in the last row. (variable)
 * 
 * If we do not see uniformity in numbers/given data, we can't use Greedy.
 * Thus, we will use Recursion / DP.
 * 
 * Here we will need to run a loop from o to m-1 (m is the number of elements in the first row).
 * And find the max of all the path sums from the first row to the last row.
 * 
 * Recursion:
 * Time Complexity: O(3^n) = exponential
 * Space Complexity: O(n) for recursion stack
 * 
 * Memoization:
 * Time Complexity: O(n*m)
 * Space Complexity: O(n*m) + O(n) = Dp space + Stack space
 * 
 * Tabulation:
 * Time Complexity: O(n*m)
 * Space Complexity: O(n*m) = Dp space
 * 
 * Space optimization:
 * Time Complexity: O(n*m)
 * Space Complexity: O(n) = Dp space
 */
public class VariableStartAndEnd {

    public static int recursion(int i, int j, int n, int m, int[][] mat){
		if(j < 0 || j >= m) return -(int)1e9;
		if(i == 0) return mat[i][j];
		int u = mat[i][j] + recursion(i - 1, j, n, m, mat);
		int l = mat[i][j] + recursion(i - 1, j - 1, n, m, mat);
		int r = mat[i][j] + recursion(i - 1, j + 1, n, m, mat);
		return Math.max(u, Math.max(l, r));
	}

	public static int memoization(int i, int j, int n, int m, int[][] mat, int[][] dp){
		if(j < 0 || j >= m) return -(int)1e9;
		if(i == 0) return mat[i][j];
		if(dp[i][j] != -1) return dp[i][j];
		int u = mat[i][j] + recursion(i - 1, j, n, m, mat);
		int l = mat[i][j] + recursion(i - 1, j - 1, n, m, mat);
		int r = mat[i][j] + recursion(i - 1, j + 1, n, m, mat);
		return dp[i][j] = Math.max(u, Math.max(l, r));
	}

	public static int tabulation(int n, int m, int[][] mat){
		int[][] dp = new int[n][m];
		int max = Integer.MIN_VALUE;

		for(int i = 0; i < n; i++){
			for(int j = 0; j < m; j++){
				if(i == 0) dp[i][j] = mat[i][j];
				else{
					int d = mat[i][j], l = mat[i][j], r = mat[i][j];
					d += dp[i - 1][j];
					if(j + 1 < m) l += dp[i - 1][j + 1];
                    // this else block is necessary as we have to not just skip the 
                    // element where "j+1<m" but also we have to add a negative value
                    // to make it less than the other two values and skip it.
                    // else it might be considered as the max value.
					else l += -(int)1e9;
					if(j > 0) r += dp[i - 1][j - 1];
					else r += -(int)1e9;
					dp[i][j] = Math.max(d, Math.max(l, r));
				}
				if(i == n - 1){
					max = Math.max(max, dp[i][j]);
				}
			}
		}
		return max;
	}

	public static int space(int n, int m, int[][] mat){
		int[] prev = new int[m];
		int max = Integer.MIN_VALUE;

		for(int i = 0; i < n; i++){
			int[] curr = new int[m];
			for(int j = 0; j < m; j++){
				if(i == 0) curr[j] = mat[i][j];
				else{
					int d = mat[i][j], l = mat[i][j], r = mat[i][j];
					d += prev[j];
					if(j + 1 < m) l += prev[j + 1];
					else l += -(int)1e8;
					if(j > 0) r += prev[j - 1];
					else r += -(int)1e8;
					curr[j] = Math.max(d, Math.max(l, r));
				}
				if(i == n - 1){
					max = Math.max(max, curr[j]);
				}
			}
			prev = curr;
		}
		return max;
	}
	
	public static int getMaxPathSum(int[][] matrix) {
		// Write your code here
		int n = matrix.length;
		int m = matrix[0].length;
		int max = Integer.MIN_VALUE;

		// for(int i = 0; i < m; i++){
		// 	max = Math.max(max, recursion(n - 1, i, n, m, matrix));
		// }
		// return max;

		// int[][] dp = new int[n][m];
		// for(int i = 0; i < n; i++)
		// 	for(int j = 0; j < m; j++)
		// 		dp[i][j] = -1;
		// for(int i = 0; i < m; i++){
		// 	max = Math.max(max, memoization(n - 1, i, n, m, matrix, dp));
		// }
		// return max;

		// return tabulation(n, m, matrix);

		return space(n, m, matrix);
	}
}
