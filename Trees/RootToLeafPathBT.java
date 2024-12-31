/*
 * Problem statement: to find all the paths for root to leaf in a binary tree.
 * 
 * Approach: Recursion
 * 1. Traverse the tree using pre-order traversal and keep adding the nodes to the path.
 * 2. If the node is leaf node then add the path to the list.
 * 3. If the node is not leaf node then traverse the left and right child.
 * 4. Remove the last node from the path after traversing the left and right child.
 * 
 * Time Complexity: O(n) - as we are visiting each node once.
 * Aux Complexity: O(H) - recursion stack, WC = O(n).
 * Space Complexity: O(1)
 */

import java.util.ArrayList;

public class RootToLeafPathBT {
    public static void getPaths(Node root, ArrayList<ArrayList<Integer>> ans, ArrayList<Integer> ds){
        if(root.left == null && root.right == null) {
            ds.add(root.data);
            ans.add(new ArrayList<>(ds));
            ds.remove(ds.size() - 1);
            return;
        }
        ds.add(root.data);
        if(root.left != null) getPaths(root.left, ans, ds);
        if(root.right != null) getPaths(root.right, ans, ds);
        ds.remove(ds.size() - 1);
    }
    public static ArrayList<ArrayList<Integer>> Paths(Node root) {
        // code here
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        if(root == null) return ans;
        ArrayList<Integer> ds = new ArrayList<>();
        getPaths(root, ans, ds);
        return ans;
    }
}

/*
 * Another way if you want to iterate and store in "string"
 * 
 * Time Complexity: O(n) - as we are visiting each node once.
 * Aux Complexity: O(H) - recursion stack, WC = O(n).
 */
public class RootToLeafPathBT{
    public void getPath(TreeNode node, String path, List<String> ans){
        // Since string we do not need to remove after back-tracking
        if(node.left == null && node.right == null) ans.add(path + node.val);
        path += node.val;
        if(node.left != null) getPath(node.left, path + "->", ans);
        if(node.right != null) getPath(node.right, path + "->", ans);
    }
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> ans = new ArrayList<>();
        String path = "";
        if(root ==  null) return ans;
        getPath(root, path, ans);
        return ans;
    }
}