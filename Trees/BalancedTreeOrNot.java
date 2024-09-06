/*
 * Brute force approach:
 * Problem: to find whether the given tree is balanced or not.
 * 
 * We will find the height of the left and right subtree for each node and check whether the difference between the height of the left and right subtree is greater than 1.
 * If it is greater than 1, then the tree is not balanced.
 * Else will repeat the process for all the nodes.
 * 
 * Time Complexity: O(n^2) - as we are visiting each node and finding the height of the left and right subtree.
 * Space Complexity: O(H) - recursion stack 
 */
public class BalancedTreeOrNot{
    public boolean isBalanced(Node root) {
        // If the tree is empty, it's balanced
        if (root == null) {
            return true;
        }

        // Calculate the height of left and right subtrees
        int leftHeight = getHeight(root.left);
        int rightHeight = getHeight(root.right);

        // Check if the absolute difference in heights
        // of left and right subtrees is <= 1
        if (Math.abs(leftHeight - rightHeight) <= 1 &&
            isBalanced(root.left) &&
            isBalanced(root.right)) {
            return true;
        }

        // If any condition fails, the tree is unbalanced
        return false;
    }

    // Function to calculate the height of a subtree
    public int getHeight(Node root) {
        // Base case: if the current node is NULL,
        // return 0 (height of an empty tree)
        if (root == null) {
            return 0;
        }

        // Recursively calculate the height
        // of left and right subtrees
        int leftHeight = getHeight(root.left);
        int rightHeight = getHeight(root.right);

        // Return the maximum height of left and right subtrees
        // plus 1 (to account for the current node)
        return Math.max(leftHeight, rightHeight) + 1;
    }
}
/**
 * Optimal Approach:
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
        
        // Balance is never updated to true once it is false.
        if(Math.abs(lh - rh) > 1) balanced = false;
        return Math.max(lh, rh) + 1;
    }
    public boolean isBalanced(TreeNode root) {
        findHeight(root);
        return balanced;
    }
}