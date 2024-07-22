/**
 * Problem: Given a binary tree, find its height.
 * 
 * Time Complexity: O(n) - as we are visiting each node once.
 * Space Complexity: O(H) - recursion stack
 */
class Solution {
    public int maxDepth(TreeNode root) {
        if(root == null) return 0;
        int lheight = maxDepth(root.left);
        int rheight = maxDepth(root.right);

        if(lheight > rheight) return lheight + 1;
        else return rheight + 1;   
    }
}