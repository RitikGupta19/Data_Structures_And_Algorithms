/*
 * Problem: Given a binary tree, return the zigzag level order traversal of its nodes' values.
 * (ie, from left to right, then right to left for the next level and alternate between).
 * 
 * Approach 1:
 * If result is asked in the form of list of lists, then we can use the same approach as level order traversal
 * and then reverse the list of lists at even levels.
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */
public class ZigZagTraversal {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        List<List<Integer>> ans = new ArrayList<>();
        boolean leftToRight = true;
        if(root == null) return ans;
        q.offer(root);

        while(!q.isEmpty()){
            int qs = q.size();
            List<Integer> level = new ArrayList<>();
            for(int i = 0; i < qs; i++){
                TreeNode curr = q.poll();

                level.add(curr.val);
                if(curr.left != null) q.offer(curr.left);
                if(curr.right != null) q.offer(curr.right);
            }
            if(!leftToRight) {
				// Can try adding elemets in reverse order in final array
				// better complexity
                Collections.reverse(level);
            }
            leftToRight = !leftToRight;
            ans.add(level);
        }
        return ans;
    }
}

/*
 * Same approach can be used to get the result in the form of a list of integers
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */
public class ZigZagTraversal{
    ArrayList<Integer> zigZagTraversal(Node root)
	{
	    //Add your code here.
	    ArrayList<Integer> ans = new ArrayList<>();
	    if(root == null) return ans;
	    
	    boolean ltor = true;
	    int start = 0;
	    Queue<Node> q = new LinkedList<>();
	    q.offer(root);
	    
	    while(!q.isEmpty()){
	        int size = q.size();
	        ArrayList<Integer> temp = new ArrayList<>();
	        
	        for(int i = 0; i < size; i++){
	            Node curr = q.poll();
	            temp.add(curr.data);
	            
	            if(curr.left != null) q.offer(curr.left);
	            if(curr.right != null) q.offer(curr.right);
	        }
	        
	        if(ltor == false) Collections.reverse(temp);
	        ans.addAll(temp);
	        ltor = !ltor;
	        
	    }
	    
	    return ans;
	}
}

/*
 * Optimal way: without reversing the list at even levels for list of lists
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */
public class ZigZagTraversal{
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        List<List<Integer>> ans = new ArrayList<>();
        boolean leftToRight = true;
        if(root == null) return ans;
        q.offer(root);

        while(!q.isEmpty()){
            int qs = q.size();
            Integer[] level = new Integer[qs];
            for(int i = 0; i < qs; i++){
                TreeNode curr = q.poll();
                
                int ind = leftToRight == true ? i : qs - 1 - i;
                level[ind] = curr.val;

                if(curr.left != null) q.offer(curr.left);
                if(curr.right != null) q.offer(curr.right);
            }
            leftToRight = !leftToRight;
            ans.add(Arrays.asList(level)); 
        }
        return ans;
    }
}
