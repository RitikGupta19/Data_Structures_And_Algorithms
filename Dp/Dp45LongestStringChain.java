/**
 * Problem: Longest String Chain
 * eg: [a, ba, bca, bda, bdca] => [a, ba, bda, bdca] longest string chain of length 4.
 * Each word in the chain can be formed by adding one letter to the previous word.
 * order of the letters in the word does not change.
 * abcd, dcbae => is not a string chain. But abcd, eabcd is a string chain.
 * Link: https://leetcode.com/problems/longest-string-chain/
 * 
 * Approach:
 * 1. Sort the array of words in ascending order of their lengths.
 * 2. Use a DP array to store the length of the longest string chain ending at each index.
 * 3. Condition: implement a compare method and check dp[curr] < 1 + dp[prev] (usual in dp41-44).
 * Compare method: 
 * - check if the length of the first string is one less than the second string. NOT MORE THAN THAT
 * - check if the second string is a subsequence of the first string.
 * 
 * Time Complexity: O(nlogn + n^2 * m) - sorting + for DP and compare method.
 * Space Complexity: O(n) - for DP array.
 * 
 * Hint: similarity to longest increasing subsequence. 
 * How? Longest, subsequence are same.
 * Instead of increasing, we are checking for string chain. STRING LENGTH IS INCREASING.
 */
public class Dp45LongestStringChain {

    public boolean compare(String a, String b){
        if(Math.abs(a.length() - b.length()) != 1) return false;
        if(a.length() < b.length()) return compare(b, a);
        int i = 0, j = 0;
        // both pointers should end together or with max diff of 1.
        // b c d a
        // b d a       is a valid example
        while(i < a.length() && j < b.length()){
            if(a.charAt(i) == b.charAt(j)) {
                i++;
                j++;
            }
            else i++;
        }
        if(i == j || i - 1 == j) return true;
        else return false;
    }

    public int dpHelper(String[] words, int n, int[] dp){
        int maxx = 1;
        Arrays.sort(words, (a, b) -> a.length() - b.length());
        
        for(int curr = 0; curr < n; curr++){
            for(int prev = 0; prev < curr; prev++){
                if(compare(words[curr], words[prev]) && dp[curr] < 1 + dp[prev]){
                    dp[curr] = 1 + dp[prev];
                }
            }
            maxx = Math.max(maxx, dp[curr]);
        }

        return maxx;
    }

    public int longestStrChain(String[] words) {
        int n = words.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        return dpHelper(words, n, dp);   
    }
}
