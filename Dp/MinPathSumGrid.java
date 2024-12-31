/*
 * Problem: we have to travel from left top corner to bottom right corner in a grid.
 * We can only move right or down.
 * Each cell has a value, we have to find the minimum sum of values of cells in the path.
 * 
 * Approach:
 * Similar to the GridUniquePaths.java problem, but instead of adding the number of ways to reach the cell,
 * we add the value of the cell and get the min. value.
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
public class MinPathSumGrid {
    public int recursion(int n, int m, int[][] grid){
        if(n == 0 && m == 0) return grid[n][m];
        if(n < 0 || m < 0) return (int)1e9;
        int left = grid[n][m] + recursion(n, m - 1, grid);
        int up = grid[n][m] + recursion(n - 1, m, grid);
        return Math.min(up, left);
    }

    public int memoization(int n, int m, int[][] grid, int[][] dp){
        if(n == 0 && m == 0) return grid[n][m];
        if(n < 0 || m < 0) return (int)1e9;
        if(dp[n][m] != -1) return dp[n][m];

        int left = grid[n][m] + memoization(n, m - 1, grid, dp);
        int up = grid[n][m] + memoization(n - 1, m, grid, dp);
        return dp[n][m] = Math.min(up, left);
    }

    public int tabulation(int n, int m, int[][] grid){
        int[][] dp = new int[n][m];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(i == 0 && j == 0) dp[i][j] = grid[i][j];
                else {
                    int left = grid[i][j], up = grid[i][j];
                    if(i > 0) up += dp[i - 1][j];
                    else up += 1e9;
                    if(j > 0) left += dp[i][j - 1];
                    else left += 1e9;
                    dp[i][j] = Math.min(left, up);
                }
            }
        }
        return dp[n - 1][m - 1];
    }

    public int space(int n, int m, int[][] grid){
        int[] prev = new int[m];
        for(int i = 0; i < n; i++){
            int[] curr = new int[m];
            for(int j = 0; j < m; j++){
                if(i == 0 && j == 0) curr[j] = grid[i][j];
                else {
                    int left = grid[i][j], up = grid[i][j];
                    if(i > 0) up += prev[j];
                    else up += 1e9;
                    if(j > 0) left += curr[j - 1];
                    else left += 1e9;
                    curr[j] = Math.min(left, up);
                }
            }
            prev = curr;
        }
        return prev[m - 1];
    }

    public int minPathSum(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        // return recursion(n - 1, m - 1, grid);

        // int[][] dp = new int[n][m];
        // for(int i = 0; i < n; i++)
        //     for(int j = 0; j < m; j++)
        //         dp[i][j] = -1;
        // return memoization(n - 1, m - 1, grid, dp);

        // return tabulation(n, m, grid);

        return space(n, m, grid);
    }
}
