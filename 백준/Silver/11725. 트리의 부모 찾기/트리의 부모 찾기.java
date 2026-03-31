import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        List<Integer>[] adj = new ArrayList[n+1];
        for (int i = 1; i <= n; i++) adj[i] = new ArrayList<>();
        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken());
            adj[a].add(b); adj[b].add(a);
        }
        int[] parents = new int[n+1];
        boolean[] visit = new boolean[n+1];

        Queue<Integer> q = new ArrayDeque<>();
        q.add(1); visit[1] = true;
        while (!q.isEmpty()) {
            int curr = q.poll();

            for (int next : adj[curr]) {
                if (visit[next]) continue;
                parents[next] = curr;
                visit[next] = true;
                q.offer(next);
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 2; i <= n; i++) sb.append(parents[i]).append('\n');
        System.out.println(sb);
    }
    static int n;
}