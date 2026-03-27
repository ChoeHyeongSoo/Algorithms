import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        section = new char[n][n];

        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < n; j++)
                section[i][j] = line.charAt(j);
        }

        v1 = new boolean[n][n];
        v2 = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!v1[i][j]) bfs(i, j, 1, section[i][j]);
                if (!v2[i][j]) bfs(i, j, 2, section[i][j]);
            }
        }
        System.out.println(norm + " " + rg);
    }

    static int n, norm = 0, rg = 0;
    static boolean[][] v1, v2;
    static Deque<Integer> q = new ArrayDeque<>();
    static int[][] dir = {{1, 0, -1, 0}, {0, 1, 0, -1}};
    static char[][] section;

    private static void bfs(int r, int c, int type, char curr) {

        if (type == 1) {
            v1[r][c] = true;
            norm++;
        }
        else {
            v2[r][c] = true;
            rg++;
        }
        q.add(r * n + c);

        while (!q.isEmpty()) {
            int tmp = q.poll();

            for (int i = 0; i < 4; i++) {
                int nr = tmp / n + dir[0][i];
                int nc = tmp % n + dir[1][i];

                if (nr < 0 || nr >= n || nc < 0 || nc >= n) continue;
                if (type == 1) {
                    if (section[nr][nc]!=curr) continue;
                    if (v1[nr][nc]) continue;
                    v1[nr][nc] = true;
                } else {
                    if (v2[nr][nc]) continue;
                    if (curr=='B') {
                        if (section[nr][nc]!=curr) continue;
                    } else {
                        if (section[nr][nc]=='B') continue;
                    }
                    v2[nr][nc] = true;
                }
                q.offer(nr * n + nc);
            }
        }
    }
}