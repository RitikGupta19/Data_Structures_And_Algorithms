/**
 * Problem: Multiply two linked lists
 * Output: Return the product of two linked lists (IN NUMBER FORM)
 * 
 * Key takeaways:
 * 1. Handling the product in long format.
 * 2. Use MOD to avoid overflow. (1e9 + 7)
 * Why 1e9+7 as it is a very large prime number and is used to avoid overflow.
 * 
 * Approach:
 * 1. Traverse both LL and convert them into numbers.
 * 2. Multiply the numbers and return the result.
 *  
 * Time Complexity: O(max(n, m))
 * Space Complexity: O(1)
 */
public class MultiplyLL {
    public final long MOD = 1000000007;
    public long multiplyTwoLists(Node first, Node second) {

        long num1 = 0, num2 = 0;
        
        Node curr1 = first, curr2 = second;
        
        while(curr1 != null || curr2 != null){
            
            if(curr1 != null) {
                num1 = ((num1 * 10) + curr1.data) % MOD;
                curr1 = curr1.next;
            }
            
            if(curr2 != null) {
                num2 = ((num2 * 10) + curr2.data) % MOD;
                curr2 = curr2.next;
            }
        }
        return (num1 * num2) % MOD; 
    }
}
