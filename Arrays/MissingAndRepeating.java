/**
 * Problem: find the missing and repeating number in an array of n elements  (1 to n);
 * 
 * Approach 1:
 * 1. Use a HashSet to store the elements.
 * 2. Traverse the array and check if the element is present in the hashset.
 * 3. If yes, then it is repeating.
 * 4. If no, then it is missing.
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 * 
 * Approach 2: Mathematic approach
 * Imp tip: Always do mathematical operations with similar data types.
 * Long should be sum with long.
 * 
 * 1. Find the sum of n natural numbers.
 * 2. Find the sum of the given array.
 * 3. Find the sum of squares of n natural numbers.
 * 4. Find the sum of squares of the given array.
 * 5. Let x be the missing number and y be the repeating number.
 * 6. x - y = sum - sumOfN
 * 7. x^2 - y^2 = sumOfSquares - sumOfSquaresOfN
 * 8. Solve the above two equations to get x and y.
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */
public class MissingAndRepeating {
        ArrayList<Integer> findTwoElement(int arr[]) {
        // code here
        long n = (long)arr.length;
        long sumeq = n * (n  + 1) / 2;
        long sumsquareEq = n * (n + 1) * (2 * n + 1) / 6;
        
        for(int i = 0; i < n; i++){
            sumeq -= (long)arr[i];
            sumsquareEq -= ((long)arr[i] * (long)arr[i]);
        }
        
        long xSuby = sumeq;
        long xPlusy = sumsquareEq / xSuby;
        
        long x = (xSuby + xPlusy) / 2;
        long y = xPlusy - x;
        
        ArrayList<Integer> ans = new ArrayList<>();
        ans.add((int)y);
        ans.add((int)x);
        return ans;
    }
}
