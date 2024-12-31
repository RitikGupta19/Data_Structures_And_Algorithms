/*
 * Problem:
 * We have to find if there is a subset of the given array whose sum is equal to the given target.
 * 
 * Recursion:
 * Time Complexity: O(2^n)
 * Space Complexity: O(n) for recursion stack
 * 
 * Memoization:
 * Time Complexity: O(n*target)
 * Space Complexity: O(n*target) + O(n) = Dp space + Stack space
 * 
 * Tabulation:
 * Time Complexity: O(n*target)
 * Space Complexity: O(n*target) = Dp space
 * 
 * Space optimization:
 * Time Complexity: O(n*target)
 * Space Complexity: O(target) = Dp space
 */
public class SubsetSumK {
    public static boolean recursion(int i, int k, int[] arr){
        // We have two parameters on which the base condition depends
        // thus two base conditions
        if(k == 0) return true;
        if(i < 0) return false;
        // or
        // if(i == 0) return a[0] == k;
        boolean take = false;
        if(k - arr[i] >= 0) take = recursion(i - 1, k - arr[i], arr);
        boolean notake = recursion(i - 1, k, arr);
        return take || notake;
    }

    // In memoization we need to take the dp as non boolean
    // dp size is the size of the parameters on which the base condition depends
    public static boolean memoization(int i, int k, int[] arr, int[][] dp){
        if(k == 0) return true;
        if(i == 0) return arr[0] == k;
        if(dp[i][k] != -1) return dp[i][k] == 1 ? true : false;
        boolean take = false;
        if(k - arr[i] >= 0) take = memoization(i - 1, k - arr[i], arr, dp);
        boolean notake = memoization(i - 1, k, arr, dp);
        dp[i][k] = take || notake ? 1 : 0;
        return take || notake;
    }

    public static boolean tabulation(int n, int k, int[] arr){
        boolean[][] dp = new boolean[n][k + 1];
        // Base condition: just think when target will be 0 
        // what are all possible values of index ie. 1,2,....n
        for(int i = 0; i < n; i++) dp[i][0] = true;
        // next base condition for i = 0 , all possible values of target i.e 1,2,3,..n
        // but it will be true only for value arr[0] rest false
        // if arr[0] > k then prevent from exception
        if(arr[0] <= k) dp[0][arr[0]] = true; 

        for(int i = 1; i < n; i++){
            for(int t = 1; t <= k; t++){
                boolean take = false;
                if(t - arr[i] >= 0) take = dp[i - 1][t - arr[i]];
                boolean notake = dp[i - 1][t];
                dp[i][t] = take || notake;
            }
        }
        return dp[n - 1][k];
    }

    public static boolean space(int n, int k, int[] arr){
        boolean[] prev = new boolean[k + 1];
        prev[0] = true;
        // preventing from exception because of out of bound
        if(arr[0] <= k) prev[arr[0]] = true;

        for(int i = 1; i < n; i++){
            boolean[] curr = new boolean[k + 1];
            curr[0] = true;
            for(int t = 1; t <= k; t++){
                boolean take = false;
                if(t - arr[i] >= 0) take = prev[t - arr[i]];
                boolean notake = prev[t];
                curr[t] = take || notake;
            }
            prev = curr;
        }
        return prev[k];
    }
    
    public static boolean subsetSumToK(int n, int k, int arr[]){
        // Write your code here.
        // return recursion(n - 1, k, arr);

        // Since there are two things on which it depends
        // Or two say two parameters which affect the base condition
        // thus 2d DP
        // int[][] dp = new int[n][k + 1];
        // for(int i = 0; i < n; i++) 
        //     for(int j = 0; j <= k; j++){
        //         dp[i][j] = -1;
        //     }
        // return memoization(n - 1, k, arr, dp);

        // return tabulation(n, k, arr);

        return space(n, k, arr);
    }   
}
