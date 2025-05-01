/**
 * Sort two sorted arrays without using extra space.
 * Link: https://www.geeksforgeeks.org/problems/merge-two-sorted-arrays-1587115620/1
 * 
 * Possible Approaches:
 * 1. Not follow the constraint of not using extra space.
 *  - Create a new array of size n+m and copy the elements of both arrays into it.
 *  - Sort the new array and copy the elements back to the original arrays.
 *  - Time Complexity: O((n+m)log(n+m))
 *  - Space Complexity: O(n+m)
 * 
 * Another could be to take another array and use two pointers to traverse both arrays.
 * - Compare the elements at the pointers and copy the smaller element to the new array.
 * - Move the pointers accordingly.
 * - Time Complexity: O(n+m)
 * - Space Complexity: O(n+m)
 * 
 * 2. Follow the constraint of not using extra space.
 * - Use two pointers to traverse both arrays (one at last element & another at first).
 * - Compare the elements at the pointers and swap them if the element in the first array is greater than the element in the second array.
 * - Move the pointers accordingly.
 * - Sort both arrays after the swapping is done.
 * - Time Complexity: O(n+m + nlogn + mlogm) = O(nlogn + mlogm)
 * - Space Complexity: O(1) 
 */
public class MergeTwoSortedArrayWithoutExtraSpace {
    // APPROACH 1
    public void mergeArrays(int a[], int b[]) {
        // code here
        
        int n = a.length, m = b.length;
        int[] c = new int[n + m];
        int i = 0, j = 0, k = 0;
        while(i < n && j < m){
            if(a[i] <= b[j]) c[k++] = a[i++];
            else c[k++] = b[j++];
        }
        while(i < n) c[k++] = a[i++];
        while(j < m) c[k++] = b[j++];
        
        for(int l = 0; l < n + m; l++){
            if(l < n) a[l] = c[l];
            else b[l - n] = c[l];

        }
    }
    // APPROACH 2
    public void mergeArrays(int a[], int b[]) {
        int n = a.length, m = b.length;
        int i = n - 1, j = 0;
        while(i >= 0 && j < m){
            if(a[i] >= b[j]){
                int t = a[i];
                a[i] = b[j];
                b[j] = t;
                i--;
                j++;
            }
            else break;
        }
        Arrays.sort(a);
        Arrays.sort(b);
        return;
    }
}
