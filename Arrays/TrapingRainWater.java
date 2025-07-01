/**
 * Trapping rain water:
 * GFG: https://www.geeksforgeeks.org/dsa/trapping-rain-water/
 * 
 * Brute Force:
 * 1. For each element, find the maximum height to the left and right.
 * 
 * Time Complexity: O(n^2)
 * Space Complexity: O(1)
 * 
 * Optimized Approach:
 * 1. Create two arrays, maxLeft and maxRight.
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 * 
 * Two Pointer Approach:
 * 1. Use two pointers, one at the start and one at the end.
 * 2. Maintain two variables, lmax and rmax, to keep track of the maximum heights from the left and right.
 * 3. Move the pointers towards each other, calculating the water trapped at each step.
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
public class TrapingRainWater{
    public int trap(int[] height) {
        int n = height.length;
        int[] maxLeft = new int[n];
        int[] maxRight = new int[n];
        maxLeft[0] = height[0];
        maxRight[n - 1] = height[n - 1];
        for(int i = 1; i < n; i++){
            maxLeft[i] = Math.max(maxLeft[i - 1], height[i]);
            maxRight[n - 1 - i] = Math.max(maxRight[n - i], height[n - 1 - i]);
        }
        int water = 0;
        for(int i = 0; i < n; i++){
            if(Math.min(maxLeft[i], maxRight[i]) - height[i] >= 0)
                water += (Math.min(maxLeft[i], maxRight[i]) - height[i]);
        }

        return water;
    }

    public int trap(int[] height) {
        int n = height.length;
        int lmax = 0, rmax = 0, l = 0, r = n - 1;
        int ans = 0;
        
        while(l <= r){
            // Min of two heights will always decide the water trapped at that point.
            if(height[l] < height[r]){
                // Now if left height is less than right height
                // We have two options: 
                // 1. maximize height from left side (if possible)
                // 2. already maximum, so calculate water trapped
                if(lmax < height[l]) lmax = height[l];
                else ans += lmax - height[l];
                l++;
            }
            else {
                if(rmax < height[r]) rmax = height[r];
                else ans += rmax - height[r];
                r--;
            }
        }

        return ans;
    }
}