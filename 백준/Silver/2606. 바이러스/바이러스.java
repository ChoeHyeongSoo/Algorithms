import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(br.readLine());
        List<Integer>[] adj = new List[v+1];
        for (int i = 1; i <= v; i++) adj[i] = new ArrayList<>();
        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int me = Integer.parseInt(st.nextToken());
            int you = Integer.parseInt(st.nextToken());
            adj[me].add(you); adj[you].add(me);
        }
        boolean[] visit = new boolean[v+1];
        int cnt = 0; visit[1] = true;
        Deque<Integer> q = new ArrayDeque<>();
        q.add(1);
        while (!q.isEmpty()) {
            int curr = q.poll();
            if (adj[curr].isEmpty()) continue;

            for (int next : adj[curr]) {
                if (visit[next]) continue;
                cnt++; visit[next] = true;
                q.offer(next);
            }
        }
        System.out.println(cnt);
    }
    static int v;
}