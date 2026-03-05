import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
class D4_4193_SwimContest {
    /*

     */
    public static void main(String args[]) throws Exception {
        StringBuilder ans = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            ans.append("#").append(test_case).append(" ");
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int[][] stage = new int[n][n];
//            List<Integer> bangbang = new ArrayList<>(); // r*n+c로 저장 -> r: x/n, c: x%n
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    stage[i][j] = Integer.parseInt(st.nextToken());
//                    if (stage[i][j]==2) bangbang.add(i*n+j);
                }
            }
            st = new StringTokenizer(br.readLine());
            int start_r = Integer.parseInt(st.nextToken()), start_c = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            int end_r = Integer.parseInt(st.nextToken()), end_c = Integer.parseInt(st.nextToken());

            int min_time = Integer.MAX_VALUE;
            // 1은 없어지지 않고, 2는 없어진다. 최소 거리 계산 - 2인 경우 거리+ 2라고 생각하면 될듯 : 다익?
            int[][] timetable = new int[n][n];
            boolean[] v = new boolean[n * n]; // 1차원 맵핑: r*n+c의 최대== n*(n-1)+m-1
            Queue<player> q = new LinkedList<>();
//            q.offer(start_r*n+start_c);
            q.offer(new player(start_r, start_c, 0, v));
            while (!q.isEmpty()) {
                player curr = q.poll();
                if (curr.t > min_time) continue;
                if (curr.r == end_r && curr.c == end_c) {
                    min_time = Math.min(min_time, curr.t);
                    continue;
                }
                // 현재 거리가 이전보다 큰 순간 그 케이스는 버리기. - 그거랑 관련된 좌표를 넣기 전에 컨티뉴
                boolean[] curr_v = Arrays.copyOf(curr.v, n * n);
                for (int d = 0; d < 4; d++) {
                    int r = curr.r + dir_r[d];
                    int c = curr.c + dir_c[d];
                    int t = curr.t;
                    if (r >= 0 && r < n && c >= 0 && c < n)
                        if (stage[r][c] != 1 && !curr_v[r * n + c]) {
                            // 소용돌이 조건 수정.. 0, 1초까지 유
                            if (stage[r][c] >= t)
                                t += (stage[r][c] - t + 1);   // 내 앞이 소용돌이다, 지금 시간이 0이면 2초뒤에 갈 수 있으니
                            else t++;
                            curr_v[r * n + c] = true;
                            q.offer(new player(r, c, t, curr_v));
                            curr_v[r * n + c] = false;
                        }
                    // t
                }
            }

            ans.append(min_time).append('\n');
        }
        System.out.print(ans);
    }

    static int n;
    static int[] dir_r = {1, 0, -1, 0}, dir_c = {0, 1, 0, -1};
}

class player {
    int r, c, t;
    boolean[] v;

    public player(int r, int c, int t, boolean[] v) {
        this.r = r;
        this.c = c;
        this.t = t;
        this.v = v;
    }
}