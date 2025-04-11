/**
 * Problem: to find the number of longest increasing subsequences in an array.
 * Link: https://leetcode.com/problems/number-of-longest-increasing-subsequence/description/
 * 
 * We cannot simply number of the maximum length of increasing subsequence from dp array.
 * As we ignore sum of them when max length is already present 
 * eg: dp[curr](4) > 1 + dp[prev](3) => case ignored for max length LIS
 * 
 * Approach:
 * 1. Use dp and cnt arrays.
 * 2. dp[i] = length of longest increasing subsequence ending at index i.
 * 3. cnt[i] = number of longest increasing subsequences ending at index i.
 * 4. For each index, if dp[curr] < 1 + dp[prev], update dp[curr] and cnt[curr] = cnt[prev].
 * 5. If dp[curr] == 1 + dp[prev], update cnt[curr] += cnt[prev].
 * 
 * Time Complexity: O(n^2) - for dp and cnt arrays.
 * Space Complexity: O(n) - for dp and cnt arrays.
 * 
 * Hint:
 * LIS problem extension.
 */
public class Dp47NumberOfLongestIncreasingSubsequence {
    public int dpHelper(int[] nums, int n, int[] dp, int[] cnt){
        int max = 1;

        for(int curr = 0; curr < n; curr++){
            for(int prev = 0; prev < curr; prev++){
                if(nums[curr] > nums[prev] && dp[curr] < 1 + dp[prev]){
                    dp[curr] = 1 + dp[prev];
                    cnt[curr] = cnt[prev];
                    max = Math.max(max, dp[curr]);
                }
                else if(nums[curr] > nums[prev] && dp[curr] == 1 + dp[prev])
                    cnt[curr] += cnt[prev];
            }
        }

        int ans = 0;
        for(int i = 0; i < n; i++){
            if(max == dp[i])
                ans += cnt[i];
        }
        return ans;
    }

    public int findNumberOfLIS(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int[] cnt = new int[n];
        Arrays.fill(cnt, 1);

        return dpHelper(nums, n, dp, cnt); 
    }
}
