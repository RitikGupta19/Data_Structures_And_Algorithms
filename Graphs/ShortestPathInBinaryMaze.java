/**
 * Problem: To find the shortest distance from the top left corner to the bottom right corner in a binary matrix.
 * https://leetcode.com/problems/shortest-path-in-binary-matrix/
 * The matrix contains 0s and 1s, where 0 represents an open cell and 1 represents a blocked cell.
 * The path can only be formed by moving in 8 directions (up, down, left, right, and diagonally).
 * The distance between two adjacent cells is 1.
 * 
 * Approach: We can use BFS to find the shortest path in an unweighted graph.
 * We do not need priority queue as the dist from one cell to another is always 1
 * We will maintain a queue to explore the cells and a distance matrix to keep track of the distance from the source cell.
 * We will also check if the cell is valid (within bounds, not blocked, and not visited) before adding it to the queue.
 * The BFS will continue until we reach the destination cell or exhaust all possibilities.
 * If we reach the destination cell, we return the distance. If not, we return -1.
 * 
 * Time Complexity: O(n * m), where n is the number of rows and m is the number of columns in the matrix.
 * Space Complexity: O(n * m) for the distance matrix and queue.
 * 
 */
class Tuple{
    int dist;
    int x;
    int y;
    Tuple(int dist, int x, int y){
        this.dist = dist;
        this.x = x;
        this.y = y;
    }
}
class Solution {
    static final int[] r = {-1, -1, -1, 0, 1, 1, 1, 0};
    static final int[] c = {-1, 0, 1, 1, 1, 0, -1, -1};

    public boolean isValid(int x, int y, int d, int n, int m, int[][] grid, int[][] dist){
        return x >= 0 && x < n && y >= 0 && y < m && grid[x][y] != 1 && d < dist[x][y];
    }

    public int shortestPathBinaryMatrix(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        if(grid[0][0] == 1 || grid[n - 1][m - 1] == 1) return -1;

        int[][] dist = new int[n][m];
        for(int i = 0; i < n; i++)
            for(int j = 0; j < m; j++)
                dist[i][j] = (int)1e8;

        Queue<Tuple> q = new LinkedList<>();
        q.offer(new Tuple(1, 0, 0));
        dist[0][0] = 1;

        while(!q.isEmpty()){
            Tuple t = q.poll();
            int d = t.dist, x = t.x, y = t.y;
            if(x == n - 1 && y == m - 1) return d;
            for(int k = 0 ; k < 8; k++){
                int nr = x + r[k];
                int nc = y + c[k];

                if(isValid(nr, nc, d + 1, n, m, grid, dist)){
                    q.offer(new Tuple(d + 1, nr, nc));
                    dist[nr][nc] = d + 1;
                }
            }
        }

        return -1;
    }
}