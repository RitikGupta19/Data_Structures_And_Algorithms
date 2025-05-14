/**
 * Problem: Given an array of integers nums and an integer k, find the longest subarray with sum k.
 * 
 * Approach:
 * 1. Brute Force: O(n^2)
 *  - Iterate through all possible subarrays and check if their sum equals k.
 *  - Use two nested loops to calculate the sum of each subarray.
 * 
 * 2. Optimal: O(n) prefix sum
 */
public class LongestSubarraySumK {
    public int longestSubarray(int[] arr, int k) {
        // APPROACH: BRUTE FORCE - NESTED LOOPS
        int n = arr.length;
        int max = 0;
        
        for(int i = 0; i < n; i++){
            int sum = 0;
            for(int j = i; j < n; j++){
                sum += arr[j];
                if(sum == k) {
                    max = Math.max(max, j - i + 1);
                }
            }
        }
        return max;
        
        // APPROACH: OPTIMAL - HASHMAP + PREFIX SUM
        int n = arr.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        int sum = 0, max = 0;
        for(int i = 0; i < n; i++){
            sum += arr[i];
            // As we do not have a map with (0,1) entry
            if(sum == k) max = Math.max(max, i + 1);
            // either above or below (only one possible)
            else if(map.containsKey(sum - k)){
                max = Math.max(max, i - map.get(sum - k));
            }
            // only adding one time to maintain longest subarray
            if(!map.containsKey(sum)) map.put(sum, i);
        }
        
        return max;
    }
}
