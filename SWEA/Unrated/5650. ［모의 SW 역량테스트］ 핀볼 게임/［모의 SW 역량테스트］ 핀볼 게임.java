import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
class Solution {
    public static void main(String args[]) throws Exception {
        StringBuilder ans = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());
        for (int test_case = 1; test_case <= T; test_case++) {
            ans.append("#").append(test_case).append(" ");
            StringTokenizer st = new StringTokenizer(br.readLine().trim());
            n = Integer.parseInt(st.nextToken());

            map = new int[n+2][n+2];
            Arrays.fill(map[0], 5);         // 패딩: 테두리를 전부 벽으로 설정
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