/**
 * Problem: to find whether two trees are identical or not
 * Trees are considered identical when:
 * Value of a node in the first tree is equal to the value of the corresponding node in the second tree.
 * Left subtree of this node is identical to the left subtree of the corresponding node.
 * Right subtree of this node is identical to the right subtree of the corresponding node.
 * 
 * Approach:
 * We will iterate in pre-order traversal and check if the value of the node is equal or not.
 * Idea to use pre-order is to validate the equality of values of node before going to subtrees.
 * 
 * Time Complexity: O(n) - as we are visiting each node once.
 * Space Complexity: O(H) - recursion stack
 */
class Solution {
    private boolean findIdenticalOrNot(TreeNode a, TreeNode b){
        if((a == null && b != null) || (a != null && b == null)) return false;
        if(a == null && b == null) return true;
        if(a.val != b.val) return false;
        return findIdenticalOrNot(a.left, b.left) && findIdenticalOrNot(a.right, b.right);
        
    }
    public boolean isSameTree(TreeNode p, TreeNode q) {
        return findIdenticalOrNot(p, q);
    }
}