/*
 * Problem: Design a class to find the kth largest element in a stream. Note that it is the kth largest element in the sorted order, not the kth distinct element.
 * 
 * Approach:
 * Use of priority queue
 * 
 * 1. We will create a priority queue and keep the size of the queue as k
 * 2. We will add the elements to the queue
 * 3. If the size of the queue is greater than k, we will remove the top element
 * 4. Finally, we will return the top element
 * 
 * Time complexity: O(nlogk) where n is the number of elements in the stream
 * Space complexity: O(k) where k is the size of the priority queue
 */
public class KthLargestElementStream {
    private int k;
    private PriorityQueue<Integer> pq;

    public KthLargest(int k, int[] nums) {
        this.k = k;
        pq = new PriorityQueue<>();

        for(int i : nums){
            this.add(i);
        }    
    }
    
    public int add(int val) {
        pq.add(val);
        if(pq.size() > k) pq.remove();
        return pq.peek();
    }
}
