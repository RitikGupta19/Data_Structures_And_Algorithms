/**
 * Basic Calculator: https://leetcode.com/problems/basic-calculator/
 * Problem Statement: Implement a basic calculator to evaluate a simple expression string containing non-negative integers, '+', '-', '(', ')', and spaces.
 * 
 * Time Complexity: O(n) where n is the length of the string.
 * Space Complexity: O(n) for the stack.
 */
class Solution {
    public int calculate(String s) {
        int n = s.length();
        Stack<Integer> st = new Stack<>();
        int num = 0, ans = 0, sign = 1;

        for(int i = 0; i < n; i++){
            char c = s.charAt(i);
            if(Character.isDigit(c)){
                num = num * 10 + (c - '0');
            }
            if(c == '+'){
                ans += sign * num;
                num = 0;
                sign = 1;
            } 
            else if(c == '-'){
                ans += sign * num;
                num = 0;
                sign = -1;
            }
            else if(c == '('){
                st.push(ans);
                st.push(sign);
                ans = 0;
                sign = 1;
            }
            else if(c == ')'){
                ans += sign * num;
                num = 0;
                ans *= st.pop();
                ans += st.pop();
            }
        }
        if(num != 0) ans += sign * num;
        return ans;
    }
}