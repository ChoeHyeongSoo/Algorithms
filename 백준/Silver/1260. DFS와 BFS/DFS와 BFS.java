import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        v = Integer.parseInt(st.nextToken());
        adj = new ArrayList[n+1];
        for (int i = 1; i <= n; i++) adj[i] = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            adj[x].add(y); adj[y].add(x);
        }

        for (int i = 1; i <= n; i++) Collections.sort(adj[i]);

        sb = new StringBuilder();
        // 1. DFS
        visit_dfs = new boolean[n+1];
        dfs(v);
        sb.append('\n');

        // 2. BFS
        Deque<Integer> q = new ArrayDeque<>();
        boolean[] visit_bfs = new boolean[n+1];
        q.offer(v);
        visit_bfs[v] = true;
        while (!q.isEmpty()) {
            int curr = q.poll();
            sb.append(curr).append(" ");

            for (int next : adj[curr]) {
                if (visit_bfs[next]) continue;
                visit_bfs[next] = true;
                q.offer(next);
            }
        }
        System.out.println(sb);
    }

    static int n, m, v;
    static List<Integer>[] adj;
    static boolean[] visit_dfs;
    static StringBuilder sb;

    public static void dfs(int tmp) {
        visit_dfs[tmp] = true;
        sb.append(tmp + " ");

        for (int next : adj[tmp]) {
            if (visit_dfs[next]) continue;
            visit_dfs[next] = true;
            dfs(next);
        }
    }
}