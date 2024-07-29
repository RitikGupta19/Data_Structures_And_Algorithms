/**
 * Problem: check whether the binary tree follows child sum property or not
 * Child sum property: the value of the parent node is equal to the sum of the values of the left and right children
 * 
 * Intuition:
 * Recursively check the child sum property for the left and right children of the node
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(h) where h is the height of the tree
 */
class Solution
{
    //Function to check whether all nodes of a tree have the value 
    //equal to the sum of their child nodes.
    public static int isSumProperty(Node root)
    {
        // add your code here
        // When No node return 1 - valid tree
        if(root == null) return 1;
        
        // consider null nodes value as '0'
        int left = root.left != null ? root.left.data : 0;
        int right = root.right != null ? root.right.data : 0;
        
        // for all non-leaf nodes check child sum property
        if((root.left != null || root.right != null) && root.data != (left + right)) return 0;
        
        // recursively check the child sum property for the left and right children
        if(isSumProperty(root.left) == 0) return 0;
        if(isSumProperty(root.right) == 0) return 0;
        return 1;
    }
}