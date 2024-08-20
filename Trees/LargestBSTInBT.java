/*
 * Problem: https://www.geeksforgeeks.org/problems/largest-bst/1
 * Given a binary tree. Find the size of the largest subtree which is a Binary Search Tree (BST).
 * Here, we are not returning the size of the BST, but the size of the largest subtree which is a BST
 * 
 * Approach:
 * We will maintain a class MyNode which will store the min, max and size of the tree
 * We need size and what is min and max of the tree to validate for each node
 * We start from the leaf to root as BST will be from leaf to root
 * If it is valid bst in bottoms level then only it will be valid bst in upper levels
 * 
 * 1. Do post-order traversal
 * 2. For each node, check if it is a valid BST or not
 * 3. If it is a valid BST, then maintain the min, max and size of the tree
 * 4. If not, then return min as LEAST, max as MAX and size as Max(left, right) - as we need to have the size of the largest subtree. We are keeping LEAST and MAX value so that it further does not affect the upper levels
 * 5. Finally, return the size of the largest subtree
 * If node null - then return 0 size, max - Integer.MIN_VALUE and min - Integer.MAX_VALUE
 * 
 * Time complexity: O(n) where n is the number of nodes in the tree
 * Space complexity: O(h) where h is the height of the tree 
 */
class MyNode{
    int min;
    int max;
    int size;
    MyNode(int min, int max, int size){
        this.min = min;
        this.max = max;
        this.size = size;
    }
}
public class LargestBSTInBT {
    static MyNode helper(Node root){
        if(root == null) return new MyNode(Integer.MAX_VALUE, Integer.MIN_VALUE, 0);
        MyNode l = helper(root.left);
        MyNode r = helper(root.right);
        
        if(l.max < root.data && root.data < r.min){
            int min = Math.min(l.min, root.data);
            int max = Math.max(r.max, root.data);
            return new MyNode(min, max, l.size + r.size + 1);
        }
        
        return new MyNode(Integer.MIN_VALUE, Integer.MAX_VALUE, Math.max(l.size, r.size));
    }
    // Return the size of the largest sub-tree which is also a BST
    static int largestBst(Node root)
    {
        // Write your code here
        if(root == null) return 0;
        MyNode ans = helper(root);
        return ans.size;
    }
}
