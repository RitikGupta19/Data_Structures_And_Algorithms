/**
 * Problem: https://www.geeksforgeeks.org/minimum-swaps-required-group-1s-together/
 * We need to find the min. number of swaps needed to group all 1s together.
 * 
 * Brute force:
 * 1. Count the number of 1s in the array.
 * 2. For each possible subarray we find the number of ones.
 * 3. Total ones - ones in subarray = swaps needed
 * 4. We take the minimum of all the swaps needed.
 * 
 * Time Complexity: O(n^2)
 * Space Complexity: O(1)
 */
public static int minSwaps(int arr[], int n) {
    // Complete the function
    int ans = Integer.MAX_VALUE;
    int ones = 0;
    
    for(int i = 0; i < n; i++){
        if(arr[i] == 1)
            ones++;
    }
    
    for(int i = 0; i <= n - ones; i++){
        int subOnes = 0;
        for(int j = i; j < i + ones; j++){
            if(arr[j] == 1)
                subOnes++;
        }
        ans = Math.min(ans, ones - subOnes);
    }
    
    return ones == 0 ? -1 : ans;
}

/**
 * Optimal Approach:
 * We count the total number of ones in the array.
 * We find the number of ones in the first window of size 'ones'.
 * We slide the window and find the number of ones in the next window.
 * We maintain the max ones in a subarray.
 * Total ones - max ones = min swaps needed.
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
public static int minSwaps(int arr[], int n) {
    // Complete the function
    int totalOnes = 0;
    
    for(int i = 0; i < n; i++){
        totalOnes += arr[i];
    }
    
    if(totalOnes == 0) return -1;
    
    int start = 0, end = totalOnes - 1,  maxSubOnes = 0, curr = 0;
    
    for(int i = 0; i < totalOnes; i++)
        curr += arr[i];
    
    
    while(end < n){
        maxSubOnes = Math.max(maxSubOnes, curr);
        
        if(end + 1 < n)
            curr += arr[end + 1];
        curr -= arr[start++];
        end++;
    }
    
    return totalOnes - maxSubOnes;
}