/*
 * Print the left view of BT
 * 
 * Intuition:
 * Level Order traversal and we will print the first element of each level
 * or add the first element at each level in ans list
 * 
 * Time Complexity: O(n) where n is the number of nodes in the tree
 * Space Complexity: O(n) - Queue
 */
public class LeftViewBT {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();
        if(root == null) return ans;
        q.offer(root);

        while(!q.isEmpty()){
            int qs = q.size();
            for(int i = 0 ; i < qs; i++){
                TreeNode curr = q.poll();
                if(i == (qs - 1)) ans.add(curr.val);
                if(curr.left != null) q.offer(curr.left);
                if(curr.right != null) q.offer(curr.right);
            }
        }
        return ans;
    }
}

/*
 * Recursive Solution:
 * We will traverse in a NLR fashion and add the first element at each level
 * This can be done by adding elements only when list size is equal to the level
 * 
 * Time Complexity: O(n) where n is the number of nodes in the tree
 * Space Complexity: O(1) - Recursive stack
 * Axual Space Complexity: O(n) - Recursive stack
 */
public class LeftViewBT{
    void printR(Node root, int level, ArrayList<Integer> ans){
        if(root == null) return;
        if(level == ans.size()) ans.add(root.data);
        if(root.left != null) printR(root.left, level + 1, ans);
        if(root.right != null) printR(root.right, level + 1, ans);
    }
    ArrayList<Integer> leftView(Node root) {
        // code here
        ArrayList<Integer> ans = new ArrayList<>();
        printR(root, 0, ans);
        return ans;
    }
}
