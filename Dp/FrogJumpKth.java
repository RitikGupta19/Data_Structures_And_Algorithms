/*
 * Frog can jump 1 to k steps:
 * Find the min. energy needed by frog to do this work.
 * Followup for the frog can jum 1/ 2 steps
 * Just added loop instead of trying to calculate for 1 and 2 steps explicitly.
 * 
 * Recursion:
 * Time Complexity: O(k^n)
 * Space Complexity: O(n)
 * 
 * Memoization:
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 * 
 * Tabulation:
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 * 
 * Space Optimized:
 * Not possible as we need to store the energy for k prev steps.
 * So here space would remain same.
 */
public class FrogJumpKth {
    public static int recursion(int n, int k, int[] h){
        if(n == 0) return 0;
        int minEnergy = Integer.MAX_VALUE;
        for(int j = 1; j <= k; j++){
            int jump = Integer.MAX_VALUE;
            if(n - j >= 0) jump = recursion(n - j, k, h) + Math.abs(h[n] - h[n - j]);
            else break;
            minEnergy = Math.min(jump, minEnergy);
        }
        return minEnergy;
    }

    public static int memoization(int n, int k, int[] dp, int[] h){
        if(n == 0) return 0;
        if(dp[n] != -1) return dp[n];
        int minEnergy = Integer.MAX_VALUE;
        for(int j = 1; j <= k; j++){
            int jump = Integer.MAX_VALUE;
            if(n - j >= 0) jump = memoization(n - j, k, dp, h) + Math.abs(h[n] - h[n - j]);
            else break;
            minEnergy = Math.min(jump, minEnergy);
            dp[n] = minEnergy;
        }
        return dp[n];
    }

    public static int tabulation(int n, int k, int[] h){
        int[] dp = new int[n];
        dp[0] = 0;
        for(int i = 1; i < n; i++){
            int minEnergy = Integer.MAX_VALUE;
            for(int j = 1; j <= k; j++){
                int jump = Integer.MAX_VALUE;
                if(i - j >= 0) jump = dp[i - j] + Math.abs(h[i] - h[i - j]);
                else break;
                minEnergy = Math.min(jump, minEnergy);
                dp[i] = minEnergy;
            }
        }
        return dp[n - 1];
    }
    
    public static int minimizeCost(int n, int k, int []height){
        // Write your code here.
        
        return recursion(n - 1, k, height);

        int[] dp = new int[n];
        for(int i = 0; i < n; i++)
            dp[i] = -1;
        return memoization(n - 1, k, dp, height);

        return tabulation(n, k, height);
    }
}
