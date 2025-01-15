/*
 * TC - O(n)
 * SC - O(1)
 */
public class Dp35BuySellStocksOnce {
        public static int maximumProfit(ArrayList<Integer> p){
        // Write your code here.
        int min = Integer.MAX_VALUE;
        int max = 0;
        int n = p.size();

        for(int i = 0; i < n; i++){
            if(p.get(i) < min) min = p.get(i);
            max = Math.max(p.get(i) - min, max);
        }   

        return max;
    }
}
