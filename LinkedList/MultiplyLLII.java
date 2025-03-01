import java.math.BigInteger;

/**
 * Problem: Multiply two linked lists
 * Output: Return the product of two linked lists (IN LL FORM)
 * 
 * Key takeaways:
 * 1. BigInteger class: https://www.geeksforgeeks.org/biginteger-class-in-java/
 * Why we used it as here the number could be of length 100 digits
 * 2. Operators of BigInteger class.
 * 
 * Approach:
 * 1. Traverse both LL and convert them into numbers.
 * 2. Multiply the numbers and return the result.
 * 3. Convert the product into a char array and create a new LL.
 * 
 * Time Complexity: O(max(n, m)) + O(max(n, m)) => O(max(n, m))
 * Space Complexity: O(max(n, m))
 */
public class MultiplyLLII {
    public final static long MOD = 1000000007;
    public static Node head = null;

    public static Node createLL(char[] arr){
        int n = arr.length;
        Node dummy = new Node(-1);
        Node temp = dummy;
        for(int i = 0; i < n; i++){
            temp.next = new Node(arr[i] - '0');
            temp = temp.next;
        }
        return dummy.next;
    }

    public static Node multiplyLL(Node head1, Node head2) {
        // Write your code here
        BigInteger num1 = BigInteger.valueOf(0);
        BigInteger num2 = BigInteger.valueOf(0);
        Node curr1 = head1;
        Node curr2 = head2;

        while(curr1 != null || curr2 != null){
            if(curr1 != null){
                num1 = num1.multiply(BigInteger.valueOf(10)).add(BigInteger.valueOf(curr1.data));
                curr1 = curr1.next;
            }
            if(curr2 != null){
                num2 = num2.multiply(BigInteger.valueOf(10)).add(BigInteger.valueOf(curr2.data));
                curr2 = curr2.next;
            }
        }

        BigInteger prod = num1.multiply(num2);
        char[] arr = String.valueOf(prod).toCharArray();
        return createLL(arr);
    }
}
