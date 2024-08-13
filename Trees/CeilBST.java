/*
 * Problem
 * Ceil in BST
 * To find the ceil of a given key in a BST
 * 
 * Time Complexity: O(h) where h is the height of the tree
 * Space Complexity: O(h) - Recursive stack
 */
public class CeilBST {
    public static void helper(TreeNode<Integer> node, int x, int[] max){
        if(node == null) return;

        if(node.data < x) helper(node.right, x, max);
        else{
            if(node.data < max[0])  max[0] = node.data;
            helper(node.left, x, max);
        }
        return;
    }

    public  static int findCeil(TreeNode<Integer> node, int x) {

        // Write your code here
        int[] max = {Integer.MAX_VALUE};
        helper(node, x, max);
        return max[0] == Integer.MAX_VALUE ? -1 : max[0];
    }
}
