/*
 * Problem Statement: To calculate the diameter of the tree.
 * Diameter of the tree is the longest path from one node to another node.
 * This path may include the root node or may not include the root node.
 * 
 * Approach: Brute force solution:
 * Here we will calculate the height of left and right subtree each time for each node.
 * Also, we will calculate the left subtree & right subtree diameter for each node.
 * Then we will compare the diameter of the left subtree, right subtree and the diameter of the tree.
 * 
 * Time Complexity: O(n^2) - as we are visiting each node and calculating the height of the tree.
 * Space Complexity: O(H) - recursion stack
 */
int diameter(Node root){
    // base case if tree is empty
    if (root == null)
        return 0;

    // get the height of left and right sub-trees
    int lheight = height(root.left);
    int rheight = height(root.right);

    // get the diameter of left and right sub-trees
    int ldiameter = diameter(root.left);
    int rdiameter = diameter(root.right);

    /* Return max of following three
        1) Diameter of left subtree
        2) Diameter of right subtree
        3) Height of left subtree + height of right subtree + 1
        */
    return Math.max(lheight + rheight + 1,
                    Math.max(ldiameter, rdiameter));
}

// Function used to calculate height
static int height(Node node){
    // base case tree is empty
    if (node == null)
        return 0;

    // If tree is not empty then height = 1 + max of
    //  left height and right heights
    return (1
            + Math.max(height(node.left),
                        height(node.right)));
}

/**
 * Approach: Optimal solution:
 * We would calculate the height of the left subtree and the height of the right subtree for each node.
 * Then we will maintain the max diameter at each node by comparing the 
 * diameter value till now and (l_height + r_height)
 * 
 * 1. Calculate the height of the left subtree.
 * 2. Calculate the height of the right subtree.
 * 3. Maintain max diameter at each node.
 * 
 * Time Complexity: O(n) - as we are visiting each node once.
 * Space Complexity: O(H) - recursion stack
 */
class Solution {
    int d = 0;
    private int findDia(TreeNode node){
        if(node == null) return 0;
        int lheight = findDia(node.left);
        int rheight = findDia(node.right);
        d = Math.max(d, lheight + rheight);
        return Math.max(lheight, rheight) + 1;
    }
    public int diameterOfBinaryTree(TreeNode root) {
        findDia(root);
        return d;
    }
}


