import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());    // vertex
        m = Integer.parseInt(br.readLine());    // edge
        adj = new ArrayList[n+1];
        for (int i = 1; i <= n; i++) adj[i] = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken()), to = Integer.parseInt(st.nextToken()), weight = Integer.parseInt(st.nextToken());
            adj[from].add(new int[]{to, weight}); adj[to].add(new int[]{from, weight});
        }

        int mst_weight = 0, connection = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
        boolean[] visit = new boolean[n+1]; pq.offer(new int[]{1, 0});
        while (!pq.isEmpty()) {
            int[] curr = pq.poll(); // 0: Index, 1: Weight

            if (visit[curr[0]]) continue;

            visit[curr[0]] = true;
            mst_weight+=curr[1]; connection++;

            if (connection==n) break; // 노드 수만큼 연결되면 종료

            for (int[] next : adj[curr[0]])
                if (!visit[next[0]]) pq.offer(next); // 다익스트라와 달리 가중치 누적해서 입력 x
        }
        System.out.println(mst_weight);
    }
    static int n, m;
    static  List<int[]>[] adj;
}