import java.util.HashMap;

/**
 * Given two strings s and t, determine if they are isomorphic.
 * Link: https://leetcode.com/problems/isomorphic-strings/
 * Each character in a string must be mapped to a single character in the other string.
 * Vice versa should also be true.
 * 
 * Approach 1:
 * 1. Use of two nested loops.
 * 2. For each character in s, check if the corresponding character in t is same.
 * 3. If not, return false.
 * 4. If all characters are same, return true.
 * 
 * Time complexity: O(n^2)
 * Space complexity: O(1)
 * 
 * Approach 2:
 * 1. Use of two hashmaps to store the index of each character in both strings.
 * 2. For each character in s, check if the index of the character in s is same as the index of the character in t.
 * 3. If not, return false.
 * 4. If all characters are same, return true.
 * 
 * Time complexity: O(n)
 * Space complexity: O(n)
 */
public class IsomorphicStrings {
    // APPROACH 1
    public boolean isIsomorphic(String s, String t) {
        int n = s.length(), m = t.length();
        if(n != m) return false;

        for(int i = 0; i < n; i++){
            char c1 = s.charAt(i), c2 = t.charAt(i);
            for(int j = i + 1; j < n; j++){
                if(s.charAt(j) == c1 && t.charAt(j) != c2) return false;
            }
        }

        return true;
    }

    // APPROACH 2
    public boolean isIsomorphic(String s, String t) {
        int n = s.length(), m = t.length();
        if(n != m) return false;
        HashMap<Character, Integer> m1 = new HashMap<>();
        HashMap<Character, Integer> m2 = new HashMap<>();

        for(int i = 0; i < n; i++){
            m1.putIfAbsent(s.charAt(i), i);
            m2.putIfAbsent(t.charAt(i), i);
            if(m1.get(s.charAt(i)) != m2.get(t.charAt(i))) return false;
        }
        return true;
    }
}
