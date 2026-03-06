import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;
class Solution {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());   // 공사가능 깊이

            int[][] mountain =  new int[n][n];
            int highest = 0;
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    mountain[i][j] = Integer.parseInt(st.nextToken());
                    if (mountain[i][j] > highest) highest = mountain[i][j];
                }
            }

            boolean use = false;
            boolean[][] include = new boolean[n][n];
            longest = 0;
            for (int i = 0; i < n; i++) {           // 가장 높은 봉우리에서 함수 진입 : 그냥 for문으로 구현
                for (int j = 0; j < n; j++) {
                    if (mountain[i][j]==highest) {
                        include[i][j] = true;
                        route(1, i, j, k, mountain, use, include);
                        include[i][j] = false;
                    }
                }
            }
            System.out.println("#" + test_case + " " + longest);
        }
    }

    static int[] dir_r = {1, 0, -1, 0}, dir_c = {0, 1, 0, -1};
    static int longest;
    public static void route(int cnt, int r, int c, int k,  int[][] mountain, boolean use, boolean[][] include) {   // 지나온 루트는 다시 탐색하지 않도록 방문맵 생성? - 방문 처리-복구 및 초기화 중요
        int tmp = mountain[r][c];
        for (int i = 0; i < 4; i++) {
            int next_r = r + dir_r[i], next_c = c + dir_c[i];
            if (next_r < 0 || next_r >= mountain.length || next_c < 0 || next_c >= mountain.length) continue;
            if (include[next_r][next_c]) continue;
            // 현재 위치말고 다음 걸 깎아서, boolean true인 상태로 넘기기
            if (tmp <= mountain[next_r][next_c]) { // 기저 조건: 사방이 자신보다 높다 - 반복 내부에서 다 막히면 자동으로 복귀
                // 높아도 깎는 경우 가능 -> 자신보다 낮게 깎을 수 있으면 깎고, true처리 후에 진입
                if (!use && (tmp > mountain[next_r][next_c] - k)) {
                    int origin = mountain[next_r][next_c];
                    mountain[next_r][next_c] = tmp-1;
                    use = true; include[next_r][next_c] = true;
                    route(cnt + 1, next_r, next_c, k, mountain, use, include);
                    include[next_r][next_c] = false; use = false;
                    mountain[next_r][next_c] = origin;
                }
                if (longest < cnt) longest = cnt;
            }

            else {
                include[next_r][next_c] = true;
                route(cnt + 1, next_r, next_c, k, mountain, use, include);
                include[next_r][next_c] = false;
            }
        }
    }
}