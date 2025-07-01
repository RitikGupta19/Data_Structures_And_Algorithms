/**
 * Link: https://leetcode.com/problems/median-of-two-sorted-arrays/description/
 * 
 * 1. Merge the two sorted arrays and find the median.
 * Time Complexity: O(n + m) - where n and m are the lengths of the two arrays.
 * Space Complexity: O(n + m) - for storing the merged array.
 * 
 * 2. Use binary search to find the median.
 * Time Complexity: O(log(min(n, m))) - where n and m are the lengths of the two arrays.
 * Space Complexity: O(1) - no extra space is used.
 */
public class MedianOfTwoSortedArrays {
    // APPROACH 1: Merge and Find Median
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        int i = 0, j = 0;
        int N = n + m;
        int ind1 = -1, ind2 = -1, ele1 = 0, ele2 = 0, cnt = 0;

        if(N % 2 == 0) {
            ind1 = N / 2;
            ind2 = N / 2 - 1;
        }
        else ind1 = N / 2;

        while(i < n && j < m){
            if(nums1[i] <= nums2[j]){
                if(cnt == ind1) ele1 = nums1[i];
                if(cnt == ind2) ele2 = nums1[i];
                i++;
                cnt++;
            }
            else{
                if(cnt == ind1) ele1 = nums2[j];
                if(cnt == ind2) ele2 = nums2[j];
                j++;
                cnt++;
            }
        }

        while(i < n){
            if(cnt == ind1) ele1 = nums1[i];
            if(cnt == ind2) ele2 = nums1[i];
            i++;
            cnt++;
        }

        while(j < m){
            if(cnt == ind1) ele1 = nums2[j];
            if(cnt == ind2) ele2 = nums2[j];
            j++;
            cnt++;
        }

        if(N % 2 == 0) return ((double)(ele1 + ele2) / 2.0);
        else return (double)ele1;
    }
    
    // APPROACH 2: Binary Search
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        if(n > m) return findMedianSortedArrays(nums2, nums1);
        int left = (n + m + 1) / 2;
        int low = 0, high = n;

        while(low <= high){
            int mid1 = (low + high) >> 1;
            int mid2 = left - mid1;
            int l1 = Integer.MIN_VALUE, l2 = Integer.MIN_VALUE;
            int r1 = Integer.MAX_VALUE, r2 = Integer.MAX_VALUE;

            if(mid1 >= 1) l1 = nums1[mid1 - 1];
            if(mid2 >= 1) l2 = nums2[mid2 - 1];
            if(mid1 < n) r1 = nums1[mid1];
            if(mid2 < m) r2 = nums2[mid2];

            if(l1 <= r2 && l2 <= r1){
                if((n + m) % 2 == 0) return ((double)(Math.max(l1, l2) + Math.min(r1, r2)) / 2.0); 
                else return Math.max(l1, l2);
            }
            else if(l1 > r2) high = mid1 - 1;
            else low = mid1 + 1;
        }

        return 0;
    }
}
