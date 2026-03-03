package Algorithm.Simulation;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

class SW_Test_A_MicroIsolation { // 2382
    /*
    정사각형 구역 안에 K개의 미생물 군집이 있다. 이 구역은 가로 N개, 세로 N개, 총 N * N 개의 동일한 크기의 정사각형 셀들로 이루어져 있다.
    미생물들이 구역을 벗어나는걸 방지하기 위해, 가장 바깥쪽 가장자리 부분에 위치한 셀들에는 특수한 약품이 칠해져 있다.
    ① 최초 각 미생물 군집의 위치와 군집 내 미생물의 수, 이동 방향이 주어진다. 약품이 칠해진 부분에는 미생물이 배치되어 있지 않다. 이동방향은 상, 하, 좌, 우 네 방향 중 하나이다.
    ② 각 군집들은 1시간마다 이동방향에 있는 다음 셀로 이동한다.
    ③ 미생물 군집이 이동 후 약품이 칠해진 셀에 도착하면 군집 내 미생물의 절반이 죽고, 이동방향이 반대로 바뀐다.
       미생물 수가 홀수인 경우 반으로 나누어 떨어지지 않으므로, 다음과 같이 정의한다.
       살아남은 미생물 수 = 원래 미생물 수를 2로 나눈 후 소수점 이하를 버림 한 값
       따라서 군집에 미생물이 한 마리 있는 경우 살아남은 미생물 수가 0이 되기 때문에, 군집이 사라지게 된다,
    ④ 이동 후 두 개 이상의 군집이 한 셀에 모이는 경우 군집들이 합쳐지게 된다.
       합쳐 진 군집의 미생물 수는 군집들의 미생물 수의 합이며, 이동 방향은 군집들 중 미생물 수가 가장 많은 군집의 이동방향이 된다.
       합쳐지는 군집의 미생물 수가 같은 경우는 주어지지 않으므로 고려하지 않아도 된다.
    M 시간 동안 이 미생물 군집들을 격리하였다. M시간 후 남아 있는 미생물 수의 총합을 구하여라.
     */
    public static void main(String args[]) throws Exception {
        System.setIn(new FileInputStream("res/Algorithm/Simulation/input_SW_Test_MicroIsolation.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());   // 군집 수

            micro_crowds = new micro_crowd[k];

            for (int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine());
                int input_r = Integer.parseInt(st.nextToken()), input_c = Integer.parseInt(st.nextToken());
                int input_cnt = Integer.parseInt(st.nextToken()), input_dir = Integer.parseInt(st.nextToken());
                micro_crowd c = new micro_crowd(input_r, input_c, input_cnt, input_dir, i);
                micro_crowds[i] = c;
            }

            for (int i = 0; i < m; i++) // M 시간 이후
                action(n);

            int ans = 0;
            for (micro_crowd curr : micro_crowds) {
                if (curr == null) continue;
                ans += curr.number;
            }
            System.out.println("#" + test_case + " " + ans);
        }
    }
    static micro_crowd[] micro_crowds;
    // 군집 이동/시간 - cell[0 or N][~], cell[~][0 or N] -> 군집 수 /= 2 , 방향 반전 ((dir+2)%4)
    // cond2) 군집 결합 - 수 많은 쪽의 dir로 this.dir 변경
    public static void action(int n) {
        int[] dr = {0, -1, 1, 0, 0}, dc = {0, 0, 0, -1, 1};   // dir+2)%4 update
        Map<Integer, List<Integer>> next_state = new HashMap<>();
        // 1. 다음 위치 탐색
        for (int i = 0; i < micro_crowds.length; i++) {
            if (micro_crowds[i]==null) continue;
            micro_crowd tmp = micro_crowds[i];
            tmp.r = tmp.r + dr[tmp.dir];
            tmp.c = tmp.c + dc[tmp.dir];
            if (tmp.r == 0 || tmp.r == n - 1 ||
                    tmp.c == 0 || tmp.c == n - 1) {      // 약품 도달 시, 방향 전환
                tmp.dir = change_dir(tmp.dir);
                tmp.number /= 2;
                if (tmp.number==0) {
                    micro_crowds[i] = null; // 군집 소멸
                    continue;}
            }
            // 2. 충돌 동작 : 2개 이상의 충돌 고려해야 한다. : 저장없이 스위칭하면 케이스 실패
            // 2.1) v 자료구조 선택 / 2.2) 충돌한 거 처리하는 함수
            int p = tmp.r * n + tmp.c; // *1차원 맵핑 : 배열은 객체이므로 저장하기 어렵다. 1차원 맵필으로 map 혹은 set 활용
            if (next_state.get(p)==null) next_state.put(p, new ArrayList<>());
            next_state.get(p).add(tmp.no);
        }
        for (List<Integer> check : next_state.values()) {
            if (check.size()==1) continue;
            merge_state(check);
        }
    }

    private static void merge_state(List<Integer> check) {

        int max = 0, idx = 0, sum = 0;
        for (int tmp : check) {
            sum+=micro_crowds[tmp].number;
            if (micro_crowds[tmp].number > max) {
                max = micro_crowds[tmp].number;
                idx = micro_crowds[tmp].no;
            }
        }
        for (int tmp : check) {
            if (tmp!=idx) micro_crowds[tmp]=null;
            else micro_crowds[tmp].number = sum;
        }
    }

    public static int change_dir(int dir) {
        switch (dir) {
            case 1:
                return 2;
            case 2:
                return 1;
            case 3:
                return 4;
            case 4:
                return 3;
        }
        return 0;
    }
}

class micro_crowd {
    int r, c, number, dir, no;
    public micro_crowd(int r, int c, int number, int dir, int no) {
        this.r = r;
        this.c = c;
        this.number = number;
        this.dir = dir;
        this.no = no;
    }
}