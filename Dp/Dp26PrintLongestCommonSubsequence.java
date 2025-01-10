/*
 * Problem: we have to print the longest common subsequence of two strings
 * 
 * Input: n = 6, m = 6, s1 = "ABCDGH", s2 = "AEDFHR"
 * Output: "ADH"
 * 
 * Tabulation:
 * Time Complexity: O(n*m)
 * Space Complexity: O(n*m) + O(n+m) => Dp array + String
 * 
 * Longer approach: after tabulation
 * we got the dp table
 * we iterated from (n-1,m-1) to (0,0) and created the string
 * 
 * Time Complexity: O(n*m) + O(n+m) => Tabulation loops + creating the string
 * In worst case we need to traverse last row and first column leading to n+m iterations (instead of diagonal)
 * Space Complexity: O(n*m) + O(n+m) => Dp array + String
 */
public class Dp26PrintLongestCommonSubsequence {
    public static int tabulation(int n, int m, String a, String b, String ans){
        int[][] dp = new int[n + 1][m + 1];
        for(int i = 0; i <= n; i++) dp[0][i] = 0;
        for(int i = 0; i <= m; i++) dp[i][0] = 0;

        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= m; j++){
                if(a.charAt(i - 1) == b.charAt(j - 1)){
                    dp[i][j] = 1 + dp[i - 1][j];
                    ans += a.charAt(i);
                }
                else dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }

        return dp[n][m];
    }

    public static String tabulation1(int n, int m, String a, String b){
        int[][] dp = new int[n + 1][m + 1];
        for(int i = 0; i <= n; i++) dp[0][i] = 0;
        for(int i = 0; i <= m; i++) dp[i][0] = 0;

        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= m; j++){
                if(a.charAt(i - 1) == b.charAt(j - 1)){
                    dp[i][j] = 1 + dp[i - 1][j];
                }
                else dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }

        int len = dp[n][m];
        StringBuilder ans= new StringBuilder("");
        int i = n, j = m, k = len - 1;
        while(i > 0 && j > 0){
            if(a.charAt(i - 1) == b.charAt(j - 1)){
                ans.setCharAt(k, a.charAt(i - 1));
                k--;
                i--;
                j--;
            } 
            else if(dp[i - 1][j] > dp[i][j - 1]) i--;
            else j--;
        }
        return ans.toString();
    }

    public static String findLCS(int n, int m, String s1, String s2){
        // Write your code here.
        
        // String ans = "";
        // if(tabulation(n, m, s1, s2, ans) == 0) return "";
        // return ans;

        return tabulation1(n, m, s1, s2);
    }
}
