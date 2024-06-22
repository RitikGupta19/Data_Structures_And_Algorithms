/**
 * Tags: Amazon OA
 * 
 * Problem: To swap 0s and 1s in an array to make all 0s come before 1s or vice versa.
 * Find the minimum number of swaps required.
 * Eg: [0, 1, 0, 1] -> 1 => [0, 0, 1, 1]
 * Eg: [1, 1, 0, 0, 1] -> 2 => [1, 1, 1, 0, 0]
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
public class Main {
    public static int findSwaps(int[] arr, int t){
      int cnt = 0, last = 0;
      for(int i = 0; i < arr.length; i++){
        if(arr[i] == t){
          cnt += i - last;
          last++;
        }
      }
      return cnt;
    }
    
    public static void main(String[] args) {
      int[] arr = {1,1,1,0,0,0};
      int[] arr1 = {0,0,0,1,1,1};
      int[] arr2 = {0,0,0,0,1,0,1,0,0};
      int[] arr3 = {0,0,1,0,1,0};
      
      int swaps = Math.min(findSwaps(arr, 0), findSwaps(arr, 1));
      System.out.println("Ans: " + swaps);
      swaps = Math.min(findSwaps(arr1, 0), findSwaps(arr1, 1));
      System.out.println("Ans: " + swaps);
      swaps = Math.min(findSwaps(arr2, 0), findSwaps(arr2, 1));
      System.out.println("Ans: " + swaps);
      swaps = Math.min(findSwaps(arr3, 0), findSwaps(arr3, 1));
      System.out.println("Ans: " + swaps);
  }
}