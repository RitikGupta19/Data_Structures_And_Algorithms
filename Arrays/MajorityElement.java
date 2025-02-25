/**
 * Majority Element: https://www.geeksforgeeks.org/problems/majority-element-1587115620/1?itm_source=geeksforgeeks&itm_medium=article&itm_campaign=practice_card
 * 
 * To find the number which is greater than n/2 times in the array. 
 * 
 * Approach 1: Use 2 nested loops to count the frequency of each element. If the frequency is greater than n/2, return that element.
 * Time complexity: O(n^2)
 * Space complexity: O(1)
 * 
 * Approach 2: Use a hashmap to store the frequency of each element. Traverse the hashmap and check if the frequency is greater than n/2.
 * Time complexity: O(n)
 * Space complexity: O(n)
 * 
 * Approach 3: Use Moore's Voting Algorithm.
 * Reduce the count each time a diff number arrives.
 * If the count is 0, then change the majority element to the current element.
 * 
 * Time complexity: O(n)
 * Space complexity: O(1)
 */
public class MajorityElement {
    static int majorityElement(int arr[]) {
        // your code here
        int n = arr.length;
        int m1 = 0, c1 = 0;
        
        for(int i = 0; i < n; i++){
            if(c1 == 0){
                m1 = arr[i];
                c1++;
            }
            else if(m1 == arr[i]) c1++;
            else c1--;
        }
        
        c1 = 0;
        for(int i = 0; i < n; i++)
            if(arr[i] == m1) c1++;
            
        if(c1 > n/2) return m1;
        return -1;
    }
}
