package Algorithm.Simulation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class SW_Test_A_PinBall {
    /*

     */
    public static void main(String args[]) throws Exception {
        StringBuilder ans = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());
        for (int test_case = 1; test_case <= T; test_case++) {
            ans.append("#").append(test_case).append(" ");
            StringTokenizer st = new StringTokenizer(br.readLine().trim());
            int n = Integer.parseInt(st.nextToken());

            map = new int[n][n];
            position[][] holes = new position[11][2];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine().trim());
                for (int j = 0; j < n; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if (map[i][j] >= 6) {
                        if (holes[map[i][j]][0] == null) holes[map[i][j]][0] = new position(i, j, 0);
                        else holes[map[i][j]][1] = new position(i, j, 0);
                    }
                }
            }
            int cnt = 0;
            for (int c = 0; c < map.length; c++) {    // 아랫방향 탐색: dir=1
                for (int r = 0; r < map.length - 1; r++) {
                    if (map[r][c] == 0) {
                        if (map[r + 1][c] <= 0) continue;
                        end.r = r;
                        end.c = c;
                        position ball = new position(r + 1, c, 1);
                        change_dir(r + 1, ball, holes);
                        pinball(ball, holes, 1);
                    }
                }
            }

            ans.append('\n');




        }
        System.out.print(ans);
    }

    static int[][] map, each_case = {{}, {1, 3, 0, 2}, {3, 0, 1, 2}, {2, 0, 3, 1}, {1, 2, 3, 0}, {1, 0, 3, 2}};
    static int[] dir_r = {-1, 1, 0, 0}, dir_c = {0, 0, -1, 1}; // u-d-l-r
    static position end = new position();
    static int max = 0;

    public static void change_dir(int obs, position x, position[][] holes) {
        // 블록에 따라서 현재 진행방향과 블록에 따라 각각 전환 : 웜홀은 같은 쌍의 위치, 방향 그대로 유지한 상태로 전환
        if (obs >= 6) {
            if (x.r == holes[obs][0].r && x.c == holes[obs][0].c) {
                x.r = holes[obs][1].r;
                x.c = holes[obs][1].c;
            } else {
                x.r = holes[obs][0].r;
                x.c = holes[obs][0].c;
            }
        } else
            x.dir = each_case[obs][x.dir];

//        return x;
    }

    // 블록 찾기 : row, col에 대해서 진행 방향에 가장 가까운 거 찾고 방향 전환, 연쇄적으로 진행
    // for문으로 같은 row, col에 방향에 따라 현재 r,c에서 내림, 오름으로 가장 가까운 물체 탐색
    // < n-1까지 idx+1에 대해 탐색. 마지막이 0이면 어차피 해당 방향은 정답 0이 된다.
    public static void pinball(position ball, position[][] holes, int cnt) {
        // 0: continue, -1: break; else change_dir, 결과 동일 포지션이면 탈출, 기존의 범위 지났는데 그대로인 경우

        // 벽에 갖다박으면 현재 cnt*2 + 1, 종료;
        switch (ball.dir) {
            case 0:
                for (int r = ball.r; r > 1; r--) {
                    if (r == end.r && ball.c == end.c) {max = Math.max(max, cnt); return;} // 현재까지의 카운트?
                    if (map[r][ball.c] == 0) {
                        if (map[r - 1][ball.c] == 0) continue;
                        if (map[r - 1][ball.c] == -1) {max = Math.max(max, cnt); return;}
                        ball.r = r - 1;
                        change_dir(map[r - 1][ball.c], ball, holes);
                        pinball(ball, holes, cnt + 1);
                    }
                }
                break;
            case 1:
                for (int r = ball.r; r < map.length - 1; r++) {
                    if (r == end.r && ball.c == end.c) {max = Math.max(max, cnt); return;}
                    if (map[r][ball.c] == 0) {
                        if (map[r + 1][ball.c] == 0) continue;
                        if (map[r + 1][ball.c] == -1) {max = Math.max(max, cnt); return;}
                        ball.r = r + 1;
                        change_dir(map[r + 1][ball.c], ball, holes);
                        pinball(ball, holes, cnt + 1);
                    }
                }
                break;
            case 2:
                for (int c = ball.c; c > 1; c--) {
                    if (ball.r == end.r && c == end.c) {max = Math.max(max, cnt); return;}
                    if (map[ball.r][c] == 0) {
                        if (map[ball.r][c-1] == 0) continue;
                        if (map[ball.r][c-1] == -1) {max = Math.max(max, cnt); return;}
                        ball.c = c - 1;
                        change_dir(map[ball.r][c-1], ball, holes);
                        pinball(ball, holes, cnt + 1);
                    }
                }
                break;
            case 4:
                for (int c = ball.c; c < map.length-1; c++) {
                    if (ball.r == end.r && c == end.c) {max = Math.max(max, cnt); return;}
                    if (map[ball.r][c] == 0) {
                        if (map[ball.r][c+1] == 0) continue;
                        if (map[ball.r][c+1] == -1) {max = Math.max(max, cnt); return;}
                        ball.c = c + 1;
                        change_dir(map[ball.r][c+1], ball, holes);
                        pinball(ball, holes, cnt + 1);
                    }
                }
                break;
        }
    }
}

class position {
    int r, c, dir;

    public position() {
    }

    public position(int r, int c, int dir) {
        this.r = r;
        this.c = c;
        this.dir = dir;
    }
}