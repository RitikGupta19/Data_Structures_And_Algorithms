/**
 * Find closest primes numbers in a range
 * Link: https://leetcode.com/problems/closest-prime-numbers-in-range/
 * Eg: 10 - 20, primes  11, 13, 17, 19
 * Output: 11, 13 as closest primes , diff = 2
 * 
 * 
 * Approach:
 * 1. Sieve of Eratosthenes to find all the prime numbers in the range [left, right].
 * 2. Store the prime numbers in an ArrayList.
 * 3. Iterate through the ArrayList and find the closest prime numbers by checking the difference between consecutive prime numbers.
 * 4. If the difference is less than the minimum difference found so far, update the closest prime numbers.
 * 5. Return the closest prime numbers as an array.
 * 
 * Time Complexity: O(N * log(log(N))) for Sieve of Eratosthenes + O(P) for finding closest primes, where P is the number of primes in the range.
 * Space Complexity: O(N) for storing the prime numbers in the boolean array and ArrayList.
 */
public class FindClosestPrimeNumInRange {
        public int[] closestPrimes(int left, int right) {
        int n = right;
        boolean[] isPrime = new boolean[n + 1];
        Arrays.fill(isPrime, true);
        isPrime[0] = false;
        isPrime[1] = false;
        for(int i = 2; i * i <= right; i++){
            if(isPrime[i] == true){
                for(int j = i * i; j <= right; j += i) isPrime[j] = false;
            }
        }

        ArrayList<Integer> primes = new ArrayList<>();
        for(int i = left; i <= right; i++){
            if(isPrime[i] == true) primes.add(i);
        }

        int[] ans = {-1, -1};
        int min = Integer.MAX_VALUE;
        for(int i = 1; i < primes.size(); i++){
            if(min > primes.get(i) - primes.get(i - 1)){
                min = primes.get(i) - primes.get(i - 1);
                ans[0] = primes.get(i - 1);
                ans[1] = primes.get(i);
            }

        }

        return ans;
    }
}
