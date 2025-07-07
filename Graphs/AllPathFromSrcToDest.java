/**
 * Given a directed acyclic graph (DAG) of n nodes labeled from 0 to n - 1,
 * find all possible paths from node 0 to node n - 1 and return them as a list of lists.
 * Link: https://leetcode.com/problems/all-paths-from-source-to-target/
 * 
 * Approach:
 * 1. Use Depth First Search (DFS) to explore all paths from the source node (0) to the destination node (n - 1).
 * 2. Maintain a path list to keep track of the current path.
 * 3. When the destination node is reached, add the current path to the result list.
 * 4. Backtrack by removing the last node from the path after exploring all its adjacent nodes.
 * 
 * We use else as we want to either reach the destination or explore all adjacent nodes.
 * And remove the last node after reaching the destination or exploring all adjacent nodes.
 * 
 * Time Complexity: O(2^n) where n is the number of nodes in the graph, as we may explore all paths.
 * In worst case, the number of paths can be exponential in the number of nodes.
 * 
 * Space Complexity: O(n) for the path list and the recursion stack.
 */
public class AllPathFromSrcToDest {
    public void findPaths(int node, int dest, int[][] graph, List<Integer> path, List<List<Integer>> ans){
        path.add(node);
        if(node == dest){
            List<Integer> l = new ArrayList<>(path);
            ans.add(l);
        }
        else{
            for(int adj: graph[node]) findPaths(adj, dest, graph, path, ans);
        }
        path.remove(path.size() - 1);
    }

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        int n = graph.length;
        List<List<Integer>> ans = new ArrayList<>();
        if(n == 0) return ans;

        List<Integer> path = new ArrayList<>();
        findPaths(0, n - 1, graph, path, ans);
        return ans;
    }
}

// BFS APPROACH:
class Record{
    int node;
    List<Integer> path;
    Record(int node, List<Integer> path){
        this.node = node;
        this.path = path;
    }
}
class Solution {
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> ans = new ArrayList<>();
        int n = graph.length;
        if(n == 0) return ans;

        Queue<Record> q = new LinkedList<>();
        q.offer(new Record(0, new ArrayList<>(Arrays.asList(0))));

        while(!q.isEmpty()){
            // Not necessary to get the queue size and iterate over it,
            int qs = q.size();
            for(int i = 0; i < qs; i++){
                Record r = q.poll();
                int node = r.node;
                List<Integer> p = r.path;
                // can also get the current (node) element from the path (last element)
                if(node == n - 1) ans.add(new ArrayList<>(p));
                
                for(int ele: graph[node]){
                    p.add(ele);
                    q.offer(new Record(ele, new ArrayList<>(p)));
                    p.remove(p.size() - 1);
                }
            }
        }

        return ans;
    }
}
