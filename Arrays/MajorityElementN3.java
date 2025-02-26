/**
 * Problem: Given an array of integers, find all elements that appear more than n/3 times.
 * https://www.geeksforgeeks.org/problems/majority-vote/1?itm_source=geeksforgeeks&itm_medium=article&itm_campaign=practice_card
 * 
 * Approach 1: Using nested loops
 * 1. Traverse the array and for each element, check if it appears more than n/3 times.
 * Time Complexity: O(n^2)
 * Space Complexity: O(1)
 * 
 * Approach 2: Using HashMap
 * 1. Traverse the array and store the frequency of each element in a HashMap.
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 * 
 * Approach 3: Using Boyer-Moore Voting Algorithm
 * 1. Initialize two variables m1 and m2 to store the majority elements and two variables c1 and c2 to store their counts.
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
public class MajorityElementN3 {
    public List<Integer> findMajority(int[] nums) {
        // Your code goes here.
        List<Integer> ans = new ArrayList<>();
        int n = nums.length;
        
        int m1 = Integer.MIN_VALUE, m2 = Integer.MIN_VALUE;
        int c1 = 0, c2 = 0;
        for(int i = 0; i < n; i++){
            if(c1 == 0 && m2 != nums[i]) {
                m1 = nums[i];
                c1++;
            }
            else if(c2 == 0 && m1 != nums[i]){
                m2 = nums[i];
                c2++;
            }
            else if(nums[i] == m1) c1++;
            else if(nums[i] == m2) c2++;
            else {
                c1--;
                c2--;
            }
        }
        c1 = 0; c2 = 0;
        for(int i = 0; i < n; i++){
            if(m1 == nums[i]) c1++;
            if(m2 == nums[i]) c2++;
        }
        if(c1 > n/3) ans.add(m1);
        if(c2 > n/3) ans.add(m2);
        
        if(ans.size() > 1) Collections.sort(ans);
        return ans;
    }
}
