/**
 * link: https://leetcode.com/problems/minimum-operations-to-write-the-letter-y-on-a-grid/
 * To find the kth largest number in an array
 * 
 * Approach 1: Sort the array in descending order and return the kth element
 * 
 * Time Complexity: O(nlogn) => n = number of elements
 * Space Complexity: O(1)
 * 
 * Approach 2: Use a min heap of size k. Add elements to the heap until it reaches size k.
 * 
 * Time Complexity: O(nlogk) => n = number of elements, k = size of heap
 * Space Complexity: O(k)
 */
public class KthLargestNumber {
        public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        for(int i: nums) pq.offer(i);
        int ans = Integer.MIN_VALUE;
        for(int i = 0; i < k; i++) ans = pq.poll();
        return ans;
    }
}
