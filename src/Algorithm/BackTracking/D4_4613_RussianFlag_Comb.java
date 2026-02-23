package Algorithm.BackTracking;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class D4_4613_RussianFlag_Combination {
    /*
    깃발은 N행 M열로 나뉘어 있고, 각 칸은 흰색, 파란색, 빨간색 중 하나로 칠해져 있다.
    당신은 몇 개의 칸에 있는 색을 다시 칠해서 이 깃발을 러시아 국기처럼 만들려고 한다. 다음의 조건을 만족해야 한다.
    - 위에서 몇 줄(한 줄 이상)은 모두 흰색으로 칠해져 있어야 한다.
    - 다음 몇 줄(한 줄 이상)은 모두 파란색으로 칠해져 있어야 한다.
    - 나머지 줄(한 줄 이상)은 모두 빨간색으로 칠해져 있어야 한다.
    [입력]
    - 각 테스트 케이스의 첫 번째 줄에는 두 정수 N,M(3≤N,M≤50)이 공백으로 구분되어 주어진다.
    - 다음 N개의 줄에는 M개의 문자로 이루어진 문자열이 주어진다. i번 째 줄의 j번째 문자는 깃발에서 i번째 행 j번째 열인 칸의 색을 의미한다.
     */
    public static void main(String args[]) throws Exception {

        System.setIn(new FileInputStream("res/Algorithm/BackTracking/input_D4_4613_RussianFlag.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            String[] flag = new String[n];
            for (int i = 0; i < n; i++) {
                flag[i] = br.readLine();
            }

            int cnt = 0;
            // 1. row 0 : W & row n-1 : R - 필수조건
            for (int i = 0; i < m; i++) {
                if (flag[0].charAt(i) != 'W') cnt++;
                if (flag[n - 1].charAt(i) != 'R') cnt++;
            }

            int[][] cost = get_color_cost(n, m, flag);

            min_cost = Integer.MAX_VALUE;
            int[] boundary = new int[2];    // 파랑영역 시작, 빨강영역 시작의 범위를 조합
            combination(0, 1, n, boundary, cost);

            cnt += min_cost;

            System.out.println("#" + test_case + " " + cnt);
        }
    }

    static int min_cost;

    public static void combination(int cnt, int start, int n, int[] boundary, int[][] cost) {

        if (cnt == 2) {
            int tmp = getCosts(boundary, cost);
            if (tmp < min_cost) min_cost = tmp;
            return;
        }

        for (int i = start; i < n; i++) { // init_start > 0
            boundary[cnt] = i;
            combination(cnt + 1, i + 1, n, boundary, cost);
        }
    }

    public static int getCosts(int[] boundary, int[][] cost) {

        int total_cost = 0;

        for (int i = 0; i < cost.length - 1; i++) {
            if (i < boundary[0]) total_cost += cost[i][0];      // 흰색 영역
            else if (i < boundary[1]) total_cost += cost[i][1]; // 파란색 영역
            else total_cost += cost[i][2];                      // 빨간색 영역
        }

        return total_cost;
    }

    private static int[][] get_color_cost(int n, int m, String[] flag) {

        int[][] cost = new int[n][3];    // 0: W, 1: B, 2: R

        for (int i = 1; i <= n - 2; i++) {
            int wht = 0, blue = 0, red = 0;
            for (int j = 0; j < m; j++) {
                char color = flag[i].charAt(j);     // 각 열의 상태 조사
                switch (color) {
                    case 'W':
                        if (i < n - 2)
                            wht++;
                        break;
                    case 'B':
                        blue++;
                        break;
                    case 'R':
                        if (i > 1)
                            red++;
                        break;
                }
            }
            cost[i][0] = m - wht;
            cost[i][1] = m - blue;
            cost[i][2] = m - red;
        }
        return cost;
    }
}

