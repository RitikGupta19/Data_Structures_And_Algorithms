/**
 * Longest Increasing Subsequence
 * https://leetcode.com/problems/longest-increasing-subsequence/
 * 
 * Approach 1: Brute Force
 * 1. Find all the subsequence using recursion / power set.
 * 2. Check if the subsequence is increasing or not.
 * 3. Keep track of the maximum length of increasing subsequence.
 * 
 * Time Complexity: O(2^n * n) - 2^n for generating all subsequences and O(n) for checking if the subsequence is increasing.
 * Space Complexity: O(n) - for storing the subsequence.
 * 
 * Approach 2:
 * Recurrence Relation: Take and not take.
 * 
 * Time Complexity: O(2^n)
 * Space Complexity: O(n) - for recursion stack.
 * 
 * Approach 3: Memoization
 * 1. Use memoization to store the results of subproblems.
 * 2. Use a 2D array to store the results of subproblems.
 * 
 * Time Complexity: O(n^2)
 * Space Complexity: O(n^2) + O(n) - for memoization array + for recursion stack.
 * 
 * Approach 4: Tabulation
 * 1. Use tabulation to store the results of subproblems.
 * 2. Use a 2D array to store the results of subproblems.
 * 
 * Time Complexity: O(n^2)
 * Space Complexity: O(n^2) - for tabulation array.
 * 
 * Approach 5: Space Optimization
 * 1. Use a 1D array to store the results of subproblems.
 * 
 * Time Complexity: O(n^2)
 * Space Complexity: O(n) - for tabulation array.
 * 
 * Approach 6: Dp and hash method - USED FOR PRINTING THE LIS
 * 1. DP stores the lengths of LIS till that index.
 * 2. Hash stores the previous index of the element in the LIS.
 * 3. Backtrack the hash to get the LIS.
 * 
 * Time Complexity: O(n^2)
 * Space Complexity: O(n) - for DP and hash array.
 * 
 * Approach 7: Binary Search
 * 1. We will try to make the LIS in list.
 * 2. We will write the upcoming elements in the list at right position using binary search.
 * 3. If the element is greater than the last element of the list, we will add it to the end of the list.
 * 4. If the element is in between the list, we will replace with the element which is greater than it.
 * 
 * Time Complexity: O(nlogn)
 * Space Complexity: O(n) - for storing the LIS.
 */
public class Dp41LongestIncreasingSubsequence {
    // APPROACH 2
    public static int recursion(int curr, int prev, int[] nums, int n){
		if(curr == n) return 0;

		int len = recursion(curr + 1, prev, nums, n);
		if(prev == -1 || nums[curr] > nums[prev]) len = Math.max(len, 1 + recursion(curr + 1, curr, nums, n));
		return len;
	}

    // APPROACH 3
	public static int memoization(int curr, int prev, int[] nums, int[][] dp, int n){
		if(curr == n) return 0;
		if(dp[curr][prev + 1] != -1) return dp[curr][prev + 1];

		int len = memoization(curr + 1, prev, nums, dp, n);
		if(prev == -1 || nums[curr] > nums[prev]) len = Math.max(len, 1 + memoization(curr + 1, curr, nums, dp, n));
		return dp[curr][prev + 1] = len;
	}

    // APPROACH 4
	public static int tabulation(int[] nums, int n){
		int[][] dp = new int[n + 1][n + 1];
		
		for(int curr = n - 1; curr >= 0; curr--){
			for(int prev = curr - 1; prev >= -1; prev--){
				int len = dp[curr + 1][prev + 1];
				if(prev == -1 || nums[curr] > nums[prev]) len = Math.max(len, 1 + dp[curr + 1][curr + 1]);
				dp[curr][prev + 1] = len;
			}
		}

		return dp[0][0];
	}

    // APPROACH 5
	public static int space(int[] nums, int n){
		int[] curr = new int[n + 1];
		int[] next = new int[n + 1];
		for(int crr = n - 1; crr >= 0; crr--){
			for(int prev = crr - 1; prev >= -1; prev--){
				int len = next[prev + 1];
				if(prev == -1 || nums[crr] > nums[prev]) len = Math.max(len, 1 + next[crr + 1]);
				curr[prev + 1] = len;
			}
			next = curr;
		}

		return next[0];
	}

    // APPROACH 6 - WITHOUT PRINTING LIS
    public static int dp(int[] nums, int n){
		int[] dp = new int[n];
		Arrays.fill(dp, 1);

		int max = Integer.MIN_VALUE;
		for(int curr = 0; curr < n; curr++){
			for(int prev = 0; prev < curr; prev++){
				if(nums[curr] > nums[prev]){
					dp[curr] = Math.max(dp[curr], 1 + dp[prev]);
				}
			}
			max = Math.max(max, dp[curr]);
		}

		return max;
	}

    // APPROACH 7 - SELF BS LOGIC
    public static int binarySearch(int low, int high, List<Integer> lis, int ele){
        while(low <= high) {
            int mid = (low + high) >> 1;
            if(lis.get(mid) == ele) return mid;
            if(lis.get(mid) > ele) high = mid - 1;
            else {
                if(mid + 1 > high) return mid + 1;
                else if(lis.get(mid + 1) <= ele) low = mid + 1;
                else return mid + 1;
            }
        }
        return low;
    }

    public static int bs(int nums[], int n){
        List<Integer> lis = new ArrayList<>();
        lis.add(nums[0]);
        for(int i = 1; i < n; i++){
            int ind = binarySearch(0, lis.size() - 1, lis, nums[i]);
            if(lis.size() - 1 < ind) lis.add(nums[i]);
            else lis.set(ind, nums[i]);
        }
        return lis.size();
    }

    // APPROACH 7 - COLLECTIONS BS LOGIC -> Used to return the index:
    // if the element is present, it returns the index of the element.
    // if the element is not present, it returns negative of (insertion point).
    // Insertion point is the index at which the element should be inserted to maintain the order.
    public static int bs(int nums[], int n){
        List<Integer> lis = new ArrayList<>();
        lis.add(nums[0]);
        for(int i = 1; i < n; i++){
            if(lis.get(lis.size() - 1) < nums[i]) lis.add(nums[i]);
            else {
                int ind = Collections.binarySearch(lis, nums[i]);
                if(ind < 0) ind = -ind -1;
                lis.set(ind, nums[i]);
            }
        }
        return lis.size();
    }

    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        // return recurrence(0, -1, nums, n);

        int[][] dp = new int[n][n + 1];
        for(int i = 0; i < n; i++) Arrays.fill(dp[i], -1);
        // return memoization(0, -1, nums, dp, n);

        // return tabulation(nums, n);

        // return space(nums, n);

        // return dp(nums, n);

        return bs(nums, n);
    }
}

// APPROACH 6 - FOR PRINTING LIS
public static int lastInd = -1;

public static void dp(int[] nums, int n, int[] dps, int[] hash){
    int max = Integer.MIN_VALUE;
    for(int curr = 0; curr < n; curr++){
        for(int prev = 0; prev < curr; prev++){
            if(nums[curr] > nums[prev] && (dps[curr] < 1 + dps[prev])){
                dps[curr] = 1 + dps[prev];
                hash[curr] = prev;
            }
        }
        if(max < dps[curr]){
            max = dps[curr];
            lastInd = curr;
        }
    }
}

public static List< Integer > printingLongestIncreasingSubsequence(int []arr, int x) {
    // Write Your Code Here
    int n = arr.length;
    int[] dps = new int[n];
    Arrays.fill(dps, 1);
    int[] hash = new int[n];
    for(int i = 0; i < n; i++) hash[i] = i;

    dp(arr, n, dps, hash);
    
    List<Integer> l = new ArrayList<>();
    l.add(arr[lastInd]);
    while(lastInd != hash[lastInd]){
        lastInd = hash[lastInd];
        l.add(arr[lastInd]);
    }
    return l;
}
