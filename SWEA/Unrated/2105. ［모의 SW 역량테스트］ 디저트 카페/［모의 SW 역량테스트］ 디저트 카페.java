import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Solution {
    public static void main(String args[]) throws Exception {
        StringBuilder ans = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            ans.append("#").append(test_case).append(" ");
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            map = new int[n+2][n+2];

            for (int i = 1; i <= n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 1; j <= n; j++)
                    map[i][j] = Integer.parseInt(st.nextToken());
            }

            max = -1;
            v = new boolean[n+2][n+2];
            menu = new boolean[101];
            for (int i = 1; i <= n-2; i++) {  // 동남으로만 출발
                for (int j = 1; j <= n; j++) {
                    int init = map[i][j];
                    if (map[i+dir_r[0]][j+dir_c[0]]==0 || map[i+dir_r[0]][j+dir_c[0]]==init) continue;
                    dfs(i, j, i, j, 0, 1);
                }
            }

            ans.append(max).append('\n');
        }
        System.out.print(ans);
    }

    static int[][] map;
    static int[] dir_r = {1, 1, -1, -1}, dir_c = {1, -1, -1, 1}; // 동남 / 동서 / 북서 / 북동
    static int max;
    static boolean[][] v;
    static boolean[] menu;

    public static void dfs(int cond_r, int cond_c, int r, int c, int dir, int cnt) {

        v[r][c] = true;
        menu[map[r][c]] = true;

        for (int d = dir; d <= dir+1 && d < 4; d++) {
            int next_r = r + dir_r[d];
            int next_c = c + dir_c[d];

            if (next_r==cond_r && next_c==cond_c) {
                if (d>=2) max = Math.max(max, cnt);
                continue;
            }

            if (map[next_r][next_c]==0 || v[next_r][next_c]) continue;
            if (menu[map[next_r][next_c]]) continue;

            dfs(cond_r, cond_c, next_r, next_c, d, cnt+1);
        }

        v[r][c] = false;
        menu[map[r][c]] = false;
    }
}