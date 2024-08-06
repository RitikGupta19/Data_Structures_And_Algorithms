/*
 * Problem: Construct Binary Tree from Pre-order and In-order Traversal
 * In-order and pre-order will always construct a unique binary tree
 * 
 * Intuition:
 * It is to maintain a preStart, preEnd , inStart, inEnd for both the arrays
 * as we know the fact that the first element in pre-order is the root
 * 
 * Then recursively construct the left and right subtree
 * 
 * Time Complexity: O(n) where n is the number of nodes in the tree
 * Space Complexity: O(n + n) - Recursive stack + HashMap
 *  
 */
public class ConstructBTFromInorderPreorder {
    private TreeNode helper(int[] pre, int preStart, int preEnd, int[] in, int inStart, int inEnd, HashMap<Integer, Integer> map){
        if(preStart > preEnd || inStart > inEnd) return null;
        
        TreeNode root = new TreeNode(pre[preStart]);
        
        int inRoot = map.get(pre[preStart]);
        int nodesLeft = inRoot - inStart;

        root.left = helper(pre, preStart + 1, preStart + nodesLeft, in, inStart, inRoot - 1, map);
        root.right  = helper(pre, preStart + nodesLeft + 1, preEnd, in, inRoot + 1, inEnd, map);

        // We have returned the root of the tree in last
        // to assign to root.left and root.right
        // and in end to return the root of the tree
        return root;
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < inorder.length; i++)
            map.put(inorder[i], i);
        int inStart = 0, preStart = 0, inEnd = inorder.length - 1, preEnd = preorder.length - 1;
        return helper(preorder, preStart, preEnd, inorder, inStart, inEnd, map);
    }
}
