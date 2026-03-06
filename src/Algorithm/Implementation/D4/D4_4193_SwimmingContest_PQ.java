package Algorithm.Implementation.D4;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class D4_4193_SwimmingContest_PQ {
    /*
    수영대회 결성을 가로 N 세로 N만큼의 공간만 사용하여 진행하하며, 이 공간을 벗어나면 실격처리가 되므로 공간안에서 가장 빠른 길을 찾아야 한다.
    이 공간에는 섬과 같은 지나갈 수 없는 장애물과, 주기적으로 사라졌다 나타나는 소용돌이 같은 장애물이 존재한다.
    ( 섬과 같은 장애물은 지도에서 1로 표시, 소용돌이 같은 장애물은 2로 표시 )
    소용돌이는 생성되고 2초동안 유지되다가 1초동안 잠잠해진다.
    예를들어, 0초에 생성된 소용돌이는 0초, 1초까지 유지되고 2초에 사라지게된다. 또한 3초, 4초에는 생성되고 5초에 사라진다.
    (단 ,한번 통과한 소용돌이 위에서는 머물러 있을 수 있다 )
    이런 바다에서 삼성이를 우승시키려면 어떤 경로로 보내야 될까? 해당 경로로 수영을 했을때 삼성이는 몇초만에 골인 할 수 있을까?
    */
    public static void main(String args[]) throws Exception {
        StringBuilder ans = new StringBuilder();
        System.setIn(new FileInputStream("res/Algorithm/Implementation/input_D4_4193_SwimmingContest_PQ.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            ans.append("#").append(test_case).append(" ");
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int[][] stage = new int[n][n];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++)
                    stage[i][j] = Integer.parseInt(st.nextToken());
            }
            st = new StringTokenizer(br.readLine());
            int start_r = Integer.parseInt(st.nextToken()), start_c = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            end_r = Integer.parseInt(st.nextToken()); end_c = Integer.parseInt(st.nextToken());

            // 우선순위큐: 시작점으로부터의 거리 = 노드의 깊이 / BFS는 동일한 층은 같은 순서로 돌지만, 거리가 멀어져도 시간순으로 탐색하도록 선택
            PriorityQueue<player> pq = new PriorityQueue<>();
            boolean[][] v = new boolean[n][n];
            v[start_r][start_c] = true;
            pq.offer(new player(start_r, start_c, 0));

            int min_time = -1;  // 불가능 케이스 존재*
            while (!pq.isEmpty()) {
                player curr = pq.poll();
                if (curr.r == end_r && curr.c == end_c) {min_time = curr.t; break;}
                for (int dir = 0; dir < 4; dir++) {
                    int r = curr.r + dir_r[dir];
                    int c = curr.c + dir_c[dir];
                    int t = curr.t;
                    if (r>=0 && r<n && c>=0 && c<n && stage[r][c]!=1 && !v[r][c]){
                        // 0(생성) 1 2(소멸) 3(생성) 4 5(소멸) 6(생성) 7 8(소멸) 9(생성)
                        if (stage[r][c]>=2) t = (t/3+1)*3; // t%3==0에서만 통과 가능
                        else t++;
                        v[r][c]=true;
                        pq.offer(new player(r, c, t));
                    }
                }
            }
            ans.append(min_time).append('\n');
        }
        System.out.print(ans);
    }
    static int end_r, end_c;
    static int[] dir_r = {1, 0, -1, 0}, dir_c = {0, 1, 0, -1};
}

class player implements Comparable<player> {
    int r, c, t;

    public player(int r, int c, int t) {
        this.r = r;
        this.c = c;
        this.t = t;
    }

    @Override
    public int compareTo(player o) {
        return this.t - o.t;
    }
}