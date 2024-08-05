/*
 * https://leetcode.com/problems/flatten-binary-tree-to-linked-list/
 * Problem: to flatten the given binary tree to linked list
 * It should be done in-place (No new node should be created)
 * 
 * When new node can be created: simply do a pre-order traversal and create a new linked list
 * 
 * Intuition:
 * To do the reverse pre-order traversal and keep track of the previous node
 * NLR -> RLN
 * Use a prev node to keep track of the previous node
 * 
 * Algorithm:
 * 1. Flatten the right subtree
 * 2. Flatten the left subtree
 * 3. Link the left subtree to the right subtree
 * 
 * Time Complexity: O(n) where n is the number of nodes in the tree
 * Space Complexity: O(h) where h is the height of the tree, O(N) for skewed tree
 */
class FlattenBT {
    TreeNode prev = null;
    public void flatten(TreeNode root) {
        if(root == null) return;

        flatten(root.right);
        flatten(root.left);

        // Initially prev is null, so for the first leaf node, prev will be null
        // Now prev will update to last processed node

        // EG: 6 -> 7 (L) null (R), prev = null
        // 7 -> null(L & R), prev = 7
        // next time, 6 -> null (L) 7 (R), prev = 6
        root.right = prev;
        root.left = null;
        prev = root;
    }
}

/*
 * Solution with stack:
 * Complexity same as previous solution
 * Time Complexity: O(n) where n is the number of nodes in the tree
 * Space Complexity: O(n) where n is the number of nodes in the tree
 */
class FlattenBT {
    public void flatten(TreeNode root) {
        if(root == null) return;
        Stack<TreeNode> st = new Stack<>();
        st.push(root);

        while(!st.isEmpty()){
            TreeNode curr = st.peek();
            st.pop();

            // Main idea to push the right ele first and left later
            // So that we can link the left to right (left side processed first)
            if(curr.right != null) st.push(curr.right);
            if(curr.left != null) st.push(curr.left);

            // Link curr node right to the top of the stack (left element)
            if(!st.isEmpty()) curr.right = st.peek();
            // make left as null
            curr.left = null;
        }
    }
}

/**
 * Solution with Morris Traversal:
 * 
 * Time Complexity: O(n) where n is the number of nodes in the tree
 * Space Complexity: O(1) - no extra space is used
 */
class FlattenBT {
    public void flatten(TreeNode root) {
        TreeNode curr = root;
        while(curr != null){
            if(curr.left != null){
                TreeNode prev = curr.left;
                while(prev.right != null){
                    prev = prev.right;
                }
                prev.right = curr.right;
                curr.right = curr.left;
                curr.left = null;
            }
            curr = curr.right;
        }   
    }
}

