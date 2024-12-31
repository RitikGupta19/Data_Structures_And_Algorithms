/*
 * HOUSE ROBBER 1
 * Problem: to find the maximum subsequence sum of an array such that no two elements are adjacent.
 * Subsequence: If you remove some elements of an array.
 * The remaining elements remains in same order.
 * 
 * Approach: we need to try multiple/every combination - Thus = recursion/ DP.
 * Eg: if we pick 0th element in our sum - we cannot pick non-adjacent elements.
 * Also, here we will make the recurrence relation in a way:
 * f(n) = maximum sum of subsequence of array a[0....n]
 * f(2) = maximum sum of subsequence of array a[0,1,2]
 * 
 * Recursion:
 * Time Complexity: O(2^n)
 * Space Complexity: O(n) => Stack space
 * 
 * Memoization:
 * Time Complexity: O(n)
 * Space Complexity: O(n) => Stack space + Table space
 * 
 * Tabulation:
 * Time Complexity: O(n)
 * Space Complexity: O(n) => Table space
 * 
 * Space Optimized:
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
public class MaxSumNonAdj {
    public static int recursion(int n, ArrayList<Integer> nums){
		if(n == 0) return nums.get(n);
        // Any negative integer place will not be picked.
		if(n < 0) return 0;
		int pick = nums.get(n) + recursion(n - 2, nums);
		int nonPick = recursion(n - 1, nums);
		return Math.max(pick, nonPick);
	}

	public static int memoization(int n, int[] dp, ArrayList<Integer> nums){
		if(n == 0) return nums.get(n);
		if(n < 0) return 0;
		if(dp[n] != -1) return dp[n];
		int pick = nums.get(n) + memoization(n - 2, dp, nums);
		int nonPick = memoization(n - 1, dp, nums);
		return dp[n] = Math.max(pick, nonPick);
	}

	public static int tabulation(int n, ArrayList<Integer> nums){
		int[] dp = new int[n];
		dp[0] = nums.get(0);
		for(int i = 1; i < n; i++){
            // PICK CASE: We will always pick that element.
			int pick = nums.get(i);
            // Checking for the edge case: if we are at 2nd+ element.
			if(i > 1) pick += dp[i - 2];
			int nonPick = dp[i - 1];
			dp[i] = Math.max(pick, nonPick);
		}
		return dp[n - 1];
	}

	public static int space(int n, ArrayList<Integer> nums){
		int prev = nums.get(0), prev2 = 0;
		for(int i = 1; i < n; i++){
			int pick = nums.get(i);
			if(i > 1) pick += prev2;
			int nonPick = prev;
			int curr = Math.max(pick, nonPick);
			prev2 = prev;
			prev = curr;
		}
		return prev;
	}

	public static int maximumNonAdjacentSum(ArrayList<Integer> nums) {
		// Write your code here.
		int n = nums.size();

		// return recursion(n - 1, nums);

		// int[] dp = new int[n];
		// for(int i = 0; i < n; i++)
		// 	dp[i] = -1;
		// return memoization(n - 1, dp, nums);

		// return tabulation(n, nums);

		return space(n, nums);
	}
}
