/**
 * Implement a queue using two stacks:
 * Before inserting element in one stack
 * We will push all the elements of stack1 to stack2
 * Then insert the element in stack1
 * Then push all the elements of stack2 to stack1
 * 
 * Time Complexity: O(n) for push and O(1) for pop
 * Space Complexity: O(2n)
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

/**
 * Here we will push all the elements in input stack
 * While popping or peeking we will check if output stack is empty or not
 * If it is empty then we will push all the elements from input stack to output stack
 * Then pop or peek from output stack
 * 
 * If output stack is not empty then we will pop or peek from output stack
 * 
 * Push Efficient Approach - O(1)
 * Pop Efficient Approach - O(1) or O(n)
 * Amortized Time Complexity: O(1)
 * Peek Efficient Approach - O(1) or O(n)
 * Space Complexity: O(2n)
 */
class MyQueue {
    Stack<Integer> input = new Stack<>();
    Stack<Integer> output = new Stack<>();

    public MyQueue() {
    }
    
    public void push(int x) {
        input.push(x);
    }
    
    public int pop() {
        if(!output.isEmpty())
            return output.pop();
        while(!input.isEmpty()){
            output.push(input.pop());
        }
        return output.pop();
    }
    
    public int peek() {
        if(!output.isEmpty())
            return output.peek();
        while(!input.isEmpty()){
            output.push(input.pop());
        }
        return output.peek();
    }
    
    public boolean empty() {
        return input.isEmpty() && output.isEmpty();
    }
}