/*
 * Problem: to search the given element in BST
 * 
 * Time Complexity: O(h) where h is the height of the tree - worst case O(n) for skewed tree
 * Here time complexity is O(h) because we are traversing only one path from root to leaf
 * 
 * Space Complexity: O(1) - no extra space is used, recursion stack is used which is O(h) where h is the height of the tree
 */
public TreeNode searchBST(TreeNode root, int val) {
    if(root == null) return null;

    if(root.val == val) return root;
    if(root.val < val) return searchBST(root.right, val);
    if(root.val > val) return searchBST(root.left, val);   
    return null;
}