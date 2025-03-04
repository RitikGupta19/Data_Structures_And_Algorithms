/**
 * MaxSubarraySum: kadane's algorithm
 * Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.
 * 
 * Approach:
 * 1. Initialize max and sum as Integer.MIN_VALUE and 0 respectively.
 * 2. Traverse the array and add the elements to sum.
 * 3. If sum is greater than max, update max.
 * 4. If sum is less than or equal to 0, reset sum to 0.
 * This step helps us to ignore the negative sum sub-array.
 * As we get a sub-array due to which sum is reduced to 0 or becomes negative, we can ignore that sub-array.
 * 5. Return max.
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
public class MaxSubarraySum {
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        int max = Integer.MIN_VALUE, sum = 0;
        for(int i = 0; i < n; i++){
            sum += nums[i];
            if(max < sum) max = sum;
            if(sum <= 0) sum = 0;
        }
        return max;
    }
}
