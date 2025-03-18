/**
 * Problem: Find if a number is divisible by 8
 * https://www.geeksforgeeks.org/problems/divisible-by-82732/0
 * 
 * Approach:
 * 1. If the number is of length 1, check if it is divisible by 8.
 * 2. If the number is of length 2, check if the number or its reverse is divisible by 8.
 * 3. If the number is of length 3, check if any permutation of the number is divisible by 8.
 * 
 * Divisibility by 8 rule: last 3 digits is divisible by 8.
 * We would count the freq. of all the digits in number whose length is >= 3.
 * Iterate from 104 - 999:
 * 104 (first 3 digit number divisible by 8) to 999 (last 3 digit number)
 * Count the freq of digits for each number.
 * 
 * If the freq of each digit in the number is less than or equal to the freq of digits in the given number, then the number is divisible by 8.
 * Because Eg: 80200, it can have 0 count as 3
 * But for us 200 is enough to check if it is divisible by 8.
 * It has only 2 zeros. Thus, freq2 > freq, then it is not divisible by 8.
 * 
 * Time Complexity: O(n) where n is the length of the number
 * Space Complexity: O(1)
 */
public class DivisibleBy8{
    int isDivisible8(String s){
        // code here
        int n = s.length();
        if(n == 1) return (Integer.valueOf(s) % 8) == 0 ? 1 : 0;
        
        if(n == 2){
            String r = "" + s.charAt(1) + s.charAt(0);
            if((Integer.valueOf(s) % 8 == 0) || (Integer.valueOf(r) % 8 == 0)) return 1;
            else return 0;
        }
        
        int[] freq = new int[10];
        for(char c: s.toCharArray()) freq[c - '0']++;
        
        for(int i = 104; i <= 999; i += 8){
            int[] freq2 = new int[10];
            int num = i;
            while(num > 0) {
                freq2[num % 10]++;
                num /= 10;
            }
            
            num = i;
            // We are using continue as it is not usefull to check further if first count does not match
            if(freq2[num % 10] > freq[num % 10]) continue;
            num = num /10;
            if(freq2[num % 10] > freq[num % 10]) continue;
            num = num / 10;
            if(freq2[num % 10] > freq[num % 10]) continue;
            
            return 1;
        }
        
        return 0;
    }
}