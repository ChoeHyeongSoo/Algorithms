package Algorithm.Graph.MST;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class D4_SW_Application_4th_ToOne { // 1251.
    public static void main(String args[]) throws Exception {
        StringBuilder ans = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            ans.append("#").append(test_case).append(" ");
            int n = Integer.parseInt(br.readLine());
            Island_1251[] islands = new Island_1251[n];
            StringTokenizer x_line = new StringTokenizer(br.readLine());
            StringTokenizer y_line = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                int x = Integer.parseInt(x_line.nextToken()), y = Integer.parseInt(y_line.nextToken());
                islands[i] = new Island_1251(x, y);
            }
            double ratio = Double.parseDouble(br.readLine());

            List<Edge> edges = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                for (int j = i+1; j < n; j++) {
                    edges.add(new Edge(i, j, cal_distance(islands[i], islands[j])));
                }
            }

            Collections.sort(edges, (a, b)-> Double.compare(a.d, b.d));

            parents = new int[n+1];
            for (int i = 1; i <= n; i++) parents[i] = i;

            int connected = 0;
            double cost = 0;
            for (int i = 0; i < edges.size(); i++) {

                Edge curr = edges.get(i);

                if (!union(curr.u, curr.v)) continue;
                connected++; cost+=(ratio * curr.d);
                if (connected==n-1) break;
            }
            ans.append(Math.round(cost)).append('\n');
        }
        System.out.print(ans);
    }

    static int[] parents;

    public static boolean union(int x, int y) {
        int root_x = find(x);
        int root_y = find(y);

        if (root_x==root_y) return false;

        if (root_x < root_y) parents[root_y] = root_x;
        else parents[root_x] = root_y;

        return true;
    }

    public static int find(int v) {
        if (parents[v]==v) return v;
        return parents[v] = find(parents[v]);
    }

    public static double cal_distance(Island_1251 u, Island_1251 v) {
        long dx = u.x - v.x;
        long dy = u.y - v.y;
        return dx * dx + dy * dy;
    }

}

class Island_1251 {
    int x, y;

    public Island_1251(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Edge {
    int u, v;
    double d;

    public Edge(int u, int v, double d) {
        this.u = u;
        this.v = v;
        this.d = d;
    }
}