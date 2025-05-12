/**
 * Reverse a singly linked list.
 * Methods:
 * 1. Iterative
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 * 
 * 2. Recursive
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 * 
 * 3. Stack
 * Time Complexity: O(n)
 * Space Complexity: O(n) 
 */
public class ReverseLL {
    // APPROACH: ITERATIVE
    public ListNode reverseList(ListNode head) {
        ListNode curr = head, prev = null;
        while(curr != null){
            ListNode front = curr.next;
            curr.next = prev;
            prev = curr;
            curr = front;
        }
        return prev;
    }

    // APPROACH: RECURSION
    public ListNode reverseList(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode newHead = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    // APPROACH: STACK
    public ListNode reverseList(ListNode head) {
        Stack<Integer> st = new Stack<>();
        while(head != null){
            st.push(head.val);
            head = head.next;
        }
        ListNode dummy = new ListNode(0);
        ListNode temp = dummy;
        while(!st.isEmpty()){
            temp.next = new ListNode(st.pop());
            temp = temp.next;
        }
        return dummy.next;
    }
}
