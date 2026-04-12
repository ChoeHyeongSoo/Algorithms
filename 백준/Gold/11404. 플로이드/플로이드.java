import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
public class Main{
    static final int INF = 10000001;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(br.readLine());
        int[][] adj = new int[n+1][n+1];
        for (int i = 1; i <= n; i++) {
            Arrays.fill(adj[i], INF);
            adj[i][i] = 0;
        }
        for (int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken()), to = Integer.parseInt(st.nextToken()), distance = Integer.parseInt(st.nextToken());
            adj[from][to] = Math.min(adj[from][to], distance);
        }
        
        for (int k = 1; k <= n; k++)   // 거치는 경로
            for (int i = 1; i <= n; i++)   // from
                for (int j = 1; j <= n; j++)   // to
                    adj[i][j] = Math.min(adj[i][j], adj[i][k] + adj[k][j]);
        
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++)
                sb.append(adj[i][j]>=INF?0:adj[i][j]).append(" ");
            sb.append('\n');
        }
        System.out.println(sb);
    }
    static int n;
}