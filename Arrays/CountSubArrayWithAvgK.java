/**
 * Problem link: https://www.geeksforgeeks.org/count-of-subarrays-with-average-k/
 * 
 * Brute force:
 * To find the avg for each possible subarray.
 * If the target avg matches subarray avg we increment the res count.
 * 
 * Time Complexity: O(n^2)
 * Space Complexity: O(1)
 */
public class Main {
    public static int countSubarray(int[] arr, int k){
      int res = 0;
      
      for(int i = 0; i < arr.length; i++){
        double sum = 0;
        
        for(int j = i; j < arr.length; j++){
          sum += arr[j];
          
          if((double)k == (sum/(double)(j - i + 1))){
            res++;
          }
        }
      }
      return res;
    }
    
    public static void main(String[] args) {
      int k = 4;
      int[] arr1 = {1, 4, 2, 6, 10};
      int ans1 = countSubarray(arr1, k);
      System.out.println(ans1);
      k = 6;
      int[] arr2 = {12, 5, 3, 10, 4, 8, 10, 12, -6, -1};
      int ans2 = countSubarray(arr2, k);
      System.out.println(ans2);
  }
}

/**
 * Optimal Approach:
 * 
 * Subset with avg k = (a + b + c + d) / 4
 * a + b + c + d = 4k
 * (a - k) + (b - k) + (c - k) + (d - k) = 0
 * This means if we can reduce each element with the average.
 * We need to find the subarray with sum 0.
 * So the question become similar to count subarrays with sum 0.
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */
public static int countSubarray(int[] arr, int k){
      HashMap<Integer, Integer> map = new HashMap<>();
      map.put(0, 1);
      int res = 0, curr = 0;
      
      for(int i = 0; i < arr.length; i++){
        curr += (arr[i] - k);
        
        res += map.getOrDefault(curr, 0);
        map.put(curr, map.getOrDefault(curr, 0) + 1);
      }
      
      return res;
}