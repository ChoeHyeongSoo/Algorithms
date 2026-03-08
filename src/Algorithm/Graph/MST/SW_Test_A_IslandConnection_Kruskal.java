package Algorithm.Graph.MST;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class SW_Test_A_IslandConnection_Kruskal {
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

            // 2. 비용 저장
            int i_n = islands.size(), cost_idx = 0;
            List<Cost_UF> costs = new ArrayList<>();

            for (int i = 0; i < i_n-1; i++) {       // 가능한 것들만 리스트로 추가
                for (int j = i+1; j < i_n; j++) {
                    int d = get_distance(i, j);
                    if (d != Integer.MAX_VALUE) costs.add(new Cost_UF(i, j, d));
                }
            }

            Collections.sort(costs);

            // 3. Kruskal
            int cost = 0, edge = 0;
            int[] v = new int[i_n];
            for (int i = 0; i < i_n; i++) v[i] = i;
            for (Cost_UF tmp : costs) {
                if (!union(v, tmp.x, tmp.y)) {cost+=tmp.d; edge++;} // 같은 그룹 ? 연결하고 비용 업데이트
                if (edge == i_n-1) break;
            }
            cost = (edge == i_n-1) ? cost : -1; // 연결 안 된 섬이 있으면 -1 처리

            ans.append(cost).append('\n');
        }
        System.out.print(ans);
    }

    static int n;
    static int[][] map;
    static boolean[][] map_v;   // 섬 범위 결정 위한 방문배열
    static List<Island_UF> islands;

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

        islands.add(new Island_UF(r, c, endR, endC));
    }

    public static int get_distance(int a, int b) { // 섬 연결 가능하면 거리, 불가능하면 -1 반환

        Island_UF x = islands.get(a);
        Island_UF y = islands.get(b);

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

    public static int find(int[] p, int o){
        if (p[o]== o) return o;         // 자기 자신을 가리키면 반환
        return p[o] = find(p, p[o]);   // 작은 idx를 루트로 설정
    }

    public static boolean union(int[] p, int x, int y){
        int rootX = find(p, x);
        int rootY = find(p, y);

        if (rootX == rootY) return true;

        if (rootX < rootY) p[rootY] = rootX;
        else p[rootX] = rootY;

        return false;
    }

}

class Island_UF {
    int startR, startC, endR, endC;

    public Island_UF(int startR, int startC, int endR, int endC) {
        this.startR = startR;
        this.startC = startC;
        this.endR = endR;
        this.endC = endC;
    }
}

class Cost_UF implements Comparable<Cost_UF>{
    int x, y, d;

    public Cost_UF(int x, int y, int d) {
        this.x = x;
        this.y = y;
        this.d = d;
    }

    @Override
    public int compareTo(Cost_UF o) {
        return this.d - o.d;
    }
}