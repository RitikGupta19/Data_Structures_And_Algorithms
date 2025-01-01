/*
 * Problem: find the minimum coins needed to get the required amount.
 * 
 * Approach:
 * Here we will take the coin or not
 * When we take the coin - this time we not need to reduce the index
 * When we do not take the coin - as usual we need to reduce the index
 * 
 * Recursion:
 * Time Complexity: >>O(2^n) = exponential - As we are not always reducing the ind
 * We are keeping it same. Which makes more possible ways instead of 2
 * Space Complexity: O(target) for recursion stack - this is way more
 * as it depends on target, it can be target = 100000000
 * 
 * Memoization:
 * Time Complexity: O(n*target)
 * Space Complexity: O(n*target) + O(target) (way more) = Dp space + Stack space
 * 
 * Tabulation:
 * Time Complexity: O(n*target)
 * Space Complexity: O(n*target) = Dp space
 * 
 * Space optimization:
 * Time Complexity: O(n*target)
 * Space Complexity: O(target) = Dp space
 */
public class Dp20CoinsChangeMinCoins {
    public int recursion(int ind, int[] c, int t){
        if(t == 0) return 0;
        if(ind == 0){
            if(t % c[0] == 0) return t / c[0];
            return (int)1e9;
        }
        int take = (int)1e9;
        if(c[ind] <= t) {
            take = 1 + recursion(ind, c, t - c[ind]);
        }
        int notake = recursion(ind - 1, c, t);
        return Math.min(take, notake);
    }

    public int memoization(int ind, int[] c, int t, int[][] dp){
        if(t == 0) return 0;
        if(ind == 0){
            if(t % c[0] == 0) return t / c[0];
            return (int)1e9;
        }
        if(dp[ind][t] != -1) return dp[ind][t];
        int notake = memoization(ind - 1, c, t, dp);
        int take = (int)1e9;
        if(c[ind] <= t) take = 1 + memoization(ind, c, t - c[ind], dp);
        return dp[ind][t] = Math.min(take, notake);
    }

    public int tabulation(int n, int[] c, int t){
        int[][] dp = new int[n][t + 1];

        for(int i = 0; i <= t; i++){
            if(i % c[0] == 0) dp[0][i] = i / c[0];
            else dp[0][i] = (int)1e9;
        }

        for(int i = 1; i < n; i++){
            for(int j = 0; j <= t; j++){
                int notake = dp[i - 1][j];
                int take = (int)1e9;
                if(c[i] <= j) take = 1 + dp[i][j - c[i]];
                dp[i][j] = Math.min(take, notake);
            }
        }

        return dp[n - 1][t];
    }

    public int space(int n, int[] c, int t){
        int[] prev = new int[t + 1];

        for(int i = 0; i <= t; i++){
            if(i % c[0] == 0) prev[i] = i / c[0];
            else prev[i] = (int)1e9;
        }

        for(int i = 1; i < n; i++){
            int[] curr = new int[t + 1];
            for(int j = 0; j <= t; j++){
                int notake = prev[j];
                int take = (int)1e9;
                if(c[i] <= j) take = 1 + curr[j - c[i]];
                curr[j] = Math.min(take, notake);
            }
            prev = curr;
        }
        return prev[t];
    }

    public int coinChange(int[] coins, int amount) {
        int n = coins.length;
        Arrays.sort(coins);
        
        // int ans = recursion(n - 1, coins, amount);
        
        // int[][] dp = new int[n][amount + 1];
        // for(int i = 0; i < n; i++)
        //     for(int j = 0; j <= amount; j++)
        //         dp[i][j] = -1;
        // int ans = memoization(n - 1, coins, amount, dp);

        // int ans = tabulation(n, coins, amount);

        int ans = space(n, coins, amount);
        
        if(ans >= (int)1e9) return -1;
        return ans;
    }
}
