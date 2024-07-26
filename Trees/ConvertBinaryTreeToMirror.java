/**
 * Problem: to convert a binary tree to its mirror
 * 
 * Intuition:
 * Firstly recursively reach to the leaf node and then swap the left and right children of each node
 * 1. Recursively swap the left and right children of each node
 * 2. Base case: if the node is null, return
 * 
 * Algorithm:
 * 1. Call the method to the left child of the root node
 * 2. Call the method to the right child of the root node
 * 3. Swap the left and right children of the root node
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(h) where h is the height of the tree
 */
class ConvertBinaryTreeToMirror
{
    public static void mirror(Node node)
    {
        // Your code here
        if(node == null) return;
        
        mirror(node.left);
        mirror(node.right);
        
        Node temp = node.left;
        node.left = node.right;
        node.right = temp;
    }
}

/**
 * Level Order Traversal method:
 * Another approach is to use a queue to store the nodes of the tree level by level
 * 
 * Time Complexity: O(n) - for traversing all the nodes
 * Space Complexity: O(n) - for the queue
 */
class ConvertBinaryTreeToMirror{
    void mirror(Node node) {
        // Your code here
        if(node == null) return;
        
        Queue<Node> q = new LinkedList<>();
        q.offer(node);
        
        while(!q.isEmpty()){
            Node n = q.poll();
            Node t = n.left;
            n.left = n.right;
            n.right = t;
            
            if(n.left != null) q.offer(n.left);
            if(n.right != null) q.offer(n.right);
        }
    }
}