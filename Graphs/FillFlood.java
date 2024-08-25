/*
 * Problem: https://leetcode.com/problems/flood-fill/
 * See the description in the above link
 * 
 * Simple DFS problem. We need to fill the color in the given image starting from the given pixel.
 * 
 * Time complexity: O(n * m) where n is the number of rows and m is the number of columns in the image
 * Space complexity: O(n * m) where n is the number of rows and m is the number of columns in the image
 */
public class FillFlood {
    private boolean isValid(int[][] img, int[][] ans, int nr, int nc, int sr, int sc, int n, int m, int clr){
        return nr >= 0 && nr < n && nc >= 0 && nc < m && img[nr][nc] == img[sr][sc] && ans[nr][nc] != clr;
    }
    private void dfs(int[][] img, int[][] ans, int sr, int sc, int n, int m, int clr){
        ans[sr][sc] = clr;

        int[] r = {-1, 0, 1, 0};
        int[] c = {0, 1, 0, -1};

        for(int k = 0; k < 4; k++){
            int nr = sr + r[k];
            int nc = sc + c[k];

            if(isValid(img, ans, nr, nc, sr, sc, n, m, clr))
                dfs(img, ans, nr, nc, n, m, clr);
        }
    }
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int n = image.length;
        int m = image[0].length;
        int[][] ans = new int[n][m];

        for(int i = 0 ; i < n; i++)
            for(int j = 0; j < m; j++)
                ans[i][j] = image[i][j];

        dfs(image, ans, sr, sc, n, m, color);
        return ans; 
    }
}
