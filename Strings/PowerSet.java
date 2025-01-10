/*
 * Problem: to generate all the possible power set of a string
 * except the empty string
 * 
 * Method 1: Recursion
 * Time Complexity: O(2^n)
 * Space Complexity: O(n)
 * 
 * Method 2: Bit-manipulation
 * Time Complexity: O(2^n * n)
 * Space Complexity: O(1)
 * Approach:
 * To check whether the ith bit is set or not for a number is to do the AND operation of that number
 * with [001(1) shifted i times to left] and check whether the result is 0 or not.
 * If the result is 0 then the ith bit is not set else it is set.
 * 
 * Eg: 5 = 101, we have to check 2nd bit is set or not
 * 5 & 4 = 101 & 100 = 100 != 0, so 2nd bit is set
 * 
 * Thus here we will iterate from 0 to 2^n and for each number we will check whether the ith bit is set or not
 * and if it is set then we will add the character at that index to the string.
 * This will help us to get the power set of the string.
 * Why we will iterate from 0 to 2^n?
 * String with length - n can have 2^n possible subsequences.
 */
public class PowerSet {
    public static void recursion(int i, String str, String s, ArrayList<String> ans){
        if(i == str.length()) return;
        ans.add(s + str.charAt(i));
        recursion(i + 1, str, s + str.charAt(i), ans);
        recursion(i + 1, str, s, ans);
    }

    public static ArrayList<String> subsequences(String str) {
        // Write your code here
        int n = str.length();
        int subseq = (int)Math.pow(2, n);
        ArrayList<String> ans = new ArrayList<>();

        // Bit manipulation way
        // for(int i = 0; i < subseq; i++){
        //     String s = "";
        //     for(int j = 0; j < n; j++){
        //         if((i & (1 << j)) != 0) s += str.charAt(j);
        //     }
        //     if(s != "") ans.add(s);
        // }
        // return ans;

        // Recursion
        String s = "";
        recursion(0, str, s, ans);
        return ans;

    }
}
