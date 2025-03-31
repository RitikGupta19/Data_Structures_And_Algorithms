import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Find k closest elements to x in a sorted array.
 * Link: https://leetcode.com/problems/find-k-closest-elements/
 * 
 * Approach 1:
 * 1. Use two pointers l and r to find the closest elements.
 * 2. If the diff of arr[l] and x is greater than the diff of arr[r] and x, decrement r.
 * 3. Else increment l.
 * 4. Continue until the difference between l and r is less than k.
 * 
 * Time Complexity: O(n) where n is the size of array.
 * Space Complexity: O(1) for pointers.
 * 
 * Approach 2:
 * 1. Create a max heap of size k on the basis of diff of elements. Heap<Pair>, pair = diff, element.
 * 2. If the size of heap is less than k, add the element to heap.
 * 3. If the size of heap is equal to k, check if the diff of current element is less than the diff of top element of heap.
 * 4. If yes, remove the top element and add the current element to heap.
 * 5. If no, continue.
 * 6. Finally, convert the heap to a list and return it.
 * 
 * Time Complexity: O(nlogk) where n is the size of array and k is the size of heap.
 * Space Complexity: O(k) for heap.
 * 
 * Approach 3:
 * 1. Create min heap of size k on the basis of elements in arr. Heap<Integer>.
 * 2. If the size of heap is less than k, add the element to heap.
 * 3. If the size of heap is equal to k, check if diff of the top element of heap is greater than the diff with the current element.
 * 4. If yes, remove the top element and add the current element to heap.
 * 5. If no, continue.
 * 6. Finally, convert the heap to a list and return it.
 * 
 * Time Complexity: O(nlogk) where n is the size of array and k is the size of heap.
 * Space Complexity: O(k) for heap.
 */
public class KClosestElements {
    // APPROACH 1:
    int n = arr.length;
    int l = 0, r = n - 1;

    //  >= as we need k elements not more than that
    while(r - l >= k){
        // >= as we need to check for the closest elements and smallest elements too if diff is same.
        if(Math.abs(arr[r] - x) >= Math.abs(arr[l] - x)) r--;
        else l++;
    }

    List<Integer> ans = new ArrayList<>();
    for(int i = l; i <= r; i++) ans.add(arr[i]);

    return ans;
}

    // APPROACH 2:
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        // Need to keep the max diff elements at top - easy to remove.
        PriorityQueue<Pair> pq = new PriorityQueue<>((p1, p2) -> p2.diff - p1.diff);
        List<Integer> ans = new ArrayList<>();
        int n = arr.length;

        for(int i = 0; i < n; i++){
            int diff = Math.abs(arr[i] - x);
            if(pq.size() < k) pq.offer(new Pair(diff, arr[i]));
            else if(pq.peek().diff > diff) {
                pq.poll();
                pq.offer(new Pair(diff, arr[i]));
            }
        }

        while(!pq.isEmpty()) ans.add(pq.poll().ele);
        // Need to sort as storing elements in heap according to diff.
        Collections.sort(ans);
        return ans;
    }

    // APPROACH 3:
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int n = arr.length;
        // storing the smallest elements at top. Hence no need to sort in last.
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for(int i = 0; i < n; i++){
            if(pq.size() < k) pq.offer(arr[i]);
            // check diff on the go, and add only if needed
            else if(Math.abs(pq.peek() - x) > Math.abs(arr[i] - x)) {
                pq.poll();
                pq.offer(arr[i]);
            }
        }

        List<Integer> ans = new ArrayList<>();
        while(!pq.isEmpty()) ans.add(pq.poll());
        return ans;
    }
}
