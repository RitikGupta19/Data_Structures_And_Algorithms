/*
 * Problem: https://www.naukri.com/code360/problems/ninja-s-training_3621003?source=youtube&campaign=striver_dp_videos&utm_source=youtube&utm_medium=affiliate&utm_campaign=striver_dp_videos&leftPanelTabValue=PROBLEM
 * 
 * Ninja has to train each day and get some merit points.
 * He has 3 tasks to train and each task has some merit points.
 * He can train only one task a day.
 * He cannot train the same task on two consecutive days.
 * Find the max merit points by Ninja.
 * Similar to MaxSumNonAdj.java but with 2D DP.
 * 
 * Recursion:
 * Time Complexity: O(3^n)
 * Space Complexity: O(n) => Stack space
 * 
 * Memoization:
 * Time Complexity: O(n*4*3) - not all the states will have 4 states but for WC - N*4*3
 * Space Complexity: O(n*4) + O(n) => Table space + Stack space
 * 
 * Tabulation:
 * Time Complexity: O(n*4*3)
 * Space Complexity: O(n*4) => Table space
 * 
 * Space Optimized:
 * Time Complexity: O(n*4*3)
 * Space Complexity: O(4) => Table space
 */
public class NinjaTraining2DDP {
    public static int recursion(int n, int[][] points, int last){
        // Here at base case
        // We need to find the max among the 3 tasks except the last executed task.
        if(n == 0){
            int max = 0;
            for(int i = 0; i < 3; i++){
                if(i != last) max = Math.max(max, points[n][i]);
            }
            return max;
        }
        // We will try all the tasks and find the max among them.
        // Except the last executed task.
        int max = 0;
        for(int i = 0; i < 3; i++){
            if(i != last){
                int point = points[n][i] + recursion(n - 1, points, i);
                max = Math.max(max, point);
            }
        }
        return max;
    }

    public static int memoization(int n, int[][] points, int last, int[][] dp){
        if(n == 0){
            int max = 0;
            for(int i = 0; i < 3; i++){
                if(i != last) max = Math.max(max, points[n][i]);
            }
            return max;
        }
        // Dp of size N * 4 is needed as DP can have 3th task - that is nothing executed before
        // 0 - > last task was 0
        // 1 - > last task was 1
        // 2 - > last task was 2
        // 3 - > last task was nothing
        // In case of N = 1 then we need to have 4 size dp. as ans would be dp[n][3];
        if(dp[n][last] != -1) return dp[n][last];
        int max = 0;
        for(int i = 0; i < 3; i++){
            if(i != last){
                int point = points[n][i] + memoization(n - 1, points, i, dp);
                max = Math.max(max, point);
            }
        }
        return dp[n][last] = max;
    }

    public static int tabulation(int n, int[][] points){
        // dp[0th day][0th task] = Max of points[0][1] & points[0][2]
        int[][] dp = new int[n][4];
        dp[0][0] = Math.max(points[0][1], points[0][2]);
        dp[0][1] = Math.max(points[0][0], points[0][2]);
        dp[0][2] = Math.max(points[0][0], points[0][1]);
        dp[0][3] = Math.max(points[0][0], Math.max(points[0][1], points[0][2]));
        
        // Calculating for each day
        for(int i = 1; i < n; i++){
            // Considering last for all possible values
            for(int last = 0; last < 4; last++){
                dp[i][last] = 0;
                // Trying all the tasks
                for(int task = 0; task < 3; task++){
                    if(last != task){
                        int point = points[i][task] + dp[i - 1][task];
                        dp[i][last] = Math.max(dp[i][last], point);
                    }
                }
            }
        }

        // Initially the function was called with nth day and last task as 3.
        return dp[n - 1][3];
    }

    public static int space(int n, int[][] points){
        int[] dp = new int[4];
        dp[0] = Math.max(points[0][1], points[0][2]);
        dp[1] = Math.max(points[0][0], points[0][2]);
        dp[2] = Math.max(points[0][0], points[0][1]);
        dp[3] = Math.max(points[0][0], Math.max(points[0][1], points[0][2]));
        
        for(int day = 1; day < n; day++){
            // We just need the last stage (4 size array) for next row of dp
            // Hence using the dummy array
            int[] temp = new int[4];
            for(int last = 0; last < 4; last++){
                temp[last] = 0;
                for(int task = 0; task < 3; task++){
                    if(last != task){
                        temp[last] = Math.max(temp[last], points[day][task] + dp[task]);
                    }
                }
            }
            dp = temp;
        }

        return dp[3];
    }



    public static int ninjaTraining(int n, int points[][]) {
        // Write your code here..
        // return recursion(n - 1, points, 3);

        // int[][] dp = new int[n][4];
        // for(int i = 0; i < n; i++){
        //     for(int j = 0; j < 4; j++)
        //         dp[i][j] = -1;
        // }
        // return memoization(n - 1, points, 3, dp);

        // return tabulation(n, points);

        return space(n, points);
    }
}
