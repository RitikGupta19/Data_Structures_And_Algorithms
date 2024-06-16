/**
 * We always push opening braces into the stack and 
 * when we encounter a closing brace, we check if the top of the stack is the matching opening brace.
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */

class Solution {
    private boolean isMatchingBrace(char c1, char c2){
        if((c1 == ')' && c2 == '(') || (c1 == ']' && c2 == '[') || (c1 == '}' && c2 == '{')){
            return true;
        }
        return false;
    }
    public boolean isValid(String s) {
        char[] arr = s.toCharArray();
        Stack<Character> st = new Stack<>();

        for(int i = 0; i < arr.length; i++){
            if(arr[i] == '(' || arr[i] == '[' || arr[i] == '{'){
                    st.push(arr[i]);
            }
            else{
                if(st.isEmpty()) return false;
                else if(isMatchingBrace(arr[i], st.peek())){
                    st.pop();
                }
                else return false;
            }
        }   
        return st.isEmpty();
    }
}