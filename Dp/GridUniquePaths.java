/*
 * Problem: to find the all possible unique paths from top left to bottom right in a grid.
 * You can move either down or right at any point in time.
 * If we start from top left to bottom right.
 * 
 * Else if you start from bottom right to top left
 * then we can move left or up in the grid.
 * 
 * Recursion:
 * Time Complexity: O(2^(m*n))
 * Space Complexity: O((m - 1) + (n - 1)) = Stack space (path length)
 * 
 * Memoization:
 * Time Complexity: O(m*n)
 * Space Complexity: O((m - 1) + (n - 1)) + O(m*n) = Stack space (path length) + Dp space
 * 
 * Tabulation:
 * Time Complexity: O(m*n)
 * Space Complexity: O(m*n)
 * 
 * Space optimization:
 * Time Complexity: O(m*n)
 * Space Complexity: O(n)
 */
public class GridUniquePaths {

    public int recursion(int m , int n){
        if(m == 0 && n == 0) return 1;
        if(m < 0 || n < 0) return 0;
        return recursion(m, n - 1) + recursion(m - 1, n);
    }

    public int memoization(int m, int n, int[][] dp){
        if(m == 0 && n == 0) return 1;
        if(m < 0 || n < 0) return 0;
        if(dp[m][n] != -1) return dp[m][n];
        return dp[m][n] = memoization(m, n - 1, dp) + memoization(m - 1, n, dp);
    }

    public int tabulation(int m, int n){
        int[][] dp = new int[m][n];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(i == 0 && j == 0) dp[i][j] = 1;
                else {
                    int up = 0, left = 0;
                    if(i > 0) up = dp[i - 1][j];
                    if(j > 0) left = dp[i][j - 1];
                    dp[i][j] = up + left;
                }
            }
        }
        return dp[m - 1][n - 1];
    }

    public int space(int m, int n){
        int[] dp = new int[n];
        for(int i = 0; i < m; i++){
            int[] temp = new int[n];
            for(int j = 0; j < n; j++){
                if(i == 0 && j == 0) temp[j] = 1;
                else {
                    int up = 0, left = 0;
                    if(i > 0) up = dp[j];
                    if(j > 0) left = temp[j - 1];
                    temp[j] = up + left;
                }
            }
            dp = temp;
        }
        return dp[n - 1];
    }
    
    public int uniquePaths(int m, int n) {
        // return recursion(m - 1, n - 1);

        // int[][] dp = new int[m][n];
        // for(int i = 0 ; i < m; i++)
        //     for(int j = 0; j < n; j++)
        //         dp[i][j] = -1;
        
        // return memoization(m - 1, n - 1, dp);

        // return tabulation(m, n);

        return space(m, n);
    }   
}
