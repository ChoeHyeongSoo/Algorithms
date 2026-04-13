import java.net.*;
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int v = Integer.parseInt(st.nextToken()), e = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken()), x = Integer.parseInt(st.nextToken());

        List<Integer>[] adj = new ArrayList[v+1];
        for (int i = 1; i <= v; i++) adj[i] = new ArrayList<>();

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken()), to = Integer.parseInt(st.nextToken());
            adj[from].add(to);
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b)->a[1]-b[1]);
        int[] dist = new int[v+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[x] = 0;
        pq.offer(new int[]{x, 0});

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();

            if (dist[curr[0]] < curr[1]) continue;

            for (int next : adj[curr[0]]) {
                if (dist[next] <= dist[curr[0]]+1) continue;
                dist[next] = dist[curr[0]]+1;
                pq.offer(new int[]{next, dist[next]});
            }
        }

        StringBuilder ans = new StringBuilder();
        for (int i = 1; i <= v; i++) if (dist[i]==k) ans.append(i).append('\n');
        System.out.println(ans.toString().isEmpty() ? -1 : ans);
    }
}
