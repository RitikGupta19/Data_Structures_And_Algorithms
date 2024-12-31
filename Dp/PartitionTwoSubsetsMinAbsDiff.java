/*
 * Problem: the problem states to find the min. absolute difference between the sum of two subsets.
 * 
 * To understand this problem, you need to understand the significance of dp array.
 * In earlier questions like SubsetSumK.java, we were using the dp array to store the boolean values.
 * Here the dp[n - 1][k] means that can we get the target 'k' in an array of o-n-1
 * dp[5][k]: means can we get the target 'k' in an array of 5 elements.
 * 
 * So, if we get the last row of the dp array.
 * We will have the array which shows can we get the sum 'k' in an array of n elements.
 * 
 * Approach:
 * 1. Find the sum of all the elements in the array.
 * 2. Construct the last row of the dp array.
 * 3. Find the minimum difference between the sum of two subsets.
 * 
 * Eg: [2,3,7], k = 12
 * possible values of 0 to 12:
 *     0 1 2 3 4 5 6 7 8 9 10 11 12
 * dp   T F T T F T F T F T T  F  T
 * s1   0  2  3 5 7 9 10 12
 * s2   12 10 9 7 5 3 2  0
 * diff 12 8  6 2 2 6 8  12
 * since we can see only half of the dp array is useful.
 * Later on the subsets are repeating.
 * Thus we will get the min diff in half of the sum.
 * 
 * Time Complexity: O(n*sum)
 * Space Complexity: O(sum) = Dp space
 */
public class PartitionTwoSubsetsMinAbsDiff {
    public static int minSubsetSumDifference(int []arr, int n) {
        // Write your code here.
        int sum = 0;
        for(int i = 0; i < n; i++) sum += arr[i];
        
        boolean[] prev = new boolean[sum + 1];
        prev[0] = true;
        if(arr[0] <= sum) prev[arr[0]] = true;

        for(int i = 1; i < n; i++){
            boolean[] curr = new boolean[sum + 1];
            curr[0] = true;
            for(int t = 1; t <= sum; t++){
                boolean take = false;
                if(t - arr[i] >= 0) take = prev[t - arr[i]];
                boolean notake = prev[t];
                curr[t] = take || notake;
            }
            prev = curr;
        }
        
        int min = Integer.MAX_VALUE;
        for(int s1 = 0; s1 <= sum/2; s1++){
            if(prev[s1] == true){
                min = Math.min(min, Math.abs((sum - s1) - s1));
            }
        }

        return min;
    }
}
