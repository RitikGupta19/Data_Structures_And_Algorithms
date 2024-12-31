/*
 * 
 * Recursion:
 * Time Complexity: O(3^n * 3^n) = alice * bob = exponential
 * Space Complexity: O(n) for recursion stack
 * 
 * Memoization:
 * Time Complexity: O(n*m*m) * 9 = n*m*m states possible
 * Thus max n*m*m* times memoization will be called
 * Also, we are running a loop of 9 times for each state
 * Thus, O(n*m*m) * 9 = O(n*m*m)
 * In the previous questions there were loops that used to run 2 or 3 times
 * We can ignore them. But here, 9 cannot be ignored as it is close to 10 and big.
 * Space Complexity: O(n*m*m) + O(n) = Dp space + Stack space
 * 
 * Tabulation:
 * Time Complexity: O(n*m*m) * 9 = O(n*m*m)
 * Space Complexity: O(n*m*m) = Dp space
 * 
 * Space optimization:
 * Time Complexity: O(n*m*m) * 9 = O(n*m*m)
 * Space Complexity: O(m*m) = Dp space
 */
public class CherryPickII3DDp {

    public int recursion(int i, int j1, int j2, int n, int m, int[][] grid){
        // to prevent from going outside of matrix
        if(j1 < 0 || j1 >= m || j2 < 0 || j2 >= m) return -(int)1e8;
        if(i == n - 1){
            // base condition also has two condition
            // if j == k then count only one else add
            if(j1 == j2) return grid[i][j1];
            else return grid[i][j1] + grid[i][j2];
        }
        int max = Integer.MIN_VALUE;
        // trying all the possible bob moves (up, left, right) for all possible alex
        // we can see pattern (i + 1) always
        // j - 1, j , j + 1
        // calculating max from all possible values
        for(int k1 = -1; k1 <= 1; k1++){
            for(int k2 = -1; k2 <= 1; k2++){
                if(j1 == j2) max = Math.max(max, grid[i][j1] + recursion(i + 1, j1 + k1, j2 + k2, n, m, grid));
                else max = Math.max(max, grid[i][j1] + grid[i][j2] + recursion(i + 1, j1 + k1, j2 + k2, n, m, grid));
            }
        }
        return max;
    }

    // Memoization:
    // i * j1 * j2 = n (can have n values) * m (can have m values) * m = 3D DP
    // this is how you get the size of the dp array for a problem
    // just multiply the ind variables
    public int memoization(int i, int j1, int j2, int n, int m , int[][] grid, int[][][] dp){
        if(j1 < 0 || j1 >= m || j2 < 0 || j2 >= m) return -(int)1e8;
        if(i == n - 1){
            if(j1 == j2) return grid[i][j1];
            else return grid[i][j1] + grid[i][j2];
        }
        if(dp[i][j1][j2] != -1) return dp[i][j1][j2];
        int max = -(int)1e8;
        for(int k1 = -1; k1 <= 1; k1++){
            for(int k2 = -1; k2 <= 1; k2++){
                if(j1 == j2) max = Math.max(max, grid[i][j1] + memoization(i + 1, j1 + k1, j2 + k2, n, m, grid, dp));
                else max = Math.max(max, grid[i][j1] + grid[i][j2] + memoization(i + 1, j1 + k1, j2 + k2, n, m, grid, dp)); 
            }
        }
        return dp[i][j1][j2] = max;
    }

    public int tabulation(int n, int m, int[][]  grid){
        int[][][] dp = new int[n][m][m];

        // populating for base case
        for(int j = 0; j < m; j++){
            for(int k = 0; k < m; k++){
                if(j == k) dp[n - 1][j][k] = grid[n - 1][j];
                else dp[n - 1][j][k] = grid[n - 1][j] + grid[n - 1][k];
            }
        }

        for(int i = n - 2; i >= 0; i--){
            for(int j = 0; j < m; j++){
                for(int k = 0; k < m; k++){
                    // try all possible moves for all possible states of j and k
                    int max = Integer.MIN_VALUE;
                    for(int d1 = -1; d1 <= 1; d1++){
                        for(int d2 = -1; d2 <= 1; d2++){
                            int val = 0;
                            if(j == k) val = grid[i][j];
                            else val = grid[i][j] + grid[i][k];
                            // if going outside of matrix then add -1e8
                            if ((j + d1 < 0 || j + d1 >= m) || (k + d2 < 0 || k + d2 >= m))
                                val += -(int)1e8;
                            else
                                val += dp[i + 1][j + d1][k + d2];
                            max = Math.max(max, val);
                        }
                    }
                    dp[i][j][k] = max;
                }
            }
        }
        // i = 0 & j = 0 and k = m - 1 (starting points)
        return dp[0][0][m - 1];
    }

    public int space(int n, int m, int[][]  grid){
        int[][] prev = new int[m][m];
        for(int j = 0; j < m; j++){
            for(int k = 0; k < m; k++){
                if(j == k) prev[j][k] = grid[n - 1][j];
                else prev[j][k] = grid[n - 1][j] + grid[n - 1][k];
            }
        }

        for(int i = n - 2; i >= 0; i--){
            int[][] curr = new int[m][m];
            for(int j = 0; j < m; j++){
                for(int k = 0; k < m; k++){
                    int max = Integer.MIN_VALUE;
                    for(int d1 = -1; d1 <= 1; d1++){
                        for(int d2 = -1; d2 <= 1; d2++){
                            int val = 0;
                            if(j == k) val = grid[i][j];
                            else val = grid[i][j] + grid[i][k];
                            if ((j + d1 < 0 || j + d1 >= m) || (k + d2 < 0 || k + d2 >= m))
                                val += -(int)1e8;
                            else
                                val += prev[j + d1][k + d2];
                            max = Math.max(max, val);
                        }
                    }
                    curr[j][k] = max;
                }
            }
            prev = curr;
        }
        return prev[0][m - 1];
    }

    public int cherryPickup(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        
        // Always starting from fixed points
        // return recursion(0, 0, m - 1, n, m, grid);

        // int[][][] dp = new int[n][m][m];
        // for(int i = 0; i < n; i++)
        //     for(int j = 0; j < m; j++)
        //         for(int k = 0; k < m; k++)
        //             dp[i][j][k] = -1;
        // return memoization(0, 0, m - 1, n, m, grid, dp);

        // return tabulation(n, m, grid);

        return space(n, m, grid);
    }
}
