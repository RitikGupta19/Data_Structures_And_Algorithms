/**
 * Problem: to sort the given linked list.
 * 
 * Approach 1:
 * 1. Convert the linked list to array.
 * 2. Sort the array.
 * 3. Convert the array back to linked list.
 * 
 * Time Complexity: O(nlogn) + O(n) + O(n) => O(nlogn)
 * Space Complexity: O(n)
 * 
 * Approach 2:
 * 1. Merge Sort
 * 
 * Time Complexity: O(nlogn)
 * Space Complexity: O(logn) => Stack space
 */
public class SortLL {
    public ListNode mergeLL(ListNode left, ListNode right){
        ListNode dummy = new ListNode(0);
        ListNode temp = dummy;
        ListNode curr1 = left;
        ListNode curr2 = right;

        while(curr1 != null && curr2 != null){
            if(curr1.val <= curr2.val){
                temp.next = curr1;
                curr1 = curr1.next;
            }
            else {
                temp.next = curr2;
                curr2 = curr2.next;
            }
            temp = temp.next;
        }
        if(curr1 != null){
            temp.next = curr1;
        }else {
            temp.next = curr2;
        }
        return dummy.next;
    }

    public ListNode findMiddle(ListNode head){
        ListNode slow = head;
        ListNode fast = head.next;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public ListNode sortList(ListNode head) {
        if(head == null || head.next == null) return head;

        ListNode middle = findMiddle(head);
        ListNode right = middle.next;
        middle.next = null;
        ListNode left = head;
        left = sortList(left);
        right = sortList(right);

        return mergeLL(left, right);
    }
}
