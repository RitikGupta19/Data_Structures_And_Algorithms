/**
 * Problem: To find the unique triplets in the array which gives the sum of zero.
 * Problem link: https://leetcode.com/problems/3sum/
 * 
 * The problem can be solved using three approaches:
 * 1. Brute Force: O(N^3) time complexity and O(1) space complexity.
 * 2. Hashing: O(N^2) time complexity and O(N) space complexity. (remainder technique)
 * 3. Two Pointer: O(N^2) time complexity and O(1) space complexity.
 */
public class 3Sum {
    // APPROACH 1
    public List<List<Integer>> threeSum(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();

        for(int i = 0; i < n; i++){
            if(i > 0 && nums[i] == nums[i - 1]) continue;
            for(int j = i + 1; j < n; j++){
                if(j > i + 1 && nums[j] == nums[j - 1]) continue;
                for(int k = j + 1; k < n; k++){
                    if(k > j + 1 && nums[k] == nums[k - 1]) continue;
                    long sum = (long)nums[i];
                    sum += (long) nums[j];
                    sum += (long) nums[k];
                    if(sum == 0){
                        List<Integer> temp = new ArrayList<>();
                        temp.add(nums[i]);
                        temp.add(nums[j]);
                        temp.add(nums[k]);
                        ans.add(temp);
                    }
                }
            }
        }
        return ans;
    }

    // APPROACH 2
    public List<List<Integer>> threeSum(int[] nums) {
        int n = nums.length;
        Set<List<Integer>> set = new HashSet<>();

        for(int i = 0; i < n; i++){
            Set<Long> hashSet = new HashSet<>();
            for(int j = i + 1; j < n; j++){
                long sum = (long)nums[i];
                sum += (long) nums[j];
                long rem = 0 - sum;
                if(hashSet.contains(rem)){
                    List<Integer> temp = new ArrayList<>();
                    temp.add(nums[i]);
                    temp.add(nums[j]);
                    temp.add((int)rem);  
                    Collections.sort(temp);
                    set.add(temp);
                }
                hashSet.add((long)nums[j]);
            }
        }
        return new ArrayList<>(set);
    }

    // APPROACH 3
    public List<List<Integer>> threeSum(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();

        for(int i = 0; i < n; i++){
            if(i > 0 && nums[i] == nums[i - 1]) continue;
            int k = i + 1;
            int l = n - 1;
            while(k < l){
                long sum = (long)nums[i] + (long)nums[k] + (long)nums[l];
                if(sum == 0){
                    List<Integer> temp = new ArrayList<>();
                    temp.add(nums[i]);
                    temp.add(nums[k]);
                    temp.add(nums[l]);  
                    ans.add(temp);
                    k++;
                    l--;
                    while(k < l && nums[k] == nums[k - 1]) k++;
                    while(k < l && nums[l] == nums[l + 1]) l--;
                }
                else if(sum < 0) k++;
                else l--;
            }
        }
        return ans;
    }
}
