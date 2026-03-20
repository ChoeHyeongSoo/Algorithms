import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        adj = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) adj[i][j] = Integer.parseInt(st.nextToken());
        }

        sb = new StringBuilder();
        for (int s = 0; s < n; s++) {
            boolean[] check = new boolean[n];
            dfs(s, check);
            for (boolean k : check) sb.append(k? 1 : 0).append(" ");
            sb.append('\n');
        }

        System.out.println(sb);
    }
    static int n;
    static int[][] adj;
    static StringBuilder sb;

    public static void dfs(int i, boolean[] r) {

        for (int next = 0; next < n; next++) {
            if (r[next] || adj[i][next]==0) continue;
            r[next] = true;
            dfs(next, r);
        }
    }
}