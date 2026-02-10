package Algorithm.Implementation.D3;

import java.util.Scanner;

class D3_11315_Ohmok {    // cnt로 풀 것 !! 대신, RangeofIndex 조심

    public static void main(String args[]) throws Exception {

        //  System.setIn(new FileInputStream("res/input_D3_SW_8th_Cipher.txt"));

        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {

            int n = sc.nextInt();
            char[][] ohmok = new char[n][n];
            String target = "ooooo";
            int row_cond = 0;
            for (int i = 0; i < n; i++) {
                String line = sc.next();
                if (line.contains(target)) {
                    row_cond = 1;
                }  // row condition filter
                for (int j = 0; j < n; j++) {
                    char c = line.charAt(j);
                    ohmok[i][j] = c;
                }
            }

            String ans = "NO";
            if (row_cond == 1)
                ans = "YES";
            else {
                // How to Verify continuous?
//                ans = Verify_using_SB(ans, n, ohmok, target);    // 입력받을 떄부터 행에 오목이면 탐색 종료하기 위해 SB로 쭉 시작
                ans = Verify_using_int(ans, n, ohmok);
            }
            System.out.println("#" + test_case + " " + ans);
        }
    }

    private static String Verify_using_int(String ans, int n, char[][] ohmok) {

        int[] dir_row = {1, 1, 1}; // 동남, 남, 남서 방향 체크 - row에 대해선 이미 필터링
        int[] dir_col = {1, 0, -1};   // 1, 2사분면(Upside)은 중복이므로 체크 불필요

        for (int i = 0; i < n; i++) {   // 각 위치에서 연속성 체크
            for (int j = 0; j < n; j++) {
                if (ohmok[i][j]=='.') continue;
                for (int dir = 0; dir < 3; dir++) {   // 방향벡터 위한 반복문
                    int continuous = 1;
                    for (int k = 1; k < 5; k++) {
                        boolean index_range = (i + dir_row[dir]*k < n) && (i + dir_row[dir]*k >= 0)
                                && (j + dir_col[dir]*k < n) && (j + dir_col[dir]*k >= 0);
                        if (!index_range) break;
                        if (ohmok[i + dir_row[dir]*k][j + dir_col[dir]*k] == '.') {
                            break;
                        }
                        continuous++;
                    }
                    if (continuous==5) {ans="YES"; return ans;}
                }
            }
        }

        return ans;
    }

    private static String Verify_using_SB(String ans, int n, char[][] ohmok, String target) {

        while_loop:
        while (true) {
            // 큰 대각선 체크: 0,0 - n-1,n-1 & 0,n-1 - n-1,0
            StringBuilder sb_slash_r = new StringBuilder(), sb_slash_l = new StringBuilder();
            for (int i = 0; i < n; i++) {
                sb_slash_r.append(ohmok[i][i]);
                sb_slash_l.append(ohmok[i][n - 1 - i]);
                if (i >= 4 && (sb_slash_r.toString().contains(target) || sb_slash_l.toString().contains(target))) {
                    ans = "YES";
                    break while_loop;
                }
            }
            // 큰 대각선에서 좌, 우로 줄어들며 체크
            for (int k = 1; k < n - 4; k++) {
                StringBuilder sb_slash_r_upper = new StringBuilder(), sb_slash_r_lower = new StringBuilder();
                StringBuilder sb_slash_l_upper = new StringBuilder(), sb_slash_l_lower = new StringBuilder();
                for (int i = 0; i < n - k; i++) {
                    // 각 대각선 방향에 오목 체크
                    sb_slash_r_upper.append(ohmok[i][i+k]); sb_slash_r_lower.append(ohmok[i+k][i]);
                    sb_slash_l_upper.append(ohmok[i][n -1-(i+k)]); sb_slash_l_lower.append(ohmok[i+k][n -1-i]);
                    boolean check1 = sb_slash_r_upper.toString().contains(target), check2 = sb_slash_r_lower.toString().contains(target);
                    boolean check3 = sb_slash_l_lower.toString().contains(target), check4 = sb_slash_l_lower.toString().contains(target);
                    if (i >= 4 && check1 || check2 || check3 || check4) {
                        ans = "YES";
                        break while_loop;
                    }
                }
            }
            // col 방향 체크
            for (int i = 0; i < n; i++) {
                StringBuilder sb_col = new StringBuilder();
                for (int j = 0; j < n; j++) {
                    sb_col.append(ohmok[j][i]);
                    if (j >= 4 && sb_col.toString().contains(target)) {
                        ans = "YES";
                        break while_loop;
                    }
                }
            }
            break;
        }
        return ans;
    }

}