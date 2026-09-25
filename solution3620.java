import java.util.*;

public class solution3620 {

    static boolean check(int limit,
                         List<int[]>[] graph,
                         int[] topo,
                         boolean[] online,
                         long k) {

        int n = graph.length;

        long INF = Long.MAX_VALUE / 4;

        long[] dist = new long[n];
        Arrays.fill(dist, INF);

        dist[0] = 0;

        for (int u : topo) {

            if (dist[u] == INF)
                continue;

            if (u != 0 && u != n - 1 && !online[u])
                continue;

            for (int[] edge : graph[u]) {

                int v = edge[0];
                int w = edge[1];

                if (w < limit)
                    continue;

                if (v != 0 && v != n - 1 && !online[v])
                    continue;

                if (dist[v] > dist[u] + w)
                    dist[v] = dist[u] + w;
            }
        }

        return dist[n - 1] <= k;
    }

    public static int maxScore(int[][] edges, boolean[] online, long k) {

        int n = online.length;

        List<int[]>[] graph = new ArrayList[n];
        int[] indegree = new int[n];

        for (int i = 0; i < n; i++)
            graph[i] = new ArrayList<>();

        int maxEdge = 0;

        for (int[] e : edges) {
            graph[e[0]].add(new int[]{e[1], e[2]});
            indegree[e[1]]++;
            maxEdge = Math.max(maxEdge, e[2]);
        }

        Queue<Integer> q = new LinkedList<>();
        int[] topo = new int[n];
        int idx = 0;

        for (int i = 0; i < n; i++)
            if (indegree[i] == 0)
                q.offer(i);

        while (!q.isEmpty()) {
            int u = q.poll();
            topo[idx++] = u;

            for (int[] edge : graph[u]) {
                if (--indegree[edge[0]] == 0)
                    q.offer(edge[0]);
            }
        }

        int low = 0, high = maxEdge;
        int ans = -1;

        while (low <= high) {

            int mid = low + (high - low) / 2;

            if (check(mid, graph, topo, online, k)) {
                ans = mid;
                low = mid + 1;
            } else
                high = mid - 1;
        }

        return ans;
    }

    public static void main(String[] args) {

        int[][] edges = {
                {0,1,5},
                {1,3,10},
                {0,2,3},
                {2,3,4}
        };

        boolean[] online = {true,true,true,true};

        long k = 10;

        System.out.println(maxScore(edges, online, k));
    }
}
