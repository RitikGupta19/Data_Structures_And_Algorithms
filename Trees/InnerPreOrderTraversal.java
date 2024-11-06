/*
 * Recursive PreOrder Traversal
 * 
 * Time Complexity: O(n) where n is the number of nodes in the tree
 * Space Complexity: O(logn) - Recursive stack OR O(N) in the worst case
 */
public class PreOrderTraversal {
    public static void calculatePreOrderTraversal(TreeNode root, List<Integer> ans){
        if(root == null) return ;
        ans.add(root.data);
        calculatePreOrderTraversal(root.left, ans);
        calculatePreOrderTraversal(root.right, ans);
    }
}

/*
 * Stack PreOrder Traversal
 * 
 * Time Complexity: O(n) where n is the number of nodes in the tree
 * Space Complexity: O(n) - Stack
 */
public class PreOrderTraversal {
    public List<Integer> preorderTraversal(TreeNode root) {
        Stack<TreeNode> st = new Stack<>();
        List<Integer> ans = new ArrayList<>();
        TreeNode curr = root;
        while(true){
            if(curr != null){
                ans.add(curr.val);
                st.push(curr);
                curr = curr.left;
            }
            else {
                if(st.isEmpty()) break;
                curr = st.pop();
                curr = curr.right;
            }
        }   
        return ans;     
    }
}

// OR

public class PreOrderTraversal{
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        Stack<TreeNode> st = new Stack<>();
        if(root ==  null) return ans;
        st.push(root);
        while(!st.isEmpty()){
           root = st.pop();
           ans.add(root.val);
           if(root.right != null) st.push(root.right);
           if(root.left != null) st.push(root.left);
        }    
        return ans;    
    }
}

/*
 * Morris Order Traversal
 * 
 * Time Complexity: O(2n) where n is the number of nodes in the tree
 * Space Complexity: O(1) - No extra space and No recursive stack
 */
public class InnerPreOrderTraversal {
    public static List < Integer > getPreOrderTraversal(TreeNode root) {
		List<Integer> ans = new ArrayList<>();
		TreeNode curr = root;

		while(curr != null){
			if(curr.left == null){
				ans.add(curr.data);
				curr = curr.right;
			}
			else{
				TreeNode prev = curr.left;
				while(prev.right != null && prev.right != curr){
					prev = prev.right;
				}

				if(prev.right == null){
					prev.right = curr;
					ans.add(curr.data);
					curr = curr.left;
				}
				else{
					prev.right = null;
					curr = curr.right;
				}
			}
		}

		return ans;
    }
}
