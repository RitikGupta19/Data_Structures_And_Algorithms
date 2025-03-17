/**
 * Number of Islands in Tree: https://leetcode.com/discuss/interview-question/1682632/google-phone-screen-number-of-islands-in-a-tree
 * 
 * In 2d grid, 1 represents land and 0 represents water. 
 * Find the number of islands in the grid.
 * Approach: 
 * 1. Traverse the grid.
 * 2. If we find a 1, then we will do a DFS from that cell.
 * 3. In the DFS, we will mark all the connected 1s as visited.
 * 4. We will increment the count of islands.
 * 5. Return the count.
 * 
 * Time Complexity: O(n*m)
 * Space Complexity: O(n*m)
 */
public class NumberOfIslands {
    public boolean isValid(int r, int c, int n, int m, char[][] grid, boolean[][] vis){
        return r >= 0 && r < n && c >= 0 && c < m && grid[r][c] != '0' && vis[r][c] == false;    
    }
    
    public void dfs(int i, int j, int n, int m, char[][] grid, boolean[][] vis){
        vis[i][j] = true;
        
        for(int l = -1; l <= 1; l++){
            for(int k = -1; k <= 1; k++){
                int nr = i + l;
                int nc = j + k;
                if(isValid(nr, nc, n, m, grid, vis))
                    dfs(nr, nc, n, m, grid, vis);
            }
        }
    }
    
    public int numIslands(char[][] grid) {
        // Code here
        int n = grid.length;
        int m = grid[0].length;
        
        boolean[][] vis = new boolean[n][m];
        int cnt = 0;
        
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(vis[i][j] == false && grid[i][j] != '0'){
                    cnt++;
                    dfs(i, j, n, m, grid, vis);
                }
            }
        }
        
        return cnt;
    }
}