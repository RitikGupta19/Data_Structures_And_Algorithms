/**
 * Problem: https://leetcode.com/problems/group-anagrams/description/
 * We need to group the anagrams together.
 * Eg: ["eat","tea","tan","ate","nat","bat"]
 * Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
 * 
 * Approach 1:
 * 1. Use the hashmap: key = sorted value of string, value = list of strings.
 * Eg: eat, tea, ate => aet -> [eat, tea, ate]
 *
 * Time Complexity: O(n * klogk) => n = number of strings, k = max length of string
 * Space Complexity: O(n)
 * 
 * 2. Use the hashmap: key = hash of freq of char, value = list of strings.
 * Eg: eat, tea, ate => 1$1$1$ -> [eat, tea, ate]
 * 
 * Time Complexity: O(n * k)
 * Space Complexity: O(n)
 */

 // Approach 1
 class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> ans = new ArrayList<>();
        HashMap<String, List<String>> map = new HashMap<>();

        for(String s: strs){
            char[] arr = s.toCharArray();
            Arrays.sort(arr);
            String key = new String(arr);
            
            if(!map.containsKey(key)){
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(s);
        }

        return new ArrayList<>(map.values());
    }
}

// Approach 2
// freq of char as key with $ as delimiter to create hash
class Solution {
    static final int MAX_CHAR = 26;

    public String getHash(char[] ch){
        String hash = "";
        int[] freq = new int[MAX_CHAR];
        for(int i = 0; i < ch.length; i++){
            freq[ch[i] - 'a']++;
        }
        for(int i = 0; i < MAX_CHAR; i++)
            hash += Integer.toString(freq[i]) + '$';
        return hash;
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> ans = new ArrayList<>();
        HashMap<String, List<String>> map = new HashMap<>();

        for(String s: strs){
            String hash = getHash(s.toCharArray());
            
            if(!map.containsKey(hash)){
                map.put(hash, new ArrayList<>());
            }
            map.get(hash).add(s);
        }

        return new ArrayList<>(map.values());
    }
}