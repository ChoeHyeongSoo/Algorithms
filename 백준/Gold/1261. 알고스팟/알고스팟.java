import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;
public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        int[][] grid = new int[n+1][m+1];
        for (int i = 1; i <= n; i++) {
            String line = br.readLine();
            for (int j = 1; j <= m; j++) grid[i][j] = line.charAt(j-1) - '0';
        }

        Deque<int[]> dq = new ArrayDeque<>();
        boolean[][] visited = new boolean[n+2][m+2];
        visited[1][1] = true; dq.offer(new int[]{1, 1, 0});

        while(!dq.isEmpty()) {

            int[] curr = dq.poll();

            if (curr[0] == n && curr[1] == m) {
                System.out.println(curr[2]);
                break;
            }

            for (int d = 0; d < 4; d++) {
                int r = curr[0] + dir[0][d];
                int c = curr[1] + dir[1][d];

                if (r<=0 || r>n || c<=0 || c>m) continue;
                if (visited[r][c]) continue;
                visited[r][c] = true;
                if (grid[r][c]==0) dq.addFirst(new int[]{r, c, curr[2]});   // 0-1 최단거리 
                else dq.addLast(new int[]{r, c, curr[2]+1});
            }
        }
    }
    static int m, n;
    static int[][] dir = {{1, 0, -1, 0}, {0, 1, 0, -1}};
}