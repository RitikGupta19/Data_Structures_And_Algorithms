/*
 * Kth Largest Element in an Array
 * Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.
 * 
 * Approach:
 * 1. We will use a priority queue to store the elements
 * 2. We will add all the elements to the queue
 * 3. We will remove the top element k - 1 times
 * 4. Finally, we will return the top element
 * 
 * Time complexity: O(nlogn) where n is the number of elements in the array
 * Space complexity: O(n) where n is the number of elements in the array
 */
class KthLargestElementInArray {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        for(int i = 0; i < nums.length; i++){
            pq.offer(nums[i]);
        }

        for(int i = 0; i < k - 1; i++){
            pq.poll();
        }
        return pq.peek();
    }
}