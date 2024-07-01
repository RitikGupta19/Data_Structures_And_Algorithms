/**
 * Tags: Amazon OA
 * 
 * Find the minimum number of steps to reach the destination from the source in a 2D matrix having obstacles.
 * The source is at the top-left corner of the matrix and the destination is number 9 and 1 represents the obstacles.
 * 
 * Eg: 0 1 0
 *     0 0 0
 *     1 9 1
 * Ans = 3
 * 
 * Time Complexity: O(n*m) => In WC we will visit all the cells of the matrix
 * Space Complexity: O(n*m)
 */
import java.util.*;

class Pair{
  int row;
  int col;
  Pair(int row, int col){
    this.row = row;
    this.col = col;
  }
}
public class Solution {

    public static boolean isValid(int r, int c, int[][] grid, int[][] dis, int n, int m){
      return r >= 0 && r < n && c >= 0 && c < m && grid[r][c] != 1 && dis[r][c] == -1;
    }
    public static int findMinSteps(int[][] grid){
      if(grid[0][0] == 1) return 0;
      
      Queue<Pair> q = new LinkedList<>();
      int n = grid.length, m = grid[0].length;
      int[][] dis = new int[n][m];
      for(int[] row: dis)
        Arrays.fill(row, -1);
        
      dis[0][0] = 0;
      q.offer(new Pair(0,0));
      
      int[] dr = {-1, 0, 1, 0};
      int[] dc = {0, 1, 0, -1};
      
      while(!q.isEmpty()){
        Pair p = q.poll();
        int r = p.row;
        int c = p.col;
        
        if(grid[r][c] == 9 && dis[r][c] != 0){
          return dis[r][c];
        }
        
        for(int k = 0; k < 4; k++){
          int nr = r + dr[k];
          int nc = c + dc[k];
          
          if(isValid(nr, nc, grid, dis, n, m)){
            q.offer(new Pair(nr, nc));
            dis[nr][nc] = dis[r][c] + 1;
          }
        }
      }
      
      return -1;
    }
}

/**
 * Another way to solve this problem is to use a BFS approach.
 */
public class Solution {

    public static boolean isValid(int r, int c, int[][] grid, boolean[][] dis, int n, int m){
      return r >= 0 && r < n && c >= 0 && c < m && grid[r][c] != 1 && !dis[r][c];
    }
    public static int findMinSteps(int[][] grid){
      if(grid[0][0] == 1) return 0;
      
      Queue<Pair> q = new LinkedList<>();
      int n = grid.length, m = grid[0].length;
      boolean[][] dis = new boolean[n][m];
      
      dis[0][0] = true;
      q.offer(new Pair(0,0));
      
      int[] dr = {-1, 0, 1, 0};
      int[] dc = {0, 1, 0, -1};
      int step = 0;
      
      while(!q.isEmpty()){
        int qs = q.size();
        
        for(int i = 0; i < qs; i++){
          Pair p = q.poll();
          int r = p.row;
          int c = p.col;
          
          if(grid[r][c] == 9 && dis[r][c]){
            return step;
          }
          
          for(int k = 0; k < 4; k++){
            int nr = r + dr[k];
            int nc = c + dc[k];
            
            if(isValid(nr, nc, grid, dis, n, m)){
              q.offer(new Pair(nr, nc));
              dis[nr][nc] = true;
            }
          }  
        }
        step++;
      }
      
      return step;
    }
}

/**
 * Similar problem: https://www.geeksforgeeks.org/shortest-path-in-grid-with-obstacles/
 */