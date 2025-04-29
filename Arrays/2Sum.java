/**
 * Problem: To find the pair of indices in the array which gives the sum of target.
 * Problem link: https://leetcode.com/problems/two-sum/
 * 
 * The problem can be solved using two approaches:
 * 1. Brute Force: O(N^2) time complexity and O(1) space complexity.
 * 2. Hashing: O(N) time complexity and O(N) space complexity.
 */
public class 2Sum {
    // Optimal approach
    public int[] twoSum(int[] nums, int target) {
        int n = nums.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        int[] ans = new int[2];
        ans[0] = Integer.MIN_VALUE;
        ans[1] = Integer.MIN_VALUE;
        for(int i = 0; i < n; i++){
            int sec = target - nums[i];
            if(map.containsKey(sec)){
                ans[0] = i;
                ans[1] = map.get(sec);
                break;
            }
            map.put(nums[i], i);
        }
        return ans;
    }
}
