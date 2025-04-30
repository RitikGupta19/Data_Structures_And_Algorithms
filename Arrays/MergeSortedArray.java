/**
 * Problem: Merge Sorted Array with given extra space in one of the arrays.
 * https://leetcode.com/problems/merge-sorted-array/
 * 
 * The problem can be solved using two approaches:
 * 1. Combine two arrays and sort them: O((m+n)log(m+n)) time complexity and O(1) space complexity.
 * 2. Two pointer approach: O(m+n) time complexity and O(1) space complexity.
 */
public class MergeSortedArray {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int k = n + m - 1;
        int i = m - 1, j = n - 1;

        while(i >= 0 && j >= 0){
            if(nums1[i] >= nums2[j]){
                nums1[k] = nums1[i];
                i--;
            } else {
                nums1[k] = nums2[j];
                j--;
            }
            k--;
        }
        
        while(j >= 0) nums1[k--] = nums2[j--];
    }
}
