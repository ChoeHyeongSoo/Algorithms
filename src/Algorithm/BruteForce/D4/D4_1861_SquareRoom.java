package Algorithm.BruteForce.D4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class D4_1861_SquareRoom {
    /*
    N2개의 방이 N×N형태로 늘어서 있다.
    위에서 i번째 줄의 왼쪽에서 j번째 방에는 1이상 N2 이하의 수 Ai,j가 적혀 있으며, 이 숫자는 모든 방에 대해 서로 다르다.
    당신이 어떤 방에 있다면, 상하좌우에 있는 다른 방으로 이동할 수 있다.
    물론 이동하려는 방이 존재해야 하고, 이동하려는 방에 적힌 숫자가 현재 방에 적힌 숫자보다 정확히 1 더 커야 한다.
    처음 어떤 수가 적힌 방에서 있어야 가장 많은 개수의 방을 이동할 수 있는지 구하는 프로그램을 작성하라.
     */
    public static void main(String args[]) throws Exception {
        StringBuilder ans = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            ans.append("#").append(test_case).append(" ");
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            room = new int[n][n];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++)
                    room[i][j] = Integer.parseInt(st.nextToken());
            }

            max_move[0] = n*n; max_move[1] = 0;  // 0: room no. / 1: movements
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (room[i][j] == n*n) continue;
                    move(i, j, n);
                }
            }
//            if (max_move[1]==0) max_move[0] = 1;
            ans.append(max_move[0]).append(" ").append(max_move[1]).append('\n');
        }
        System.out.print(ans);
    }

    static int[][] room;
    static int[] dir_r = {1, 0, -1, 0}, dir_c = {0, 1, 0, -1}, max_move = new int[2];

    private static void move(int r, int c, int n) {

        int cnt = 1, curr = room[r][c];
        int curr_r = r;
        int curr_c = c;
        boolean pass = true;

        while (pass) {

            pass = false;
            for (int dir = 0; dir < 4; dir++) {

                int next_r = curr_r + dir_r[dir];
                int next_c = curr_c + dir_c[dir];

                if (next_r >= 0 && next_r < n && next_c >= 0 && next_c < n) {
                    if (room[next_r][next_c] == curr + 1) {
                        curr_r = next_r; curr_c = next_c; curr = room[next_r][next_c];
                        cnt++; pass = true; break;}
                }
            }

        }

        if (cnt > max_move[1]) {max_move[0] = room[r][c]; max_move[1] = cnt;}
        if (cnt==max_move[1])
            if (room[r][c] < max_move[0]) {max_move[0] = room[r][c]; max_move[1] = cnt;}
    }
}