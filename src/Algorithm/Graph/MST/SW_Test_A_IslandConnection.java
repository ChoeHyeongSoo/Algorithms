package Algorithm.Graph.MST;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

class SW_Test_A_IslandConnection {
    public static void main(String args[]) throws Exception {
        StringBuilder ans = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            ans.append("#").append(test_case).append(" ");
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            map = new int[n][n];
            map_v = new boolean[n][n];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++)
                    map[i][j] = Integer.parseInt(st.nextToken());
            }

            // 1. 섬 정의
            islands = new ArrayList<>();
            for (int i = 0; i < n; i++)     // 섬 리스트에 추가
                for (int j = 0; j < n; j++)
                    if (!map_v[i][j] && map[i][j]==1) define(i, j);

            // 2. 비용 저장: 조합(nC2) & 고립된 섬 판별 가능
            int i_n = islands.size(), cost_idx = 0;
            Cost[] costs = new Cost[i_n*(i_n-1)/2]; // 0 : 1~n-1 , 1 : 2~n-1 ...
            boolean impossible = false;
            for (int i = 0; i < i_n-1; i++) {
                int poss = 0;
                for (int j = i + 1; j < i_n; j++) {
                    int curr_d = get_distance(islands.get(i), islands.get(j));
                    if (curr_d<Integer.MAX_VALUE) poss++;   // 가능한 게 하나라도 있으면 통과
                    costs[cost_idx++] = new Cost(i, j, curr_d); // 연결된 섬 번호, 거리 입력
                }
                if (poss==0) {impossible = true; break;}    // 한 섬이 고립 - 종료
            }

            // 3. 사이클 판별 & 가격 업데이트
            int cost = -1;
            if (!impossible) {
                cost = 0;
                int[] parent = new int[i_n];
                for (int i = 0; i < i_n; i++) parent[i] = i;
                int edge = 0;

                Arrays.sort(costs); // costs 정렬: 가장 저렴한 것부터 판단, 노드가 많을수록 PQ가 유리

                for (Cost tmp : costs) {
                    if (tmp.d==Integer.MAX_VALUE) break;
                    int root_x = find(parent, tmp.x);
                    int root_y = find(parent, tmp.y);

                    if (root_x != root_y) { // 사이클의 루트 노드를 최소번호가 되도록
                        if (root_x < root_y) parent[root_y] = root_x;
                        else parent[root_x] = root_y;

                        cost += tmp.d; edge++;
                        if (edge == i_n - 1) break; // 사이클 그래프 간선: 노드-1
                    }
                }
                if (edge != i_n - 1) cost = -1;
            }

            ans.append(cost).append('\n');
        }
        System.out.print(ans);
    }

    static int n;
    static int[][] map;
    static boolean[][] map_v;   // 섬 범위 결정 위한 방문배열
    static List<Island> islands;

    public static void define(int r, int c) {   // 각 섬의 시작-끝 좌표 저장 후 리스트 추가

        int endR = n-1, endC = n-1;
        for (int i = r; i < n-1; i++) {
            if (map[i+1][c]==0) {endR = i; break;}
        }
        for (int i = c; i < n-1; i++) {
            if (map[r][i+1]==0) {endC = i; break;}
        }

        for (int i = r; i <= endR; i++)
            for (int j = c; j <= endC; j++)
                map_v[i][j] = true;

        islands.add(new Island(r, c, endR, endC));
    }

    public static int get_distance(Island x, Island y) { // 섬 연결 가능하면 거리, 불가능하면 -1 반환

        if (x.startR <= y.startR && y.startR <= x.endR || y.startR <= x.startR && x.startR <= y.endR) {
            if (x.startC > y.endC) return x.startC - y.endC - 1;
            if (y.startC > x.endC) return y.startC - x.endC - 1;
        }

        if (x.startC <= y.startC && y.startC <= x.endC || y.startC <= x.startC && x.startC <= y.endC) {
            if (x.startR > y.endR) return x.startR - y.endR - 1;
            if (y.startR > x.endR) return y.startR - x.endR - 1;
        }

        return Integer.MAX_VALUE;
    }

    public static int find(int[] p, int x){
        if (p[x]==x) return x;         // 자기 자신을 가리키면 반환
        return p[x] = find(p, p[x]);   // 작은 idx를 루트로 설정
    }

}

class Island {
    int startR, startC, endR, endC;
    int link;

    public Island(int startR, int startC, int endR, int endC) {
        this.startR = startR;
        this.startC = startC;
        this.endR = endR;
        this.endC = endC;
    }
}

class Cost implements Comparable<Cost>{
    int x, y, d;

    public Cost(int x, int y, int d) {
        this.x = x;
        this.y = y;
        this.d = d;
    }

    @Override
    public int compareTo(Cost o) {
        return this.d - o.d;
    }
}