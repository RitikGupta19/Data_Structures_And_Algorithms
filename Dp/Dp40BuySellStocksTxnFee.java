public class Dp40BuySellStocksTxnFee {
    public static int memoization(int i, int buy, int n, int[] p, int[][] dp, int fee){
        if(i == n) return 0;
        if(dp[i][buy] != -1) return dp[i][buy];

        if(buy == 1) return dp[i][buy] = Math.max(-p[i] + memoization(i + 1, 0, n, p, dp, fee), memoization(i + 1, 1, n, p, dp, fee));
        return dp[i][buy] = Math.max(p[i] - fee + memoization(i + 1, 1, n, p, dp, fee), memoization(i + 1, 0, n, p, dp, fee));
    }

    public static int tabulation(int n, int[] p, int fee){
        int[][] dp = new int[n + 1][2];

        for(int i = n - 1; i >= 0; i--){
            for(int j = 1; j >= 0; j--){
                if(j == 1) dp[i][j] = Math.max(-p[i] + dp[i + 1][0], dp[i + 1][1]);
                else dp[i][j] = Math.max(p[i] - fee + dp[i + 1][1], dp[i + 1][0]);
            }
        }
        return dp[0][1];
    }

    public static int maximumProfit(int[] prices, int n, int fee) {
        // Write your code here.
        // int[][] dp = new int[n][2];
        // for(int i = 0; i < n; i++){
        //     for(int j = 0; j < 2; j++)
        //         dp[i][j] = -1;
        // }
        // return memoization(0, 1, n, prices, dp, fee);

        return tabulation(n, prices, fee);
    }
}
