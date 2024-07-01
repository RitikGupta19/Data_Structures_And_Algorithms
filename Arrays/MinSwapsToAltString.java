/**
 * Link: https://leetcode.com/problems/minimum-number-of-flips-to-make-the-binary-string-alternating/description/
 * 
 * Intution:
 * Since we can shift the first char to the last char - this gives us hint for rotation of arr
 * OR we can say to append whole string and then traverse the string using sliding window for
 * all possible substrings of length n.
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(2 * n)
 */
class Solution {
    public int minFlips(String s) {
        int m = s.length();
        s += s;
        String s1 = "", s2 = "";
        for(int i = 0; i < s.length(); i++){
            s1 += i % 2 == 0 ? '0' : '1';
            s2 += i % 2 == 0 ? '1' : '0';
        }

        int ans1 = 0, ans2 = 0, ans = Integer.MAX_VALUE;
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) != s1.charAt(i)) ans1++;
            if(s.charAt(i) != s2.charAt(i)) ans2++;
            // After adding for the char after n-1th position
            // We will subtract for the first char of the window
            if(i >= m){
                if(s.charAt(i - m) != s1.charAt(i - m)) ans1--;
                if(s.charAt(i - m) != s2.charAt(i - m)) ans2--;
            }
            if(i >= m - 1){
                ans = Math.min(ans, Math.min(ans1, ans2));
            }
        }
        return ans;
    }
}

/**
 * The above solution may give TLE:
 * Better approach: without using two new strings
 * 
 * Time COmplexity: O(n)
 * Space Complexity: O(1)
 */
class Solution {
    public int minFlips(String s) {
        int m = s.length();
        s += s;

        int ans1 = 0, ans2 = 0, ans = Integer.MAX_VALUE;
        for(int i = 0; i < s.length(); i++){
            char c1 = i % 2 == 0 ? '0' : '1';
            char c2 = i % 2 == 0 ? '1' : '0';
            if(s.charAt(i) != c1) ans1++;
            if(s.charAt(i) != c2) ans2++;
            if(i >= m){
                c1 = (i - m) % 2 == 0 ? '0' : '1';
                c2 = (i - m) % 2 == 0 ? '1' : '0';
                if(s.charAt(i - m) != c1) ans1--;
                if(s.charAt(i - m) != c2) ans2--;
            }
            if(i >= m - 1){
                ans = Math.min(ans, Math.min(ans1, ans2));
            }
        }
        return ans;
    }
}