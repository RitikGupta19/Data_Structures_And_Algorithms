/**
 * Tags: Amazon OA
 * 
 * StrengthOfPass: Given a string, find the strength of the password.
 * The strength of the password increases by one for one consonant and one vowel
 * Eg: RitikGupta -> Ri ti Gu ta -> 4
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */
import java.util.*;

public class Main {
    public static int findStrongPass(String str){
      boolean consonant = false, vowel = false;
      int count = 0;
      char[] a = str.toCharArray();
      for(int i = 0; i < a.length; i++){
        if(a[i] == 'a' || a[i] == 'e' || a[i] == 'i' || a[i] == 'o' || a[i] == 'u'){
          vowel = true;
        }
        else {
          consonant = true;
        }
        if(vowel && consonant){
          count++;
          vowel = false;
          consonant = false;
        }
      }
      return count;
    }
    
    public static void main(String[] args) {
      String ques = "thisisbeautiful";
      int ans = findStrongPass(ques);
      System.out.println("Ans: " + ans);
  }
}