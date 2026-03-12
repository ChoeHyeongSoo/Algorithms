package Algorithm.Graph.BFS;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

class SW_Test_A_CatchUnderground { // 1953.
    /*
    교도소로 이송 중이던 흉악범이 탈출하는 사건이 발생하여 수색에 나섰다.
    탈주범은 탈출한 지 한 시간 뒤, 맨홀 뚜껑을 통해 지하터널의 어느 한 지점으로 들어갔으며, 지하 터널 어딘가에서 은신 중인 것으로 추정된다.
    터널끼리 연결이 되어 있는 경우 이동이 가능하므로 탈주범이 있을 수 있는 위치의 개수를 계산하여야 한다. 탈주범은 시간당 1의 거리를 움직일 수 있다
    [제약 사항]
    1. 시간 제한 : 최대 50개 테이트 케이스를 모두 통과하는데, C/C++/Java 모두 1초
    2. 지하 터널 지도의 세로 크기 N, 가로 크기 M은 각각 5 이상 50 이하이다. (5 ≤ N, M ≤ 50)
    3. 맨홀 뚜껑의 세로 위치 R 은 0 이상 N-1이하이고 가로 위치 C 는 0 이상 M-1이하이다. (0 ≤ R ≤ N-1, 0 ≤ C ≤ M-1)
    4. 탈출 후 소요된 시간 L은 1 이상 20 이하이다. (1 ≤ L ≤ 20)
    5. 지하 터널 지도에는 반드시 1개 이상의 터널이 있음이 보장된다.
    6. 맨홀 뚜껑은 항상 터널이 있는 위치에 존재한다.
     */
    public static void main(String args[]) throws Exception {
        StringBuilder ans = new StringBuilder();
        System.setIn(new FileInputStream("res/Algorithm/Graph/BFS/input_SW_Test_A_CatchUnderground.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            ans.append("#").append(test_case).append(" ");
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int R = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            int L = Integer.parseInt(st.nextToken());
            map = new int[n][m];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < m; j++)
                    map[i][j] = Integer.parseInt(st.nextToken());
            }

            boolean[][] v = new boolean[n][m];
            Deque<position> q = new ArrayDeque<>();
            Deque<position> next = new ArrayDeque<>();	// 다음 시간대에 출발할 곳 저장
            q.offer(new position(R, C, map[R][C]));
            v[R][C] = true;
            int cnt = 1;

            for (int i = 1; i < L; i++) {

                while (!q.isEmpty()) {
                    position curr = q.poll();
                    // 방향은 dir[tunnel].length 동안 dir[tunnel][d]로 반복
                    for (int d = 0; d < dir_r[curr.tunnel].length; d++) {
                        int r = curr.r + dir_r[curr.tunnel][d];
                        int c = curr.c + dir_c[curr.tunnel][d];

                        if (r >= 0 && r < n && c >= 0 && c < m) {
                            if (v[r][c]||map[r][c]==0) continue; // 터널 연결 파악 필요
                            if (!is_connected_s(r, c, curr.tunnel, d)) continue;
                            v[r][c] = true; cnt++;
                            next.offer(new position(r,c,map[r][c]));
                        }
                    }
                }

                while(!next.isEmpty()) q.offer(next.poll());
            }

            ans.append(cnt).append('\n');
        }
        System.out.print(ans);
    }

    static int[][] dir_r = {{0}, {1, -1, 0, 0}, {1, -1}, {0, 0}, {-1, 0}, {1, 0}, {1, 0}, {-1, 0}};
    static int[][] dir_c = {{0}, {0, 0, 1, -1}, {0, 0}, {1, -1}, {0, 1}, {0, 1}, {0, -1}, {0, -1}};
    static int[][] map;

    public static boolean is_connected_s(int r, int c, int curr, int dir) {

        int next = map[r][c];

        int case1 = dir_r[curr][dir];
        int case2 = dir_c[curr][dir];

        if (case1!=0) {
            for (int d_r : dir_r[next])
                if (d_r == case1 * -1) return true;
        }
        else {
            for (int d_c : dir_c[next])
                if (d_c == case2 * -1) return true;
        }

        return false;
    }

    public static boolean is_connected(int r, int c, int curr, int dir) {	// 너무 길다; 더 효율적으로!

        int next = map[r][c];

        switch (curr) {
            case 1:
                if (dir==0 && (next==1 || next==2 || next==4 || next==7)) return true;
                if (dir==1) for (int d : dir_r[next]) if (d==1) return true;
                if (dir==2 && (next==1 || next==3 || next==6 || next==7)) return true;
                if (dir==3) for (int d : dir_c[next]) if (d==1) return true;
                break;
            case 2:
                if (dir==0) for (int d : dir_r[next]) if (d==-1) return true;
                if (dir==1) for (int d : dir_r[next]) if (d==1) return true;
            case 3:
                if (dir==0) for (int d : dir_c[next]) if (d==-1) return true;
                if (dir==1) for (int d : dir_c[next]) if (d==1) return true;
            case 4:
                if (dir==0) for (int d : dir_r[next]) if (d==1) return true;
                if (dir==1) for (int d : dir_c[next]) if (d==-1) return true;
            case 5:
                if (dir==0) for (int d : dir_r[next]) if (d==-1) return true; // 그만;
                if (dir==1) for (int d : dir_r[next]) if (d==1) return true;
            case 6:
                if (dir==0) for (int d : dir_r[next]) if (d==-1) return true;
                if (dir==1) for (int d : dir_r[next]) if (d==1) return true;
            case 7:
                if (dir==0) for (int d : dir_r[next]) if (d==-1) return true;
                if (dir==1) for (int d : dir_r[next]) if (d==1) return true;
        }

        return false;
    }
}

class position{
    int r, c, tunnel;

    public position(int r, int c, int tunnel) {
        this.r = r;
        this.c = c;
        this.tunnel = tunnel;
    }
}