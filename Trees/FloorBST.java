/*
 * Problem:https://www.naukri.com/code360/problems/floor-from-bst_920457?source=youtube&campaign=Striver_Tree_Videos&utm_source=youtube&utm_medium=affiliate&utm_campaign=Striver_Tree_Videos&count=25&page=1&search=&sort_entity=order&sort_order=ASC&leftPanelTabValue=PROBLEM
 * Floor in BST
 * To find the floor of a given key in a BST
 * 
 * Time Complexity: O(h) where h is the height of the tree
 * Space Complexity: O(h) - Recursive stack
 */
public class FloorBST {
    public static void helper(TreeNode<Integer> root, int[] min, int X){
        if(root == null) return;

        if(root.data > X) helper(root.left, min, X);
        else {
            if(min[0] < root.data) min[0] = root.data;
            helper(root.right, min, X);
        }
        return ;
    }
    public static int floorInBST(TreeNode<Integer> root, int X) {
        //    Write your code here.
        int[] min = {Integer.MIN_VALUE};
        helper(root, min, X);
        return min[0] == Integer.MIN_VALUE ? -1 : min[0];
    }    
}
