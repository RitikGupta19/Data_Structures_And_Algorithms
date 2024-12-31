/*
 * Problem: to find the max width of BT
 * Hint: Keep the track of left most and right most index using the index of the tree elements
 * 
 * Link: https://takeuforward.org/data-structure/maximum-width-of-a-binary-tree/
 * We have to also count the null elements in width 
 * thus can be done using the index of the tree elements instead of vertical index.
 * 
 * Intuition:
 * 1. We will use level order traversal to find the maximum width of the tree
 * 2. Keep the track of left most and right most index using the index of the tree elements
 * 
 * Time Complexity: O(n) where n is the number of nodes in the tree
 * Space Complexity: O(n) - Queue
 */
class Pair{
    TreeNode root;
    int index;
    Pair(TreeNode root, int index){
        this.root = root;
        this.index = index;
    }
}
public class MaxWidthOfBT {
    public int widthOfBinaryTree(TreeNode root) {
        int maxWidth = Integer.MIN_VALUE;
        Queue<Pair> q = new LinkedList<>();
        if(root == null) return 0;
        q.offer(new Pair(root, 0));

        while(!q.isEmpty()){
            int qs = q.size();
            int leftMostIndex = -1, rightMostIndex = -1;
            for(int i = 0; i < qs; i++){
                Pair curr = q.poll();
                TreeNode node = curr.root;
                int index = curr.index;

                if(i == 0) leftMostIndex = index;
                if(i == qs - 1) rightMostIndex = index;
                if(node.left != null) q.offer(new Pair(node.left, (2 * index + 1)));
                if(node.right != null) q.offer(new Pair(node.right, (2 * index + 2)));
            }
            maxWidth = Math.max(maxWidth, rightMostIndex - leftMostIndex + 1);
        }
        return maxWidth;
    }
}