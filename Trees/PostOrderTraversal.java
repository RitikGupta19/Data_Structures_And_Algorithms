/*
 * Recursive PostOrder Traversal of a Binary Tree
 * 
 * Time Complexity: O(n) where n is the number of nodes in the tree
 * Space Complexity: O(logn) - Recursive stack OR O(N) in the worst case
 */
public class PostOrderTraversal {
    public static void calculatePostOrder(TreeNode root, List<Integer> ans){
        if(root == null) return;
        calculatePostOrder(root.left, ans);
        calculatePostOrder(root.right, ans);
        ans.add(root.data);
    }
}

/*
 * Stack PostOrder Traversal of a Binary Tree
 * 
 * Time Complexity: O(n) where n is the number of nodes in the tree
 * Space Complexity: O(n) - Stack
 */
public class PostOrderTraversal {
    public List<Integer> postorderTraversal(TreeNode root) {
        Stack<TreeNode> st = new Stack<>();
        List<Integer> ans = new ArrayList<>();
        TreeNode curr = root;
        while(curr != null || !st.isEmpty()){
            if(curr != null){
                st.push(curr);
                curr = curr.left;
            }
            else {
                TreeNode temp = st.peek().right;
                if(temp == null){
                    temp = st.pop();
                    ans.add(temp.val);
                    while(!st.isEmpty() && temp == st.peek().right){
                        temp = st.pop();
                        ans.add(temp.val);
                    }
                }
                else curr = temp;
            }
        }
        return ans;
    }
}

/*
 * Morris PostOrder Traversal of a Binary Tree
 * 
 * Time Complexity: O(2n) where n is the number of nodes in the tree
 * Space Complexity: O(1) - No extra space and No recursive stack
 */
public class PostOrderTraversal {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        TreeNode curr = root;
        while(curr != null){
            if(curr.right == null){
                ans.add(curr.val);
                curr = curr.left;
            }
            else {
                TreeNode prev = curr.right;
                while(prev.left != null && prev.left != curr){
                    prev = prev.left;
                }

                if(prev.left == null){
                    prev.left = curr;
                    ans.add(curr.val);
                    curr = curr.right;
                }
                else{
                    prev.left = null;
                    curr = curr.left;
                }
            }
        }
        Collections.reverse(ans);
        return ans;
    }
}

