/*
 * Problem:
 * To traverse tree level wise
 * 
 * Solution: using queue
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */
public class LevelOrderTraversal{
    public List<List<Integer>> levelOrder(TreeNode root) {
        Queue<TreeNode> q= new LinkedList<>();
        List<List<Integer>> ans = new ArrayList<>();
        if(root == null) return ans;
        q.offer(root);

        while(!q.isEmpty()){
            List<Integer> level = new ArrayList<>();
            int qs = q.size();
            for(int i = 0; i < qs; i++){
                TreeNode curr = q.poll();
                level.add(curr.val);
                if(curr.left != null) q.offer(curr.left);
                if(curr.right != null) q.offer(curr.right);
            }
            ans.add(level);
        }
        return ans;
    }
}