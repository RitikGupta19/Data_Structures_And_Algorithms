/**
 * Implement a queue using two stacks:
 * Before inserting element in one stack
 * We will push all the elements of stack1 to stack2
 * Then insert the element in stack1
 * Then push all the elements of stack2 to stack1
 * 
 * Time Complexity: O(n) for push and O(1) for pop
 * Space Complexity: O(n)
 */
class MyQueue {
    Stack<Integer> st = new Stack<>();
    Stack<Integer> t = new Stack<>();

    public MyQueue() {
        
    }
    
    public void push(int x) {
        while(!st.isEmpty()){
            t.push(st.pop());
        }
        st.push(x);
        while(!t.isEmpty()){
            st.push(t.pop());
        }
    }
    
    public int pop() {
        return st.pop();
    }
    
    public int peek() {
        return st.peek();
    }
    
    public boolean empty() {
        return st.isEmpty();
    }
}