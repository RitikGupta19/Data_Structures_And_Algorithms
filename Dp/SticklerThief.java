/*
 * Problem: https://www.geeksforgeeks.org/problems/stickler-theif-1587115621/1?itm_source=geeksforgeeks&itm_medium=article&itm_campaign=practice_card

    Problem is similar to the "MaxSumNonAdj.java" 

    Approach:
    We will either pick the current element or not pick it.
    If we pick the current element, we will not pick the previous element.

    Recurrence Relation:
    TC: O(2^n)
    SC: O(n)

    Memoization:
    TC: O(n)
    SC: O(n)

    Tabulation:
    TC: O(n)
    SC: O(n)

    Space Optimized:
    TC: O(n)
    SC: O(1)
 */

 class Solution {
    // Function to find the maximum money the thief can get.
    public int recursion(int i, int[] arr){
        if(i == 0) return arr[0];
        
        int take = arr[i];
        if(i >= 2) take += recursion(i - 2, arr);
        int notake = recursion(i - 1, arr);
        
        return Math.max(take, notake);
    }
    
    public int memoization(int i, int[] arr, int[] dp){
        if(i == 0) return arr[0];
        if(dp[i] != -1) return dp[i];
        
        int take = arr[i];
        if(i >= 2) take += memoization(i - 2, arr, dp);
        int notake = memoization(i - 1, arr, dp);
        
        return dp[i] = Math.max(take, notake);
    }
    
    public int tabulation(int n, int[] arr){
        int[] dp = new int[n];
        dp[0] = arr[0];
        
        for(int i = 1; i < n; i++){
            int take = arr[i];
            if(i >= 2) take += dp[i - 2];
            int notake = dp[i - 1];
            dp[i] = Math.max(take, notake);
        }
        return dp[n - 1];
    }
    
    public int space(int n, int[] arr){
        int prev = arr[0], prev2 = 0;
        
        for(int i = 1; i < n; i++){
            int take = arr[i];
            if(i >= 2) take += prev2;
            int notake = prev;
            int curr = Math.max(take, notake);
            prev2 = prev;
            prev = curr;
        }
        return prev;
    }
    
    public int findMaxSum(int arr[]) {
        // Your code here
        int n = arr.length;
        // return recursion(n - 1, arr);
        
        // int[] dp = new int[n];
        // for(int i = 0; i < n; i++) dp[i] = -1;
        // return memoization(n - 1, arr, dp);
        
        // return tabulation(n, arr);
        
        return space(n, arr);
    }
}