/**
 * Max depth of N-ary tree:
 * Problem link: https://leetcode.com/problems/maximum-depth-of-n-ary-tree/
 * Similar to the max path sum in binary tree / max path sum in nary tree.
 * 
 * Similar problems: https://www.geeksforgeeks.org/maximum-path-sum-in-a-n-ary-tree/
 * 
 * Approach:
 * 1. If the root is null, return 0.
 * 2. If the root has no children, return 1.
 * 3. Maintain top two maximum depths, but will return the max of two + 1.
 * 4. For all the children we will find the maximum depth.
 * 
 * Time complexity: O(n): since each node is visited once.
 * Space complexity: O(h): where h is the height of the tree.
 */
public class MaxDepthNaryTree {
/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/

class Solution {
    public int maxDepth(Node root) {
        if(root == null) return 0;
        if(root.children.size() == 0) return 1;

        int m1 = Integer.MIN_VALUE, m2 = Integer.MIN_VALUE;
        int n = root.children.size();
        for(int i = 0; i < n; i++){
            int val = maxDepth(root.children.get(i));
            // Maintain top two maximum depths
            if(val > m1){
                m2 = m1;
                m1 = val;
            }
            else if(val > m2) m2 = val;
        }

        // returning the max of two + 1
        return m1 + 1;
    }
}   
}
