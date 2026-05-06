import java.util.*;

class Solution {
    public int minOperations(int[][] grid, int x) {
        int m = grid.length, n = grid[0].length;
        int size = m * n;
        int[] arr = new int[size];
        
              int idx = 0;
        for (int[] row : grid) {
            for (int val : row) {
                arr[idx++] = val;
            }
        }
        
                int rem = arr[0] % x;
        for (int val : arr) {
            if (val % x != rem) {
                return -1;
            }
        }
        
              Arrays.sort(arr);
        
      
        int median = arr[size / 2];
        
   
        int operations = 0;
        for (int val : arr) {
            operations += Math.abs(val - median) / x;
        }
        
        return operations;
    }
}