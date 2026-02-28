package Algorithm.Simulation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class SW_Test_A_StemCell {  // 5653.
    /*
    줄기세포 배양 시뮬레이션 프로그램을 만들려고 한다. 줄기세포들을 배양 용기에 도포한 후 일정 시간 동안 배양을 시킨 후 줄기 세포의 개수가 몇 개가 되는지 계산하는 시뮬레이션 프로그램을 만들어야 한다.
    하나의 줄기 세포는 가로, 세로 크기가 1인 정사각형 형태로 존재하며 배양 용기는 격자 그리드로 구성되어 있으며 하나의 그리드 셀은 줄기 세포의 크기와 동일한 가로, 세로 크기가 1인 정사각형이다.
    각 줄기 세포는 생명력이라는 수치를 가지고 있다. 초기 상태에서 줄기 세포들은 비활성 상태이며 생명력 수치가 X인 줄기 세포의 경우 X시간 동안 비활성 상태이고 X시간이 지나는 순간 활성 상태가 된다.
    줄기 세포가 활성 상태가 되면 X시간 동안 살아있을 수 있으며 X시간이 지나면 세포는 죽게 된다. 세포가 죽더라도 소멸되는 것은 아니고 죽은 상태로 해당 그리드 셀을 차지하게 된다.
    활성화된 줄기 세포는 첫 1시간 동안 상, 하, 좌, 우 네 방향으로 동시에 번식을 한다. 번식된 줄기 세포는 비활성 상태이다.
    하나의 그리드 셀에는 하나의 줄기 세포만 존재할 수 있기 때문에 번식하는 방향에 이미 줄기 세포가 존재하는 경우 해당 방향으로 추가적으로 번식하지 않는다.
    두 개 이상의 줄기 세포가 하나의 그리드 셀에 동시 번식하려고 하는 경우 생명력 수치가 높은 줄기 세포가 해당 그리드 셀을 혼자서 차지하게 된다.
    줄기 세포의 크기에 비해 배양 용기의 크기가 매우 크기 때문에 시뮬레이션에서 배양 용기의 크기는 무한하다고 가정한다
    줄기 세포의 초기 상태 정보와 배양 시간 K시간이 주어질 때, K시간 후 살아있는 줄기 세포(비활성 상태 + 활성 상태)의 총 개수를 구하는 프로그램을 작성하라.
    */
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());   // k시간 뒤 생존 수

            // 초기 줄기세포 어떤 형태로 어떤 자료구조에 받을 것인가 : List<List<>>?
            List<Cell> cells = new ArrayList<>();
            boolean[][] grid = new boolean[n+2*k][m+2*k];
            int min_t = 0;
            for (int i = k; i < k+n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = k; j < k+m; j++) {
                    int life = Integer.parseInt(st.nextToken());
                    if (life>0) {
                        Cell tmp = new Cell(i, j, life, life);
                        cells.add(tmp);
                        grid[i][j] = true;
                        if (min_t > life) min_t = life;
                    }
                }
            }

            // 상하좌우 확장: 1) 활성화 상태인 세포만 2) 우선순위 존재 (생명력)
            PriorityQueue<Cell> pq = new PriorityQueue<>(); // 생명력 높은 순서로 q에서 작업 - 다익스트라?
            int t = min_t;
            for (Cell c : cells) {
                c.time -= min_t;
                if (c.time==0){
                    pq.add(c);
                    c.status = 1;
                }
            }

            // t < k인 동안 작업: 1) 큐 빌 때까지 세포 증식 2) 이전에 존재했던 세포 수명 단축 - 새로 추가 or 사망 처리
            int[] dr = {1, 0, -1, 0}, dc = {0, 1, 0, -1};
            while (t < k) {

                int line = cells.size();    // 기존 cell 구분선

                while (!pq.isEmpty()) {
                    Cell tmp = pq.poll();
                    for (int i = 0; i < 4; i++) {
                        int r = tmp.r + dr[i];
                        int c = tmp.c + dc[i];

                        if (!(r < 0 || r >= grid.length || c < 0 || c >= grid.length)) {
                            if (!grid[r][c]) {
                                grid[r][c] = true;
                                Cell next = new Cell(r, c, tmp.life, tmp.life);
                                cells.add(next);
                            }
                        }
                    }
                }

                for (int i = 0; i < line; i++) {
                    Cell c = cells.get(i);
                    if (--c.time == -c.life) c.status = 2;
                    if (c.time==0) pq.add(c);
                }
                t++;
            }

            int cnt = 0;
            for (Cell c : cells) {
                if (c.status!=2)
                    cnt++;
            }

            System.out.println("#" + test_case + " " + cnt);
        }
    }
}

class Cell implements Comparable<Cell>{
    int r, c, life, time, status;

    // @param time: Left time to die
    // @param status: 1: Activate / 2: Dead
    public Cell(int r, int c, int life, int time) {
        this.r = r;
        this.c = c;
        this.life = life;
        this.time = time;
    }

    @Override
    public int compareTo(Cell o) {  // For PriorityQueue : 이거 반대로 해서 고생했다
        return o.life - this.life;
    }
}