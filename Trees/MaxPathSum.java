/*
 * Problem: to find the maximum path sum in a binary tree
 * Root node is not necessarily to be the part of the path
 * 
 * Intuition:
 * Kind of similar to the diameter of the tree
 * 
 * 1. Recursively find the maximum path sum in the left and right subtrees
 * 2. Here in max path sum - keep 0 as the minimum value and update the max path sum
 *    0 - is to avoid the negatives sums (if parent node is positive and all children are negative)
 * 3. Update the max path sum as the maximum of the current max path sum and the sum of the left and right subtrees
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(h) where h is the height of the tree
 */

 class MaxPathSum {
    private int maxSum(TreeNode node, int[] mp){
        if(node == null) return 0;

        int lp = Math.max(0, maxSum(node.left, mp));
        int rp = Math.max(0, maxSum(node.right, mp));

        mp[0] = Math.max(mp[0], (lp + rp + node.val));
        return node.val + Math.max(lp, rp);
    }
    public int maxPathSum(TreeNode root) {
        int[] mp = new int[1];
        mp[0] = root.val;
        maxSum(root, mp);
        return mp[0];
    }
}