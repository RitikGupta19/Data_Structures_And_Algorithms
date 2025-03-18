/**
 * Given a string S. The task is to print all permutations of a given string.
 * 
 * Time Complexity: O(n * n!)
 * Space Complexity: O(n!)
 * 
 * For unique permutations, we can use a set to store the permutations.
 * Else, we can use a list to store the permutations.
 */
public class AllPossiblePermutations {
        public void swap(int i, int j, char[] arr){
        char t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
    
    public void helper(char[] arr, int i, Set<String> ans){
        if(i == arr.length){
            String s = new String(arr);
            ans.add(s);
            return;
        }
        
        for(int j = i; j < arr.length; j++){
            swap(i, j, arr);
            helper(arr, i + 1, ans);
            swap(i, j, arr);
        }
    }
    
    public ArrayList<String> findPermutation(String s) {
        // Code here
        int n = s.length();
        char[] arr = s.toCharArray();
        Set<String> ans = new HashSet<>();
        ArrayList<String> res = new ArrayList<>();
        helper(arr, 0, ans);
        res.addAll(ans);
        return res;
    }
}
