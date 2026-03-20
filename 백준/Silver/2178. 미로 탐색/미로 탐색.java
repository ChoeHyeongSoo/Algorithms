import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main{
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] map = new int[n+2][m+2];
        for (int i = 1; i <= n; i++) {
            String line = br.readLine();
            for (int j = 1; j <= m; j++)
                map[i][j] = line.charAt(j-1) - '0';
        }

        int cnt = 0;
        int[][] dir = {{1, 0, -1, 0}, {0, 1, 0, -1}};
        Deque<int[]> q = new ArrayDeque<>();
        boolean[] v = new boolean[(n+2)*(m+2)];
        q.offer(new int[]{1*(m+2) + 1, 1});
        v[1*(m+2) + 1] = true;

        outer_loop:
        while (!q.isEmpty()) {

            int[] curr = q.poll();

            for (int d = 0; d < 4; d++) {
                int r = curr[0]/(m+2) + dir[0][d];
                int c = curr[0]%(m+2) + dir[1][d];

                if (r==n&&c==m) {cnt = curr[1]+1; break outer_loop;}
                if (map[r][c]==0 || v[r*(m+2)+c]) continue;

                v[r*(m+2)+c] = true;
                q.offer(new int[]{r*(m+2)+c, curr[1]+1});
            }
        }

        System.out.println(cnt);
    }
}