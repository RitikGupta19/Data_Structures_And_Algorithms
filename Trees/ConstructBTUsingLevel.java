/*
 * Construct a binary tree using level order traversal
 */
public class ConstructBTUsingLevel {
    public TreeNode constructBT(int[] arr){
        public TreeNode buildTree(int[] array) {
            if (array == null || array.length == 0) {
                return null;
            }
    
            Queue<TreeNode> queue = new LinkedList<>();
            TreeNode root = new TreeNode(array[0]);
            queue.offer(root);
    
            int index = 1;
            while (!queue.isEmpty() && index < array.length) {
                TreeNode current = queue.poll();
    
                // Create left child
                if (index < array.length && array[index] != -1) { // Assuming -1 represents null
                    TreeNode leftChild = new TreeNode(array[index]);
                    current.left = leftChild;
                    queue.offer(leftChild);
                }
                index++;
    
                // Create right child
                if (index < array.length && array[index] != -1) { // Assuming -1 represents null
                    TreeNode rightChild = new TreeNode(array[index]);
                    current.right = rightChild;
                    queue.offer(rightChild);
                }
                index++;
            }
    
            return root;
        }
    }
}