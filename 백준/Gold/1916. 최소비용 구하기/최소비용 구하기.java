import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.temporal.ValueRange;
import java.util.*;

public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(br.readLine());
        List<int[]>[] edges = new ArrayList[n+1];
        for (int i = 1; i <= n; i++) edges[i] = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()), v = Integer.parseInt(st.nextToken()), d = Integer.parseInt(st.nextToken());
            edges[u].add(new int[]{v, d});
        }
        st = new StringTokenizer(br.readLine());
        int s = Integer.parseInt(st.nextToken()), e = Integer.parseInt(st.nextToken());
        int[] dist = new int[n+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b)->a[1]-b[1]);
        dist[s] = 0; pq.offer(new int[]{s, 0});

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();

            if (curr[0]==e) break;

            if (dist[curr[0]] < curr[1]) continue; // 특수한 상황에서 현재까지 최단 경로로 온 게 여러 개면 모든 가능성 검토

            for (int[] next : edges[curr[0]]) {

                if (dist[next[0]] <= dist[curr[0]]+next[1]) continue; // next까지 가는 거리 중 최소가 되는 건 어느 경로든 상관 x
                dist[next[0]] = dist[curr[0]]+next[1];

                pq.offer(new int[]{next[0], dist[next[0]]});
            }
        }
        System.out.println(dist[e]);
    }
    static int n, m;
}