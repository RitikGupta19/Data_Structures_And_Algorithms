/*
 * Problem: Given two strings, find the length of the longest common subsequence
 * 
 * Brute force method:
 * Exponential in nature.
 * To generate all the subsequences of a string, we can use the power set method.
 * We can generate the power set of both the strings and then check for the common subsequences.
 * 
 * Recursive method:
 * We can generate the subsequences and compare them on the way.
 * Here instead of generating all the subsequences
 * We will use parameters to do this.
 * 
 * Recursion:
 * Time Complexity: O(2^(n+m))
 * Space Complexity: O(n + m) = Recursion stack
 * 
 * Memoization:
 * Time Complexity: O(n*m)
 * Space Complexity: O(n*m) + O(n + m) = Dp array + Recursion stack 
 * In non-matching case (Worst case):
 * If you will draw the recursion tree, you will see that at each level
 * we are reducing any one string by 1 character.
 * That means we are alternating between the two strings. Thus n + m levels
 * 
 * Tabulation:
 * Time Complexity: O(n*m)
 * Space Complexity: O(n*m)
 * 
 * Space Optimized:
 * Time Complexity: O(n*m)
 * Space Complexity: O(m)
 */
public class Dp25LongestCommonSubsequence {
    public int recursion(int n, int m, String a, String b){
        if(n < 0 || m < 0) return 0;
        if(a.charAt(n) == b.charAt(m)) return 1 + recursion(n - 1, m - 1, a, b);
        return Math.max(recursion(n - 1, m, a, b), recursion(n, m - 1, a, b));
    }

    public int memoization(int n, int m, String a, String b, int[][] dp){
        if(n < 0 || m < 0) return 0;
        if(dp[n][m] != -1) return dp[n][m];
        if(a.charAt(n) == b.charAt(m)) return dp[n][m] = 1 + memoization(n - 1, m - 1, a, b, dp);
        return dp[n][m] = Math.max(memoization(n - 1, m, a, b, dp), memoization(n, m - 1, a, b, dp));
    }

    public int tabulation(int n, int m, String a, String b){
        int[][] dp = new int[n + 1][m + 1];
        for(int i = 0; i <= n; i++) dp[i][0] = 0;
        for(int i = 0; i <= m; i++) dp[0][i] = 0;
        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= m; j++){
                if(a.charAt(i - 1) == b.charAt(j - 1)) dp[i][j] = 1 + dp[i - 1][j - 1];
                else dp[i][j] = Math.max(dp[i-1][j], dp[i][j - 1]);
            }
        }
        return dp[n][m];
    }

    public int space(int n, int m, String a, String b){
        int[] prev = new int[m + 1];
        for(int i = 0; i <= n; i++) prev[0] = 0;
        for(int i = 1; i <= n; i++){
            int[] curr = new int[m + 1];
            for(int j = 1; j <= m; j++){
                if(a.charAt(i - 1) == b.charAt(j - 1)) curr[j] = 1 + prev[j - 1];
                else curr[j] = Math.max(prev[j], curr[j - 1]);
            }
            prev = curr;
        }
        return prev[m];
    }

    public int longestCommonSubsequence(String text1, String text2) {
        int n = text1.length();
        int m = text2.length();
        // return recursion(n - 1, m - 1, text1, text2);

        // int[][] dp = new int[n][m];
        // for(int i = 0 ; i < n; i++)
        //     for(int j = 0; j < m; j++)
        //         dp[i][j] = -1;
        // return memoization(n - 1, m - 1, text1, text2, dp);

        // We will use shifting of index here
        // Hint: As base case is for index = -1
        // To maintain the base case
        // Here we will consider each n as n - 1, amd m as m - 1
        // return tabulation(n, m, text1, text2);

        return space(n, m, text1, text2);
    }
}
