/*
 * Top view of Binary Tree
 * 
 * 1. TreeMap Approach: Automatically sorts the keys in ascending order
 * 2. HashMap Approach: Manually sorting the keys / keeping the min and max values of the vertical distance
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.TreeMap;

import javax.swing.tree.TreeNode;

public class TopViewBT {
    public static List<Integer> getTopView(TreeNode root) {
        // Write your code here.
        List<Integer> ans = new ArrayList<>();
        TreeMap<Integer, Integer> map = new TreeMap<>();
        Queue<TreeNodeWithInt> q = new LinkedList<>();
        q.offer(new TreeNodeWithInt(root, 0));
        while(!q.isEmpty()){
            int qs = q.size();
            for(int i = 1; i <= qs; i++){
                TreeNodeWithInt ele = q.poll();
                TreeNode curr = ele.node;
                int index = ele.index;
                if(!map.containsKey(index)){
                    map.put(index, curr.data);
                }
                if(curr.left != null) {
                    q.offer(new TreeNodeWithInt(curr.left, index - 1));
                }
                if(curr.right != null){
                    q.offer(new TreeNodeWithInt(curr.right, index + 1));
                }
            }
        }
        for (int value : map.values()) {
            ans.add(value);
        }
        return ans;
    }
}

/*
 * HashMap Approach:
 */
static ArrayList<Integer> topView(Node root)
    {
        // add your code
        ArrayList<Integer> ans = new ArrayList<>();
        Queue<Pair> q = new LinkedList<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        int minIndex = 0;
        int maxIndex = 0;
        
        if(root == null) return ans;
        
        q.offer(new Pair(root, 0));
        while(!q.isEmpty()){
            int qs = q.size();
            for(int i = 0; i < qs; i++){
                Pair p = q.poll();
                Node n = p.node;
                int ind = p.index;
                if(!map.containsKey(ind)){
                    map.put(ind, n.data);
                }
                if(n.left != null) {
                    minIndex = Math.min(minIndex, ind - 1);
                    q.offer(new Pair(n.left, ind - 1));
                }
                if(n.right != null){
                    maxIndex = Math.max(maxIndex, ind + 1);
                    q.offer(new Pair(n.right, ind + 1));
                }
            }
        }
        for(int i = minIndex; i <= maxIndex; i++){
            ans.add(map.get(i));
        }
        return ans;
    }
