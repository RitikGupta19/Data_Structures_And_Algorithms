/*
 * Problem: Serialize and Deserialize Binary Tree
 * https://leetcode.com/problems/serialize-and-deserialize-binary-tree/
 * 
 * Approach:
 * 1. Serialize:
 * 2. We will do level order traversal and store the values in a string
 * 3. If the node is null, we will store null in the string
 * 4. If the node is not null, we will store the value in the string
 * 5. We will also store the left and right child of the node in the queue
 * 6. Finally, we will return the string
 * 
 * 7. Deserialize:
 * 8. We will split the string by comma
 * 9. We will create a queue and add the root node to the queue
 * 10. We will iterate over the queue
 * 11. For each node, we will check if the left and right child is null or not
 * 12. If it is not null, we will create a new node and add it to the queue
 * 13. Finally, we will return the root node
 * 
 * Time complexity: O(n) where n is the number of nodes in the tree
 * Space complexity: O(n) where n is the number of nodes in the tree
 */

import java.util.LinkedList;
import java.util.Queue;

import javax.swing.tree.TreeNode;

public class SerializeDeserializeBT {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if(root == null) return "[]";
        String s = "";
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while(!q.isEmpty()){
            int qs = q.size();
            for(int i = 0; i < qs; i++){
                TreeNode curr = q.poll();
                if(curr.val != Integer.MIN_VALUE) s += String.valueOf(curr.val) + ',';
                else {
                    s += "null,";
                    continue;
                }

                if(curr.left != null) q.offer(curr.left);
                else q.offer(new TreeNode(Integer.MIN_VALUE));
                if(curr.right != null) q.offer(curr.right);
                else q.offer(new TreeNode(Integer.MIN_VALUE));
            }
            if(q.isEmpty()) s = s.substring(0, s.length() - 1);
        }
        s = '[' + s + ']';
        System.out.println(s);
        return s;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        data = data.substring(1, data.length() - 1);
        String[] arr = data.split(",", -1);
        if(arr[0] == "") return null;
        
        TreeNode root = new TreeNode(Integer.valueOf(arr[0]));
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        int k = 1;
        
        while(!q.isEmpty()){
            int qs = q.size();
            for(int i = 0; i < qs; i++){
                TreeNode node = q.poll();
                for(int j = 0; j < 2; j++){
                    if(!arr[k].equals("null")) {
                        System.out.println(arr[k]);
                        if(k < arr.length) {
                            TreeNode temp = new TreeNode(Integer.valueOf(arr[k++]));
                            if(j % 2 == 0) node.left = temp;
                            else node.right = temp;
                            q.offer(temp);
                        }
                        else break;
                    } else k++;
                }
            }
        }
        return root;
    }
}
