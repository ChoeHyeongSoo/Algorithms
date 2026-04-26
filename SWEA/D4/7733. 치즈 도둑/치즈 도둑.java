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
            n = Integer.parseInt(st.nextToken());
            cheese = new int[n+2][n+2];

            int max_day = 0;
            for (int i = 1; i <= n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 1; j <= n; j++) {
                    cheese[i][j] = Integer.parseInt(st.nextToken());
                    if (max_day < cheese[i][j]) max_day = cheese[i][j];
                }
            }

            // t=1부터 그룹 카운팅
            int max_cnt = 1;
            visit = new int[n+2][n+2];
            for (int day = 1; day < max_day; day++) {
                int curr = 0;
                for (int i = 1; i <= n; i++) {
                    for (int j = 1; j <= n; j++) {
                        if (visit[i][j] == day || cheese[i][j] <= day) continue;
                        bfs(i, j, day);
                        curr++;
                    }
                }
                max_cnt = Math.max(max_cnt, curr);
            }

            ans.append(max_cnt).append('\n');
        }
        System.out.print(ans);
    }
    static int n;
    static int[][] cheese, visit, dir = {{1, 0, -1, 0}, {0, 1, 0, -1}};
    static Deque<Integer> q = new ArrayDeque<>();

    public static void bfs(int r, int c, int day) {

        visit[r][c] = day;
        q.offer(r*(n+2)+c);

        while (!q.isEmpty()) {

            int curr = q.poll();

            for (int d = 0; d < 4; d++) {

                int nr = curr / (n+2) + dir[0][d];
                int nc = curr % (n+2) + dir[1][d];

                if (visit[nr][nc] == day) continue;
                if (cheese[nr][nc] <= day) continue;

                visit[nr][nc] = day;
                q.offer(nr * (n+2) + nc);
            }
        }
    }
}