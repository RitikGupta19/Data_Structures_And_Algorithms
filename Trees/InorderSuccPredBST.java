/*
 * Problem: https://www.geeksforgeeks.org/problems/predecessor-and-successor/1
 * To find the in-order successor and predecessor of a given key in a BST
 * 
 * Intuition:
 * To follow the in-order traversal, we will first move to the left subtree
 * Then we will check if the root is less than the key and greater than the predecessor
 * If yes, then update the predecessor
 * Then we will check if the root is greater than the key and less than the successor
 * If yes, then update the successor
 * Finally, we will move to the right subtree
 * 
 * Time Complexity: O(n) where n is the number of nodes in the tree
 * Space Complexity: O(n) - Recursive stack
 */
public class InorderSuccPredBST {
        // Main in-order logic
        // we are comparing the key with the root and updating the predecessor and successor
        public static void helper(Node root, Node[] pre, Node[] suc, int key){
        if(root == null) return;
        helper(root.left, pre, suc, key);
        if(root.data < key && root.data > pre[0].data) pre[0] = root;
        if(root.data > key && root.data < suc[0].data) suc[0] = root;
        helper(root.right, pre, suc, key);
        return;
    }
    public static void findPreSuc(Node root, Node[] pre, Node[] suc, int key) {
        pre[0] = new Node(Integer.MIN_VALUE);
        suc[0] = new Node(Integer.MAX_VALUE);
        helper(root, pre, suc, key);
        if(pre[0].data == Integer.MIN_VALUE) pre[0] = new Node(-1);
        if(suc[0].data == Integer.MAX_VALUE) suc[0] = new Node(-1);
        return;
    }
}
