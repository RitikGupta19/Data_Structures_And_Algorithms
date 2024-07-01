/**
 * Problem Link: https://www.geeksforgeeks.org/minimum-number-of-operations-required-to-obtain-a-given-binary-string/
 * 
 * Brute force:
 * Intution:
 * Iterate from o to n-1th position and match with 't' string.
 * If not equal, flip the string from i to n-1 & increment the count.
 * 
 * Time Complexity: O(n^2)
 * Space Complexity: O(n)
 */
public class Main {
    public static int findMinFlips(String s){
      int n = s.length();
      String t = "";
      int ans = 0;
      
      for(int i = 0; i < n; i++)
        t += '0';
        
      for(int i = 0; i < n; i++){
        if(s.charAt(i) != t.charAt(i)){
          ans++;
          String temp = "";
          for(int j = i; j < n; j++)
            temp += t.charAt(i) == '0' ? '1' : '0';
          t = t.substring(0, i) + temp;
        }
      }
      
      return ans;
    }
    
    public static void main(String[] args) {
      String s1 = "101";
      String s2 = "10111";
      int ans1 = findMinFlips(s1);
      System.out.println(ans1);
      int ans2 = findMinFlips(s2);
      System.out.println(ans2);
  }
}

/**
 * Optimal Approach:
 * 
 * Intution:
 * Maintain last -> stores the last char on which operation was performed.
 * Ans -> Count the number of flips needed.
 * Flip the last char each time ans is increased.
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
public class Main {
    public static int findMinFlips(String s){
      int n = s.length();
      int ans = 0;
      char last = '0';
      
      for(int i = 0; i < n; i++){
        if(s.charAt(i) != last){
          ans++;
          last = last == '0' ? '1' : '0';
        }
      }
      
      return ans;
    }
    
    public static void main(String[] args) {
      String s1 = "101";
      String s2 = "10111";
      String s3 = "10101010101010110011111111001010101010100000000111111111";
      int ans1 = findMinFlips(s1);
      System.out.println(ans1);
      int ans2 = findMinFlips(s2);
      System.out.println(ans2);
      int ans3 = findMinFlips(s3);
      System.out.println(ans3);
  }
}