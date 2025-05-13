/**
 * Problem: https://leetcode.com/problems/subarray-sum-equals-k/
 * Find the count of continuous subarrays whose sum equals to k.
 * 
 * Approach:
 * 1. Brute Force: O(n^2)
 *   - Iterate through all possible subarrays and check if their sum equals k.
 *   - Use two nested loops to calculate the sum of each subarray.
 * 2. Optimal: O(n) prefix sum
 *  - Use a HashMap to store the cumulative sum and its frequency.
 */
public class SubarraySumKCount {
    // APPROACH BRUTE FORCE:
    public int subarraySum(int[] nums, int k) {
        int n = nums.length;
        int cnt = 0;
        for(int i = 0; i < n; i++){
            int sum = 0;
            for(int j = i; j < n; j++){
                sum += nums[j];
                if(sum == k) cnt++;
            }
        }
        return cnt;

        // APPROACH OPTIMAL:
        int n = nums.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int cnt = 0, sum = 0;

        for(int i = 0; i < n; i++){
            sum += nums[i];
            int rem = sum - k;
            cnt += map.getOrDefault(rem, 0);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return cnt;
    }
}
