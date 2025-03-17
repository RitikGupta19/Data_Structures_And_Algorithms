/**
 * Problem: https://leetcode.com/problems/first-unique-character-in-a-string/
 * Approach:
 * 1. Create a frequency array of size 26.
 * 2. Traverse the string and increment the frequency of each character.
 * 3. Traverse the string again and check the frequency of each character.
 * 4. If the frequency is 1, then return the index.
 * 5. Else return -1.
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(1) => 26 characters only.
 */
public class FirstUniqueCharacterInString {
    public int firstUniqChar(String s) {
        int n = s.length();
        int[] freq = new int[26];

        for(int i = 0; i < n; i++){
            freq[s.charAt(i) - 'a'] += 1;
        }

        for(int i = 0; i < n; i++) {
            if(freq[s.charAt(i) - 'a'] == 1) return i;
        }

        return -1;
    }
}
