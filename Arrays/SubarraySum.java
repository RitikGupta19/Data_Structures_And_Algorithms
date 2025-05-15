/**
 * Similar to find sub-array with sum k
 */
public class SubarraySum {
    static boolean findsum(int arr[]) {
        // Your code here
        int n = arr.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int cnt = 0, sum = 0;
        for(int i = 0; i < n; i++){
            sum += arr[i];
            cnt += map.getOrDefault(sum, 0);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return cnt == 0 ? false : true;
    }
}
