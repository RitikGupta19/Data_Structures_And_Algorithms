/*
 * Problem: we need to count the possible subsets such that the difference of the two subsets is equal to the given difference.
 * 
 * https://www.naukri.com/code360/problems/partitions-with-given-difference_3751628
 * 
 * Let say Arr = [] needs to be divided into subsets s1 and s2 
 * such that their difference is 'd' and union of them makes arr.
 * 
 * Approach: 
 * s1 - s2 = d
 * s1 = d + s2
 * 
 * s1 + s2 = sum
 * s2 = sum - s1
 * 
 * s2 = sum - (d + s2)
 * s2 = (sum - d) / 2
 * 
 * we need to find the subsets with (sum - d) / 2 as target just like dp17.
 * We have sum , d.
 * 
 * Recursion:
 * Time Complexity: O(2^n)
 * Space Complexity: O(n) for recursion stack
 * 
 * Memoization:
 * Time Complexity: O(n*sum)
 * Space Complexity: O(n*sum) + O(n) = Dp space + Stack space
 * 
 * Tabulation:
 * Time Complexity: O(n*sum)
 * Space Complexity: O(n*sum) = Dp space
 * 
 * Space optimization:
 * Time Complexity: O(n*sum)
 * Space Complexity: O(sum) = Dp space
 */
public class Dp18CountPartitionWithGivenDiff {
    public static int mod = (int)(1e9 + 7);
	public static int recursion(int n, int t, int[] arr){
        // Handled the condition when arr[0] = 0
        // Since 0 <= arr[0] <= 10^9
		if(n == 0){
            // If target == 0 and first element is also 0
            // then two possible subsets are there.
            // One without '0' element , one with it.
			if(t == 0 && arr[0] == 0) return 2;
            // Other possible case t == 0 or arr[0] == t
            // then it could be 1 possible subset
			if(t == 0 || arr[0] == t) return 1;
			else return 0;
		}
		return (recursion(n - 1, t - arr[n], arr) + recursion(n - 1, t, arr)) % mod;
	}

	public static int memoization(int n, int t, int[] arr, int[][] dp){
		if(n == 0){
			if(t == 0 && arr[0] == 0) return 2;
			if(t == 0 || arr[0] == t) return 1;
			else return 0;
		}
		if(dp[n][t] != -1) return dp[n][t];
		int take = 0;
		if(t >= arr[n]) take = memoization(n - 1, t - arr[n], arr, dp);
		int notake = memoization(n - 1, t, arr, dp);
		return dp[n][t] = (take + notake) % mod;
	}

	public static int tabulation(int n, int t, int[] arr){
		int[][] dp = new int[n][t + 1];
		if(arr[0] == 0) dp[0][0] = 2;
		else dp[0][0] = 1;
		if(arr[0] != 0 && arr[0] <= t) dp[0][arr[0]] = 1;
		for(int i = 1; i < n; i++){
            for(int j = 0; j <= t; j++){
                int take = 0;
                if(j >= arr[i]) take = dp[i - 1][j - arr[i]];
                int notake = dp[i - 1][j];
                dp[i][j] = (take + notake) % mod;
            }
        }
        return dp[n - 1][t];
	}

	public static int space(int n, int t, int[] arr){
		int[] prev = new int[t + 1];
		if(arr[0] == 0) prev[0] = 2;
		else prev[0] = 1;
		if(arr[0] != 0 && arr[0] <= t) prev[arr[0]] = 1;
		for(int i = 1; i < n; i++){
			int[] curr = new int[t + 1];
			curr[0] = 1;
			for(int j = 0; j <= t; j++){
				int take = 0;
				if(j >= arr[i]) take = prev[j - arr[i]];
				int notake = prev[j];
				curr[j] = (take + notake) % mod;
			}
			prev = curr;
		}
		return prev[t];
	}

	public static int countPartitions(int n, int d, int[] arr) {
		// Write your code here.
		int sum = 0;
		for(int i = 0; i < n; i++) sum += arr[i];

		int t = (sum - d) / 2;
		if((sum - d) < 0 || (sum - d) % 2 != 0) return 0;
		
		// return recursion(n - 1, t, arr);

		// int[][] dp = new int[n][t + 1];
		// for(int i = 0; i < n; i++){
		// 	for(int j = 0; j <= t; j++){
		// 		dp[i][j] = -1;
		// 	}
		// }
		// return memoization(n - 1, t, arr, dp);

		return tabulation(n, t, arr);

		// return space(n, t, arr);8

	}
}
