import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;
class Solution {
    public static void main(String args[]) throws Exception {
        StringBuilder ans = new StringBuilder();
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

            PriorityQueue<player> pq = new PriorityQueue<>();
            boolean[][] v = new boolean[n][n];
            v[start_r][start_c] = true;
            pq.offer(new player(start_r, start_c, 0));

            int min_time = -1;
            while (!pq.isEmpty()) {
                player curr = pq.poll();

                if (curr.r == end_r && curr.c == end_c) {min_time = curr.t; break;}
                for (int dir = 0; dir < 4; dir++) {
                    int r = curr.r + dir_r[dir];
                    int c = curr.c + dir_c[dir];
                    int t = curr.t;
                    if (r>=0 && r<n && c>=0 && c<n && stage[r][c]!=1 && !v[r][c]){
                        if (stage[r][c]>=2) {
                            // 0(생성) 1 2(소멸) 3(생성) 4 5(소멸) 6(생성) 7 8(소멸) 9(생성) 10 11(소멸) 12(생성) : t%3==0에서만 통과 가능
                            t = (t/3+1)*3;
                        }
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