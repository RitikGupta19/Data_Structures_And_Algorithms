/*
 * Problem: https://leetcode.com/problems/house-robber-ii/
 * 
 * Problem is same as the  "MaxSumNonAdj.java" but with a twist.
 * Here the houses are arranged in a circle.
 * This means first and last house are adjacent. Thus, both cannot be the part of the ans.
 * 
 * Approach:
 * We will find the maximum sum of subsequence of array a[0....n-1] and a[1....n].
 * That means calling our old function twice.
 * 
 * Time Complexity: O(2n)
 * Space Complexity: O(1)
 */

public class HouseRobber2 {
    public int space(int n, ArrayList<Integer> arr){
        int prev2 = 0, prev = arr.get(0);
        for(int i = 1; i < n; i++){
            int pick = arr.get(i);
            if(i > 1) pick += prev2;
            int nonpick = prev;
            int curr = Math.max(pick, nonpick);
            prev2 = prev;
            prev = curr;
        }
        return prev;
    }
    public int rob(int[] nums) {
        int n = nums.length;
        if(n == 1) return nums[0];
        ArrayList<Integer> a = new ArrayList<>();
        ArrayList<Integer> b = new ArrayList<>();
        for(int i = 0; i < n; i++){
            if(i != 0) a.add(nums[i]);
            if(i != n - 1) b.add(nums[i]);
        }
        int max1 = space(n - 1, a);
        int max2 = space(n - 1, b);
        return Math.max(max1, max2);
    }
}
