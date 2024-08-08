/*
 * Problem: https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/description/
 * To build the BST from sorted array
 * 
 * Intuition:
 * The idea is to maintain a start and end index for the array
 * Then find the mid element and recursively construct the left and right subtree
 * 
 * Time Complexity: O(n) where n is the number of nodes in the tree
 * Space Complexity: O(n) - Recursive stack
 */

import javax.swing.tree.TreeNode;

public class ConvertBSTFromSortedArr {
    private TreeNode helper(int[] nums, int start, int end){
        if(start > end) return null;
        int mid = (start + end) / 2;
        TreeNode node = new TreeNode(nums[mid]);
        node.left = helper(nums, start, mid - 1);
        node.right = helper(nums, mid + 1, end);
        return node;
    }
    public TreeNode sortedArrayToBST(int[] nums) {
        return helper(nums, 0, nums.length - 1);
    }
}
