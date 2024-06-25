/**
 * Given a string - find the minimum number of flips needed to make the string monotone increasing.
 * Eg: 00110 -> 1 for 00111
 * 01010 -> 2 for 01111
 * 
 * Here the idea is to keep track of number of ones and zeros before and after the current index
 * respectively. Then we can calculate the number of flips needed to make the string monotone increasing.
 * Eg: 0 1 0 | 0 0 1
 * Here we need to flip 1 one to zero on left side and
 * 2 zeros to one on right side. So total flips = 1 + 2 = 3
 * So we need to check this for all the possible splits and choose the minimum of all.
 * We can do this by using PREFIX SUMS and maintaining two arrays - ones and zeros
 *  
 * Link: https://leetcode.com/problems/flip-string-to-monotone-increasing/
 * 
 * Time Complexity: O(3n)
 * Space Complexity: O(2n)
 */
class Solution {
    public int minFlipsMonoIncr(String s) {
        int n = s.length();
        int[] ones = new int[n];
        int[] zeros = new int[n];

        // Count of '1s' till the current index from left
        for(int i = 0; i < n; i++){
            if(s.charAt(i) == '1')
                ones[i] = i == 0 ? 1 : ones[i - 1] + 1;
            else 
                ones[i] = i == 0 ? 0 : ones[i - 1];
        }

        // Count of '0s' till the current index from right
        for(int i = n - 1; i >= 0; i--){
            if(s.charAt(i) == '0')
                zeros[i] = i == n -1 ? 1 : zeros[i + 1] + 1;
            else
                zeros[i] = i == n - 1? 0 : zeros[i + 1];
        }
        
        // Check for all possible splits and choose the minimum
        int minFlips = Integer.MAX_VALUE;
        for(int i = 0; i <= n; i++){
            int flips = i == 0 ? 0 : ones[i - 1];
            flips += i == n ? 0 : zeros[i];
            minFlips = Math.min(flips, minFlips);
        }

        return minFlips;
    }
}

/**
 * Can optimize the two different for loops to a single loop
 * 
 * for(int i = 0, j = n - 1; j >= 0; i++, j--){}
 */


/**
 * Optimal Approach:
 * '1' does not impact the flips
 * '0' have two options:
 * Either flip '0' to '1' and increase the flip count
 * Or keep '0' as it is and flip all the '1' before it
 * So we maintain oneCount and flipCount and choose the minimum of the two
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
class Solution {
    public int minFlipsMonoIncr(String s) {
        int cntOne = 0, cntFlip = 0;

        char[] arr = s.toCharArray();
        for(char c: arr){
            if(c == '1')
                cntOne++;
            else{
                cntFlip++;
                cntFlip = Math.min(cntFlip, cntOne);
            }
        }

        return cntFlip;
    }
}