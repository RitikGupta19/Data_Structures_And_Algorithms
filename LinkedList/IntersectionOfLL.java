import java.util.HashSet;

/**
 * Problem: Intersection of two linked lists
 * Find the merge point of two linked lists.
 * https://takeuforward.org/data-structure/find-intersection-of-two-linked-lists/
 * 
 * Approach 1:
 * 1. Use nested loops to compare each node of the first linked list with each node of the second linked list.
 * 2. If the nodes are equal, return the node.
 * 
 * Time complexity: O(n * m) where n and m are the lengths of the linked lists.
 * Space complexity: O(1)
 * 
 * Approach 2:
 * 1. Traverse the first linked list and store the nodes in a set.
 * 2. Traverse the second linked list and check if the node is present in the set.
 * 3. If present, return the node.
 * 
 * Time complexity: O(n + m) where n and m are the lengths of the linked lists.
 * Space complexity: O(n) where n is the length of the first linked list.
 * 
 * Approach 3:
 * 1. Traverse the first linked list and find the length of the linked list.
 * 2. Traverse the second linked list and find the length of the linked list.
 * 3. Traverse the longer linked list by the difference of the lengths.
 * 4. Traverse both linked lists and compare the nodes.
 * 5. If the nodes are equal, return the node.
 * 
 * Time complexity: O(2*max(m,nn)) + O(abs(m - n)) + O(min(m, n)) where n and m are the lengths of the linked lists.
 * Space complexity: O(1)
 * 
 * Approach 4:
 * 1. Use two pointers to traverse the linked lists.
 * 2. If any of the pointers reaches the end of the linked list, move it to the head of the other linked list.
 * 3. Break the loop when the pointers are equal.
 * 
 * Time complexity: O(2 * max(n, m)) where n and m are the lengths of the linked lists.
 * Space complexity: O(1) 
 */
public class IntersectionOfLL {
    // APPROACH 1
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode curr = headA;
        while(curr != null){
            ListNode curr2 = headB;
            while(curr2 != null){
                if(curr == curr2) return curr;
                curr2 = curr2.next;
            }
            curr = curr.next;
        }
        return null;
    }

    // APPROACH 2
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        HashSet<ListNode> set = new HashSet<>();
        while(headA != null){
            set.add(headA);
            headA = headA.next;
        }
        while(headB != null){
            if(set.contains(headB)) return headB;
            headB = headB.next;
        }
        return null;
    }
    
    // APPROACH 3
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int a  = 0, b = 0;
        ListNode c1 = headA, c2 = headB;
        while(c1 != null || c2 != null) {
           if(c1 != null) {
            a++;
            c1 = c1.next;
           }
           if(c2 != null){
            b++;
            c2 = c2.next;
           }
        }
        int diff = a - b;
        c1 = headA; c2 = headB;
        if(diff > 0) while(diff-- != 0) c1 = c1.next;
        else while(diff++ != 0) c2 = c2.next;

        while(c1 != null){
            if(c1 == c2) return c1;
            c1 = c1.next;
            c2 = c2.next;
        }
        return null;
    }

    // APPROACH 4
    static Node intersectPoint(Node head1, Node head2) {
        Node c1 = head1;
        Node c2 = head2;
        while(c1 != c2){
            c1 = c1 == null ? head2 : c1.next;
            c2 = c2 == null ? head1 : c2.next;
        } 
        return c1;
    }
}
