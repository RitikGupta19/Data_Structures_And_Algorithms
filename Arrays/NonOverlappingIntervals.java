/**
 * Non-overlapping Intervals:
 * https://leetcode.com/problems/non-overlapping-intervals/description/
 * Problem: Given a collection of intervals, find the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.
 * 
 * Approach: Greedy Approach (Sort on the basis of start time)
 * 1. Sort the intervals based on the start time.
 * 2. Traverse the intervals and check if the end time of the current interval is greater than the start time of the next interval.
 * 3. If it is, then increment the count and update the end time to the minimum of the two end times. (Greedy)
 * 4. Else, update the end time to the end time of the current interval.)
 * 5. Return the count.
 * 
 * Time Complexity: O(nlogn)
 * Space Complexity: O(1)
 * 
 * Approach 2: Sort on the basis of end time.
 */
public class NonOverlappingIntervals {
     public int eraseOverlapIntervals(int[][] intervals) {
        int n = intervals.length;
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        int ans = 0;
        int lastEnd = intervals[0][1];
        for(int i = 1; i < n; i++){
            if(lastEnd > intervals[i][0]){
                ans++;
                lastEnd = intervals[i][1] < lastEnd ? intervals[i][1] : lastEnd;
            }else lastEnd = intervals[i][1];
        }

        return ans;
    }
}

/**
 * If you want to return the intervals that are not overlapping, you can use the below code:
 */
class Pair{
    int x; 
    int y;
    Pair(int x, int y){
        this.x = x;
        this.y = y;
    }
}
class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        int n = intervals.length;
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        int ans = 0;
        int lastEnd = intervals[0][1];
        ArrayList<Pair> temp = new ArrayList<>();
        temp.add(new Pair(intervals[0][0], intervals[0][1]));

        for(int i = 1; i < n; i++){
            if(lastEnd > intervals[i][0]){
                ans++;
                if(intervals[i][1] < lastEnd){
                    lastEnd = intervals[i][1];
                    temp.remove(temp.size() - 1);
                    temp.add(new Pair(intervals[i][0], intervals[i][1]));
                }
            } else {
                lastEnd = intervals[i][1];
                temp.add(new Pair(intervals[i][0], intervals[i][1]));
            }
        }
        for(Pair p: temp){
            System.out.println("x: " + p.x + " y: " + p.y);
        }
        return ans;
    }
}