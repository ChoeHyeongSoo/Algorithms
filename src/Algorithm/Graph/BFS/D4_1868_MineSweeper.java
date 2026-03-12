package Algorithm.Graph.BFS;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

class D4_1868_MineSweeper {
    /*
    표의 각 칸을 클릭했을 때, 그 칸이 지뢰가 있는 칸이라면 ‘파핑 파핑!’이라는 소리와 함께 게임은 끝난다.
    지뢰가 없는 칸이라면 변이 맞닿아 있거나 꼭지점이 맞닿아 있는 최대 8칸에 대해 몇 개의 지뢰가 있는지가 0에서 8사이의 숫자로 클릭한 칸에 표시된다.
    만약 이 숫자가 0이라면 근처의 8방향에 지뢰가 없다는 것이 확정된 것이기 때문에 그 8방향의 칸도 자동으로 숫자를 표시해 준다.
    실제 게임에서는 어떤 위치에 지뢰가 있는지 알 수 없지만, 이 문제에서는 특별히 알 수 있다고 하자.
    지뢰를 ‘*’로, 지뢰가 없는 칸을 ‘.’로, 클릭한 지뢰가 없는 칸을 ‘c’로 나타냈을 때 표가 어떻게 변화되는지 나타낸다.
    파핑 파핑 지뢰 찾기를 할 때 표의 크기와 표가 주어질 때, 지뢰가 있는 칸을 제외한 다른 모든 칸의 숫자들이 표시되려면 최소 몇 번의 클릭을 해야 하는지 구하는 프로그램을 작성하라.
     */
    public static void main(String args[]) throws Exception {
        StringBuilder ans = new StringBuilder();
        System.setIn(new FileInputStream("res/input_SW_Test_A_MineSweeper.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            ans.append("#").append(test_case).append(" ");
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            map = new char[n][n]; board = new int[n][n]; v = new boolean[n][n];
            bombs = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                map[i] = br.readLine().toCharArray();
                for (int j = 0; j < n; j++)
                    if (map[i][j]=='*') {bombs.add(i*n+j); v[i][j] = true;}
            }

            make_board();     // 숫자판 만들기
            int cnt = BFS();  // 최소 클릭 계산

            ans.append(cnt).append('\n');
        }
        System.out.print(ans);
    }

    static int n;
    static char[][] map;
    static int[][] board;
    static boolean[][] v;
    static List<Integer> bombs;
    static int[] dir_r = {1, 1, 0, -1, -1, -1, 0, 1}, dir_c = {0, 1, 1, 1, 0, -1, -1, -1};

    public static void make_board() {

        for (int key : bombs) { // 폭탄 좌표 기준으로 보드 카운트++

            int r = key/n, c = key%n;
            board[r][c] = -1;   // 폭탄은 -1 처리

            for (int d = 0; d < 8; d++) {
                int next_r = r + dir_r[d];
                int next_c = c + dir_c[d];

                if (next_r >=0 && next_r < n && next_c >=0 && next_c < n) {
                    if (map[next_r][next_c]=='.')   // 폭탄은 카운트하지 않도록
                        board[next_r][next_c]++;
                }
            }
        }
    }

    public static int BFS() {

        Deque<Integer> q = new ArrayDeque<>();
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j]!=0 || v[i][j]) continue;   // 최소를 위해 0만 클릭
                v[i][j] = true; cnt++;
                q.offer(i*n+j);
                while (!q.isEmpty()) {
                    int key = q.poll();

                    for (int d = 0; d < 8; d++) {
                        int r = key/n + dir_r[d];
                        int c = key%n + dir_c[d];


                        if (r >=0 && r < n && c >=0 && c < n) { // 루프 내부 변수 확인: r*n+j로 틀린그림찾기;
                            if (v[r][c]) continue;
                            if (board[r][c]==0) q.offer(r*n+c); // 인접한 0은 같은 턴에 오픈
                            v[r][c] = true;
                        }
                    }
                }
            }
        }

        for (boolean[] r : v)
            for (boolean b : r)
                if (!b) cnt++;

        return cnt;
    }
}