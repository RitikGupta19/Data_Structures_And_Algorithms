/**
 * Quick Sort: https://www.geeksforgeeks.org/problems/quick-sort/1
 * 
 * 1. Choose a pivot element.
 * 2. Partition the array into two halves:
 *    - Elements less than or equal to the pivot on the left.
 *    - Elements greater than the pivot on the right.
 * 3. Recursively apply the same logic to the left and right halves.
 * 
 * Time Complexity: O(n log n) on average, O(n^2) in the worst case.
 * Space Complexity: O(log n) for the recursion stack.
 */
public class QuickSort {
        static void quickSort(int arr[], int low, int high) {
        // code here
        if(low >= high) return;
        int pIndex = partition(arr, low, high);
        quickSort(arr, low, pIndex - 1);
        quickSort(arr, pIndex + 1, high);
    }

    static int partition(int arr[], int low, int high) {
        int pivot = arr[low];
        int i = low, j = high;
        
        while(i < j){
            while(arr[i] <= pivot && i <= high - 1) i++;
            while(arr[j] > pivot && j >= low + 1) j--;
            if(i < j){
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        
        int temp = arr[low];
        arr[low] = arr[j];
        arr[j] = temp;
        return j;
    }
}
