/*
 * FrogJump Problem: https://www.naukri.com/code360/problems/frog-jump_3621012?leftPanelTabValue=PROBLEM
 * Frog need to reach from the first stair to the nth stair.
 * Frog can jump either 1 or 2 stairs at a time.
 * Find the min. energy needed by frog to do this work.
 * Each stair has some energy value, which is the energy required to jump on that stair in an array.
 * 
 * Recursion:
 * Time Complexity: O(2^n)
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
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
public class FrogJump {
    
    public static int recursion(int n, int[] h){
        if(n == 0) return 0;
        int left = recursion(n - 1, h) + Math.abs(h[n] - h[n - 1]);
        int right = Integer.MAX_VALUE;
        if(n > 1) right = recursion(n - 2, h) + Math.abs(h[n] - h[n - 2]);
        return Math.min(left, right);
    }

    public static int memoization(int n, int[] h, int[] dp){
        if(n == 0) return 0;
        if(dp[n] != -1) return dp[n];
        int left = memoization(n - 1, h, dp) + Math.abs(h[n] - h[n - 1]);
        int right = Integer.MAX_VALUE;
        if(n > 1) right = memoization(n - 2, h, dp) + Math.abs(h[n] - h[n - 2]);
        dp[n] = Math.min(left, right);
        return Math.min(left, right);
    }

    public static int tabulation(int n, int[] h){
        int[] dp = new int[n];
        dp[0] = 0;
        for(int i = 1; i < n; i++){
            int left = dp[i - 1] + Math.abs(h[i] - h[i - 1]);
            int right = Integer.MAX_VALUE;
            if(i > 1) right = dp[i - 2] + Math.abs(h[i] - h[i - 2]);
            dp[i] = Math.min(left, right);
        }
        return dp[n - 1];
    }

    public static int space(int n, int[] h){
        int prev = 0, prev2 = 0;
        for(int i = 1; i < n; i++){
            int left = prev + Math.abs(h[i] - h[i - 1]);
            int right = Integer.MAX_VALUE;
            if(i > 1) right = prev2 + Math.abs(h[i] - h[i - 2]);
            int curr = Math.min(left, right);
            prev2 = prev;
            prev = curr;
        }
        return prev;
    }

    public static int frogJump(int n, int heights[]) {

        return recursion(n - 1, heights);

        int[] dp = new int[n];
        for(int i = 0; i < n; i++)
            dp[i] = -1;
        dp[0] = 0;
        memoization(n - 1, heights, dp);
        return dp[n - 1];

        return tabulation(n, heights);

        return space(n, heights);
    }
}