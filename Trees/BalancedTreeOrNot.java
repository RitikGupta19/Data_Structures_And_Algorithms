/**
 * Problem: to find whether the given tree is height balanced or not.
 * Difference in height of left subtree and right subtree should not be greater than 1.
 * 
 * Approach: 
 * 1. To find the height of the left and right subtree
 * 2. If the difference between the height of the left and right subtree is greater than 1, then the tree is not balanced.
 * 3. Else it is balanced.
 * 
 * Time Complexity: O(n) - as we are visiting each node once.
 * Space Complexity: O(H) - recursion stack
 */
class Solution {
    boolean balanced = true;
    private int findHeight(TreeNode node){
        if(node == null) return 0;
        int lh = findHeight(node.left);
        int rh = findHeight(node.right);
        if(Math.abs(lh - rh) > 1) balanced = false;
        return Math.max(lh, rh) + 1;
    }
    public boolean isBalanced(TreeNode root) {
        findHeight(root);
        return balanced;
    }
}