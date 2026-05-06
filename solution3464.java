import java.util.*;

class solution3464 {
    public int maxDistance(int side, int[][] points, int k) {
        int n = points.length;
        long[] unfolded = new long[n];
        
        for (int i = 0; i < n; i++) {
            int x = points[i][0];
            int y = points[i][1];
            if (x == 0) unfolded[i] = y;
            else if (y == side) unfolded[i] = (long) side + x;
            else if (x == side) unfolded[i] = (long) 3 * side - y;
            else unfolded[i] = (long) 4 * side - x;
        }
        
        Arrays.sort(unfolded);
        
        int low = 1, high = side;
        int ans = 0;
        
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (canPlace(unfolded, k, mid, (long) 4 * side)) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return ans;
    }
    
    private boolean canPlace(long[] unfolded, int k, int minDist, long totalLen) {
        int n = unfolded.length;
        
    
        for (int i = 0; i < n; i++) {
            
            if (unfolded[i] >= unfolded[0] + minDist && i > 0) break; 
            
            int count = 1;
            long lastPos = unfolded[i];
            long firstPos = unfolded[i];
            
            for (int j = i + 1; j < n; j++) {
                if (unfolded[j] - lastPos >= minDist) {
                    lastPos = unfolded[j];
                    count++;
                    if (count == k) break;
                }
            }
            
            if (count == k && (totalLen - lastPos + firstPos) >= minDist) {
                return true;
            }
            
            
            if (i > n / k) break; 
        }
        return false;
    }
}