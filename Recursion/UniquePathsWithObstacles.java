/**
 * Problem Statement: https://leetcode.com/problems/unique-paths-ii/description/
 * Find the unique paths from top left to bottom right in a grid with obstacles.
 * 
 * Recursion solution:
 * Time Complexity: O(2^(m+n))
 * Space Complexity: O(m+n)
 */

class Solution {
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

/**
 * Dynamic Programming solution:
 */