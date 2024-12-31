/*
 * Print the Vertical Order Traversal of BT
 * 
 * Intuition:
 * We will use a TreeMap to store the vertical order traversal of the tree
 * We will use a Queue to traverse the tree in a level order fashion
 * We will use a Pair class to store the TreeNode and its vertical distance
 * 
 * Time Complexity: O(nlogn) where n is the number of nodes in the tree
 * Space Complexity: O(n) - Map + Queue
 */
public class VerticalOrderTraversalBT {
    public static ArrayList<Integer> verticalOrderTraversal(TreeNode<Integer> root) 
    {
        //    Write your code here.
        ArrayList<Integer> ans = new ArrayList<>();
        Queue<Pair> q = new LinkedList<>();
        Map<Integer, ArrayList<Integer>> map = new TreeMap<>();
        if(root ==  null) return ans;
        q.offer(new Pair(root, 0));
        while(!q.isEmpty()){
            int qs = q.size();
            for(int i = 0; i < qs; i++){
                Pair curr = q.poll();
                TreeNode<Integer> ele = curr.node;
                Integer line = curr.line;
                if(map.containsKey(line)){
                    ArrayList<Integer> list = map.get(line);
                    list.add(ele.data);
                    map.put(line, list);
                }
                else {
                    map.put(line, new ArrayList<>(Arrays.asList(ele.data)));
                }
                if(ele.left != null) q.offer(new Pair(ele.left, line - 1));
                if(ele.right != null) q.offer(new Pair(ele.right, line +1 ));
            }
        }
        for(ArrayList<Integer> l1 : map.values()){
            ans.addAll(l1);
        }
        return ans;
    }
}

/*
 * LeetCode question: to print the elements at same level in ascending order
 * Eg: [3,9,20,null,null,15,7] -> [[9],[3,15],[20],[7]]
 * Here if two elements are at the same level, then the element with the smaller value should come first.
 * 
 * If vertical index = 0 and level 0 -> 1, level 1 -> 6, 5, level 2 -> 4 then [1, 6, 5, 4]
 * and ans will not be [1, 4, 5 , 6]
 * 
 * Time Complexity: O(nlogn) where n is the number of nodes in the tree
 * Space Complexity: O(n/2 + n) - Map + Queue
 */
public class VerticalOrderTraversalBT{
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if(root == null) return ans;
        Map<Integer, Map<Integer, ArrayList<Integer>>> map = new TreeMap<>();
        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(root, 0, 0));
        while(!q.isEmpty()){
            int qs = q.size();
            for(int i = 0; i < qs; i++){
                Pair curr = q.poll();
                TreeNode ele = curr.node;
                int index = curr.ind;
                int level = curr.level;
                if(map.containsKey(index)){
                    Map<Integer, ArrayList<Integer>> m1 = map.get(index);
                    // Need to store the elements at the same level in ascending order
                    if(m1.containsKey(level)){
                        ArrayList<Integer> l1 = m1.get(level);
                        l1.add(ele.val);
                        Collections.sort(l1);
                        m1.put(level, l1);
                    }
                    else {
                        m1.put(level, new ArrayList<>(Arrays.asList(ele.val)));
                    }
                }
                else {
                    Map<Integer, ArrayList<Integer>> m2 = new TreeMap<>();
                    m2.put(level, new ArrayList<>(Arrays.asList(ele.val)));
                    map.put(index, m2);
                }
                if(ele.left != null) q.offer(new Pair(ele.left, index - 1, level + 1));
                if(ele.right != null) q.offer(new Pair(ele.right, index + 1, level + 1));
            }
        }
        for(Map.Entry<Integer, Map<Integer, ArrayList<Integer>>> m1: map.entrySet()){
            List<Integer> l1 = new ArrayList<>();
            for(ArrayList<Integer> l2 : m1.getValue().values()){
                l1.addAll(l2);
            }
            ans.add(l1);
        }
        return ans;
    }
}

/*
 * GFG Link: https://www.geeksforgeeks.org/vertical-order-traversal-of-binary-tree-using-map
 * Compute if absent approach for short code.
 * 
 * If want to keep all the same elements in ans then use List
 * If want to keep only new elements in ans then use Set
 * If want to keep sorted elements with HashMap use sort function
 * else can use TreeMap/TreeSet
 * 
 * Time Complexity: O(n + n/2*n/2) where n is the number of nodes in the tree
 * Space Complexity: O(n + n/2) - Recursive stack + Map
 */
class Meta{
    int lvlMin;
    int lvlMax;
    int indMin;
    int indMax;
    Meta(int lvlMin, int lvlMax, int indMin, int indMax){
        this.lvlMin = lvlMin;
        this.lvlMax = lvlMax;
        this.indMin = indMin;
           this.indMax = indMax;
    }
}
// Since hashset so only unique elements and not sorted way
public class VerticalOrderTraversalBT{
    public void getVerticalOrder(TreeNode root, int ind, int lvl, Meta m, HashMap<Integer, HashMap<Integer, HashSet<Integer>>> map){
        if(root == null) return;

        map.computeIfAbsent(ind, k -> new HashMap<>())
            .computeIfAbsent(lvl, k -> new HashSet<>())
            .add(root.val);
        if(m.lvlMin > lvl) m.lvlMin = lvl;
        if(m.lvlMax < lvl) m.lvlMax = lvl;
        if(m.indMin > ind) m.indMin = ind;
        if(m.indMax < ind) m.indMax = ind;
        getVerticalOrder(root.left, ind - 1, lvl + 1, m, map);
        getVerticalOrder(root.right, ind + 1, lvl + 1, m, map);
    }
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        Meta m = new Meta(0,0,0,0);
        HashMap<Integer, HashMap<Integer, HashSet<Integer>>> map = new HashMap<>();
        getVerticalOrder(root, 0, 0, m, map);

        for(int i = m.indMin; i <= m.indMax; i++){
            List<Integer> l1 = new ArrayList<>();
            for(int j = m.lvlMin; j <= m.lvlMax; j++){
                if(map.get(i).containsKey(j))
                    l1.addAll(map.get(i).get(j));
            }
            ans.add(l1);
        }
        return ans;
    }
}

/*
 * Can have multiple same elements but with sorted for just same level elements and not 
 * completely sorted for an vertical index
 * 
 * Time Complexity: O(n + n/2*n/2*nlogn) where n is the number of nodes in the tree
 * Space Complexity: O(n + n/2) - Recursive stack + Map
 */
public void getVerticalOrder(TreeNode root, int ind, int lvl, Meta m, HashMap<Integer, HashMap<Integer, List<Integer>>> map){
    if(root == null) return;

    map.computeIfAbsent(ind, k -> new HashMap<>())
        .computeIfAbsent(lvl, k -> new ArrayList<>())
        .add(root.val);
    if(m.lvlMin > lvl) m.lvlMin = lvl;
    if(m.lvlMax < lvl) m.lvlMax = lvl;
    if(m.indMin > ind) m.indMin = ind;
    if(m.indMax < ind) m.indMax = ind;
    getVerticalOrder(root.left, ind - 1, lvl + 1, m, map);
    getVerticalOrder(root.right, ind + 1, lvl + 1, m, map);
}
public List<List<Integer>> verticalTraversal(TreeNode root) {
    List<List<Integer>> ans = new ArrayList<>();
    Meta m = new Meta(0,0,0,0);
    HashMap<Integer, HashMap<Integer, List<Integer>>> map = new HashMap<>();
    getVerticalOrder(root, 0, 0, m, map);

    for(int i = m.indMin; i <= m.indMax; i++){
        List<Integer> l1 = new ArrayList<>();
        for(int j = m.lvlMin; j <= m.lvlMax; j++){
            if(map.get(i).containsKey(j)){
                Collections.sort(map.get(i).get(j));
                l1.addAll(map.get(i).get(j));
            }
        }
        ans.add(l1);
    }
    return ans;
}