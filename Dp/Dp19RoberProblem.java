/**
 * We cannot use Greedy approach as it doesn't guarantee the optimal solution.
 * As we do not see a uniformity in data.
 * Thus, we will try to think all possible ways to reach the destination.
 * Which can be done using recursion.
 * 
 * Express everything in (index, wt).
 * Explore all possibilities - pick / non-pick.
 * Max of all possibilities/choices.
 * 
 * Imp: Try to think base case for a single input
 * Eg: only one item in home so he can pick that only if bag allows else not.
 * 
 * Time Complexity: O(2^n)
 * Space Complexity: O(n) => Stack space
 */
static int helper(int[] wt, int[] val, int i, int W){
    if(i == 0){
        if(wt[i] <= W) return val[i];
        return 0;
    }
    int notTaken = 0 + helper(wt, val, i - 1, W);
    int taken = Integer.MIN_VALUE;
    if(wt[i] <= W){
        taken = val[i] + helper(wt, val, i - 1, W - wt[i]);
    }

    return Math.max(notTaken, taken);
}

/**
 * DP Approach:
 * What are changing parameters? index and wt
 * so we will make 2D array with index and wt.
 * 
 * Time Complexity: O(n * W) => There are N*W subproblems that will be solved at max
 * Space Complexity: O(n * W) + O(n) => Table space + Stack space
 */
public class Solution{
    static int helper(int[] wt, int[] val, int i, int W, int[][] dp){
        if(i == 0){
            if(wt[i] <= W) return val[i];
            return 0;
        }
        if(dp[i][W] != -1) return dp[i][W];

        int notTaken = 0 + helper(wt, val, i - 1, W, dp);
        int taken = Integer.MIN_VALUE;
        if(wt[i] <= W){
            taken = val[i] + helper(wt, val, i - 1, W - wt[i], dp);
        }

        dp[i][W] = Math.max(notTaken, taken);
        return dp[i][W];
    }
    static int knapsack(int[] weight, int[] value, int n, int maxWeight) {
            int[][] dp = new int[n][maxWeight + 1];
            for(int[] row: dp)
                Arrays.fill(row, -1);
            return helper(weight, value, n - 1, maxWeight, dp);

    }
}

/**
 * Tabulation: Bottom Up approach
 * 
 * Rules for tabulation:
 * 1. Write the base case
 * 2. Write the loops for each changing parameters
 * 3. Copy the recurrence
 * 
 * Time Complexity: O(n * W)
 * Space Complexity: O(n * W) => Only Table space
 */
public class Solution{
    static int knapsack(int[] weight, int[] value, int n, int maxWeight) {
            int[][] dp = new int[n][maxWeight + 1];
            for(int[] row: dp)
                Arrays.fill(row, 0);
            // required weights in sorted order
            for(int W = weight[0]; W <= maxWeight; W++){
                dp[0][W] = value[0];
            }
            for(int ind = 1; ind < n; ind++){
                for(int W = 0; W <= maxWeight; W++){
                    int notTaken = 0 + dp[ind - 1][W];
                    int taken = Integer.MIN_VALUE;
                    if(weight[ind] <= W){
                        taken = value[ind] + dp[ind - 1][W - weight[ind]];
                    }

                    dp[ind][W] = Math.max(notTaken, taken);
                }
            }
            return dp[n - 1][maxWeight];
    }
}

/**
 * Space Optimized DP:
 * We can see that we are only using the previous row values.
 * So, we can optimize the space.
 * We can keep two 1D arrays and keep updating them - curr and prev
 * 
 * Time Complexity: O(n * W)
 * Space Complexity: O(W) => Only 2 arrays
 */
public class Solution{
    static int knapsack(int[] wt, int[] val, int n, int maxWt) {
            int[] curr = new int[maxWt + 1];
            int[] prev = new int[maxWt + 1];
            // required weights in sorted order
            for(int W = wt[0]; W <= maxWt; W++) prev[W] = val[0];

            for(int ind = 1; ind < n; ind++){
                for(int W = maxWt; W >= 0; W--){
                    int notTaken = prev[W];
                    int taken = Integer.MIN_VALUE;
                    if(wt[ind] <= W){
                        taken = val[ind] + prev[W - wt[ind]];
                    }
                    curr[W] = Math.max(taken, notTaken);
                }
                prev = curr;
            }
            return prev[maxWt];
    }
}

/**
 * Optimized solution to single 1D array
 * 
 * Time Complexity: O(n * W)
 * Space Complexity: O(W) => Only 1 array
 */
public class Solution{
    static int knapsack(int[] wt, int[] val, int n, int maxWt) {
            int[] prev = new int[maxWt + 1];
            // required weights in sorted order
            for(int W = wt[0]; W <= maxWt; W++) prev[W] = val[0];

            for(int ind = 1; ind < n; ind++){
                for(int W = maxWt; W >= 0; W--){
                    int notTaken = prev[W];
                    int taken = Integer.MIN_VALUE;
                    if(wt[ind] <= W){
                        taken = val[ind] + prev[W - wt[ind]];
                    }
                    prev[W] = Math.max(taken, notTaken);
                }
            }
            return prev[maxWt];
    }
}