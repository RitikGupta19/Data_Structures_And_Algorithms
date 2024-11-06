/*
 * Recursive Solution:
 * 
 * Time Complexity: O(n) where n is the number of nodes in the tree
 * Space Complexity: O(logn) - Recursive stack OR O(N) in the worst case
 */
public class InorderTraversal {
    public static void calculateInOrderTraversal(TreeNode root, List<Integer> ans){
        if(root == null) return ;
        calculateInOrderTraversal(root.left, ans);
        ans.add(root.data);
        calculateInOrderTraversal(root.right, ans);
    }
}

/*
 * Stack Solution:
 * 
 * Time Complexity: O(n) where n is the number of nodes in the tree
 * Space Complexity: O(n) - Stack
 */
public class InorderTraversal {
    public List<Integer> inorderTraversal(TreeNode root) {
        Stack<TreeNode> st = new Stack<>();
        List<Integer> ans = new ArrayList<>();
        TreeNode curr = root;
        while(true){
            if(curr != null){
                st.push(curr);
                curr = curr.left;
            }
            else {
                if(st.isEmpty()) break;
                curr = st.pop();
                ans.add(curr.val);
                curr = curr.right;
            }
        }   
        return ans;     
    }
}

/*
 * Moris Order Solution:
 * 
 * Time Complexity: O(2n) where n is the number of nodes in the tree
 * Space Complexity: O(1) - No extra space and No recursive stack
 */
public class InorderTraversal {
    public static List< Integer > getInOrderTraversal(TreeNode root) {
        // Write your code here.
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
                    curr = curr.left;
                }
                else{
                    prev.right = null;
                    ans.add(curr.data);
                    curr = curr.right;      
                }
            }
        }
            return ans;
    }
}