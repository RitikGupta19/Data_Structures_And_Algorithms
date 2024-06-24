/**
 * Implement Stack using Queues:
 * We can insert an element in queue and then remove all the elements from start to size() - 1
 * and insert them again. This way we can implement stack using queue.
 * 
 * Time Complexity: O(n) for push and O(1) for pop
 * Space Complexity: O(n)
 */
class MyStack {
    Queue<Integer> q = new LinkedList<>();

    public MyStack() {
    }
    
    public void push(int x) {
        q.offer(x);
        for(int i = 0; i < q.size() - 1; i++){
            q.offer(q.poll());
        }
    }
    
    public int pop() {
        return q.poll();
    }
    
    public int top() {
        return q.peek();
    }
    
    public boolean empty() {
        return q.isEmpty();
    }
}