/**
 * Longest Palindromic substring:
 * Problem: https://leetcode.com/problems/longest-palindromic-substring/description/
 * 
 * Approach 1: Brute force
 * 1. Find all substrings of the given string.
 * 2. Check if the substring is a palindrome.
 * 
 * Time complexity: O(n^3)
 * Space complexity: O(1)
 * 
 * Approach 2: DP
 * 1. Create a 2D boolean array dp of size n x n, where dp[i][j] is true if the substring s[i, j] is a palindrome.
 * 2. Initialize the dp array with true for all substrings of length 1 and 2.
 * 3. For all substrings of length 3 and above, check if the substring is a palindrome.
 * 4. Update the maxLen and start index of the longest palindrome substring.
 * 
 * Time complexity: O(n^2)
 * Space complexity: O(n^2)
 * 
 * Approach 3: Expand around center
 * 1. For each character in the string, consider it as the center of the palindrome and expand around it.
 * 2. For odd length palindromes, expand around the center character.   
 * 3. For even length palindromes, expand around the center character and the next character.
 * 
 * Time complexity: O(n^2)
 * Space complexity: O(1)
 * 
 */
public class LongestPalindromicSubstring {
    public String longestPalindrome(String s) {
        int n = s.length();
        int maxLen = Integer.MIN_VALUE;
        int len = 0, start = -1;
        for(int i = 0; i < n; i++){
            // for odd and even length strings
            for(int j = 0; j <= 1; j++){
                int l = i, r = i + j;
                while(l >= 0 && r < n){
                    if(s.charAt(l) == s.charAt(r)){
                        len = r - l + 1;
                        if(len > maxLen){
                            maxLen = len;
                            start = l;
                        }
                    } else break;
                    l--;
                    r++;
                }
            }
        }

        return s.substring(start, start + maxLen);
    }
}
