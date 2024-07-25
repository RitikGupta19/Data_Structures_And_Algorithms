/**
 * Problem: to find whether two trees are mirror or not ?
 * 
 * Optimal solution space wise
 * Intuition:
 * 1. Root node of both the trees should be equal.
 * 2. Left subtree of the first tree should be equal to the right subtree of the second tree.
 * 3. Right subtree of the first tree should be equal to the left subtree of the second tree.
 * 
 * Recursive Approach:
 * Time Complexity: O(n) - as we are visiting each node once.
 * Space Complexity: O(H) - recursion stack
 */
public class TwoTreeAreMirror {
	public static boolean areMirror(BinaryTreeNode root1, BinaryTreeNode root2) {
		// Write your code here
		if(root1 == null && root2 == null) return true;
		if(root1 == null || root2 == null) return false;

		return root1.data == root2.data 
		&& areMirror(root1.left, root2.right) && areMirror(root1.right, root2.left);
	}
}

/**
 * Iterative solution: takes more space than recursive solution
 * Approach: using 2 stacks we will store the elements
 * 
 * Time Complexity: O(n) - as we are visiting each node once.
 * Space Complexity: O(n + n) - as we are using stack to store the nodes.
 */
public class TwoTreeAreMirror {
	public static boolean areMirror(BinaryTreeNode r1, BinaryTreeNode r2) {
		// Write your code here
		if(r1 == null && r2 == null) return true;
		if(r1 == null || r2 == null) return false;

		Stack<BinaryTreeNode> st1 = new Stack<>();
		Stack<BinaryTreeNode> st2 = new Stack<>();
		st1.push(r1);
		st2.push(r2);

		while(!st1.isEmpty() && !st2.isEmpty()){
			BinaryTreeNode n1 = st1.pop();
			BinaryTreeNode n2 = st2.pop();

			if(n1.data != n2.data) return false;

			// If both non null then add in stacks
			// else if one is null then return false
			if(n1.left != null && n2.right != null){
				st1.push(n1.left);
				st2.push(n2.right);
			}
			else if(n1.left != null || n2.right != null) return false;

			// If both non null then add in stacks
			// else if one is null then return false
			if(n1.right != null && n2.left != null){
				st1.push(n1.right);
				st2.push(n2.left);
			}
			else if(n1.right != null || n2.left != null) return false;
		}

		// If any of the stack is not empty then return false
		if(!st1.isEmpty() || !st2.isEmpty()) return false;
		return true;		
	}
}
