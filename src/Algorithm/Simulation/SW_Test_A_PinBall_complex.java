package Algorithm.Simulation;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class SW_Test_A_PinBall_complex {
    public static void main(String args[]) throws Exception {
        StringBuilder ans = new StringBuilder();
        System.setIn(new FileInputStream("res/Algorithm/Simulation/input_SW_Test_A_PinBall.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());
        for (int test_case = 1; test_case <= T; test_case++) {
            ans.append("#").append(test_case).append(" ");
            StringTokenizer st = new StringTokenizer(br.readLine().trim());
            n = Integer.parseInt(st.nextToken());

            map = new int[n][n];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine().trim());
                for (int j = 0; j < n; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if (map[i][j] >= 6) {
                        if (holes[map[i][j]][0] == null) holes[map[i][j]][0] = new position_complex(i, j, 0);
                        else holes[map[i][j]][1] = new position_complex(i, j, 0);
                    }
                }
            }

            max = 0;
            for (int c = 0; c < n; c++) {    // 윗방향 탐색: dir=0
                for (int r = n - 1; r >= 0; r--) {
                    if (map[r][c] != 0) {
                        if (r + 1 < n && map[r + 1][c] == 0) {
                            end.r = r + 1; end.c = c;
                            pinball(r + 1, c, 0);}
                    }
                    if (r == 0 && map[r][c] == 0) Math.max(max, 1);
                }
            }

            for (int c = 0; c < n; c++) {
                for (int r = 0; r < n; r++) {
                    if (map[r][c] != 0)
                        if (r - 1 >= 0 && map[r - 1][c] == 0) {
                            end.r = r - 1; end.c = c;
                            pinball(r - 1, c, 1);}
                    if (r == n - 1 && map[r][c] == 0) Math.max(max, 1);
                }
            }

            for (int r = 0; r < n; r++) {    // 왼쪽방향 탐색: dir=2
                for (int c = n - 1; c >= 0; c--) {
                    if (map[r][c] != 0)
                        if (c + 1 < n && map[r][c + 1] == 0) {
                            end.r = r; end.c = c + 1;
                            pinball(r, c + 1, 2);}
                    if (c == 0 && map[r][c] == 0) Math.max(max, 1);
                }
            }

            for (int r = 0; r < n; r++) {    // 오른쪽방향 탐색: dir=3
                for (int c = 0; c < n; c++) {
                    if (map[r][c] != 0)
                        if (c - 1 >= 0 && map[r][c - 1] == 0) {
                            end.r = r; end.c = c - 1;
                            pinball(r, c - 1, 3);}
                    if (c == n - 1 && map[r][c] == 0) Math.max(max, 1);
                }
            }

            ans.append(max).append('\n');
        }
        System.out.print(ans);
    }

    static int[][] map, each_case = {{}, {1, 3, 0, 2}, {3, 0, 1, 2}, {2, 0, 3, 1}, {1, 2, 3, 0}, {1, 0, 3, 2}};
    static int[] dir_r = {-1, 1, 0, 0}, dir_c = {0, 0, -1, 1};
    static int n, max = 0;
    static position_complex[][] holes = new position_complex[11][2];
    static position_complex end = new position_complex();

    private static void pinball(int r, int c, int dir) {

        int score = 0;
        int curr_r = r, curr_c = c, curr_dir = dir;

        while (true) {
            int next_r = curr_r, next_c = curr_c;

            if (curr_dir == 0) { // 위쪽 탐색
                for (int i = curr_r - 1; i >= -1; i--) {
                    if (i == -1) { // 벽 충돌
                        max = Math.max(max, score * 2 + 1);
                        return;
                    }
                    if (map[i][curr_c] != 0) { // 장애물 발견
                        next_r = i;
                        break;
                    }
                    if (i == end.r && curr_c == end.c) { // 돌아오는 길에 시작점
                        max = Math.max(max, score);
                        return;
                    }
                }
            } else if (curr_dir == 1) { // 아래쪽 탐색
                for (int i = curr_r + 1; i <= n; i++) {
                    if (i == n) { // 벽 충돌
                        max = Math.max(max, score * 2 + 1);
                        return;
                    }
                    if (map[i][curr_c] != 0) {
                        next_r = i;
                        break;
                    }
                    if (i == end.r && curr_c == end.c) {
                        max = Math.max(max, score);
                        return;
                    }
                }
            } else if (curr_dir == 2) {
                for (int i = curr_c - 1; i >= -1; i--) {
                    if (i == -1) {
                        max = Math.max(max, score * 2 + 1);
                        return;
                    }
                    if (curr_r == end.r && i == end.c) {
                        max = Math.max(max, score);
                        return;
                    }
                    if (map[curr_r][i] != 0) {
                        next_r = curr_r;
                        next_c = i;
                        break;
                    }
                }
            } else {
                for (int i = curr_c + 1; i <= n; i++) {
                    if (i == n) {
                        max = Math.max(max, score * 2 + 1);
                        return;
                    }
                    if (curr_r == end.r && i == end.c) {
                        max = Math.max(max, score);
                        return;
                    }
                    if (map[curr_r][i] != 0) {
                        next_r = curr_r;
                        next_c = i;
                        break;
                    }
                }
            }

            int obs = map[next_r][next_c];
            if (obs == -1) { // 블랙홀
                max = Math.max(max, score);
                return;
            }
            if (obs >= 6) { // 웜홀 처리
                position_complex out = (holes[obs][0].r == next_r && holes[obs][0].c == next_c) ? holes[obs][1] : holes[obs][0];
                curr_r = out.r + dir_r[curr_dir];
                curr_c = out.c + dir_c[curr_dir];

                if (curr_r < 0 || curr_r >= n || curr_c < 0 || curr_c >= n) {
                    max = Math.max(max, score * 2 + 1);
                    return;
                }
                if (curr_r == end.r && curr_c == end.c) {
                    max = Math.max(max, score);
                    return;
                }
                if (map[curr_r][curr_c] == -1) {
                    max = Math.max(max, score);
                    return;
                }
            } else {
                int next_dir = each_case[obs][curr_dir];
                if (next_dir == (curr_dir ^ 1)) { // 180도 반사면 즉시 2*cnt + 1 : 비트마스킹-XOR 연산으로 0-1 2-3
                    max = Math.max(max, score * 2 + 1);
                    return;
                }

                score++;
                curr_dir = next_dir;
                curr_r = next_r;
                curr_c = next_c;
            }
        }
    }
}

class position_complex {
    int r, c, dir;

    public position_complex() {
    }

    public position_complex(int r, int c, int dir) {
        this.r = r;
        this.c = c;
        this.dir = dir;
    }
}