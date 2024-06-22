/**
 * Tags: Amazon OA
 * 
 * The developers at Amazon want to perform a reliability drill on some servers. There are n servers 
 * where the ith server can serve request[i] number of requests and has an initial health of health[i] 
 * units.
 * Each second, the developers send the maximum possible number of requests that can be served by all 
 * the available servers. With the request, the developers can also send a virus to one of the servers 
 * that can decrease the health of a particular server by k units. The developers can choose the server 
 * where the virus should be sent. A server goes down when its health is less than or equal to 0. 
 * After all the servers are down, the developers must send one more request to conclude the failure of
 * the application. 
 * Find the minimum total number of requests that the developers must use to bring all the servers down.
 * Example
 * Consider n = 2, request = [3, 4], health = [4, 6], k = 3, 
 * The minimum number of requests required is 25.
 * 
 * n = 4;
 * k = 2;
 * int[] server1 = {1, 1, 1, 1};
 * int[] health1 = {3, 3, 3, 3};
 * Ans = 27
 */
import java.util.*;

public class Solution {

  public int requests(int n, int k, int[] s, int[] h){
    int totalReq = 0;
    while(true){
      int currReq = 0;
      
      // Calculate number of req. in initial state
      for(int i = 0; i < s.length; i++){
        if(h[i] > 0){
          currReq += s[i];
        }
      }
      
      totalReq += currReq;
      
      // Checking if all server down or not
      boolean allDown = true;
      for(int i = 0; i < h.length; i++){
        if(h[i] > 0){
          allDown = false;
          break;
        }
      }
      
      if(allDown){
        ++totalReq;
        break;
      }
      
      // Sending virus with max health and decreasing server health
      int maxHealth = Integer.MIN_VALUE, pos = -1;
      for(int i = 0; i < h.length; i++){
        if(maxHealth < h[i]){
          maxHealth = h[i];
          pos = i;
        }
      }
      
      // Reducing server health
      h[pos] = h[pos] - k;
    }
    
    return totalReq;
  }
}