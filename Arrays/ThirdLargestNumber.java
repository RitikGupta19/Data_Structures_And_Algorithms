/**
 * To find the third largest number in an array
 * https://leetcode.com/problems/third-maximum-number/description/
 *  
 */
public class ThirdLargestNumber {
    public int thirdMax(int[] arr) {
        int n = arr.length;
        long max1 = Long.MIN_VALUE, max2 = Long.MIN_VALUE, max3 = Long.MIN_VALUE;

        for(int i = 0; i < n; i++){
            if(max1 < arr[i]){
                max3 = max2;
                max2 = max1;
                max1 = (long)arr[i];
            }
            else if(arr[i] < max1 && max2 < arr[i]){
                max3 = max2;
                max2 = (long)arr[i];
            }
            else if(arr[i] < max2 && max3 < arr[i]) max3 = (long)arr[i];
        }
        
        return max3 == Long.MIN_VALUE ? (int)max1 : (int)max3;
    }
}
