/*
 * Problem: to find the longest palindromic subsequence
 * 
 * Approach:
 * Can we think of reversing the string and then finding the longest common subsequence
 * If we reverse the string
 * Then we will get a string that is present in both strings in same order
 * That will be our palindromic subsequence and automatically the largest
 * 
 * Recursion:
 * Time Complexity: O(2^n)
 * Space Complexity: O(n) = Recursion stack
 * 
 * Memoization:
 * Time Complexity: O(n^2)
 * Space Complexity: O(n^2) + O(n) = Dp array + Recursion stack
 * 
 * Tabulation:
 * Time Complexity: O(n^2)
 * Space Complexity: O(n^2)
 * 
 * Space Optimized:
 * Time Complexity: O(n^2)
 * Space Complexity: O(n)
 */
public class Dp28LongestPalindromicSubsequence{
    public static int memoization(int i, int j, String a, String b, int[][] dp){
		if(i < 0 || j < 0) return 0;
		if(dp[i][j] != 0) return dp[i][j];
		if(a.charAt(i) == b.charAt(j)) return dp[i][j] = 1 + memoization(i - 1, j - 1, a, b, dp);
		return dp[i][j] = Math.max(memoization(i - 1, j, a, b, dp), memoization(i, j - 1, a, b, dp));
	}

	public static int tabulation(String a, String b, int n){
		int[][] dp = new int[n + 1][n + 1];
		for(int i = 0; i <= n; i++) dp[0][i] = 0;
		for(int i = 0; i <= n; i++) dp[i][0] = 0;
		for(int i = 1; i <= n; i++){
			for(int j = 1; j <= n; j++){
				if(a.charAt(i - 1) == b.charAt(j - 1)) dp[i][j] = 1 + dp[i - 1][j - 1];
				else dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
			}
		}
		return dp[n][n];
	}

	public static int longestPalindromeSubsequence(String s) {
		// Write your code here.
		int n = s.length();
		int start = 0, end = n - 1;
		char[] arr = s.toCharArray();
		while(start < end){
			char t = arr[start];
			arr[start] = arr[end];
			arr[end] = t;
			start++;
			end--;
		}

		String s2 = new String(arr);

		// int[][] dp = new int[n][n];
		// for(int i = 0; i < n; i++)
		// 	for(int j = 0; j < n; j++)
		// 		dp[i][j] = 0;
		// return memoization(n - 1, n - 1, s, s2, dp);

		return tabulation(s, s2, n);
	}
}

/*
 * MY SOLUTION:
 * 
 * Approach: 
 * Hint: to use the concept of longest common subsequence
 * 
 * To use two pointers one at start and one at end
 * If start == end then we will increase the length by 1 and move both pointers
 * If both characters are same then we will increase the length by 2 and move both pointers
 * If both characters are different then we will take max of two cases
 * 
 * Recursion:
 * Time Complexity: O(2^n)
 * Space Complexity: O(n) = Recursion stack
 * 
 * Memoization:
 * Time Complexity: O(n^2)
 * Space Complexity: O(n^2) + O(n) = Dp array + Recursion stack
 * 
 * Tabulation:
 * Time Complexity: O(n^2)
 * Space Complexity: O(n^2)
 */
public class Dp28LongestPalindromicSubsequence {
    public int recursion(int start, int end, String s){
        if(start > end) return 0;
        if(start == end) return 1;
        if(s.charAt(start) == s.charAt(end)) return 2 + recursion(start + 1, end - 1, s);
        return Math.max(recursion(start + 1, end, s), recursion(start, end - 1, s));
    }

    public int memoization(int start, int end, String s, int[][] dp){
        if(start > end) return 0;
        if(dp[start][end] != 0) return dp[start][end];
        if(start == end) return dp[start][end] = 1;
        if(s.charAt(start) == s.charAt(end)) return dp[start][end] = 2 + memoization(start + 1, end - 1, s, dp);
        return dp[start][end] = Math.max(memoization(start + 1, end, s, dp), memoization(start, end - 1, s, dp));
    }

    public int tabulation(String s, int n){
        int[][] dp = new int[n][n];
        for(int i = 0; i < n; i++) dp[i][i] = 1;
        for(int i = n - 2; i >= 0; i--){
            for(int j = i + 1; j < n; j++){
                if(s.charAt(i) == s.charAt(j)) dp[i][j] = 2 + dp[i + 1][j - 1];
                else dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
            }
        }
        return dp[0][n - 1];
    }

    public int longestPalindromeSubseq(String s) {
        int n = s.length();

        // return recursion(0, n - 1, s);

        // int[][] dp = new int[n][n];
        // for(int i = 0; i < n; i++)
        //     for(int j = 0; j < n; j++)
        //         dp[i][j] = 0;
        // return memoization(0, n - 1, s, dp);

        return tabulation(s, n);
    }    
}
