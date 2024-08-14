/*
 * Problem: https://leetcode.com/problems/kth-smallest-element-in-a-bst/
 * Kth smallest element in a BST: k-1th from start in in-order traversal
 * Kth largest element in a BST: (size of in-order - k)th from end in in-order traversal
 * 
 * Brute force:
 * Intuition: In-order traversal of a BST gives elements in sorted order
 * Store the elements in in-order traversal
 * 
 * Time complexity: O(n)
 * Space complexity: O(n + h) = O(n) Stack space + List space
 */
public class KthSmallestBST {
    private void inorder(TreeNode root, List<Integer> l){
        if(root == null) return;
        inorder(root.left, l);
        l.add(root.val);
        inorder(root.right, l);
        return ;
    }
    public int kthSmallest(TreeNode root, int k) {
        if(root == null) return -1;
        List<Integer> l = new ArrayList<>();
        inorder(root, l);
        return l.size() < k - 1 ? -1 : l.get(k - 1);
    }
}

/*
 * Optimal solution:
 * Instead of storing all elements in in-order traversal, we can stop when we reach kth element
 * 
 * Time complexity: O(h + k) = O(n) h is the height of the tree
 * Space complexity: O(h) = O(n) Stack space
 */
public class KthSmallestBST{
    private void inorder(TreeNode root, int[] num, int k){
        if(root == null || num[1] >= k) return;
        inorder(root.left, num, k);
        num[1] = num[1] + 1;
        if(num[1] == k){
            num[0] = root.val;
            return;
        }
        inorder(root.right, num, k);
        return ;
    }
    public int kthSmallest(TreeNode root, int k) {
        if(root == null) return -1;
        int[] num = {Integer.MIN_VALUE, };
        inorder(root, num, k);
        return num[0] == Integer.MAX_VALUE ? -1 : num[0];
    }
}