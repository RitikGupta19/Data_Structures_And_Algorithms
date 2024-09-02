/*
 * Problem Statement: https://www.interviewbit.com/problems/distinct-numbers-in-window/discussion/p/java-sliding-window-o-n/527174/995/
 * 
 * To find the unique elements in a window of size B in an array A.
 * 
 * Sliding window approach:
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */
public class DistinctEleInWindow {
    public ArrayList<Integer> dNums(ArrayList<Integer> A, int B) {
        ArrayList<Integer> ans = new ArrayList<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        int n = A.size();
        if(B > n) return ans;
        
        int cnt = 0;
        
        // Keeping the count of unique elements in the window
        // If the frequency of an element becomes 0, decrement the count
        // If the frequency of an element becomes 1, increment the count

        // Keeping the track of starting ele with (i - B)th index
        for(int i = 0; i < n; i++){
            int freq = map.getOrDefault(A.get(i), 0);
            if(freq == 0) cnt++; 
            map.put(A.get(i), ++freq);
            
            // For first time we will add the count of unique elements in the window
            // From next time will be done in below block
            if(i + 1 == B)  ans.add(cnt);
            
            if(i < B) continue;
            
            freq = map.get(A.get(i - B));
            if(freq == 1) cnt--;
            map.put(A.get(i - B), --freq);
            ans.add(cnt);
        }
        
        return ans;
    }
}
