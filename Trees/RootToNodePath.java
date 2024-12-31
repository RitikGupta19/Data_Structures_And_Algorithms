/*
 * Problem: Given a binary tree, find the path from root to a given node.
 * 
 * Hint: We will only add elements which have potential for path else we will remove elements
 * Recursion + boolean method -> if found the node then return true
 * else false and removes the last added element from the list.
 * 
 * Time Complexity: O(n) - as we are visiting each node once.
 * Space Complexity: O(H) - recursion stack
 */
public class RootToNodePath {
    public static boolean getPath(TreeNode root, int x, ArrayList<Integer> ans){
        if(root == null) return false;

        // Add the element before checking
        // If element does not match - then will be removed
        ans.add(root.data);
        if(root.data == x) return true;

        if(getPath(root.left, x, ans) || getPath(root.right, x, ans)) return true;

        ans.remove(ans.size() - 1);
        return false;
    }
    public static ArrayList<Integer> pathInATree(TreeNode root, int x) {
        // Write your code here..
        ArrayList<Integer> ans = new ArrayList<>();
        getPath(root, x, ans);
        return ans;
    }
}
