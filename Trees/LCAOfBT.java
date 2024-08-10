/*
 * Problem: https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/
 * To find the lowest common ancestor of a binary tree
 * 
 * Brute-force approach:
 * To find the path from root to the given node p and q
 * Then compare the paths and find the last common node
 * After which all the nodes will be the different
 * The ans will be the last common node
 * 
 * Time Complexity: O(n) where n is the number of nodes in the tree
 * Space Complexity: O(n + n) - Recursive stack + List
 */
public @interface LCAOfBT {
    private boolean findPath(TreeNode root, TreeNode p, List<TreeNode> l){
        if(root == null) return false;
        l.add(root);
        if(root == p || findPath(root.left, p, l) || findPath(root.right, p, l))
            return true;
        l.remove(l.size() - 1);
        return false;
    }
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        List<TreeNode> l1 = new ArrayList<>();
        List<TreeNode> l2 = new ArrayList<>();

        if(!findPath(root, p, l1) || !findPath(root, q, l2)) return null;
        int i;
        for(i = 0; i < l1.size() && i < l2.size(); i++){
            if(l1.get(i) != l2.get(i)) break;
        }
        return l1.get(i - 1);
    }
}

/*
 * Better approach:
 * Intuition: To recursively find the left and right subtree
 * If the root is equal to p or q, then return the root
 * Then recursively find the left and right subtree
 * If both the left and right subtree are not null, then return the root
 * If left subtree is null, then return the right subtree
 * If right subtree is null, then return the left subtree
 * 
 * The idea is to return the p/q if found, else null
 * Once returned the parent(each time root will be returned)
 * 
 * Time Complexity: O(n) where n is the number of nodes in the tree
 *  Space Complexity: O(n) - Recursive stack
 */
class LCAOfBT {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null | root == p || root == q)
            return root; 

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if(left == null){
            return right;
        }
        if(right == null)
            return left;
        return root;
    }
}

