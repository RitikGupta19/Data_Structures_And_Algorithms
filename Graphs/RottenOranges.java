/**
 * Link: https://leetcode.com/problems/rotting-oranges/
 * 
 * 1. Push all rotten oranges into a queue.
 * 2. After each level increase the minute.
 * 
 * Time Complexity: O(n * m) - where n is the number of rows and m is the number of columns.
 * Space Complexity: O(n * m) - for storing the queue.
 */
class Tuple{
    int i;
    int j;
    Tuple(int i, int j){
        this.i = i;
        this.j = j;
    }
}

class RottenOranges {
    public boolean isValid(int i, int j, int n, int m, int[][] dup){
        return i >= 0 && i < n && j >= 0 && j < m && dup[i][j] == 1;
    }

    public int orangesRotting(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        Queue<Tuple> q = new LinkedList<>();
        int countFreshOranges = 0;
        int min = -1;

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(grid[i][j] == 2) q.offer(new Tuple(i, j));
                if(grid[i][j] == 1) countFreshOranges++;
            }
        }

        if(countFreshOranges == 0) return 0;
        if(q.isEmpty()) return -1;

        int[] r = {-1, 0, 1, 0};
        int[] c = {0, 1, 0, -1};

        while(!q.isEmpty()){
            int qs = q.size();
            for(int k = 0; k < qs; k++){
                Tuple t = q.poll();
                int x = t.i;
                int y = t.j;
                
                for(int l = 0; l < 4; l++){
                    int nr = x + r[l];
                    int nc = y + c[l];
                    if(isValid(nr, nc, n, m, grid)){
                        grid[nr][nc] = 2;
                        countFreshOranges--;
                        q.offer(new Tuple(nr, nc));
                    }
                }
            }
            min++;
        }

        if(countFreshOranges == 0) return min;
        else return -1;
    }
}