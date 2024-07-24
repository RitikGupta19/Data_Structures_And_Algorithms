/**
 * Problem: to print the boundary for the given binary tree.
 * Left side + Leaf nodes + Right side
 * 
 * Approach:
 * 1. Left side: 
 * Traverse the left side of the tree and add the non-leaf nodes to the list.
 * If left node is null then go the right child and repeat the above step.
 * 
 * 2. Leaf nodes: 
 * Traverse the tree using pre-order traversal and add the leaf nodes to the list.
 * 
 * 3. Right side:
 * Traverse the right side of the tree and add the non-leaf nodes to the list.
 * If right node is null then go the left child and repeat the above step.
 * Store the nodes in reverse order and then add to the list.
 * 
 * Hint: solve this question by adding root explicitly to the list and then call the above three steps.
 * This will not repeat root. Also, will not print any right side for the cases where the right subtree
 * is null from root itself.
 * 
 * Time Complexity: O(n + n + n) = O(n) - as we are visiting each node once.
 * Space Complexity: O(H) - recursion stack, WC = O(n).
 */
class Solution
{
    boolean isLeaf(Node node){
        return node.left == null && node.right == null;
    }
    void getLeft(Node node, ArrayList<Integer> ans){
        Node curr = node.left;
        while(curr != null){
            if(!isLeaf(curr)){
                ans.add(curr.data);
            }
            if(curr.left != null) curr = curr.left;
            else curr = curr.right;
        }
    }
    void getBoundary(Node node, ArrayList<Integer> ans){
        if(node == null) return;
        if(isLeaf(node)) ans.add(node.data);
        getBoundary(node.left, ans);
        getBoundary(node.right, ans);
    }
    void getRight(Node node, ArrayList<Integer> ans){
        Node curr = node.right;
        ArrayList<Integer> temp = new ArrayList<>();
        while(curr != null){
            if(!isLeaf(curr)){
                temp.add(curr.data);
            }
            if(curr.right != null) curr = curr.right;
            else curr = curr.left;
        }
        for(int i = temp.size() - 1; i >= 0; i--)
            ans.add(temp.get(i));
    }
	ArrayList <Integer> boundary(Node node)
	{
	    ArrayList<Integer> ans = new ArrayList<>();
	    
	    if(node == null) return ans;
	    
	    if(!isLeaf(node)) {
	        ans.add(node.data);
	    }
	    
	    getLeft(node, ans);
	    getBoundary(node, ans);
	    getRight(node, ans);
	    return ans;
	}
}