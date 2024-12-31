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

/*
 * Level order traversal without using queue
 * 
 * Approach:
 * 1. We will calculate the height of the tree
 * 2. We will iterate from 1 to height and print the nodes at each level
 */
public class LevelOrderTraversal{
    public int getHeight(TreeNode root){
        if(root == null) return 0;
        int lheight = getHeight(root.left);
        int rheight = getHeight(root.right);
        if(lheight > rheight ) return lheight + 1;
        else return rheight + 1;
    }
    public void getLevel(TreeNode root, int height, List<Integer> level){
        if(root == null) return;
        if(height == 1) level.add(root.val);
        else if(height > 1){
            getLevel(root.left, height - 1, level);
            getLevel(root.right, height - 1, level);
        }
    }
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        int height = getHeight(root);
        for(int i = 1; i <= height; i++){
            List<Integer> level = new ArrayList<>();
            getLevel(root, i, level);
            ans.add(level);
        }
        return ans;
    }
}