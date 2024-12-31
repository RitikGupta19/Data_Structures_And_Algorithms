/*
 * To get the preorder, inorder, and postorder traversal of a binary tree
 * in a single traversal
 * 
 * We can use the stack and maintain a visit times counter for each node.
 * If node is visited for the first time, then add it to the preorder list
 * If node is visited for the second time, then add it to the inorder list
 * If node is visited for the third time, then add it to the postorder list
 * 
 * Time Complexity: O(3n) where n is the number of nodes in the tree
 * Space Complexity: O(4n) - Stack
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import javax.swing.tree.TreeNode;

public class PreorderInorderPostorderBT {
    public static List<List<Integer>> getTreeTraversal(TreeNode root) {
        // Write your code here.
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> pre = new ArrayList<>();
        List<Integer> post = new ArrayList<>();
        List<Integer> inorder = new ArrayList<>();
        Stack<Pair> st = new Stack<>();
        if(root == null) return ans;
        st.push(new Pair(root, 1));

        while(!st.isEmpty()){
            Pair curr = st.pop();
            TreeNode node = curr.node;
            int visited = curr.times;
            if(visited == 1){
                pre.add(node.data);
                st.push(new Pair(node, ++visited));
                if(node.left != null) st.push(new Pair(node.left, 1));
            }
            else if(visited == 2){
                inorder.add(node.data);
                st.push(new Pair(node, ++visited));
                if(node.right != null) st.push(new Pair(node.right, 1));
            }
            else{
                post.add(node.data);
            }
        }
        ans.add(inorder);
        ans.add(pre);
        ans.add(post);
        return ans;
    }   
}
