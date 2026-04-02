import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;
public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        adj = new int[n+1][n+1];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken());
            adj[a][b] = 1; adj[b][a] = 1;
        }
        int min = 5001, idx = 0;
        for (int i = 1; i <= n; i++) {
            int curr = bfs(i);
            if (min > curr) {
                min = curr; idx = i;
            }
        }
        System.out.println(idx);
    }
    static int n;
    static int[][] adj;
    static Deque<int[]> q = new ArrayDeque<>();
    static int bfs(int idx) {

        boolean[] visit = new boolean[n+1];
        visit[idx] = true;
        q.offer(new int[]{idx, 1});
        int sum = 0;
        while (!q.isEmpty()) {

            int curr[] = q.poll();

            for (int i = 1; i <= n; i++) {
                if (visit[i]) continue;
                if (adj[curr[0]][i]==0) continue;
                visit[i] = true;
                q.offer(new int[]{i, curr[1]+1});
                sum+=curr[1];
            }
        }
        return sum;
    }
}