/*
 * Print the RIGHT view of BT
 * 
 * Intuition:
 * Level Order traversal and we will print the last element of each level
 * or add the last element at each level in ans list
 * 
 * Time Complexity: O(n) where n is the number of nodes in the tree
 * Space Complexity: O(n) - Queue
 */
public class LeftViewBT {
    ArrayList<Integer> rightView(Node root) {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        ArrayList<Integer> ans = new ArrayList<>();
        while(!queue.isEmpty()){
            int queueSize = queue.size();
            for(int i = 0; i < queueSize; i++){
                Node curr = queue.poll();
                if(i == queueSize - 1) ans.add(curr.data);
                if(curr.left != null) queue.offer(curr.left);
                if(curr.right != null) queue.offer(curr.right);
            }
        }
        return ans;
    }
}

/*
 * Recursive Solution:
 * We will traverse in a NRL fashion and add the last element at each level
 * This can be done by adding elements only when list size is equal to the level
 * 
 * Time Complexity: O(n) where n is the number of nodes in the tree
 * Space Complexity: O(1) - Recursive stack
 * Axual Space Complexity: O(n) - Recursive stack
 */
public class LeftViewBT{
    public void getRightSide(TreeNode root, int level, List<Integer> ans){
        if(root == null) return;
        if(level == ans.size()) ans.add(root.val);
        getRightSide(root.right, level + 1, ans);
        getRightSide(root.left, level + 1, ans);
    }
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        getRightSide(root, 0, ans);
        return ans;        
    }
}

/*
 * Additional Solution:
 * Right view using morris order: https://www.geeksforgeeks.org/print-right-view-binary-tree-2/
 */