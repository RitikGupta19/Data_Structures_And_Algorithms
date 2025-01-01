/*
 * Problem: 0/1 Knapsack problem
 * Maximize the value of items that can be put in a knapsack of capacity W.
 * 
 * Here the base condition will be determined by array index and W.
 * We will take an object or not.
 * We will add the value of the object in the knapsack and reduce the weight.
 * 
 * Recursion:
 * Time Complexity: O(2^n)
 * Space Complexity: O(n) for recursion stack
 * 
 * Memoization:
 * Time Complexity: O(n*W)
 * Space Complexity: O(n*W) + O(n) = Dp space + Stack space
 * 
 * Tabulation:
 * Time Complexity: O(n*W)
 * Space Complexity: O(n*W) = Dp space
 * 
 * Space optimization:
 * Time Complexity: O(n*W)
 * Space Complexity: O(W) = Dp space
 * 
 * More space optimization:
 * Time Complexity: O(n*W)
 * Space Complexity: O(W) = Dp space - single array - no curr array
 * In this approach we observed that while filling the curr row
 * We filled the elements from the previous row only.
 * Also, j - wt[i] is always less than j in the prev row. (Left part of j (in prev) & not the right one).
 * If we start filling from last to first then we can use the same array.
 * As from filling last to first we will be using the left side values only and not the right ones.
 */
public class Knapsack01 {
    static int recursion(int ind, int[] wt, int[] val, int maxw){
        if(maxw == 0) return 0; // can be ignored
        if(ind == 0) return maxw >= wt[0] ? val[0] : 0;
        int take = Integer.MIN_VALUE;
        if(maxw >= wt[ind]) take = val[ind] + recursion(ind - 1, wt, val, maxw - wt[ind]);
        int notake = recursion(ind - 1, wt, val, maxw);
        return Math.max(take, notake);
    }

    static int memoization(int ind, int[] wt, int[] val, int maxw, int[][] dp){
        if(maxw == 0) return 0; // can be ignored
        if(ind == 0) return wt[0] <= maxw ? val[0] : 0;
        if (dp[ind][maxw] != -1) return dp[ind][maxw];
        int take = Integer.MIN_VALUE;
        if(maxw >= wt[ind]) take = val[ind] + memoization(ind - 1, wt, val, maxw - wt[ind], dp);
        int notake = memoization(ind - 1, wt, val, maxw, dp);
        dp[ind][maxw] = Math.max(take, notake);
        return dp[ind][maxw];
    }

    static int tabulation(int n, int[] wt, int[] val, int maxw){
        int[][] dp = new int[n][maxw + 1];
        if(maxw == 0) dp[0][0] = 0; // can be ignored
        
        // just populating the first row
        for(int i = wt[0]; i <= maxw; i++) dp[0][i] = val[0];

        for(int i = 1; i < n; i++){
            for(int j = 0; j <= maxw; j++){
                int take = Integer.MIN_VALUE;
                if(wt[i] <= j) take = val[i] + dp[i - 1][j - wt[i]];
                int notake = dp[i - 1][j];
                dp[i][j] = Math.max(take, notake);
            }
        }
        return dp[n - 1][maxw];
    }

    static int space(int n, int[] wt, int[] val, int maxw){
        int[] prev = new int[maxw + 1];
        if(maxw == 0) prev[0] = 0; // can be ignored
        
        // just populating the first row
        for(int i = wt[0]; i <= maxw; i++) prev[i] = val[0];

        for(int i = 1; i < n; i++){
            int[] curr = new int[maxw + 1];
            for(int j = 0; j <= maxw; j++){
                int take = Integer.MIN_VALUE;
                if(wt[i] <= j) take = val[i] + prev[j - wt[i]];
                int notake = prev[j];
                curr[j] = Math.max(take, notake);
            }
            prev = curr;
        }
        return prev[maxw];
    }

    static int moreSpace(int n, int[] wt, int[] val, int maxw){
        int[] prev = new int[maxw + 1];
        if(maxw == 0) prev[0] = 0; // can be ignored
        
        // just populating the first row
        for(int i = wt[0]; i <= maxw; i++) prev[i] = val[0];

        for(int i = 1; i < n; i++){
            for(int j = maxw; j >= 0; j--){
                int take = Integer.MIN_VALUE;
                if(wt[i] <= j) take = val[i] + prev[j - wt[i]];
                int notake = prev[j];
                prev[j] = Math.max(take, notake);
            }
        }
        return prev[maxw];
    }

    static int knapsack(int[] weight, int[] value, int n, int maxWeight) {
        // return recursion(n - 1, weight, value, maxWeight);

        // int[][] dp = new int[n][maxWeight + 1];
        // for(int i = 0; i < n; i++)
        //     for(int j = 0; j <= maxWeight; j++)
        //         dp[i][j] = -1;
        // return memoization(n - 1, weight, value, maxWeight, dp);

        // return tabulation(n, weight, value, maxWeight);

        // return space(n, weight, value, maxWeight);

        return moreSpace(n, weight, value, maxWeight);
    }
}
