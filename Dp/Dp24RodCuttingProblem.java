/*
 * The problem states that we need to find the max rod cutting cost
 * We are given N = rod length
 * We are given price[] = price of each rod length
 * Eg: N = 8, price[] = {1, 5, 8, 9, 10, 17, 1, 7}
 * Rod cut into 4 parts of length 2, 2, 2, 2 => 5 + 5 + 5 + 5 = 20
 * Rod cut into 2 parts of length 4, 4 => 9 + 9 = 18
 * Max profit 20
 * Thus we need to cut the rod in such way to get the max cutting cost.
 * 
 * Consider this question in reverse manner:
 * We need to find the max profit that can be achieved from all the possible combinations of length N.
 * Similar to we have length 8 and infinite supply of each length - 1, 2, 3, 4, 5, 6, 7, 8
 * 
 * Recursion:
 * Time Complexity: O(2^n) - NO = exponential as standing on same index.
 * Space Complexity: O(n) for recursion stack
 * 
 * Memoization:
 * Time Complexity: O(n*n)
 * Space Complexity: O(n*n) + O(n) = Dp + Recursion stack
 * 
 * Tabulation:
 * Time Complexity: O(n*n)
 * Space Complexity: O(n*n)
 * 
 * Space:
 * Time Complexity: O(n*n)
 * Space Complexity: O(n) 
 */
public class Dp24RodCuttingProblem {
    public static int recursion(int n, int[] p, int t){
		// if(t == 0) return 0; - NOT MANDATORY
        // You can also write the base case like old problems it will come out to be this
        // when simplified.
		if(n == 0) return t * p[0];
		int notake = recursion(n - 1, p, t);
		int take = Integer.MIN_VALUE;
		if(n + 1 <= t) take = p[n] + recursion(n, p, t - (n + 1));
		return Math.max(take, notake);
	}

	public static int memoization(int n, int[] p, int t, int[][] dp){
		// if(t == 0) return 0; - NOT MANDATORY
		if(n == 0) return t * p[0];
		if(dp[n][t] != -1) return dp[n][t];
		int notake = memoization(n - 1, p, t, dp);
		int take = Integer.MIN_VALUE;
		if(n + 1 <= t) take = p[n] + memoization(n, p, t - (n + 1), dp);
		return dp[n][t] = Math.max(take, notake);
	}

	public static int tabulation(int n, int[] p){
		int[][] dp = new int[n][n + 1];
		for(int i = 0; i <= n; i++) dp[0][i] = i * (p[0]);

		for(int i = 1; i < n; i++){
			for(int j = 0; j <= n; j++){
				int notake = dp[i - 1][j];
				int take = Integer.MIN_VALUE;
				if(i + 1 <= j) take = p[i] + dp[i][j - (i + 1)];
				dp[i][j] = Math.max(take, notake);
			}
		}
		return dp[n - 1][n];
	}

	public static int space(int n, int[] p){
		int[] prev = new int[n + 1];
		for(int i = 0; i <= n; i++) prev[i] = i * (p[0]);

		for(int i = 1; i < n; i++){
			int[] curr = new int[n + 1];
			for(int j = 0; j <= n; j++){
				int notake = prev[j];
				int take = Integer.MIN_VALUE;
				if(i + 1 <= j) take = p[i] + curr[j - (i + 1)];
				curr[j] = Math.max(take, notake);
			}
			prev = curr;
		}
		return prev[n];
	}

	public static int cutRod(int price[], int n) {
		// Write your code here.
		// return recursion(n - 1, price, n);

		// int[][] dp = new int[n][n + 1];
		// for(int i = 0; i < n; i++)
		// 	for(int j = 0; j <= n; j++)
		// 		dp[i][j] = -1;
		// return memoization(n - 1, price, n, dp);

		// return tabulation(n, price);

		return space(n, price);
	}
}
