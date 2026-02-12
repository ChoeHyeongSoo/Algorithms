package Algorithm.BackTracking;

import java.io.FileInputStream;
import java.util.*;

class D4_4613_RussianFlag_DFS_n_PrefixSum {
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
        System.setIn(new FileInputStream("Algorithm/BackTracking/input_D4_4613_RussianFlag_DFS_n_PrefixSum.txt"));

        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {
            int n = sc.nextInt();
            int m = sc.nextInt();   // 각 줄의 길이
            String[] flag = new String[n];
            for (int i = 0; i < n; i++) {
                flag[i] = sc.next();
            }

            int cnt = 0;
            // 1. row 0 : W & row n-1 : R - 필수조건
            for (int i = 0; i < m; i++) {
                if (flag[0].charAt(i) != 'W') cnt++;
                if (flag[n - 1].charAt(i) != 'R') cnt++;
            }

            // 2. 최소가 될 n-2개 열 채우기 : 0과 연속되는 라인은 W, B 가능 / n-1과 연속되는 라인은 R, B 가능
            // 계산 편의를 위한 누적합 배열
            int[][] paint = prefix_sum(n, m, flag);

            // 1) 하양 끝, 파랑 끝 인덱스를 설정해두고 영역별로 비용 계산 - 최저 비교하며 찾기 (완전탐색)
            int decision1 = Integer.MAX_VALUE;
            for (int white_end = 0; white_end <= n - 3; white_end++) {
                for (int blue_end = white_end + 1; blue_end <= n - 2; blue_end++) {
                    int tmp = 0;
                    tmp += (white_end)*m - (paint[white_end][0]);   // 하양 영역 비용
                    tmp += (blue_end-white_end)*m - (paint[blue_end][1] - paint[white_end][1]); // 파랑 영역 비용
                    tmp += (n-2 - blue_end)*m - (paint[n-2][2] - paint[blue_end][2]);   // 빨강 영역 비용
                    decision1 = Math.min(decision1, tmp);
                }
            }

            int decision2 = Integer.MAX_VALUE;
            decision2 = recursive(n,m,decision2,0, 0, 0, paint);

            int ans1 = cnt + decision1;
            int ans2 = cnt + decision2;
            System.out.println("#" + test_case + " " + ans1 + " == " + ans2);
        }

        // recursive func
        // 둘째줄부터 n-3까지 누적합 기록. 하양<파랑이면 파랑 기록 쭉 파랑은 최소 1 보장. 하양일 경우 wht_idx 기록
        // wht_idx+1 >red_idx라면 두 idx중 blue 많은 쪽을 채우기, 두 idx는 초기값보다 큰 경우에만! (각 최대 최소를 최소 1칸차이로 보장)
        // 스택에 값 넣어두고 꼭 재귀로 구현하지 않아도 되고, 깊이로 들어가긴 해야 한다. 스택에 누적값 꺼내서 비교하고 다시 저장, 상태 플래그로 현재 컬러 저장
    }

    // 2) 재귀함수 구현 (dfs) : 바닥까지 비용을 누적해가며 찍고 비교한 뒤 리턴
    private static int recursive(int n, int m, int decision, int tmp, int row, int color, int[][] paint) {

        if (tmp >= decision) return decision;

        if (row > 0) {  // 0을 따로 처리했으므로 조건 추가해야 한다
            int cost = m - (paint[row][color] - paint[row - 1][color]);
            tmp += cost;
        }

        if (row == n-2){
            if (color >= 1) return Math.min(decision, tmp);
            return decision;
        }

        // 첫 재귀 시작 1로 넣어서 W-B-R-R B진입 자체를 못함
        // 누적합 그냥 쓰려고 하니까 1부터 넣게 돼서 그럼 - 그냥 각 줄 코스트만 카운트 해뒀으면 실수 x

        if (color==0) {
            if (row <= n-3)
                decision = recursive(n, m, decision, tmp, row+1, 0, paint);
            decision = recursive(n, m, decision, tmp, row+1, 1, paint);
        }
        else if (color == 1){
            decision = recursive(n, m, decision, tmp, row+1, 1, paint);
            decision = recursive(n, m, decision, tmp, row+1, 2, paint);
        }
        else if (color == 2){
            decision = recursive(n, m, decision, tmp, row+1, 2, paint);
        }

        return decision;
    }

    private static int[][] prefix_sum(int n, int m, String[] flag) {

        int[][] color_state = new int[n][3];    // 0: W, 1: B, 2: R

        for (int i = 1; i <= n - 2; i++) {
            int wht = 0, blue = 0, red = 0;
            for (int j = 0; j < m; j++) {
                char color = flag[i].charAt(j);     // 각 열의 상태 조사
                switch (color) {
                    case 'W':
                        if (i < n - 2) {
                            wht++;
                            color_state[i][0]++;
                        }
                        break;
                    case 'B':
                        blue++;
                        color_state[i][1]++;
                        break;
                    case 'R':
                        if (i > 1) {
                            red++;
                            color_state[i][2]++;
                        }
                        break;
                }
            }
            color_state[i][0] += color_state[i - 1][0];
            color_state[i][1] += color_state[i - 1][1];
            color_state[i][2] += color_state[i - 1][2];
        }
        return color_state;
    }

}

