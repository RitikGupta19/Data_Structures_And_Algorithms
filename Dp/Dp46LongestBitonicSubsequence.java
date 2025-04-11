/**
 * Problem: Longest Bitonic Subsequence
 * Bitonic sequence is a sequence that is first increasing and then decreasing.
 * Or It can just be increasing or decreasing only.
 * Link: https://www.naukri.com/code360/problems/longest-bitonic-sequence_1062688?leftPanelTabValue=PROBLEM
 * 
 * Approach:
 * 1. Use two dp arrays to store the length of increasing and decreasing subsequences.
 * 2. For each index, sum the lengths of the increasing and decreasing subsequences and subtract 1 (as duplicate).
 * 3. Find the maximum length from the bitonic array.
 * Eg: [1, 2, 5, 3, 2, 1]
 *    dp1 = [1, 2, 3, 3, 2, 1] (increasing)
 *    dp2 = [1, 1, 1, 2, 3, 4] (decreasing)
 *   bitonic = [1 + 1 - 1, 2 + 1 - 1, 3 + 1 - 1, 3 + 2 - 1, 2 + 3 - 1, 1 + 4 - 1] (bitonic)
 *  = [1, 2, 3, 4, 4, 4]
 * 
 * Time Complexity: O(n^2) - for dp1 and dp2.
 * Space Complexity: O(n) - for dp1 and dp2.
 * 
 * Hint:
 * LIS method extension
 */
public class Dp46LongestBitonicSubsequence {
    public static void increasingSeq(int[] arr, int n, int[] dp1){
        for(int curr = 0; curr < n; curr++){
            for(int prev = 0; prev < curr; prev++){
                if(arr[curr] > arr[prev] && dp1[curr] < 1 + dp1[prev]) dp1[curr] = 1 + dp1[prev];
            }
        }
    }

    public static void decreasingSeq(int[] arr, int n, int[] dp2){
        for(int curr = n - 1; curr >= 0; curr--){
            for(int prev = n - 1; prev >= curr; prev--){
                if(arr[curr] > arr[prev] && dp2[curr] < 1 + dp2[prev]) dp2[curr] = 1 + dp2[prev];
            }
        }
    }

    public static int longestBitonicSequence(int[] arr, int n) {
        // Write your code here.
        int[] dp1 = new int[n];
        int[] dp2 = new int[n];
        int[] bitonic = new int[n];
        Arrays.fill(dp1, 1);
        Arrays.fill(dp2, 1);

        increasingSeq(arr, n, dp1);
        decreasingSeq(arr, n, dp2);

        int max = -1;
        for(int i = 0; i < n; i++){
            bitonic[i] = dp1[i] + dp2[i] - 1;
            max = Math.max(max, bitonic[i]);
        }

        return max;
    }
}
