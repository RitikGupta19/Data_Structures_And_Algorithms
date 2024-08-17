/*
 * Problem: https://leetcode.com/problems/binary-search-tree-iterator/
 * Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.
 * Calling next() will return the next smallest number in the BST.
 * Calling hasNext() should return whether the next element exists.
 * 
 * Approach:
 * 1. We will make a BSTIterator class which will traverse the tree in in-order
 * 2. We will push all the left nodes of the current node to the stack
 * 3. The next method will pop the top element from the stack and push all the right nodes of the current node to the stack
 * 4. The hasNext method will return true if the stack is not empty
 * 
 * Time complexity: O(n)
 * Space complexity: O(h) Stack space 
 */
public class BSTIterator {
    Stack<TreeNode> st = new Stack<>();
    public BSTIterator(TreeNode root) {
        pushAll(root);
    }
    
    public int next() {
        TreeNode curr = st.pop();
        pushAll(curr.right);
        return curr.val;
    }
    
    public boolean hasNext() {
        return !st.isEmpty();
    }

    public void pushAll(TreeNode root){
        while(root != null){
            st.push(root);
            root = root.left;
        }
    }
}

/*
 * Similar Solution: Using list
 * Storing all the elements in a list in in-order and then iterating over the list
 */
public class BSTIterator{
    List<Integer> l = new ArrayList<>();
    int i = -1;
    
    public BSTIterator(TreeNode root) {
        inOrder(root);
    }

    public void inOrder(TreeNode root){
        if(root == null) return ;
        inOrder(root.left);
        l.add(root.val);
        inOrder(root.right);
    }
    
    public int next() {
        i++;
        return l.get(i);
    }
    
    public boolean hasNext() {
        return (i + 1) < l.size() ? true : false;
    }
}
