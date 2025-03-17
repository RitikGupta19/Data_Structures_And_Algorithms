/**
 * Problem: to find the exactly two non-repeating numbers in an array where all other numbers are repeating twice.
 * https://www.geeksforgeeks.org/problems/finding-the-numbers0215/1?itm_source=geeksforgeeks&itm_medium=article&itm_campaign=practice_card
 * 
 * Approach 1:
 * 1. Sort the array.
 * 2. Traverse the array and check if the current element is equal to the next element.
 * 3. If not, add the element to the lis + increment pointer by 1.
 * 4. If equal, increment pointer by 2.
 * 
 * Time complexity: O(nlogn + n) where n is the length of the array.
 * Space complexity: O(1)
 * 
 * Approach 2:
 * 1. XOR all the elements of the array.
 * 2. Find the rightmost set bit of the XOR result.
 * 3. Divide the array into two groups based on the rightmost set bit.
 *      - One group will have the set bit and the other will not have the set bit.
 * 4. XOR all the elements of the two groups.
 * 
 * Time complexity: O(n) where n is the length of the array.
 * Space complexity: O(1) 
 */
public class TwoNonRepeatingNumber {
    // APPROACH 1
    public List<Integer> singleNumber(int[] arr) {
        // Code here
        Arrays.sort(arr);
        int n = arr.length;
        ArrayList<Integer> ans = new ArrayList<>();
        int i = 0;
        while(i < n){
            // Need to take care to check if i + 1 is within bounds inside while loop
            // So that if last element is unique we can add it to the list
            // For last element this condition will be false and we will add it to the list
            if(i + 1 < n && arr[i] == arr[i + 1]) i += 2;
            else {
                ans.add(arr[i]);
                i++;
            }
        }
        
        return ans;
    }

    // APPROACH 2
    public List<Integer> singleNumber(int[] arr) {
        // Code here
        int n = arr.length;
        int xor = 0;
        for(int i: arr) xor = xor ^ i;
        
        // To find the rightmost set bit
        // XOR or value with 2's complement of itself
        xor = xor & (~xor + 1);
        
        ArrayList<Integer> ans = new ArrayList<>(Arrays.asList(0, 0));
        for(int i: arr){
            if((xor & i) == 0) ans.set(0, ans.get(0) ^ i);
            else ans.set(1, ans.get(1) ^ i);
        }
        
        if(ans.get(0) > ans.get(1)) {
            int t = ans.get(0);
            ans.set(0, ans.get(1));
            ans.set(1, t);
        }
        return ans;
    }
}
