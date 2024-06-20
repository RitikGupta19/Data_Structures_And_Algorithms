/**
 * AddTwoNumbers represented with two arrays
 * 
 * Time Complexity: O(Max(n, m))
 * Space Complexity: O(Max(n, m) + 1)
 */
class Solution {
    ArrayList<Integer> findSum(int a[], int b[]) {
        // code here
        int n = a.length;
        int m = b.length;
        
        ArrayList<Integer> ans = new ArrayList<>();
        int sum = 0, carry = 0;
        int i = n - 1, j = m - 1;
        
        while(i >= 0 || j >= 0 || carry == 1){
            sum = 0;
            if(i >= 0) sum += a[i--];
            if(j >= 0) sum += b[j--];
            sum += carry;
            carry = sum / 10;
            sum = sum % 10;
            ans.add(sum);
        }
        Collections.reverse(ans);
        return ans;
    }
}
