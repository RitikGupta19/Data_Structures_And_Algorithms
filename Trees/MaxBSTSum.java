/*
 * Problem: https://leetcode.com/problems/maximum-sum-bst-in-binary-tree/
 * We need to get the maximum sum of a Binary Search Tree in a given binary tree
 * 
 * Brute force:
 * Approach:
 * 1. We will traverse the tree
 * 2. For each node, we will check if it is a valid BST or not
 * 3. If it is a valid BST, we will find the sum of the BST and compare it with the max sum
 * 4. Finally, we will return the max sum
 * 
 * Time complexity: O(n^2) where n is the number of nodes in the tree - n (validate bst) * n (traverse tree)
 * Space complexity: O(h) where h is the height of the tree
 */
public class MaxBSTSum{
        int maxBSTSum = 0;
    private int findMaxSum(TreeNode root){
        if(root == null) return 0;
        return root.val + findMaxSum(root.left) + findMaxSum(root.right);
    }
    private boolean validate(TreeNode root, int min, int max){
        if(root == null) return true;
        if(root.val <= min || root.val >= max) return false;
        return validate(root.left, min, root.val) && validate(root.right, root.val, max);
    }
    public int maxSumBST(TreeNode root){
        if(root == null) return 0;
        if(validate(root, Integer.MIN_VALUE, Integer.MAX_VALUE))
            maxBSTSum = Math.max(findMaxSum(root), maxBSTSum);
        maxSumBST(root.left);
        maxSumBST(root.right);
        return maxBSTSum;
    }
}

/*
 * Optimized Solution:
 * 
 * Without traversing the tree again and again, we can get the sum of the BST and validate the BST in the same traversal
 * We will maintain a class MyNode which will store the min, max, sum and size of the tree
 * We need size, sum and what is min and max of the tree to validate for each node
 * 
 * 1. Do post-order traversal
 * 2. For each node, check if it is a valid BST or not
 * 3. If it is a valid BST, then maintain the min, max, sum and size of the tree
 * 
 * Time complexity: O(n) where n is the number of nodes in the tree
 * Space complexity: O(h) where h is the height of the tree
 */
class Node{
    int min, max, sum;
    Node(int min, int max, int sum){
        this.min = min;
        this.max = max;
        this.sum = sum;
    }
}
class MaxBSTSum {
    int ans = 0;
    private Node findMaxBST(TreeNode root){
        if(root == null) return new Node(Integer.MAX_VALUE, Integer.MIN_VALUE, 0);
        Node l = findMaxBST(root.left);
        Node r = findMaxBST(root.right);

        if(l.max < root.val && root.val < r.min){
            int sum = l.sum + r.sum + root.val;
            ans = Math.max(ans, sum);
            return new Node(Math.min(l.min, root.val), Math.max(r.max, root.val), sum);
        }

        else return new Node(Integer.MIN_VALUE, Integer.MAX_VALUE, Math.max(l.sum, r.sum));
    }
    public int maxSumBST(TreeNode root){
        if(root == null) return 0;
        Node node = findMaxBST(root);
        return ans < 0 ? 0 : ans;
    }
}