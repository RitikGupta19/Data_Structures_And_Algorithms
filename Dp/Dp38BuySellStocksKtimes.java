public class Dp38BuySellStocksKtimes {
    public static int memoization(int i, int buy, int n, int[] p, int[][][] dp, int txn, int k){
        if(txn == k || i == n) return 0;
        if(dp[i][buy][txn] != -1) return dp[i][buy][txn];

        if(buy == 1) return dp[i][buy][txn] = Math.max(-p[i] + memoization(i + 1, 0, n, p, dp, txn, k), memoization(i + 1, 1, n, p, dp, txn, k));
        return dp[i][buy][txn] = Math.max(p[i] + memoization(i + 1, 1, n, p, dp, txn + 1, k), memoization(i + 1, 0, n, p, dp, txn, k));
    }

    // Another type
    public static int memoization(int i, int buy, int n, int[] p, int[][][] dp, int txn, int k){
        if(txn == k || i == n) return 0;
        if(dp[i][buy][txn] != -1) return dp[i][buy][txn];

        if(buy == 1) return dp[i][buy][txn] = Math.max(-p[i] + memoization(i + 1, 0, n, p, dp, txn, k), memoization(i + 1, 1, n, p, dp, txn, k));
        return dp[i][buy][txn] = Math.max(p[i] + memoization(i + 1, 1, n, p, dp, txn + 1, k), memoization(i + 1, 0, n, p, dp, txn, k));
    }

    public static int tabulation(int n, int[] p, int k){
        int[][] dp = new int[n + 1][k + 1];
        
        for(int i = n - 1; i >= 0; i--){
            for(int j = k - 1; j >= 0; j--){
                if(j % 2 == 0) dp[i][j] = Math.max(-p[i] + dp[i + 1][j + 1], dp[i + 1][j]);
                else dp[i][j] = Math.max(p[i] + dp[i + 1][j + 1], dp[i + 1][j]);
            }
        }

        return dp[0][0];
    }

    public static int maximumProfit(int[] prices, int n, int l)
    {
        // Write your code here.
        int [][][] dp = new int[n][2][l + 1];
        for(int i = 0; i < n; i++)
            for(int j = 0; j < 2; j++)
                for(int k = 0; k < l; k++)
                    dp[i][j][k] = -1;
        return memoization(0, 1, n, prices, dp, 0, l);

        // return tabulation(n, prices);
    }
}
