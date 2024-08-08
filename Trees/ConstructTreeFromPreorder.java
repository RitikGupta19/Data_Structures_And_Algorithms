/*
 * Problem: https://leetcode.com/problems/construct-binary-search-tree-from-preorder-traversal/
 * To construct tree from pre-order traversal
 * 
 * Intuition:
 * To start from the first node of traversal and recursively construct the left and right subtree
 * Then break remaining array in two sub trees depending on the value of the root
 * 
 * Time Complexity: O(n^2) where n is the number of nodes in the tree
 * Space Complexity: O(n) - Recursive stack
 */
public class ConstructTreeFromPreorder {
    private TreeNode helper(int[] pre, int start, int end){
        if(start > end) return null;

        TreeNode root = new TreeNode(pre[start]);
        int i;
        for(i = start; i <= end; i++){
            if(pre[i] > root.val)
                break;
        }
        root.left = helper(pre, start + 1, i - 1);
        root.right = helper(pre, i, end);

        return root;
    }
    public TreeNode bstFromPreorder(int[] preorder) {
        return helper(preorder, 0, preorder.length - 1);
    }
}

/*
 * Optimal Solution:
 * Intuition:
 * Here is to maintain a min and max value for each node
 * If the current node value is less than min or greater than max, then return null
 * Also, increment the index of the pre-order traversal
 * we are keeping this variable as global so that after all the elements from left sub tree
 * the index value is shifted to the right sub tree correctly
 * 
 * Time Complexity: O(n) where n is the number of nodes in the tree
 * Space Complexity: O(n) - Recursive stack
 */
class ConstructTreeFromPreorderOptimal {
    int ind;
    private TreeNode helper(int[] pre, int min, int max){
        if(ind == pre.length || pre[ind] < min || pre[ind] > max) return null;
        int val = pre[ind++];
        TreeNode node = new TreeNode(val);
        node.left = helper(pre, min, val);
        node.right = helper(pre, val, max);
        return node;
    }
    public TreeNode bstFromPreorder(int[] preorder) {
        ind = 0;
        return helper(preorder, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
}
