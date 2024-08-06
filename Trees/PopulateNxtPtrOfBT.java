/*
 * Problem link: https://leetcode.com/problems/populating-next-right-pointers-in-each-node/
 * Given: perfect binary tree where all leaves are on the same level, and every parent has two children
 * We need to populate each next pointer to point to its next right node
 * 
 * Intuition:
 * Something similar to the level order traversal (BFS)
 * Gives hint to use the queue DS
 * 
 * Time Complexity: O(n) where n is the number of nodes in the tree
 * Space Complexity: O(n) - Queue
 */
public class PopulateNxtPtrOfBT {
    public Node connect(Node root) {
        if(root == null) return null;

        Queue<Node> q = new LinkedList<>();
        q.offer(root);

        while(!q.isEmpty()){
            int qs = q.size();
            
            for(int i = 1; i <= qs; i++){
                Node curr = q.poll();
                if(i == qs) curr.next = null;
                else curr.next = q.peek();

                if(curr.left != null) q.offer(curr.left);
                if(curr.right != null) q.offer(curr.right);
            }
        }

        return root;
    }
}

/*
 * Optimal Solution:
 * Using recursion
 * 
 * Time Complexity: O(n) where n is the number of nodes in the tree
 * Space Complexity: O(1) [Ignoring the recursive stack]
 */
public class PopulateNxtPtrOfBT{
    public Node connect(Node root) {
        if(root == null) return null;
        // for root: next ptr is already set to null
        if(root.left != null) root.left.next = root.right;
        // need to also check its next should not be null
        if(root.right != null && root.next != null) root.right.next = root.next.left;

        connect(root.left);
        connect(root.right);
        return root;
    }
}