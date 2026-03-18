import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main{
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        v = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }

        // 1. BFS
        cnt = 0;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                if (!v[i][j]&&map[i][j]==1) BFS(i, j);


        System.out.println(cnt);

    }

    static int[][] map, dir = {{1, 1, 0, -1, -1, -1, 0, 1}, {0, 1, 1, 1, 0, -1, -1, -1}};
    static boolean[][] v;
    static int n, m, cnt;
    static Deque<Integer> q = new ArrayDeque<>();

    public static void BFS(int r, int c) {

        v[r][c] = true;
        q.offer(r*m+c);

        while (!q.isEmpty()) {

            int curr = q.poll();

            r = curr/m;
            c = curr%m;

            for (int i = 0; i < 8; i++) {

                int nr = r + dir[0][i];
                int nc = c + dir[1][i];

                if (nr<0||nr>=n||nc<0||nc>=m) continue;
                if (map[nr][nc]==0||v[nr][nc]) continue;

                v[nr][nc] = true;
                q.offer(nr*m+nc);
            }
        }

        cnt++;
    }
}