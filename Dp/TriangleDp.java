/*
 * Coming from the earlier 3 problems where we had the fixed starting point
 * i.e (0,0) and fixed ending point i.e (m,n) where m and n are the dimensions in the grid.
 * 
 * Now, we are given a triangle of numbers and we have to find the minimum path sum from top to bottom.
 * Here the start point is fixed but end point is variable.
 * Which means we can reach any of the elements in the last row.
 * Sum should be minimum for the path.
 * 
 * If we do not see uniformity in numbers/given data, we can't use Greedy.
 * Thus, we will use Recursion / DP.
 * 
 * Recursion:
 * Time Complexity: O(2^(1+2+3+...+n)) = O(2^(n(n+1)/2)) = O(2^(n^2)) - exponential complexity
 * Space Complexity: O(n) for recursion stack
 * 
 * Memoization:
 * Time Complexity: O(n^2)
 * Space Complexity: O(n^2) + O(n) = Dp space + Stack space
 * 
 * Tabulation:
 * Time Complexity: O(n^2)
 * Space Complexity: O(n^2) = Dp space
 * 
 * Space optimization:
 * Time Complexity: O(n^2)
 * Space Complexity: O(n) = Dp space
 * 
 * 1d dp -> 2 variables
 * 2d dp -> 1d array
 * 3d dp -> 2d array
 */

import java.util.List;

public class TriangleDp {
    // Going top to down but with diff pattern
    // Recursion: always top to bottom
    public int recursion(int i, int j, int n, List<List<Integer>> t){
        // Here we kept just one base case
        // That is when reaching the last row just return the value of element
        if(i == n - 1) return t.get(i).get(j);
        // No other base condition is required
        // As we are moving down and diagonally
        // Elements are always present in the triangle (down and diagonal)
        int down = t.get(i).get(j) + recursion(i + 1, j, n, t);
        int diag = t.get(i).get(j) + recursion(i + 1, j + 1, n, t);
        return Math.min(down, diag);
    }

    public int memoization(int i, int j, int n, int[][] dp,  List<List<Integer>> t){
        if(i == n - 1) return t.get(i).get(j);
        if(dp[i][j] != -1) return dp[i][j];
        int down = t.get(i).get(j) + memoization(i + 1, j, n, dp, t);
        int diag = t.get(i).get(j) + memoization(i + 1, j + 1, n, dp, t);
        return dp[i][j] = Math.min(down, diag);
    }

    // Always bottom up approach
    public int tabulation(int n, List<List<Integer>> t){
        int[][] dp = new int[n][n];
        for(int i = n - 1; i >= 0; i--){
            for(int j = i; j >= 0; j--){
                if(i == n - 1) dp[i][j] = t.get(i).get(j);
                else{
                    int up = t.get(i).get(j) + dp[i + 1][j];
                    int diag = t.get(i).get(j) + dp[i + 1][j + 1];
                    dp[i][j] = Math.min(up, diag);
                }
            }
        }
        return dp[0][0];
    }

    public int space(int n, List<List<Integer>> t){
        int[] prev = new int[n];
        for(int i = n - 1; i >= 0; i--){
            int[] curr = new int[n];
            for(int j = i; j >= 0; j--){
                if(i == n - 1) curr[j] = t.get(i).get(j);
                else{
                    int up = t.get(i).get(j) + prev[j];
                    int diag = t.get(i).get(j) + prev[j + 1];
                    curr[j] = Math.min(up, diag);
                }
            }
            prev = curr;
        }
        return prev[0];
    }

    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();

        // return recursion(0, 0, n, triangle);
        
        // int[][] dp = new int[n][n];
        // for(int i = 0; i < n; i++)
        //     for(int j = 0; j < i + 1; j++)
        //         dp[i][j] = -1;
        // return memoization(0, 0, n, dp, triangle);

        // return tabulation(n, triangle);

        return space(n, triangle);
    }
}
