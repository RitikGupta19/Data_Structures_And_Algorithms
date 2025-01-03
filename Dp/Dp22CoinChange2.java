/*
 * Problem: we have to get the number of ways to get the target sum by using the coins.
 * Eg: coins = [1,2,5], amount = 5
 * 1+1+1+1+1
 * 1+1+1+2
 * 1+2+2
 * 5
 * Thus, 4 ways to get the target sum.
 * 
 * Recursion:
 * Time Complexity: O(2^n) - NO = exponential as standing on same index.
 * Space Complexity: O(t)
 * 
 * Memoization:
 * Time Complexity: O(n*t)
 * Space Complexity: O(n*t) + O(t) = Dp + Recursion stack
 * 
 * Tabulation:
 * Time Complexity: O(n*t)
 * Space Complexity: O(n*t)
 * 
 * Space:
 * Time Complexity: O(n*t)
 * Space Complexity: O(t)
 */
public class Dp22CoinChange2 {
    public int recursion(int n, int[] c, int t){
        if(n == 0){
            if(t % c[0] == 0) return 1;
            else return 0;
        }
        int notake = recursion(n - 1, c, t);
        int take = 0;
        if(c[n] <= t) take = recursion(n, c, t - c[n]);
        return take + notake;
    }

    public int memoization(int n, int[] c, int t, int[][] dp){
        if(n == 0){
            // This will also handle the case for t == 0 -> return 1
            if(t % c[0] == 0) return 1;
            else return 0;
        }
        if(dp[n][t] != -1) return dp[n][t];
        int notake = memoization(n - 1, c, t, dp);
        int take = 0;
        if(c[n] <= t) take = memoization(n, c, t - c[n], dp);
        return dp[n][t] = take + notake;
    }

    public int tabulation(int n, int[] c, int t){
        int[][] dp = new int[n][t + 1];
        for(int i = 0; i <= t; i++) dp[0][i] = (i % c[0] == 0) ? 1 : 0;

        for(int i = 1; i < n; i++){
            for(int j = 0; j <= t; j++){
                int notake = dp[i - 1][j];
                int take = 0;
                if(c[i] <= j) take = dp[i][j - c[i]];
                dp[i][j] = take + notake;
            }
        }
        return dp[n - 1][t];
    }

    public int space(int n, int[] c, int t){
        int[] prev = new int[t + 1];
        for(int i = 0; i <= t; i++) prev[i] = (i % c[0] == 0) ? 1 : 0;

        for(int i = 1; i < n; i++){
            int[] curr = new int[t + 1];
            for(int j = 0; j <= t; j++){
                int notake = prev[j];
                int take = 0;
                if(c[i] <= j) take = curr[j - c[i]];
                curr[j] = take + notake;
            }
            prev = curr;
        }
        return prev[t];
    }

    public int change(int amount, int[] coins) {
        int n = coins.length;
        // return recursion(n - 1, coins, amount);

        // int[][] dp = new int[n][amount + 1];
        // for(int i = 0; i < n; i++)
        //     for(int j = 0; j <= amount; j++)
        //         dp[i][j] = -1;
        // return memoization(n - 1, coins, amount, dp);

        // return tabulation(n, coins, amount);

        return space(n, coins, amount);
    }
}
