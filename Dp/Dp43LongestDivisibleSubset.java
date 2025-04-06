/**
 * Problem: Largest Divisible Subset
 * Link: https://leetcode.com/problems/largest-divisible-subset/
 * 
 * Approach: Similar to Longest Increasing Subsequence
 * Eg: [1, 7, 4, 8 ,16] => [1, 4, 8, 16] longest divisible subset of length 4.
 * Where each pair of elements in the subset is divisible.
 * We do not need to care for order so we can sort the array.
 * Then we can say [1,4,7,8,16] => [1,4,8,16] is the longest divisible subset.
 * 
 * Since order does not matter as we need only length.
 * 1. Sort the array.
 * 2. Use a DP array to store the length of the largest divisible subset ending at each index.
 * 3. Use a hash array to store the previous index of the element in the largest divisible subset.
 * 4. Backtrack the hash array to get the largest divisible subset.
 * 5. Return the largest divisible subset.
 * 
 * Time Complexity: O(n^2) - for DP and hash array.
 * Space Complexity: O(n) - for DP and hash array.
 */
public class Dp43LongestDivisibleSubset {
    public static int lastInd = -1;

    public void dps(int[] nums, int n, int[] dp, int[] hash){
        int max = -1;
        for(int curr = 0; curr < n; curr++){
            hash[curr] = curr;
            for(int prev = 0; prev < curr; prev++){
                if(nums[curr] % nums[prev] == 0 && dp[curr] < 1 + dp[prev]){
                    dp[curr] = 1 + dp[prev];
                    hash[curr] = prev;
                }
            }
            if(max < dp[curr]){
                max = dp[curr];
                lastInd = curr;
            }
        }
    }
    
    public List<Integer> largestDivisibleSubset(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int[] hash = new int[n];
        dps(nums, n, dp, hash);

        List<Integer> ans = new ArrayList<>();
        ans.add(nums[lastInd]);
        while(lastInd != hash[lastInd]){
            lastInd = hash[lastInd];
            ans.add(nums[lastInd]);
        }

        return ans;
    }
}
