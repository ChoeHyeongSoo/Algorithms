package Algorithm.BackTracking.Combination;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class SW_Test_A_DessertCafe {
    /*
    같이 한 변의 길이가 N인 정사각형 모양을 가진 지역에 디저트 카페가 모여 있다.
    원 안의 숫자는 해당 디저트 카페에서 팔고 있는 디저트의 종류를 의미하고 카페들 사이에는 대각선 방향으로 움직일 수 있는 길들이 있다.
    디저트 카페 투어는 어느 한 카페에서 출발하여 대각선 방향으로 움직이고 사각형 모양을 그리며 출발한 카페로 돌아와야 한다.
    저트 카페 투어를 하는 도중 해당 지역을 벗어나면 안 된다. 또한, 친구들은 같은 종류의 디저트를 다시 먹는 것을 싫어한다.
    즉, 카페 투어 중에 같은 숫자의 디저트를 팔고 있는 카페가 있으면 안 된다.
    디저트를 가장 많이 먹을 수 있는 경로를 찾고, 그 때의 디저트 수를 정답으로 출력하는 프로그램을 작성하라.
     */
    public static void main(String args[]) throws Exception {
        StringBuilder ans = new StringBuilder();
        System.setIn(new FileInputStream("res/input_SW_Test_De.txt"));
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
                // c가 출발점과 같아야만 북서 가능 , c가 출발점보다 낮아야 북동 가능
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