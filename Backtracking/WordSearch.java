/**
 * Link: https://leetcode.com/problems/word-search/
 * Problem Statement: Given a 2D board and a word, find if the word exists in the grid.
 * 
 * Since we need to explore all possible paths in the grid, we can use backtracking.
 * As we need to comeback by not considering some elements and start from the next element.
 * 
 * Time Complexity: O(m * n * 4^k) where k is the length of the word.
 * Space Complexity: O(m * n) for the visited array.
 */
public class WordSearch {
    public boolean recursion(int i, int j, int n, int m, char[][] board, boolean[][] vis, String word, int ind){
        if(ind == word.length()) return true;
        if(i < 0 || i >= n || j < 0 || j >= m || vis[i][j] == true || board[i][j] != word.charAt(ind)) return false;
        vis[i][j] = true;
        if(
            recursion(i + 1, j, n, m, board, vis, word, ind + 1) ||
            recursion(i, j + 1, n, m, board, vis, word, ind + 1) ||
            recursion(i - 1, j, n, m, board, vis, word, ind + 1) ||
            recursion(i, j - 1, n, m, board, vis, word, ind + 1)        
        ) return true;
        vis[i][j] = false;
        return false;
    }
    public boolean exist(char[][] board, String word) {
        int n = board.length;
        int m = board[0].length;
        boolean[][] vis = new boolean[n][m];

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(board[i][j] == word.charAt(0)){
                    boolean ans = recursion(i, j, n, m, board, vis, word, 0);
                    if(ans) return true;
                }
            }
        }
        return false;
    }
}
