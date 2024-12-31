/* 
 * Shortcut:
 * 1. Represent in terms of index: We will mark each step of staircase as index.
 * We need to go from 0,1,2....nth step.
 * 2. Do all possible stuff:
 *  - if n == 0 || n == 1 -> we have 1 way (We can take 1 step at a time.)
 *  - the reduce f(n - 1) and f(n - 2)
 *  - total ways: sum the ways.
 * 
 * This problem is very similar to the Fibonacci series.
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(n) => Stack space
 */
class StaircaseProblem{
    public int helper(int n, int[] dp){
        // In the 0th: nothing can be done so 1 way possible
        // In the 1st: only 1 way to go to '0' - so one way possible
        // Base cases for the recursion (n == 1 is to prevent n-2 = -1).
        if(n == 0 || n == 1) return 1;
        if(dp[n] != -1) return dp[n];
        dp[n] = helper(n - 1, dp) + helper(n - 2, dp);
        return dp[n];
    }
    public int climbStairs(int n) {
        int[] dp = new int[n + 1];
        for(int i = 0; i <= n; i++)
            dp[i] = -1;
        int ways = helper(n, dp);
        return ways;
    }
}
