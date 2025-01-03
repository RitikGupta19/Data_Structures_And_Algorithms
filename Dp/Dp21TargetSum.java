/*
 * Problem: 
 * We need to assign a '+' or '-' sign to each element in the array.
 * We need to find the number of ways to get the target sum by doing this.
 * Eg: [1,1,1,1,1], target = 3
 * 1+1+1+1-1 = 3
 * 1+1+1-1+1 = 3
 * 1+1-1+1+1 = 3
 * 1-1+1+1+1 = 3
 * -1+1+1+1+1 = 3
 * 5 ways to get the target sum.
 * 
 * Approach:
 * We can think of doing in this way that we try to take either + or - and do the recursion
 * on this pattern, then memoization, then tabulation and lastly space.
 * Here + (similar to take) and - (similar to notake)
 * 
 * Another Approach:
 * We can think of this problem as: We need to divide the array into two subsets
 * such that the difference between the sum of the two subsets is equal to the target.
 * S1 = consisting of all the elements with + sign
 * S2 = consisting of all the elements with - sign
 * S1 - S2 = target
 * Now we can see this is similar to the old problem CountSubsetSumK.java
 * 
 * Time Complexity: O(n*sum)
 * Space Complexity: O(n*sum) = Dp space
 */
public class Dp21TargetSum {
    public int space(int n, int[] nums, int t){
        int[] prev = new int[t + 1];
        if(nums[0] == 0) prev[0] = 2;
        else prev[0] = 1;
        if(nums[0] != 0 && nums[0] <= t) prev[nums[0]] = 1;
        for(int i = 1; i < n; i++){
            int[] curr = new int[t + 1];
            for(int j = 0; j <= t; j++){
                int notake = prev[j];
                int take = 0;
                if(nums[i] <= j) take = prev[j - nums[i]];
                curr[j] = take + notake;
            }
            prev = curr;
        }
        return prev[t];
    }
    public int findTargetSumWays(int[] nums, int target) {
        int n = nums.length;
        int sum = 0;
        for(int i = 0; i < n; i++) sum += nums[i];
        if(sum - target < 0 || (sum - target) % 2 != 0) return 0;
        return space(n, nums, (sum - target) / 2);
    }   
}
