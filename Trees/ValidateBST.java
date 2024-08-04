/*
 * Problem: to validate given binary tree is a valid BST or not
 * Here maintaining the min and max values as it could be a case where:
 * root = 5, right = 6, right of right = 3
 * Here 3 is less than 6, but greater than 5, so it is not a valid BST
 * 
 * Time Complexity: O(n) where n is the number of nodes in the tree
 * Space Complexity: O(h) where h is the height of the tree
 */
private boolean isValid(TreeNode root, long minVal, long maxVal){
    if(root == null) return true;
    if(root.val <= minVal || root.val >= maxVal) return false;
    return isValid(root.left, minVal, root.val) && isValid(root.right, root.val, maxVal);
}
public boolean isValidBST(TreeNode root) {
    return isValid(root, Long.MIN_VALUE, Long.MAX_VALUE);
}