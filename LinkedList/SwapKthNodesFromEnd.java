/**
 * Problem: https://www.geeksforgeeks.org/problems/swap-kth-node-from-beginning-and-kth-node-from-end-in-a-singly-linked-list/1?itm_source=geeksforgeeks&itm_medium=article&itm_campaign=practice_card
 * Swap nodes from beginning and end of linked list at kth position
 * 
 * Approach:
 * Place pointers at kth, kthPrev, kthLast, kthLastPrev nodes.
 * Check for nonNull pointer for kthPrev and kthLastPrev and then assign them.
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */

class Solution {
    public int getCount(Node head){
        int cnt = 0;
        Node temp = head;
        while(temp != null){
            cnt++;
            temp = temp.next;
        }
        return cnt;
    }
    
    public Node swapKthNode(Node head, int k) {
        // code here
        int n = getCount(head);
        
        if(n < k) return head;

        // to check the case for kth node from beginning and end is same
        if(2*k - 1 == n) return head;
        
        Node kth = head;
        Node kthprev = null;
        
        for(int i = 1; i < k; i++){
            kthprev = kth;
            kth = kth.next;
        }
        
        Node kthLast = head;
        Node kthLastPrev = null;
        
        for(int i = 1; i < (n - k + 1); i++){
            kthLastPrev = kthLast;
            kthLast = kthLast.next;
        }
        
        if(kthprev != null) kthprev.next = kthLast;
        if(kthLastPrev != null) kthLastPrev.next = kth;
        
        Node temp = kth.next;
        kth.next = kthLast.next;
        kthLast.next = temp;
        
        if(k == 1) head = kthLast;
        if(k == n) head = kth;
        
        return head;
        
    }
}