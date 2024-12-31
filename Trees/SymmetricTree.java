/**
 * Problem: whether the given tree is symmetric or not.
 * The given tree is symmetric if the left subtree is a mirror reflection of the right subtree.
 * 
 * Approach: Similar to the problem where we need to tell whether the two trees are mirror or not.
 * 1. Root node of both the trees should be equal.
 * 2. Left subtree of the first tree should be equal to the right subtree of the second tree.
 * 3. Right subtree of the first tree should be equal to the left subtree of the second tree.
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
class GfG
{
    public static boolean isSymmetry(Node root1, Node root2){
        if(root1 == null && root2 == null) return true;
		if(root1 == null || root2 == null) return false;

		return root1.data == root2.data 
		&& isSymmetry(root1.left, root2.right) && isSymmetry(root1.right, root2.left);
    }
    // return true/false denoting whether the tree is Symmetric or not
    public static boolean isSymmetric(Node root)
    {
        // add your code here;
        if(root == null) return true;
        
        return isSymmetry(root.left, root.right);
    }
}