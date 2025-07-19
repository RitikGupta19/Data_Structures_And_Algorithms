/**
 * Basica calculator II:  https://leetcode.com/problems/basic-calculator-ii/
 * 
 * 1. Using stack to handle the operations.
 * - we will push all the elements in stack either as positive or negative.
 * - we will multiply or divide the last element at same time with last element.
 * - we will add all the elements of stack at the end to get the answer.
 * 
 * Time Complexity: O(n) where n is the length of the string.
 * Space Complexity: O(n) for the stack.
 * 
 * 2. Using two variables to handle the last operation and the current number.
 * - we will keep track of the last operation and the current number.
 * - we will add the last operation to the answer at the end.
 * - we will multiply or divide the last number at same time with last number.
 * 
 * Time Complexity: O(n) where n is the length of the string.
 * Space Complexity: O(1) for the variables.
 */
class Solution {
    public boolean isOperator(char c){
        return c == '+' || c == '-' || c == '*' || c == '/';
    }
    public int calculate(String s) {
        int n = s.length();
        Stack<Integer> st = new Stack<>();
        // NO last variable as we have stack to maintain the last number.
        int num = 0, ans = 0;
        // Default operator is '+'.
        char op = '+';

        for(int i = 0; i < n; i++){
            char c = s.charAt(i);
            if(Character.isDigit(c)){
                // Build number with multiple digits.
                num = num * 10 + (c - '0');
            }

            // If we encounter an operator or reach the end of the string.
            if(isOperator(c) || i == n - 1){
                // Just push to stack
                if(op == '+') st.push(num);
                // Just make negative and push to stack
                else if(op == '-') st.push(-num);
                // Multiply or divide the last element in stack
                else if(op == '*') st.push(st.pop() * num);
                else if(op == '/') st.push(st.pop() / num);
                // Reset the number and update the operator
                num = 0;
                op = c;
            }
        }

        while(!st.isEmpty()) ans += st.pop();
        return ans;
    }
    public int calculate(String s) {
        int n = s.length();
        // NO stack, we will use last variable to maintain the last number.
        int num = 0, last = 0, ans = 0;
        char op = '+';

        for(int i = 0; i < n; i++){
            char c = s.charAt(i);
            if(Character.isDigit(c)){
                num = num * 10 + (c - '0');
            }

            if(isOperator(c) || i == n - 1){
                // If the last operation was '+' or '-' we add the last number to ans.
                if(op == '+') {
                    ans += last;
                    // Update the last number to current number.
                    last = num;
                }
                else if(op == '-') {
                    ans += last;
                    last = -num;
                }
                else if(op == '*') last *= num;
                else if(op == '/') last /= num;

                num = 0;
                op = c;
            }
        }

        ans += last;
        return ans;
    }
}