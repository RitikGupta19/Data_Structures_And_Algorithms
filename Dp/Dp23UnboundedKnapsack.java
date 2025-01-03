/*
 * Unbounded Knapsack:
 * Given the items with their weights and profits and a bag with a weight limit W.
 * Each item can be taken any number of times.
 * We need to find the maximum profit that can be achieved.
 * 
 * Approach:
 * We can think of this problem as: We can take the item or not.
 * If we take the item - we can take it again and again.
 * If we do not take the item - we can move to the next item.
 * 
 * Recursion:
 * Time Complexity: O(2^n) - NO = exponential as standing on same index.
 * Space Complexity: O(W) for recursion stack
 * 
 * Memoization:
 * Time Complexity: O(n*W)
 * Space Complexity: O(n*W) + O(W) = Dp + Recursion stack
 * 
 * Tabulation:
 * Time Complexity: O(n*W)
 * Space Complexity: O(n*W)
 * 
 * Space:
 * Time Complexity: O(n*W)
 * Space Complexity: O(W)
 */
public class Dp23UnboundedKnapsack {
    public static int recursion(int ind, int W, int[] p, int[] w){
        if(W == 0) return 0;
        if(ind == 0){
            if(w[0] <= W) return (W / w[0]) * p[0];
            else return -(int)1e8;
        }
        int notake = recursion(ind - 1, W, p, w);
        int take = -(int)1e8;
        if(w[ind] <= W) take = p[ind] + recursion(ind, W - w[ind], p, w);
        return Math.max(take, notake);
    }

    public static int memoization(int ind, int W, int[] p, int[] w, int[][] dp){
        if(W == 0) return 0;
        if(ind == 0){
            if(w[0] <= W) return (W / w[0]) * p[0];
            else return -(int)1e8;
        }
        if(dp[ind][W] != -1) return dp[ind][W];
        int notake = memoization(ind - 1, W, p, w, dp);
        int take = -(int)1e8;
        if(w[ind] <= W) take = p[ind] + memoization(ind, W - w[ind], p, w, dp);
        return dp[ind][W] = Math.max(take, notake);
    }

    public static int tabulation(int n, int W, int[] p, int[] w){
        int[][] dp = new int[n][W + 1];
        dp[0][0] = 0;
        for(int i = 1; i <= W; i++){
            if(w[0] <= i) 
                dp[0][i] = (i / w[0]) * p[0];
            else dp[0][i] = -(int)1e8;
        }
        // Better code for above can be 
        // No dp[0][0] condition
        // for(int i = w[0]; i <= W; i++){
        //     dp[0][i] = (i / w[0]) * p[0];
        // }
        for(int i = 1; i < n; i++){
            for(int j = 0; j <= W; j++){
                int notake = dp[i - 1][j];
                int take = -(int)1e8;
                if(w[i] <= j) take = p[i] + dp[i][j - w[i]];
                dp[i][j] = Math.max(take, notake);
            }
        }
        return dp[n - 1][W];
    }

    public static int space(int n, int W, int[] p, int[] w){
        int[] prev = new int[W + 1];
        for(int i = w[0]; i <= W; i++){
            prev[i] = (i / w[0]) * p[0];
        }
        for(int i = 1; i < n; i++){
            int[] curr = new int[W + 1];
            for(int j = 0; j <= W; j++){
                int notake = prev[j];
                int take = -(int)1e8;
                if(w[i] <= j) take = p[i] + curr[j - w[i]];
                curr[j] = Math.max(take, notake);
            }
            prev = curr;
        }
        return prev[W];
    }

    public static int unboundedKnapsack(int n, int w, int[] profit, int[] weight) {
        // Write your code here.
        // int ans = recursion(n - 1, w, profit, weight);
        

        // int[][] dp = new int[n][w + 1];
        // for(int i = 0; i < n; i++)
        //     for(int j = 0; j <= w; j++)
        //         dp[i][j] = -1;
        // int ans = memoization(n - 1, w, profit, weight, dp);

        // int ans = tabulation(n, w, profit, weight);

        int ans = space(n, w, profit, weight);

        return ans <= -(int)1e8 ? 0 : ans;
    }
}
