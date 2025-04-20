/**
 * MaxProductSubarray: https://leetcode.com/problems/maximum-product-subarray/description/
 * Find the maximum product of a contiguous subarray in an array of integers.
 * 
 * Approach 1:
 * 1. Arrays can have odd number of negative numbers, or even number of negative numbers.
 * 2. If even number of negative numbers, product can be including all the elements of array.
 * 3. Array can have 0s, we can ignore them. So we can consider max of sub-arrays to the left and right of 0.
 * 
 * Time Complexity: O(n) => n = number of elements
 * Space Complexity: O(1)
 * 
 * Approach 2:
 * 1. We can use the extended version of Kadane's algorithm to find the maximum product subarray.
 * 2. We can keep track of the maximum and minimum product at each position in the array.
 * 3. As minimum product can become maximum product if multiplied with negative number.
 * 4. We can use two variables to keep track of the maximum and minimum product at each position.
 * 
 * Time Complexity: O(n) => n = number of elements
 * Space Complexity: O(1) 
 */
public class MaxProductSubarray {
    public int maxProduct(int[] nums) {
        int n = nums.length;
        int pre = 1, suff = 1;
        int ans = Integer.MIN_VALUE;
        for(int i = 0; i < n; i++){
            if(pre == 0) pre = 1;
            if(suff == 0) suff = 1;

            pre *= nums[i];
            suff *= nums[n - i - 1];

            ans = Math.max(ans, Math.max(pre, suff));
        }   
        return ans;
    }

    // APPROACH 2:
    public int maxProduct(int[] nums) {
        int n = nums.length;
        int max = nums[0], min = nums[0], ans = nums[0];

        for(int i = 1; i < n; i++){
            int temp = Math.max(nums[i], Math.max(nums[i] * max, nums[i] * min));
            min = Math.min(nums[i], Math.min(nums[i] * max, nums[i] * min));
            max = temp;
            ans = Math.max(ans, max);
        }
        return ans;
    }
}
