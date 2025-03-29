/**
 * Count prime numbers less than = n
 * Problem: https://leetcode.com/problems/count-primes/
 * 
 * Approach 1:
 * 1. Iterate for each number till N
 * 2. Check if it is a prime number or not.
 * 
 * Time Complexity: O(N * sqrt(N))
 * Space Complexity: O(1)
 * 
 * Approach 2:
 * 1. Sieve of Eratosthenes
 * 2. Create a boolean array of size N and initialize all values to true.
 * 3. Set the first two values (0 and 1) to false as they are not prime numbers.
 * 4. Iterate from 2 to sqrt(N) and for each prime number, mark its multiples as false.
 * 5. Count the number of true values in the boolean array.
 * 
 * Time Complexity: O(N * log(log(N)))
 * Space Complexity: O(N)
 */
public class CountPrime {
    // APPROACH 2 - Sieve of Eratosthenes
    public int countPrimes(int n) {
        if(n < 2) return 0;
        boolean[] isPrime = new boolean[n];
        isPrime[0] = false;
        isPrime[1] = false;
        for(int i = 2; i < n; i++) isPrime[i] = true;
        int cnt = 0;
        // Iterating for all the numbers from 2 to sqrt(n)
        for(int i = 2; i * i < n; i++){
            if(isPrime[i] == true){
                // Marking all the multiples of i as false
                for(int j = i * i; j < n; j = j + i) isPrime[j] = false;
            }
        }

        for(int i = 0; i < n; i++)
            if(isPrime[i] == true) cnt++;
        return cnt;
    }
}
