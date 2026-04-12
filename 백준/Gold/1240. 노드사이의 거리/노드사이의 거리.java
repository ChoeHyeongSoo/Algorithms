import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        adj = new int[n+1][n+1];
        visit = new int[n+1];

        for (int i = 0; i < n-1; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken()), to = Integer.parseInt(st.nextToken()), d = Integer.parseInt(st.nextToken());
            adj[from][to] = d; adj[to][from] = d;
        }

        for (int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken()), to = Integer.parseInt(st.nextToken());
            dfs(i, from, to, 0);
        }
        System.out.println(ans);
    }
    static int n, m;
    static int[][] adj;
    static int[] visit;
    static StringBuilder ans = new StringBuilder();

    static void dfs(int step, int curr, int target, int d) {
        visit[curr] = step;
        if (curr==target) ans.append(d).append('\n');

        for (int i = 1; i <= n; i++) {
            if (adj[curr][i]==0) continue;
            if (visit[i]==step) continue;
            dfs(step, i, target, d+adj[curr][i]);
        }
    }
}