/*
 * Problem: Given two strings, find the length of the longest common substring
 * A slight variation of the longest common subsequence problem
 * In else condition we will simply put 0 instead of taking max of two
 * As we are looking for continuous substring
 * And we are not looking for eliminating one character from either string one by one
 * 
 * Recursion:
 * Time Complexity: m*n*(min(n,m))
 * Space Complexity: min(n,m) = Recursion stack
 * 
 * Memoization:
 * Time Complexity: m*n*(min(n,m))
 * Space Complexity: O(n*m) + min(n,m) = Dp array + Recursion stack
 * 
 * Tabulation:
 * Time Complexity: O(n*m)
 * Space Complexity: O(n*m)
 * 
 * Space Optimized:
 * Time Complexity: O(n*m)
 * Space Complexity: O(m)
 */
public class Dp27LongestCommonSubstring {
    public static int recursion(int n, int m, String a, String b){
        if(n < 0 || m < 0) return 0;
        if(a.charAt(n) == b.charAt(m)) return 1 + recursion(n - 1, m - 1, a, b);
        return 0;
    }

    public static int memoization(int n, int m, String a, String b, int[][] dp){
        if(n < 0 || m < 0) return 0;
        if(dp[n][m] != 0) return dp[n][m];
        if(a.charAt(n) == b.charAt(m)) return dp[n][m] = 1 + memoization(n - 1, m - 1, a, b, dp);
        return 0;
    }

    public static int tabulation(int n, int m, String a, String b){
        int[][] dp = new int[n + 1][m + 1];
        int max = Integer.MIN_VALUE;

        for(int i = 0; i <= m; i++) dp[0][i] = 0;
        for(int j = 0; j <= n; j++) dp[j][0] = 0;
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= m; j++){
                if(a.charAt(i - 1) == b.charAt(j - 1)){
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                    max = Math.max(max, dp[i][j]);
                }
                else dp[i][j] = 0;
            }
        }
        return max;
    }
    
    public static int space(int n, int m, String a, String b){
        int[] prev = new int[m + 1];
        int max = Integer.MIN_VALUE;

        for(int i = 0; i <= m; i++) prev[i] = 0;
        for(int i = 1; i <= n; i++){
            int[] curr = new int[m + 1];
            for(int j = 1; j <= m; j++){
                if(a.charAt(i - 1) == b.charAt(j - 1)){
                    curr[j] = 1 + prev[j - 1];
                    max = Math.max(max, curr[j]);
                }
                else curr[j] = 0;
            }
            prev = curr;
        }
        return max;
    }

    public static int lcs(String str1, String str2){
        // Write your code here.
        int n = str1.length();
        int m = str2.length();
        
        // int max = Integer.MIN_VALUE;
        // for(int i = 0; i < n; i++){
        //     for(int j = 0; j < m; j++){
        //         // RECURSION
        //         // max = Math.max(max, recursion(i, j, str1, str2));
                
        //         // MEMOIZATION
        //         int[][] dp = new int[n][m];
        //         for(int k = 0; k < n; k++)
        //             for(int l = 0; l < m; l++)
        //                 dp[k][l] = 0;
        //         max = Math.max(max, memoization(i, j, str1, str2, dp));
        //     }
        // }
        // return max;

        // return tabulation(n, m, str1, str2);
        
        return space(n, m, str1, str2);
    }
}
