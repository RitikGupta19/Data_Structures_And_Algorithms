/*
 * Bottom view of Binary Tree
 * Queue based level order traversal
 * 1. Using TreeMap - Automatically sorts the keys in ascending order
 * 2. Using HashMap - Manually sorting the keys / keeping the min and max values of the vertical distance
 * 
 * We will just update the value of map with the current node value for each vertical distance
 * Automatically TreeMap will keep the last value for each vertical distance
 * 
 * TreeMap approach:
 * Time Complexity: O(n * logn) where n is the number of nodes in the tree and logn operation is for TreeMap
 * storing/adding the elements.
 * 
 * Space Complexity: O(n/2 + n/2) - Map + Queue
 * 
 * HashMap Approach:
 * Time Complexity: O(n) where n is the number of nodes in the tree
 * Space Complexity: O(n) - Map + Queue
 */
class Pair{
    TreeNode node;
    int index;

    Pair(TreeNode node, int index){
        this.node = node;
        this.index = index;
    }
}
public class BottomViewBT {
    public static List<Integer> bottomView(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        Queue<Pair> q = new LinkedList<>();
        TreeMap<Integer, ArrayList<Integer>> map = new TreeMap<>();
        q.offer(new Pair(root, 0));
        while(!q.isEmpty()){
            int qs = q.size();
            for(int i = 0; i < qs; i++){
               Pair curr = q.poll();
               TreeNode ele = curr.node;
               int line = curr.index;
               if(map.containsKey(line)){
                   ArrayList<Integer> eles = map.get(line);
                   eles.add(ele.val);
                   map.put(line, eles);
               }
               else{
                   map.put(line, new ArrayList<>(Arrays.asList(ele.val)));
               }
               if(ele.left != null) q.offer(new Pair(ele.left, curr.index - 1));
               if(ele.right != null) q.offer(new Pair(ele.right, curr.index + 1));
            }
        }
        for(List<Integer> l: map.values()){
            ans.add(l.get(l.size() - 1));
        }
        return ans;
    }
}

/*
 * For other hashmap solution: https://www.geeksforgeeks.org/print-nodes-top-view-binary-tree/
 * 
 * Recursive solution (without queue): https://www.geeksforgeeks.org/bottom-view-binary-tree/
 * 
 * Time Complexity: O(n) where n is the number of nodes in the tree
 * Space Complexity: O(n) - Recursive stack
 */
 public class BottomViewBT {

    static void dfs(Node root, int hd, int depth, 
                            Map<Integer, Pair> hdMap) {
        if (root == null) return;

        // If this horizontal distance is 
        // being visited for the first time or
        // we're at a deeper level, update it
        if (!hdMap.containsKey(hd) 
                  || depth >= hdMap.get(hd).depth) {
            hdMap.put(hd, new Pair(root.data, depth));
        }

        // Traverse the left subtree with 
        // HD - 1 and increased depth
        dfs(root.left, hd - 1, depth + 1, hdMap);

        // Traverse the right subtree 
        // with HD + 1 and increased depth
        dfs(root.right, hd + 1, depth + 1, hdMap);
    }

    // Function to return the bottom view of
    // the binary tree using DFS
    static ArrayList<Integer> bottomView(Node root) {
        if (root == null) return new ArrayList<>();

        // Map to store the last node's data and its depth
        // at each horizontal distance (HD)
        Map<Integer, Pair> hdMap = new TreeMap<>();

        // Start DFS with root at HD 0 and depth 0
        dfs(root, 0, 0, hdMap);
        
        // Extract bottom view nodes from the map
        ArrayList<Integer> result = new ArrayList<>();

        // Iterate through the map in sorted HD order
        for (Pair value : hdMap.values()) {
            result.add(value.data);
        }
        
        return result;
    }

    // Pair class to store node data and depth
    static class Pair {
        int data, depth;

        Pair(int data, int depth) {
            this.data = data;
            this.depth = depth;
        }
    }
}