import java.util.*;

class solution3742 {
    public int maxPathScore(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        
        int[][][] dp = new int[m][n][k + 1];
        for (int[][] row : dp) {
            for (int[] cell : row) {
                Arrays.fill(cell, -1);
            }
        }

        int startVal = grid[0][0];
        int startCost = (startVal > 0) ? 1 : 0;
        
        if (startCost <= k) {
            dp[0][0][startCost] = startVal;
        } else {
            return -1; 
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int c = 0; c <= k; c++) {
                    if (dp[i][j][c] == -1) continue;

                    if (j + 1 < n) {
                        int val = grid[i][j + 1];
                        int nextCost = c + (val > 0 ? 1 : 0);
                        if (nextCost <= k) {
                            dp[i][j + 1][nextCost] = Math.max(dp[i][j + 1][nextCost], dp[i][j][c] + val);
                        }
                    }

                    // Try moving Down
                    if (i + 1 < m) {
                        int val = grid[i + 1][j];
                        int nextCost = c + (val > 0 ? 1 : 0);
                        if (nextCost <= k) {
                            dp[i + 1][j][nextCost] = Math.max(dp[i + 1][j][nextCost], dp[i][j][c] + val);
                        }
                    }
                }
            }
        }

        int maxScore = -1;
        for (int c = 0; c <= k; c++) {
            maxScore = Math.max(maxScore, dp[m - 1][n - 1][c]);
        }

        return maxScore;
    }
}