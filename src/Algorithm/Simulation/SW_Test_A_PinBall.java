package Algorithm.Simulation;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
class SW_Test_A_PinBall {   //5650
    /*
    게임판 위에서는 작은 핀볼 하나가 상, 하, 좌, 우 중 한 방향으로 움직인다. 핀볼은 블록이나 웜홀 또는 블랙홀을 만나지 않는 한 현재 방향을 유지하면서 계속 직진하며,
    블록의 수평면이나 수직면을 만날 경우 방향을 바꿔 반대 방향으로 돌아오고, 경사면을 만날 경우에는 직각으로 진행 방향이 꺾이게 된다.
    또한 핀볼은 벽을 만날 경우에도 반대 방향으로 돌아온다. 아래의 그림과 같이 핀볼이 왼쪽으로 움직이다 벽을 만나면 반대 방향으로 다시 돌아오게 된다.
    핀볼이 웜홀에 빠지면 동일한 숫자를 가진 다른 반대편 웜홀로 빠져 나오게 되며 진행방향은 그대로 유지된다. (웜홀은 반드시 쌍으로 주어지며, 입력에서는 6 이상 10 이하의 숫자로 표시된다.)
    게임은 핀볼이 출발 위치로 돌아오거나, 블랙홀에 빠질 때 끝나게 되며, 점수는 벽이나 블록에 부딪힌 횟수가 된다. (웜홀을 통과하는 것은 점수에 포함되지 않는다.)
    블랙홀에 빠져서 게임이 끝나더라도, 벽이나 블록에 부딪혀 획득한 점수는 남아있게 된다.
    게임판 위에서 출발 위치와 진행 방향을 임의로 선정가능 할 때, ▶ 게임에서 얻을 수 있는 점수의 최댓값을 구하여라!
    [제약 사항]
    게임판의 크기는 정사각형으로 주어지며, 한 변의 길이 N 은 5 이상 100 이하이다. (5 ≤ N ≤ 100)
    웜홀은 게임판 내에서 숫자 6 ~ 10으로 주어진다. 블랙홀은 게임판 내에서 숫자 -1 로 주어진다.
    게임판에서 웜홀 또는 블랙홀이 존재하지 않는 경우도 있다. 블랙홀은 최대 5개가 주어진다.
    웜홀이 있는 경우 반드시 쌍(pair)으로 존재하며, 웜홀이 주어지는 경우 최대 5쌍 존재한다. 웜홀을 통과한 핀볼은 동일한 숫자를 가진 반대편 웜홀로 이동하게 되며, 이때 진행방향은 그대로 유지된다.
     */
    public static void main(String args[]) throws Exception {
        StringBuilder ans = new StringBuilder();
        System.setIn(new FileInputStream("res/Algorithm/Simulation/input_SW_Test_A_PinBall.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());
        for (int test_case = 1; test_case <= T; test_case++) {
            ans.append("#").append(test_case).append(" ");
            StringTokenizer st = new StringTokenizer(br.readLine().trim());
            n = Integer.parseInt(st.nextToken());

            map = new int[n+2][n+2];
            Arrays.fill(map[0], 5);         // 테두리를 전부 벽으로 설정
            Arrays.fill(map[n+1], 5);
            for (int i = 1; i <= n; i++) {
                map[i][0] = 5; map[i][n+1] = 5;
            }

            holes = new position[11][2];      // 인덱스 꼬이지 않도록 6~10 보장
            for (int i = 1; i <= n; i++) {
                st = new StringTokenizer(br.readLine().trim());
                for (int j = 1; j <= n; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if (map[i][j] >= 6) {
                        if (holes[map[i][j]][0] == null)
                            holes[map[i][j]][0] = new position(i, j, 0);
                        else holes[map[i][j]][1] = new position(i, j, 0);
                    }
                }
            }

            max = 0;
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    for (int dir = 0; dir < 4; dir++) {
                        if (map[i][j]==0) {
                            if (map[i + dir_r[dir]][j + dir_c[dir]]==5 ||
                                    is_opposite(map[i + dir_r[dir]][j + dir_c[dir]], dir)) {   // 바로 벽이면 패쓰
                                max = Math.max(max, 1);
                                continue;
                            }
                            end.r = i; end.c = j;
                            pinball(i, j, dir);
                        }
                    }
                }
            }

            ans.append(max).append('\n');
        }
        System.out.print(ans);
    }

    static int[][] map, each_case = {{}, {1, 3, 0, 2}, {3, 0, 1, 2}, {2, 0, 3, 1}, {1, 2, 3, 0}, {1, 0, 3, 2}};
    static int[] dir_r = {-1, 1, 0, 0}, dir_c = {0, 0, -1, 1};
    static int n, max;
    static position[][]  holes;
    static position end = new position();

    private static void pinball(int r, int c, int dir) {

        int score = 0;

        while (true) {
            r += dir_r[dir];
            c += dir_c[dir];

            if ((r==end.r&&c==end.c) || map[r][c]==-1) { max = Math.max(max, score); return;}

            int obs = map[r][c];
            if (obs==5) { max = Math.max(max, score*2 + 1); return; } // 모든 벽은 벽을 치는 순간 왔던 경로를 고스란히 돌아가게 된다.
            if (obs>=6) {
                if (r==holes[obs][0].r && c==holes[obs][0].c) {
                    r = holes[obs][1].r; c = holes[obs][1].c;
                } else {
                    r = holes[obs][0].r; c = holes[obs][0].c;
                }
            } else if (obs>=1) {
                if (is_opposite(obs, dir))  { max = Math.max(max, score*2 + 1); return; } // 이건 시간을 크게 단축해주지 않았다
                score++; dir = each_case[obs][dir];}
        }
    }

    public static boolean is_opposite(int obs, int dir) {   // 반대방향으로 이동하게 되는 케이스는 시뮬레이션 종료 가능
        switch (obs){
            case 1:
                if (dir==0 || dir==3) return true;
                break;
            case 2:
                if (dir==1 || dir==3) return true;
                break;
            case 3:
                if (dir==1 || dir==2) return true;
                break;
            case 4:
                if (dir==0 || dir==2) return true;
                break;
        }
        return false;
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