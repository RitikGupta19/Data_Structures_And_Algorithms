import java.util.ArrayList;

/**
 * Problem: we need to find the numbers which have exactly 3 divisors.
 * Eg: from 1 to 16, we have 4 numbers which have exactly 3 divisors: 4, 9
 * 4 = 1,2,4
 * 9 = 1,3,9
 * 16 = 1,2,4,8,16 = so it is not a 3 divisor number
 * 25 = 1,5,25
 * 
 * Approach 1: Count divisors of each number from 1 to n and check if it has exactly 3 divisors.
 * Nested loops will take O(n^2) time complexity.
 * 
 * Approach 2: Find the prime number less than n and count them.
 * 
 * Hint:
 * If you will see the number which has exactly 3 divisors, it will be a square of a prime number.
 * Eg: 4 = 2*2, 9 = 3*3, 25 = 5*5
 * We need to find the prime number whose square is less than n.
 * Link: https://www.geeksforgeeks.org/problems/3-divisors3942/1?itm_source=geeksforgeeks&itm_medium=article&itm_campaign=practice_card
 */

public class NumberWith3Divisors {
    // to check number is prime or not
    static boolean checkPrime(int n){
        for(int i = 2; i * i <= n; i++){
            if(n != i && n % i == 0) return false;
        }
        return true;
    }

    // to count the prime number less than n
    static int helper(long num){
        int n = (int) num;
        ArrayList<Boolean> isPrime = new ArrayList<>(n + 1);
        for(int i = 0; i <= n; i++) isPrime.add(false);
        
        for(int i = 2; i * i <= n; i = i + 1){
            if(checkPrime(i) == true) isPrime.set(i, true);
        }
        
        int cnt = 0;
        for(int i = 0; i <= n; i = i + 1){
            if(isPrime.get(i) == true) cnt++;
        }
        
        return cnt;
    }
    static ArrayList<Integer> threeDivisors(ArrayList<Long> query, int q){
        // code here
        ArrayList<Integer> ans = new ArrayList<>();
        
        for(int i = 0; i < q; i++){
            ans.add(helper(query.get(i)));
        }
        
        return ans;
    }
}
