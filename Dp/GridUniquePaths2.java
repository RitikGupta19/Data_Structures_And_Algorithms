/*
 * Problem: similar to the GridUniquePaths.java problem, but with obstacles in the grid.
 * Obstacles in the grid are represented by 1.
 * 
 * Approach:
 * Just add a condition to check if the current cell is an obstacle.
 * If it is an obstacle, then the number of ways to reach that cell is 0.
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
public class GridUniquePaths2 {
    public int recursion(int n, int m, int[][] grid){
        if(n < 0 || m < 0) return 0;
        if(grid[n][m] == 1) return 0;
        if(n == 0 && m == 0) return 1;
        return recursion(n, m - 1, grid) + recursion(n - 1, m, grid);
    }

    public int memoization(int n, int m, int[][] grid, int[][] dp){
        if(n < 0 || m < 0) return 0;
        if(grid[n][m] == 1) return 0;
        if(n == 0 && m == 0) return 1;
        if(dp[n][m] != -1) return dp[n][m];
        return dp[n][m] = memoization(n, m - 1, grid, dp) + memoization(n - 1, m, grid, dp);
    }

    public int tabulation(int n, int m , int[][] grid){
        int[][] dp = new int[n][m];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(grid[i][j] == 1) dp[i][j] = 0;
                else if(i == 0 && j == 0) dp[i][j] = 1;
                else {
                    int up = 0, left = 0;
                    if(j > 0) up = dp[i][j - 1];
                    if(i > 0) left = dp[i - 1][j];
                    dp[i][j] = up + left;
                }
            }
        }
        return dp[n - 1][m - 1];
    }

    public int space(int n, int m, int[][] grid){
        int[] prev = new int[m];
        for(int i = 0; i < n; i++){
            int[] curr = new int[m];
            for(int j = 0; j < m ; j++){
                if(grid[i][j] == 1) curr[j] = 0;
                else if(i == 0 && j == 0) curr[j] = 1;
                else {
                    int up = 0, left = 0;
                    if(i > 0) up = prev[j];
                    if(j > 0) left = curr[j - 1];
                    curr[j] = up + left;
                }
            }
            prev = curr;
        }
        return prev[m - 1];
    }

    public int uniquePathsWithObstacles(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        // return recursion(n - 1, m - 1, grid);

        // int[][] dp = new int[n][m];
        // for(int i = 0; i < n; i++){
        //     for(int j = 0; j < m; j++){
        //         dp[i][j] = -1;
        //     }
        // }
        // return memoization(n - 1, m - 1, grid, dp);

        // return tabulation(n, m, grid);

        if(grid[n-1][m - 1] == 1 || grid[0][0] == 1) return 0;
        return space(n, m, grid);
    }
}

/*
 * One more solution with recursion and backtracking approach:
 */
public class GridUniquePaths2{
    private boolean isMoveValid(int r, int c, int[][] grid, int n, int m){
        return r >= 0 && r < n && c >= 0 && c < m && grid[r][c] == 0;
    }

    private void findUniquePaths(int r, int c, int[][] grid, HashSet<String> hs, String path, int n, int m){
        if(r == n - 1 && c == m - 1){
            hs.add(path);
            return;
        }

        if(isMoveValid(r + 1, c, grid, n, m)){
            path += 'D';
            findUniquePaths(r + 1, c, grid, hs, path, n, m);
            path.substring(0, path.length() - 1);
        }

        if(isMoveValid(r, c + 1, grid, n, m)){
            path += 'R';
            findUniquePaths(r, c + 1, grid, hs, path, n, m);
            path.substring(0, path.length() - 1);
        }
    }

    public int uniquePathsWithObstacles(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        HashSet<String> hs = new HashSet<>();
        String path = "";
        if(grid[0][0] == 1) return 0;
        findUniquePaths(0, 0, grid, hs, path, n, m);
        return hs.size();
    }
}