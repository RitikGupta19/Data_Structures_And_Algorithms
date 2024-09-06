/*
 * Problem: https://leetcode.com/problems/find-median-from-data-stream/
 * Ref solution: https://leetcode.com/problems/find-median-from-data-stream/solutions/74047/java-python-two-heap-solution-o-log-n-add-o-1-find/
 * 
 * Approach:
 * Using two heaps
 * One stored the smaller half of the stream
 * One stored the larger half of the stream
 * 
 * Imp:
 * Here we keep (n / 2 + 1)elements in the small heap and n / 2 elements in the large heap
 * When even (already present) - we will add to large heap and then poll from large and add to small
 * As small can have one element extra
 * 
 * When odd - we will add to small heap and then poll from small and add to large
 * 
 * Time complexity: O(logn) for addNum and O(1) for findMedian
 * Space complexity: O(n)
 */
public class FindMedianInStream {
    PriorityQueue<Integer> small;
    PriorityQueue<Integer> large;
    boolean even = true;

    public FindMedianInStream() {
        small = new PriorityQueue<>(Collections.reverseOrder());    
        large = new PriorityQueue<>();
    }
    
    public void addNum(int num) {
        if(even){
            large.offer(num);
            small.offer(large.poll());
        } 
        else {
            small.offer(num);
            large.offer(small.poll());
        }   
        even = !even;
    }
    
    public double findMedian() {    
        if(even) return (small.peek() + large.peek()) / 2.0;
        else return small.peek();
    }
}
