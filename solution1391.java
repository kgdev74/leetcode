import java.util.*;

class solution1391 {
    public boolean hasValidPath(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int[][][] dirs = {
            {},
            {{0, -1}, {0, 1}},    
            {{-1, 0}, {1, 0}},     
            {{0, -1}, {1, 0}},     
            {{0, 1}, {1, 0}},       
            
            {{0, -1}, {-1, 0}},    
            {{0, 1}, {-1, 0}}      
        };

        Map<String, String> opposite = new HashMap<>();
        opposite.put("0,-1", "0,1");
        opposite.put("0,1", "0,-1");
        opposite.put("-1,0", "1,0");
        opposite.put("1,0", "-1,0");

        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[m][n];

        queue.offer(new int[]{0, 0});
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int x = curr[0], y = curr[1];

            if (x == m - 1 && y == n - 1) return true;

            for (int[] d : dirs[grid[x][y]]) {
                int nx = x + d[0];
                int ny = y + d[1];

                if (nx < 0 || ny < 0 || nx >= m || ny >= n || visited[nx][ny]) {
                    continue;
                }

                String back = opposite.get(d[0] + "," + d[1]);

                for (int[] nd : dirs[grid[nx][ny]]) {
                    if ((nd[0] + "," + nd[1]).equals(back)) {
                        visited[nx][ny] = true;
                        queue.offer(new int[]{nx, ny});
                        break;
                    }
                }
            }
        }

        return false;
    }
}