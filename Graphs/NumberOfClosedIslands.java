/**
 * Brute force:
 * 1. Count the total number of islands in the grid.
 * 2. Count the number of islands that are connected to the boundary.
 * 3. Return the difference of the total number of islands and the number of islands connected to the boundary.
 * 
 * Time Complexity: O(n * m)
 * Space Complexity: O(n * m)
 * Here we are using 4 for loops for all 4 boundaries and doing dfs twice.
 */
class Solution {
    private boolean isValidMove(int r, int c, int[][] grid, boolean[][] vis, int n, int m){
        return r >= 0 && r < n && c >= 0 && c < m && grid[r][c] == 0 && !vis[r][c];
    }
    private void dfs(int r, int c, int[][] grid, boolean[][] vis, int[] dr, int[] dc, int n, int m){
        vis[r][c] = true;
        for(int k = 0; k < 4; k++){
            int nr = r + dr[k];
            int nc = c + dc[k];

            if(isValidMove(nr, nc, grid, vis, n, m)){
                dfs(nr, nc, grid, vis, dr, dc, n, m);
            }
        }
    }
    private int calExcludeIslands(int[][] grid, boolean[][] vis, int n, int m, int[] dr, int[] dc){
        int cnt = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                vis[i][j] = false;
            }
        }

        for(int i = 0; i < m; i++){
            if(grid[0][i] == 0 && !vis[0][i]){
                cnt++;
                dfs(0, i, grid, vis, dr, dc, n, m);
            }
        } 
        for(int i = 0; i < n; i++){
            if(grid[i][0] == 0 && !vis[i][0]){
                cnt++;
                dfs(i, 0, grid, vis, dr, dc, n, m);
            }
        } 
        for(int i = 0; i < m; i++){
            if(grid[n - 1][i] == 0 && !vis[n - 1][i]){
                cnt++;
                dfs(n -1, i, grid, vis, dr, dc, n, m);
            }
        } 
        for(int i = 0; i < n; i++){
            if(grid[i][m - 1] == 0 && !vis[i][m - 1]){
                cnt++;
                dfs(i, m - 1, grid, vis, dr, dc, n, m);
            }
        } 
        return cnt;
    }
    public int closedIsland(int[][] grid) {
        int totalIslands = 0, excludeIslands = 0;
        int n = grid.length;
        int m = grid[0].length;
        boolean[][] vis = new boolean[n][m];

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                vis[i][j] = false;
            }
        }  

        int[] dr = {-1, 0, 1, 0};
        int[] dc = {0, 1, 0, -1};

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(grid[i][j] == 0 && !vis[i][j]){
                    totalIslands++;
                    dfs(i, j, grid, vis, dr, dc, n, m);
                }
            }
        }

        excludeIslands = calExcludeIslands(grid, vis, n, m, dr, dc);

        return totalIslands - excludeIslands;
    }
}

/**
 * Better Approach:
 * 1. We can mark all the islands that touch the boundary as marked.
 * 2. Start counting the remaining islands that don't touch the boundary (enclosed islands).
 * 
 * Time Complexity: O(n * m)
 * Space Complexity: O(n * m)
 */
class Solution {
public:
    int closedIsland(vector<vector<int>>& grid) {
        int n = grid.size(), m = grid[0].size();

        // mark all the islands that touch the boundary as marked
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if ( (i == 0 || j == 0 || i == n-1 || j == m-1) && grid[i][j] == 0)
                    dfs(grid, i, j, n, m);
            }
        } 

        int result = 0;

        // Start counting the remaining islands that don't touch the boundary (enclosed islands)
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 0) {
                    dfs(grid, i, j, n, m);
                    result++;
                }
            }
        }
        
        return result;
    }

/**
 * Optimized Approach:
 * 1. We can use a single dfs to check if the island is connected to the boundary or not.
 * 2. If the island is connected to the boundary then we can ignore that island.
 * 3. If the island is not connected to the boundary then we can count that island.
 * 
 * Time Complexity: O(n * m)
 * Space Complexity: O(n * m)
 */
class Data{
    boolean isSurr;
    Data(boolean isSurr){
        this.isSurr = isSurr;
    }
}
class Solution {
    private boolean isValidMove(int r, int c, int[][] grid, boolean[][] vis, int n, int m){
        return r >= 0 && r < n && c >= 0 && c < m && grid[r][c] == 0 && !vis[r][c];
    }
    private boolean isBoundaryNode(int r, int c, int n, int m){
        return r == 0 || r == n - 1 || c == 0 || c == m - 1;
    }
    private void dfs(int r, int c, int[][] grid, boolean[][] vis, int[] dr, int[] dc, int n, int m, Data d){
        if(isBoundaryNode(r, c, n, m)){
            d.isSurr = false;
        }

        vis[r][c] = true;
        for(int k = 0; k < 4; k++){
            int nr = r + dr[k];
            int nc = c + dc[k];

            if(isValidMove(nr, nc, grid, vis, n, m)){
                dfs(nr, nc, grid, vis, dr, dc, n, m, d);
            }
        }
    }
    public int closedIsland(int[][] grid) {
        int islands = 0;
        int n = grid.length;
        int m = grid[0].length;
        boolean[][] vis = new boolean[n][m];

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                vis[i][j] = false;
            }
        }  

        int[] dr = {-1, 0, 1, 0};
        int[] dc = {0, 1, 0, -1};

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(grid[i][j] == 0 && !vis[i][j]){
                    Data d = new Data(true);
                    dfs(i, j, grid, vis, dr, dc, n, m, d);
                    if(d.isSurr){
                        islands++;
                    }
                }
            }
        }

        return islands;
    }
}