import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] map = new int[n][m];
        int init = 0;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j]==2) init = i*m+j;
            }
        }

        int[][] v = new int[n][m], dir = {{1, 0, -1, 0}, {0, 1, 0, -1}};
        for (int[] r : v) Arrays.fill(r,-1);
        Deque<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{init, 0});
        v[init/m][init%m] = 0;
        while (!q.isEmpty()) {

            int[] curr = q.poll();

            for (int d = 0; d < 4; d++) {
                int r = curr[0]/m + dir[0][d];
                int c = curr[0]%m + dir[1][d];

                if (r<0 || r >= n || c<0 || c>=m) continue;
                if (v[r][c]>-1 || map[r][c]==0) continue;

                v[r][c] = curr[1]+1;
                q.offer(new int[]{r*m+c, curr[1]+1});
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++)
                sb.append(v[i][j] == -1 ? (map[i][j] != 0 ? -1 : 0) : v[i][j]).append(" ");
            sb.append('\n');
        }
        System.out.println(sb);
    }
    static int n;
}