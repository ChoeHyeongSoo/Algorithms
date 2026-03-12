import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Solution {
    public static void main(String args[]) throws Exception {
        StringBuilder ans = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            ans.append("#").append(test_case).append(" ");
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int R = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            int L = Integer.parseInt(st.nextToken());
            map = new int[n][m];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < m; j++)
                    map[i][j] = Integer.parseInt(st.nextToken());
            }

            boolean[][] v = new boolean[n][m];
            Deque<position> q = new ArrayDeque<>();
            Deque<position> next = new ArrayDeque<>();
            q.offer(new position(R, C, map[R][C]));
            v[R][C] = true;
            int cnt = 1;

            for (int i = 1; i < L; i++) {
                while (!q.isEmpty()) {
                    position curr = q.poll();
                    // 방향은 dir[tunnel].length 동안 dir[tunnel][d]로 반복
                    for (int d = 0; d < dir_r[curr.tunnel].length; d++) {
                        int r = curr.r + dir_r[curr.tunnel][d];
                        int c = curr.c + dir_c[curr.tunnel][d];

                        if (r >= 0 && r < n && c >= 0 && c < m) {
                            if (v[r][c]||map[r][c]==0) continue; // 터널 연결 파악 필요
                            if (!is_connected_s(r, c, curr.tunnel, d)) continue;
                            v[r][c] = true; cnt++;
                            next.offer(new position(r,c,map[r][c]));
                        }
                    }
                }

                while(!next.isEmpty()) q.offer(next.poll());
            }

            ans.append(cnt).append('\n');
        }
        System.out.print(ans);
    }

    static int[][] dir_r = {{0}, {1, -1, 0, 0}, {1, -1}, {0, 0}, {-1, 0}, {1, 0}, {1, 0}, {-1, 0}};
    static int[][] dir_c = {{0}, {0, 0, 1, -1}, {0, 0}, {1, -1}, {0, 1}, {0, 1}, {0, -1}, {0, -1}};
    static int[][] map;

    public static boolean is_connected_s(int r, int c, int curr, int dir) {

        int next = map[r][c];
        int case1 = dir_r[curr][dir],  case2 = dir_c[curr][dir];

        if (case1!=0) {
            for (int d_r : dir_r[next])
                if (d_r == case1 * -1) return true;
        }
        else {
            for (int d_c : dir_c[next])
                if (d_c == case2 * -1) return true;
        }

        return false;
    }
}

class position{
    int r, c, tunnel;

    public position(int r, int c, int tunnel) {
        this.r = r;
        this.c = c;
        this.tunnel = tunnel;
    }
}