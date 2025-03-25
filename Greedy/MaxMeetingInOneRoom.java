/**
 * Problem: to find the maximum number of meetings that can be accomodated in a room.
 * Link: https://www.geeksforgeeks.org/problems/maximum-meetings-in-one-room/1?itm_source=geeksforgeeks&itm_medium=article&itm_campaign=practice_card
 * 
 * Approach:
 * 1. Sort on the basis of end time.
 * As we need to select the earliest ending meeting first to increase the count.
 * 2. Set limit to the end time of the first meeting.
 * 3. Traverse the meetings and if the start time of the meeting is greater than the limit, then select that meeting.
 * 4. Update the limit to the end time of the selected meeting.
 * 
 * Time Complexity: O(NlogN + N)
 * Space Complexity: O(N)
 */

class Meeting{
    int start;
    int end;
    int ind;
    
    Meeting(int start, int end, int ind){
        this.start = start;
        this.end = end;
        this.ind = ind;
    }
}

class MeetingSort implements Comparator<Meeting>{

    public int compare(Meeting m1, Meeting m2){
        if(m1.end < m2.end) return -1;
        // else if(m2.end < m1.end) return 1;
        // else if(m1.ind < m2.ind) return -1;
        return 1;
    }
}

public class MaxMeetingInOneRoom {
    public static ArrayList<Integer> maxMeetings(int N, int[] S, int[] F) {
        // code here
        ArrayList<Meeting> meetings = new ArrayList<>();
        for(int i = 0; i < N; i++) meetings.add(new Meeting(S[i], F[i], i));
        Collections.sort(meetings, new MeetingSort());
        
        ArrayList<Integer> ans = new ArrayList<>();
        ans.add(meetings.get(0).ind + 1);
        int limit = meetings.get(0).end;
        
        for(int i = 1; i < N; i++){
            if(limit < meetings.get(i).start){
                ans.add(meetings.get(i).ind + 1);
                limit = meetings.get(i).end;
            }
        }
        
        Collections.sort(ans);
        return ans;
    }
}
