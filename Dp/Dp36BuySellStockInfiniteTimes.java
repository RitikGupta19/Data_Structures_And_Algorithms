/*
 * Recursion
 * Time Complexity: O(2^n)
 * Space Complexity: O(n) = Recursion stack
 * 
 * Memoization
 * Time Complexity: O(2n)
 * Space Complexity: O(n) = Recursion stack
 * 
 * Tabulation
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 * 
 * Space Optimized
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 * 
 */
public class Dp36BuySellStockInfiniteTimes {
    public static long recursion(int i, boolean buy, int n, long[] p){
        if(i == n) return 0;

        if(buy) return Math.max(-p[i] + recursion(i + 1, false, n, p), recursion(i + 1, true, n, p));
        else return Math.max(p[i] + recursion(i + 1, true, n, p), recursion(i + 1, false, n, p));
    }

    public static long memoization(int i, int buy, int n, long[] p, long[][] dp){
        if(i == n) return 0;
        if(dp[i][buy] != -1) return dp[i][buy];

        if(buy == 1) return dp[i][buy] = Math.max(-p[i] + memoization(i + 1, 0, n, p, dp), memoization(i + 1, 1, n, p, dp));
        else return dp[i][buy] = Math.max(p[i] + memoization(i + 1, 1, n, p, dp), memoization(i + 1, 0, n, p, dp));
    }

    public static long tabulation(int n, long[] p){
        long[][] dp = new long[n + 1][2];
        dp[n][0] = 0;
        dp[n][1] = 0;
        long profit = Integer.MIN_VALUE;

        for(int i = n - 1; i >= 0; i--){
            for(int j = 0; j < 2; j++){
                if(j == 1) dp[i][j] = Math.max(-p[i] + dp[i + 1][0], dp[i + 1][1]);
                else dp[i][j] = Math.max(p[i] + dp[i + 1][1], dp[i + 1][0]);
            }
        }

        return dp[0][1];
    }

    public static long space(int n, long[] p){
        long[] next = new long[2];
        next[0] = 0;
        next[1] = 0;
        long profit = Integer.MIN_VALUE;

        for(int i = n - 1; i >= 0; i--){
            long[] curr = new long[2];
            for(int j = 0; j < 2; j++){
                if(j == 1) curr[j] = Math.max(-p[i] + next[0], next[1]);
                else curr[j] = Math.max(p[i] + next[1], next[0]);
            }
            next = curr;
        }

        return next[1];
    }

    public static long getMaximumProfit (int n, long[] values) {
        // Your code goes here.

        // return recursion(0, true, n, values);

        // long [][] dp = new long[n][2];
        // for(int i = 0; i < n; i++)
        //     for(int j = 0; j < 2; j++)
        //         dp[i][j] = -1;
        // return memoization(0, 1, n, values, dp);

        // return tabulation(n, values);

        return space(n, values);
    }
}
