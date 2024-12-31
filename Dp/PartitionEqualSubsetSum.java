/*
 * Problem: https://leetcode.com/problems/partition-equal-subset-sum/
 * Find whether two equal sum subsets exist in the given array.
 * We have to divide the array into two subsets such that the sum of both subsets is equal.
 * 
 * Approach:
 * If you think about it, it is similar to the SubsetSumK.java problem.
 * We will find the sum of all the elements in the array.
 * We will then find the subset whose sum is half of the total sum.
 * If that subset exists, then the other subset will also have the same sum.
 * 
 * If the sum of the array is odd, then we can't divide it into two equal sum subsets.
 * If the sum of the array is even, then we can divide it into two equal sum subsets.
 * 
 * Space optimization:
 * Time Complexity: O(n*sum)
 * Space Complexity: O(sum) = Dp space
 */
public class PartitionEqualSubsetSum {
    public boolean space(int n, int k, int[] nums){
        boolean[] prev = new boolean[k + 1];
        prev[0] = true;
        if(nums[0] <= k) prev[nums[0]] = true;

        for(int i = 1; i < n; i++){
            boolean[] curr = new boolean[k + 1];
            curr[0] = true;
            for(int t = 1; t <= k; t++){
                boolean take = false;
                if(t - nums[i] >= 0) take = prev[t - nums[i]];
                boolean notake = prev[t];
                curr[t] = take || notake;
            }
            prev = curr;
        }
        return prev[k];
    }
    public boolean canPartition(int[] nums) {
        int sum = 0;
        int n = nums.length;
        for(int i = 0; i < n; i++) sum += nums[i];
        if(sum % 2 != 0) return false;
        return space(n, sum/2, nums);
    }    
}
