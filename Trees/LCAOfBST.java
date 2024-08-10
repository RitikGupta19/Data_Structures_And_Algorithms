/*
 * Problem:https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/description/
 * Find the LCA of a BST
 * 
 * Intuition:
 * Here it makes easy to find the LCA as the left subtree will be less than the root
 * and right subtree will be greater than the root
 * We will move to the left or right subtree depending on the value of the root
 * Once the value of the root is between p and q, then return the root
 * 
 * Time Complexity: O(n) where n is the number of nodes in the tree
 * Space Complexity: O(n) - Recursive stack
 */
public class LCAOfBST {
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || root == p || root == q) return root;

        if(p.val > root.val && q.val > root.val) return lowestCommonAncestor(root.right, p, q);
        else if(p.val < root.val && q.val < root.val) return lowestCommonAncestor(root.left, p, q);
        else return root;
    }
}
