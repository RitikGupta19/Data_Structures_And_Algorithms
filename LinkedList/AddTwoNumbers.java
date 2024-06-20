/**
 * Add two numbers represented by linked lists
 * 
 * Time Complexity: O(Max(n, m))
 * Space Complexity: O(Max(n, m) + 1) + O(Max(n, m)) => Ans space + Recursive stack space
 */
class Solution {
    static int carry = 0;
    static Node res;
    
    static int getSize(Node node){
        int len = 0;
        while(node != null){
            len++;
            node = node.next;
        }
        return len;
    }
    
    static void addNode(int sum){
        if(res == null){
            res = new Node(sum);
            return ;
        }
        Node d = new Node(sum);
        d.next = res;
        res = d;
    }
    
    static void addSameSize(Node h1, Node h2){
        if(h1.next != null){
            addSameSize(h1.next, h2.next);
        }
        int sum = 0;
        sum = h1.data + h2.data + carry;
        carry = sum / 10;
        sum = sum % 10;
        addNode(sum);
    }
    
    static void handleCarry(Node h1, int size){
        if(size != 0){
            handleCarry(h1.next, size - 1);
        }
        int sum = 0;
        sum = h1.data + carry;
        carry = sum / 10;
        sum = sum % 10;
        addNode(sum);
    }
    
    // Function to add two numbers represented by linked list.
    static Node addTwoLists(Node num1, Node num2) {
        // code here
        // return head of sum list
        if(num1 == null) return num2;
        if(num2 == null) return num1;
        int n = getSize(num1);
        int m = getSize(num2);

        
        if(n == m) {
            addSameSize(num1, num2);
        }
        else{
            if(n < m){
                Node temp = num2;
                num2 = num1;
                num1 = temp;
            }
            int diff = Math.abs(n - m);
            Node curr = num1;
            while(diff-- != 0){
                curr = curr.next;
            }
            diff = Math.abs(n - m);
            addSameSize(curr, num2);
            handleCarry(num1, diff - 1);
        }
        if(carry == 1) addNode(carry);
        while(res != null && getSize(res) != 1 && res.data == 0) res = res.next;
        
        // Code to clear the static res list - that will be used in other test cases as well
        Node temp = new Node(-1);
        Node itr = temp;
        while(res != null){
            Node d = new Node(res.data);
            itr.next = d;
            itr = itr.next;
            res = res.next;
        }
        carry = 0;
        return temp.next;
    }
}
