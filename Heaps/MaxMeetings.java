/**
 * Problem: https://leetcode.com/problems/maximum-number-of-events-that-can-be-attended/description/
 * Find the maximum meetings that can be attended.
 * Only one meeting can be attended in a day.
 * 
 * Approach:
 * 1. Sort the elements on the basis of start time - So we can start the early starting meeting if end is same (to maximize).
 * 2. Start the day count with first element "start day".
 * 3. If the start time of the meeting is same as the day, then add the end time to the priority queue.
 * - PQ is maintained to get the early ending meetings (to maximize).
 * 4. If the PQ is not empty, then increment the day count and remove the meeting from the PQ.
 * 5. If the day is greater than the end time of the meeting, then remove the meeting from the PQ.
 * - Since need to remove the events that cannot be executed.
 * 
 * Time Complexity: O(NlogN + NlogN + N) = O(NlogN)
 * Space Complexity: O(N) 
 */
public class MaxMeetings {
    public int maxEvents(int[][] events) {
        Arrays.sort(events, (a, b) -> a[0] - b[0]);
        int n = events.length;
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        int i = 0, cnt = 0, day = 1;
        while(i < n || !pq.isEmpty()){
            if(pq.isEmpty()) day = events[i][0];
            while(i < n && day == events[i][0]) pq.offer(events[i++][1]);

            if(!pq.isEmpty()){
                day++;
                cnt++;
                pq.poll();
            }

            while(!pq.isEmpty() && day > pq.peek()) pq.poll();
            
        }

        return cnt;
    }
}
