import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        adj = new ArrayList[n+1];
        for (int i = 1; i <= n; i++) adj[i] = new ArrayList<>();
        for (int i = 0; i < n-1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()), v = Integer.parseInt(st.nextToken());
            adj[u].add(v); adj[v].add(u);
        }
        depth = new int[n+1];
        max_k =  (int) (Math.log(n) / Math.log(2));
        parent = new int[n+1][max_k+1];
        dfs(1, 0, 1);
        fill();
        int m = Integer.parseInt(br.readLine());
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()), v = Integer.parseInt(st.nextToken());
            ans.append(LCA(u, v)).append('\n');
        }
        System.out.println(ans);
    }

    static int n, max_k;
    static List<Integer>[] adj;
    static int[] depth;
    static int[][] parent;

    static void dfs(int curr, int p, int d) {
        depth[curr] = d;
        parent[curr][0] = p;
        for (int next : adj[curr]) {
            if (next!=p) dfs(next, curr, d+1);
        }
    }

    static void fill() {
        for (int k = 1; k <= max_k; k++)
            for (int i = 1; i <= n; i++)
                parent[i][k] = parent[parent[i][k-1]][k-1];
    }

    static int LCA(int u, int v) {

        if (depth[u] > depth[v]){
            int tmp = u; u = v; v = tmp;
        }

        for (int k = max_k; k >= 0; k--) {
            if (depth[v]-depth[u] >= (1<<k)) v = parent[v][k];
        }

        if (u==v) return u;

        for (int k = max_k; k >= 0; k--) {
            if (parent[u][k] != parent[v][k]) {
                u = parent[u][k];
                v = parent[v][k];
            }
        }
        return parent[u][0];
    }
}