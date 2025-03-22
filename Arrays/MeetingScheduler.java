/**
 * Problem: https://www.hackerrank.com/contests/amazon/challenges/meeting-schedules/problem
 * You are given the slots of time when people are busy and you have to find the free slots of time when you can schedule a meeting for k minutes.
 * 
 * Tip:
 * Way to input the time using Scanner class.
 * m = number of booked times, k = minutes to book.
 * Approach:
 * 1. Create a array of 24 hr * 60 min = 1440 minutes size.
 * 2. For each booked time, increment the array index from start time to end time.
 * 3. Traverse the array and find the free slots of time when you can schedule a meeting for k minutes.
 * 
 * Time complexity: O(m): where m is the number of booked times.
 * Space complexity: O(1): since we are using a fixed size array of 1440.
 */
public class MeetingScheduler{
    /**
     * 
     * Input format:
     *  5 120
        16 00 17 00
        10 30 14 30
        20 45 22 15
        10 00 13 15
        09 00 11 00 
     */
        Scanner scan = new Scanner(System.in);
        int[] arr = new int[1440];
        int m = scan.nextInt();
        int k = scan.nextInt();
        
        while(m-- != 0){ // O(m)
            int sh = scan.nextInt();
            int sm = scan.nextInt();
            int eh = scan.nextInt();
            int em = scan.nextInt();
            
            // Convert hr to min
            for(int i = sh * 60 + sm; i < eh * 60 + em; i++) arr[i]++; // WC O(1440)
        }
        
        // for(int i = 0; i < 1440; i++) System.out.println(" " + arr[i]);
        
        int i = 0, start = 0, end = 0, flag = 0;
        while(i < 1440){
            if(arr[i] == 0){
                start = i;
                while(i < 1440 && arr[i++] == 0);
                end = i - 1;
                if(k <= end - start){

                    // for 23 59 - end = 00 00
                    if(end / 60 == 23 && end % 60 == 59) end = 0;
                    System.out.println(String.format("%02d",start/60)+" "+String.format("%02d",start%60)+"      "+String.format("%02d",end/60)+" "+String.format("%02d",end%60));
                }
            }
            i++;
        }
    }