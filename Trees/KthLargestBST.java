/*
 * Problem: Similar to the kth smallest element
 * just that we need to return the kth largest element
 * 
 * Brute force:
 * Intuition: In-order traversal of a BST gives elements in sorted order
 * Store the elements in in-order traversal
 * Return the (size of in-order - k)th element
 * 
 * Time complexity: O(n)
 * Space complexity: O(n + h) = O(n) Stack space + List space
 */
public class KthLargestBST {
    private void reverseInorder(Node root, List<Integer> l){
        if(root == null) return;
        reverseInorder(root.left, l);
        l.add(root.data);
        reverseInorder(root.right, l);
        return;
    }
    // return the Kth largest element in the given BST rooted at 'root'
    public int kthLargest(Node root,int K)
    {
        //Your code here
        List<Integer> l = new ArrayList<>();
        reverseInorder(root, l);
        return l.size() < K ? -1 : l.get(l.size() - K); 
    }
}

/*
 * Optimal solution:
 * Instead of storing all elements in in-order traversal, we can stop when we reach kth element
 * 
 * Time complexity: O(h + k) = O(n) h is the height of the tree
 * Space complexity: O(h) = O(n) Stack space
 */
public class KthLargestBST {
    private void reverseInorder(Node root, int[] num, int k){
        if(root == null || num[1] >= k) return;
        reverseInorder(root.right, num, k);
        num[1] = num[1] + 1;
        if(num[1] == k) {
            num[0] = root.data;
            return;
        }
        reverseInorder(root.left, num, k);
        return;
    }
    // return the Kth largest element in the given BST rooted at 'root'
    public int kthLargest(Node root,int K)
    {
        //Your code here
        int[] num = {Integer.MIN_VALUE, 0};
        reverseInorder(root, num, K);
        return num[0] == Integer.MIN_VALUE ? -1 : num[0]; 
    }    
}