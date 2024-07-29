/**
 * Problem: to convert an arbitrary binary tree to a tree that holds the child sum property
 * Child sum property: the value of the parent node is equal to the sum of the values of the left and right children 
 * 
 * We should not think here for bottom-up approach:
 * As it might happen that we start from children and get the sum which is less than the parents
 * So we cannot reduce the parent value as per rules
 * 
 * We can increment the value as many times but cannot decrease it.
 * So we should think for top-down approach
 * 
 * 1. If children sum < parent, then change children value to parents value
 * 2. If children sum >= parent, then change parent value
 */
public class ConvertTreeToChildSum {
    // Function to change the values of the nodes
    // based on the sum of its children's values.
    public void changeTree(TreeNode root) {
        // Base case: If the current node
        // is null, return and do nothing.
        if (root == null) {
            return;
        }

        // Calculate the sum of the values of
        // the left and right children, if they exist.
        int child = 0;
        if (root.left != null) {
            child += root.left.val;
        }
        if (root.right != null) {
            child += root.right.val;
        }

        // Compare the sum of children with
        // the current node's value and update
        if (child >= root.val) {
            root.val = child;
        } else {
            // If the sum is smaller, update the
            // child with the current node's value.
            if (root.left != null) {
                root.left.val = root.val;
            } else if (root.right != null) {
                root.right.val = root.val;
            }
        }

        // Recursively call the function
        // on the left and right children.
        changeTree(root.left);
        changeTree(root.right);

        // Calculate the total sum of the
        // values of the left and right
        // children, if they exist.
        int tot = 0;
        if (root.left != null) {
            tot += root.left.val;
        }
        if (root.right != null) {
            tot += root.right.val;
        }

        // If either left or right child
        // exists, update the current node's
        // value with the total sum.
        if (root.left != null || root.right != null) {
            root.val = tot;
        }
    }
}

