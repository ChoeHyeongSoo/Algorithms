package Algorithm.Graph.BFS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

class D4_7733_CheezeRobbery {
    /*
    치즈는 특이하게도 N*N개의 모든 칸의 맛있는 정도가 동일하지 않다. 맛있는 정도는 1부터 100 사이로 표현된다.
    요정은 100일동안 치즈를 갉아먹는데, X번째날에는 맛있는 정도가 X인 칸을 먹어버린다.
    치즈 덩어리란상, 하, 좌, 우로인접한 칸들을 하나로 묶어놓은 것을 의미한다.
    100일 중에서 치즈덩어리가 가장 많을 때의 덩어리 개수를 구하는 프로그램을 작성하라.
     */
    public static void main(String args[]) throws Exception {
        StringBuilder ans = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            ans.append("#").append(test_case).append(" ");
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            cheeze = new int[n][n];
            max_day = 0;    // max-1에 도달할 때까지 최대값 찾기
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    cheeze[i][j] = Integer.parseInt(st.nextToken());
                    if (max_day < cheeze[i][j]) max_day = cheeze[i][j];
                }
            }

            // 매번 bfs로 그룹을 찾을 것인가 - 리스트에 그룹을 두고 나눠지면 또 분리하기?
            Deque<Integer> q = new ArrayDeque<>(); // 1) bfs
            int day = 1, max_pieces = 1;
            int[][] v = new int[n][n];  // 방문 배열의 값으로 날짜를 넣으면 반복 생성 / 초기화 x
            while (day<max_day) {   // 날짜보다 작은 건 넘어가기, 끊기? or 0으로 만들기
                int tmp = 0;
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        if (v[i][j]==day || cheeze[i][j] <= day) continue;
                        v[i][j] = day;
                        q.offer(i*n+j);

                        while (!q.isEmpty()) {
                            int curr = q.poll();
                            for (int k = 0; k < 4; k++) {
                                int r = curr/n + dir_r[k];
                                int c = curr%n + dir_c[k];

                                if (r>=0 && r<n && c>=0 && c<n) {
                                    if (v[r][c]==day || cheeze[r][c] <= day) continue;
                                    v[r][c] = day;
                                    q.offer(r*n + c);
                                }
                            }
                        }
                        tmp++;
                    }
                }
                max_pieces = Math.max(max_pieces, tmp);
                day++;
            }            ans.append(max_pieces).append('\n');
        }
        System.out.print(ans);
    }

    static int n, max_day;
    static int[] dir_r = {1, 0, -1, 0}, dir_c = {0, 1, 0, -1};
    static int[][] cheeze;
}