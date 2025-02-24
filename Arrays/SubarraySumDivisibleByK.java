/**
 * Problem: https://leetcode.com/problems/subarray-sums-divisible-by-k/
 * 
 * The problem is about to get the count of the sub-arrays with sum divisible by k
 * The array can have negative elements 
 * 
 * Approach1: Brute force method using 2 nested loops
 * Time Complexity: O(n^2)
 * Space Complexity: O(1)
 * 
 * Hint:
 * 1. Consider a sub-array from i to j
 * Eg: 4 5 0 -2 -3 1, k = 5
 * 1st sub-array: 4, rem = 4 - if 4 removed then divisible by 5 (count of 4 = 0, res = 0)
 * 2nd sub-array: [4,5], sum = 9, rem = 4 - if 4 removed then divisible by 5 (count of 4 = 1, res = 0 + 1 = 1)
 * 3rd sub-array: [4,5,0], sum = 9, rem = 4 - if 4 removed then divisible by 5  (count of 4 = 2, res = 1 + 2 = 3)
 * We are trying to remove the remainder from the current sub-array
 * If it can be removed then the sub-array is divisible by k
 * Hence count of the remainder is the count of the sub-arrays divisible by k
 * 
 * Approach2: Using prefix sum and hashmap
 * Time Complexity: O(n)
 * Space Complexity: O(k), eg: k = 5 (0,1,2,3,4) values => O(k)
 */
public class SubarraySumDivisibleByK {
    public int subarraysDivByK(int[] nums, int k) {
        HashMap<Integer, Integer> prefixSum = new HashMap<>();
        int n = nums.length;
        int sum = 0, res = 0;
        prefixSum.put(0, 1);
        for(int i: nums){
            sum += i;
            int rem = sum % k;
            // Line can be skipped if no negative elements
            if(rem < 0) rem += k;
            // searching for the remainder in the hashmap
            res += prefixSum.getOrDefault(rem, 0);
            // adding the remainder to the hashmap
            prefixSum.put(rem, prefixSum.getOrDefault(rem, 0) + 1);
        }
        return res;
    }
}
