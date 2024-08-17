/*
 * Problem: https://leetcode.com/problems/two-sum-iv-input-is-a-bst/
 * Find if there are two elements in a BST that add up to a given sum
 * 
 * Approach:
 * 1. Traverse the tree in in-order and store the elements in a HashSet
 * 2. For each element, check if (sum - element) is present in the HashSet
 * 3. If yes, return true
 * 4. If no, add the element to the HashSet
 * 5. If no such pair is found, return false
 * 
 * Time complexity: O(n)
 * Space complexity: O(n + n) Stack space + HashSet space
 */

public class FindSumPairBST{
        private boolean find(TreeNode node, int k, HashSet<Integer> hs){
        if(node == null) return false;
        if(hs.contains(k - node.val)) return true;
        hs.add(node.val);
        return (find(node.left, k, hs) || find(node.right, k, hs));
    }
    public boolean findTarget(TreeNode root, int k) {
        if(root == null) return false;
        HashSet<Integer> hs = new HashSet<>();
        return find(root, k, hs);
    }
}

/*
 * Optimal solution:
 * Only one traversal is needed with recursion
 * Here we make a BSTIterator class which will traverse the tree in in-order
 * We make two instances of this class, one for normal in-order traversal and one for reverse in-order traversal
 * We will get the elements in ascending order from the first instance and in descending order from the second instance
 * We will keep two pointers, one at the beginning and one at the end i.e l and r
 * 
 * The pushAll method will push all the nodes of the current node to the stack (depending on the reverse flag)
 * The next method will pop the top element from the stack and push all the right/left nodes of the current node to the stack
 * 
 * Time complexity: O(n)
 * Space complexity: O(h + n) = O(h) Stack space + O(n) HashSet space
 * It is better from O(N) space complexity of the previous approach
 */
class BSTIterator {
    Stack<TreeNode> st = new Stack<>();
    boolean reverse = true;

    BSTIterator(TreeNode root, boolean isReverse){
        reverse = isReverse;
        pushAll(root);
    }

    public int next(){
        TreeNode curr = st.pop();
        if(reverse == false) pushAll(curr.right);
        else pushAll(curr.left);
        return curr.val;
    }

    public void pushAll(TreeNode root){
        while(root != null){
            st.push(root);
            if(reverse == true) root = root.right;
            else root = root.left;
        }
    }
}
class Solution {
    public boolean findTarget(TreeNode root, int k) {
        if(root == null) return false;
        BSTIterator b1 = new BSTIterator(root, false);
        BSTIterator b2 = new BSTIterator(root, true);
        int l = b1.next();
        int r = b2.next();
        while(l < r){
            if(l + r == k) return true;
            else if(l + r < k) l = b1.next();
            else r = b2.next();
        }
        return false;
    }
}