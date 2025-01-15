/*
 * Recursion:
 * Time Complexity: O(2^n)
 * Space Complexity: O(n) = Recursion stack
 * 
 * Memoization:
 * Time Complexity: O(n*2*3)
 * Space Complexity: O(n*2*3) + O(n) = Dp array + Recursion stack
 * 
 * Tabulation:
 * Time Complexity: O(n*2*3)
 * Space Complexity: O(n*2*3)
 * 
 * Space Optimized:
 * Time Complexity: O(n*2*3)
 * Space Complexity: O(2*3)
 * 
 * Can think of doing txn B S B S
 *                        0 1 2 3
 * dp[n][4] instead of dp[n][2][3]
 */
public class Dp37BuySellStockTwice {
    public static int memoization(int i, int buy, int n, int[] p, int[][][] dp, int txn){
        if(txn == 0 || i == n) return 0;
        if(dp[i][buy][txn] != -1) return dp[i][buy][txn];

        if(buy == 1) return dp[i][buy][txn] = Math.max(-p[i] + memoization(i + 1, 0, n, p, dp, txn), memoization(i + 1, 1, n, p, dp, txn));
        return dp[i][buy][txn] = Math.max(p[i] + memoization(i + 1, 1, n, p, dp, txn - 1), memoization(i + 1, 0, n, p, dp, txn));
    }

    public static int tabulation(int n, int[] p){
        int[][][] dp = new int[n + 1][2][3];

        for(int i = n - 1; i >= 0; i--){
            for(int j = 0; j < 2; j++){
                for(int k = 1; k < 3; k++){
                    if(j == 1) dp[i][j][k] = Math.max(-p[i] + dp[i + 1][0][k], dp[i + 1][1][k]);
                    else dp[i][j][k] = Math.max(p[i] + dp[i + 1][1][k - 1], dp[i + 1][0][k]);
                }
            }
        }

        return dp[0][1][2];
    }


    public static int space(int n, int[] p){
        int[][] next = new int[2][3];
        int[][] curr = new int[2][3];

        for(int i = n - 1; i >= 0; i--){
            for(int j = 0; j < 2; j++){
                for(int k = 1; k < 3; k++){
                    if(j == 1) curr[j][k] = Math.max(-p[i] + next[0][k], next[1][k]);
                    else curr[j][k] = Math.max(p[i] + next[1][k - 1], next[0][k]);
                }
            }
            next = curr;
        }

        return next[1][2];
    }

    public static int maxProfit(int[] prices) {
        // Write your code here.
        int n = prices.length;
        // int [][][] dp = new int[n][2][3];
        // for(int i = 0; i < n; i++)
        //     for(int j = 0; j < 2; j++)
        //         for(int k = 0; k < 3; k++)
        //             dp[i][j][k] = -1;
        // return memoization(0, 1, n, prices, dp, 2);

        // return tabulation(n, prices);

        return space(n, prices);
    }
}
