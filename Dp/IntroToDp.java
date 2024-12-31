/**
 * Recursion: Top Down approach
 * 
 * Time Complexity: O(2^n)
 * Space Complexity: O(n) => Stack space
 */

class Solution {
    public int fib(int n) {
        if(n <= 1) return n;
        return fib(n -1) + fib(n - 2);
    }
}

/**
 * Memoization: Top Down approach
 * We tend to store the results of the subproblems to avoid redundant calculations.
 * We store them inside Map/Table.
 * 
 * Fibnocci Series: Use 1D array to store the results of the subproblems.
 * Time Complexity: O(n)
 * Space Complexity: O(n) + O(n) = O(n) => Stack space + Table space
 */
class Solution {
    private int fibHelper(int n, int[] dp){
        if(n <= 1) return n;
        if(dp[n] != -1) return dp[n];
        return dp[n] = fibHelper(n - 1, dp) + fibHelper(n - 2, dp);
    }
    public int fib(int n) {
        int[] dp = new int[n + 1];
        for(int i = 0; i <= n; i++)
            dp[i] = -1;
        return fibHelper(n, dp);
    }
}

/**
 * Tabulation: Bottom Up approach
 * Prepared the base case first i.e dp[0] and dp[1]
 * 
 * Time complexity: O(n)
 * Space Complexity: O(n) => Table space
 */
class Solution {
    public int fib(int n) {
        int res = 0;
        if(n <= 1) return n;
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for(int i = 2; i <= n; i++)
            dp[i] = -1;
        for(int i = 2; i <= n; i++){
            res = dp[i] = dp[i - 1] + dp[i - 2];
        }
        return res;
    }
}

/**
 * Space Optimized Tabulation: Bottom Up approach
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
class Solution {
    public int fib(int n) {
        int res = 0;
        if(n <= 1) return n;
        int prev2 = 0;
        int prev = 1;
        for(int i = 2; i <= n; i++){
            res = prev + prev2;
            prev2 = prev;
            prev = res;
        }
        return res;
    }
}