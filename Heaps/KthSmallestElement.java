import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * Kth Smallest Element in a Sorted Matrix
 * Row and column sorted matrix
 * Link: https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/
 * 
 * Approach 1: Brute Force
 * 1. Run nested loops to flatten the matrix into a single array
 * 2. Sort the array
 * 3. Return the kth element
 * Time Complexity: O(n*m + (n*m)log(n*m))
 * Space Complexity: O(n*m)
 * 
 * Approach 2: Using Priority Queue
 * 1. Create a max heap
 * 2. Traverse the matrix and add elements to the heap
 * Time Complexity: O(n*mlogk)
 * Space Complexity: O(k)
 */
public class KthSmallestElement{
    public int kthSmallest(int[][] mat, int k) {
        int n = mat.length;
        int m = mat[0].length;
        int[] temp = new int[n*m];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                temp[i] = mat[i][j];
            }
        }
        Arrays.sort(temp);
        return temp[k - 1];
    }

    public int kthSmallest(int[][] mat, int k) {
        int n = mat.length;
        int m = mat[0].length;
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b) -> b - a);
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(pq.size() < k){
                    pq.offer(mat[i][j]);
                }
                else if(mat[i][j] < pq.peek()){
                    pq.poll();
                    pq.offer(mat[i][j]);
                }
            }
        }
        return pq.peek();
    }
}