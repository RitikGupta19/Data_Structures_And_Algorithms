/*
 * Problem: Construct Binary Tree from Post-order and In-order Traversal
 * In-order and post-order will always construct a unique binary tree
 * 
 * Intuition:
 * It is to maintain a postStart, postEnd , inStart, inEnd for both the arrays
 * as we know the fact that the first element in pre-order is the root
 * 
 * Then recursively construct the left and right subtree
 * 
 * Time Complexity: O(n) where n is the number of nodes in the tree
 * Space Complexity: O(n + n) - Recursive stack + HashMap
 *  
 */
public class ConstructBTFromInorderPostorder {
    private TreeNode helper(int[] in, int inStart, int inEnd, int[] post, int poStart, int poEnd, HashMap<Integer, Integer> map){
        if(inStart > inEnd || poStart > poEnd) return null;

        TreeNode root = new TreeNode(post[poEnd]);

        int inRoot = map.get(post[poEnd]);
        int nodesLeft = inRoot - inStart;

        root.left = helper(in, inStart, inRoot - 1, post, poStart, poStart + nodesLeft - 1, map);
        root.right = helper(in, inRoot + 1, inEnd, post, poStart + nodesLeft, poEnd - 1, map);

        return root;
    }
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < inorder.length; i++)
            map.put(inorder[i], i);
        
        return helper(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1, map);
    }
}