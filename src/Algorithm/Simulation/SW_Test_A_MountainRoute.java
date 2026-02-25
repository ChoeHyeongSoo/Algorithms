package Algorithm.Simulation;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class SW_Test_A_MountainRoute {
    /*
    등산로를 만들기 위한 부지는 N * N 크기를 가지고 있으며, 이곳에 최대한 긴 등산로를 만들 계획이다.
    등산로 부지는 아래 [Fig. 1]과 같이 숫자가 표시된 지도로 주어지며, 각 숫자는 지형의 높이를 나타낸다.
    등산로를 만드는 규칙은 다음과 같다.
    ① 등산로는 가장 높은 봉우리에서 시작해야 한다.
    ② 등산로는 산으로 올라갈 수 있도록 반드시 높은 지형에서 낮은 지형으로 가로 또는 세로 방향으로 연결이 되어야 한다.
    즉, 높이가 같은 곳 혹은 낮은 지형이나, 대각선 방향의 연결은 불가능하다.
    ③ 긴 등산로를 만들기 위해 딱 한 곳을 정해서 최대 K 깊이만큼 지형을 깎는 공사를 할 수 있다.
    N * N 크기의 지도가 주어지고, 최대 공사 가능 깊이 K가 주어진다. 이때 만들 수 있는 가장 긴 등산로를 찾아 그 길이를 출력하는 프로그램을 작성하라.
    */
    public static void main(String args[]) throws Exception {
        System.setIn(new FileInputStream("res/Algorithm/Simulation/input_SW_A_MountainRoute.txt"));
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

            // 가장 높은 봉우리에서 함수 진입 : 그냥 for문으로 구현
            boolean use = false;
            boolean[][] include = new boolean[n][n];
            longest = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (mountain[i][j]==highest) {
                        route(1, i, j, k, mountain, use, include);
                    }
                }
            }

            System.out.println("#" + test_case + " " + longest);
        }
    }

    static int[] dir_r = {1, 0, -1, 0}, dir_c = {0, 1, 0, -1};
    static int longest;

    public static void route(int cnt, int r, int c, int k,  int[][] mountain, boolean use, boolean[][] include) {   // 지나온 루트는 다시 탐색하지 않도록 방문맵 생성? - 방문 처리-복구 및 초기화 중요
        include[r][c] = true;
        int tmp = mountain[r][c];
        for (int i = 0; i < 4; i++) {   // 기저 조건: 사방이 자신보다 높다 - 반복 내부에서 다 막히면 자동으로 복귀
            int next_r = r + dir_r[i], next_c = c + dir_c[i];

            if (next_r < 0 || next_r >= mountain.length || next_c < 0 || next_c >= mountain.length) continue;
            if (include[next_r][next_c]) continue;
            if (tmp <= mountain[next_r][next_c]) {
                if (!use && (tmp > mountain[next_r][next_c] - k)) { // 높아도 깎는 경우 가능: 자신보다 낮게 깎을 수 있으면 깎고, true처리 후 recurse
                    int origin = mountain[next_r][next_c];
                    mountain[next_r][next_c] = tmp-1;       // 현재 위치말고 다음 걸 깎아서, boolean true인 상태로 넘기기
                    use = true;
                    route(cnt + 1, next_r, next_c, k, mountain, use, include);
                    use = false;
                    mountain[next_r][next_c] = origin;
                }
                if (longest < cnt) longest = cnt;
            } else
                route(cnt + 1, next_r, next_c, k, mountain, use, include);
        }
        include[r][c] = false;
    }
    // ***use bit 복구, include 처리 및 복구 위치 설정
}