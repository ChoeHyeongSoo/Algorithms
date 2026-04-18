import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        int[][] distances = new int[v+1][v+1];
        for (int i = 1; i <= v; i++) Arrays.fill(distances[i], INF);

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken()), to = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());
            distances[from][to] = dist;
        }

        for (int k = 1; k <= v; k++) {
            for (int i = 1; i <= v; i++) {
                for (int j = 1; j <= v; j++) {
                    distances[i][j] = Math.min(distances[i][j], distances[i][k] + distances[k][j]);
                }
            }
        }

        int ans = INF;
        for (int i = 1; i <= v; i++) ans = Math.min(distances[i][i], ans);
        System.out.println(ans<INF?ans:-1);
    }
    static int v, e;
    static final int INF = 160000000;
}