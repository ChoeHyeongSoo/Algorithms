package Algorithm.Simulation.D4;

import java.util.Scanner;

class D4_2117_HomeSecurity {
    /*
    N*N 크기의 도시에 홈방범 서비스를 제공하려고 한다.
    운영 비용은 서비스 영역의 면적과 동일하며, 아래와 같이 구할 수 있다. 운영 영역의 크기 K 는 1 이상의 정수이다.
    운영 비용 = K * K + (K - 1) * (K - 1) (*도시를 벗어난 영역에 서비스를 제공해도 운영 비용은 변경되지 않는다.)
     - K = 1 일 때, 운영 비용은 1 이다.
     - K = 2 일 때, 운영 비용은 5 이다.
     - K = 3 일 때, 운영 비용은 13 이다.
     도시의 크기 N과 하나의 집이 지불할 수 있는 비용 M, 도시의 정보가 주어진다.
     이때, 손해를 보지 않으면서 홈방범 서비스를 가장 많은 집들에 제공하는 서비스 영역을 찾고,
     그 때의 홈방범 서비스를 제공 받는 집들의 수를 출력하는 프로그램을 작성하라.
     */
    public static void main(String args[]) throws Exception {
//        System.setIn(new FileInputStream("res/Algorithm/Simulation/input_D4_2117_HomeSecurity.txt"));

        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {
            int n = sc.nextInt();
            int m = sc.nextInt();   // k^2 + (k-1)^2 <= m*houses

            int[][] city = new int[n][n];
            int total_home = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    city[i][j] = sc.nextInt();
                    if (city[i][j] == 1) total_home++;  // K의 초기값 설정을 위해 카운팅
                }
            }

            // 내림차순 탐색 : K의 최대값은 k^2 + (k-1)^2 = m * home의 해
            int k = (int)((1 + Math.sqrt(1 - 2 * (1 - total_home * m))) / (double) 2);
            int ans = 0;
            area_loop:
            while (k >= 1) {   // k^2 + (k-1)^2 <= m * home 만족하는 home 찾기
                ans = 0;       // * 초기화 위치 실수로 또 또 또 디버깅 지옥 : 루프 사용할 땐, 변수 초기화 위치 반드시 생각할 것!
                int cost = k * k + (k - 1) * (k - 1);
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        int home = 0;
                        if (city[i][j] == 1) home++;

                        int range = k - 1;
                        while (range > 0) {
                            // 0+-range,0 || 0, 0+-range : |x| + |y| <= k-1를 만족하는 (x, y) - K-1 >= 1까지 4*(k-1)회 반복
                            for (int dir = 0; dir < range; dir++) {     //|x| + |y| = k-1 & 부호와 값 전부 다르게
                                int[] dr = {range - dir, -dir, -(range - dir), dir}, dc = {dir, range - dir, -dir, -(range - dir)};

                                for (int each = 0; each < 4; each++) {
                                    // define i+dr, j+dc range cond
                                    if (i + dr[each] < n && i + dr[each] >= 0)
                                        if (j + dc[each] < n && j + dc[each] >= 0)
                                            if (city[i + dr[each]][j + dc[each]] == 1) home++;
                                }
                            }
                            range--;
                        }
                        ans = Math.max(ans, home);  // 최대의 m을 찾아줘야 한다. - total_home을 활용할 수 있을까?
                    }
                }

                if (cost <= m * ans) break area_loop;
                k--;
            }

            System.out.println("#" + test_case + " " + ans);
        }

    }
}
