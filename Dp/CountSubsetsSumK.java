/*
 * https://www.naukri.com/code360/problems/count-subsets-with-sum-k_3952532?leftPanelTabValue=PROBLEM
 * Problem: find the subset count with sum k
 * 
 * Currently the problem has all the positive values.
 * If there are negative values then we cannot use the array as it does not have negative index.
 * In that case use another Data structure like map.
 * 
 * Recursion:
 * Time Complexity: O(2^n)
 * Space Complexity: O(n) for recursion stack
 * 
 * Memoization:
 * Time Complexity: O(n*sum)
 * Space Complexity: O(n*sum) + O(n) = Dp space + Stack space
 * 
 * Tabulation:
 * Time Complexity: O(n*sum)
 * Space Complexity: O(n*sum) = Dp space
 * 
 * Space optimization:
 * Time Complexity: O(n*sum)
 * Space Complexity: O(sum) = Dp space
 */
public class CountSubsetsSumK {
    public static int recursion(int n, int[] num, int t){
        // Here all the conditions are handles when 1 <= num[i] <= 1000 or 10^ something
        // If 0 <= num[i] <= 1000 then we need to handle the condition like in dp18
        if(t == 0) return 1;
        if(n == 0) return t == num[0] ? 1 : 0;
        int take = 0;
        if(t - num[n] >= 0) take = recursion(n - 1, num, t - num[n]);
        int notake = recursion(n - 1, num, t);
        return take + notake;
    }

    public static int memoization(int n, int[] num, int t, int[][] dp){
        if(t == 0) return 1;
        if(n == 0) return t == num[0] ? 1 : 0;
        if(dp[n][t] != -1) return dp[n][t];
        int take = 0;
        if(t >= num[n]) take = memoization(n - 1, num, t - num[n], dp);
        int notake = memoization(n - 1, num, t, dp); 
        return dp[n][t] = take + notake;   
    }

    public static int tabulation(int n, int t, int[] num){
        int[][] dp = new int[n][t + 1];
        for(int i = 0; i < n; i++)
            dp[i][0] = 1;
        if(num[0] <= t) dp[0][num[0]] = 1;
        for(int i = 1; i < n; i++){
            for(int j = 1; j <= t; j++){
                int take = 0;
                if(j - num[i] >= 0) take = dp[i - 1][j - num[i]];
                int notake = dp[i - 1][j];
                dp[i][j] = take + notake;
            }
        }
        return dp[n - 1][t];
    }

    public static int space(int n, int t, int[] num){
        int[] prev = new int[t + 1];
        prev[0] = 1;
        if(num[0] <= t) prev[num[0]] = 1;
        for(int i = 1; i < n; i++){
            int[] curr = new int[t + 1];
            curr[0] = 1;
            for(int j = 1; j <= t; j++){
                int take = 0;
                if(j >= num[i]) take = prev[j - num[i]];
                int notake = prev[j];
                curr[j] = take + notake;
            }
            prev = curr;
        }
        return prev[t];
    }

    public static int findWays(int num[], int tar) {
        // Write your code here.
        int n = num.length;

        // return recursion(n - 1, num, tar);

        int[][] dp = new int[n][tar + 1];
        for(int i = 0; i < n; i++)
            for(int j = 0; j <= tar; j++)
                dp[i][j] = -1;
        return memoization(n - 1, num, tar, dp);

        // return tabulation(n, tar, num);

        // return space(n, tar, num);
    } 
}

/*
 * Same above question- handles 0 <= num[i] <= 1000 case
 */
public class CountSubsetsSumK{
    public static int mod = (int)(1e9 + 7);
    public static int recursion(int n, int[] num, int t){
        if(n == 0){
            if(t == 0 && num[n] == 0) return 2;
            if(t == 0 || t == num[n]) return 1;
            else return 0;
        }
        int take = 0;
        if(t - num[n] >= 0) take = recursion(n - 1, num, t - num[n]);
        int notake = recursion(n - 1, num, t);
        return (take + notake) % mod;
    }

    public static int memoization(int n, int[] num, int t, int[][] dp){
        if(n == 0){
            if(t == 0 && num[0] == 0) return 2;
            if(t == 0 || t == num[0]) return 1;
            else return 0;
        }
        if(dp[n][t] != -1) return dp[n][t];
        int take = 0;
        if(t >= num[n]) take = memoization(n - 1, num, t - num[n], dp);
        int notake = memoization(n - 1, num, t, dp); 
        return dp[n][t] = (take + notake) % mod;   
    }

    public static int tabulation(int n, int t, int[] num){
        int[][] dp = new int[n][t + 1];
        if(num[0] == 0) dp[0][0] = 2;
        else dp[0][0] = 1;
        if(num[0] != 0 && num[0] <= t) dp[0][num[0]] = 1;
        for(int i = 1; i < n; i++){
            for(int j = 0; j <= t; j++){
                int take = 0;
                if(j - num[i] >= 0) take = dp[i - 1][j - num[i]];
                int notake = dp[i - 1][j];
                dp[i][j] = (take + notake) % mod;
            }
        }
        return dp[n - 1][t];
    }

    public static int space(int n, int t, int[] num){
        int[] prev = new int[t + 1];
        if(num[0] == 0) prev[0] = 2;
        else prev[0] = 1;
        if(num[0] != 0 && num[0] <= t) prev[num[0]] = 1;
        for(int i = 1; i < n; i++){
            int[] curr = new int[t + 1];
            curr[0] = 1;
            for(int j = 0; j <= t; j++){
                int take = 0;
                if(j >= num[i]) take = prev[j - num[i]];
                int notake = prev[j];
                curr[j] = (take + notake) % mod;
            }
            prev = curr;
        }
        return prev[t];
    }

    public static int findWays(int num[], int tar) {
        // Write your code here.
        int n = num.length;

        // return recursion(n - 1, num, tar);

        // int[][] dp = new int[n][tar + 1];
        // for(int i = 0; i < n; i++)
        //     for(int j = 0; j <= tar; j++)
        //         dp[i][j] = -1;
        // return memoization(n - 1, num, tar, dp);

        // return tabulation(n, tar, num);

        return space(n, tar, num);
    }
}