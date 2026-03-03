package Algorithm.Graph.ShortestPath;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class D5_SW_Application_3rd_BestRoute {
    /*
    회사와 집의 위치, 그리고 각 고객의 위치는 이차원 정수 좌표 (x, y)로 주어지고 (0 ≤ x ≤ 100, 0 ≤ y ≤ 100)
    두 위치 (x1, y1)와 (x2, y2) 사이의 거리는 |x1-x2| + |y1-y2|으로 계산된다.
    여기서 |x|는 x의 절대값을 의미하며 |3| = |-3| = 3이다. 회사의 좌표, 집의 좌표, 고객들의 좌표는 모두 다르다.
    회사에서 출발하여 N명의 고객을 모두 방문하고 집으로 돌아오는 경로 중 가장 짧은 것을 찾으려 한다.
    회사와 집의 좌표가 주어지고, 2명에서 10명 사이의 고객 좌표가 주어질 때, 회사에서 출발해서 이들을 모두 방문하고 집에 돌아가는 경로 중 총 이동거리가 가장 짧은 경로를 찾는 프로그램을 작성하라.
    고객의 수 N은 2≤N≤10 이다. 그리고 회사의 좌표, 집의 좌표를 포함한 모든 N+2개의 좌표는 서로 다른 위치에 있으며 좌표의 값은 0이상 100 이하의 정수로 이루어진다.
    */
    public static void main(String args[]) throws Exception {
        System.setIn(new FileInputStream("Algorithm/Graph/ShortestPath/input_D5_SW_Application_3rd_BestRoute.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            company = new location(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            home = new location(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            customers = new location[n];
            for (int i = 0; i < n; i++) {
                location tmp = new location(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
                customers[i] = tmp;
            }

            boolean[] v = new boolean[n];
            min = Integer.MAX_VALUE;
            perm(v, 0, 0, company);
            System.out.println("#" + test_case + " " + min);
        }
    }

    static int min;
    static location company, home;
    static location[] customers;
    public static int cal_d(location a, location b) {
        return Math.abs(a.r - b.r) + Math.abs(a.c - b.c);
    }

    public static void perm(boolean[] v, int distance, int depth, location tmp) {

        if (distance > min) return;

        if (depth==v.length) {
            distance+=cal_d(tmp, home);
            if(distance < min) min = distance;
        }

        for (int i = 0; i < v.length; i++) {
            if (v[i]) continue;
            v[i] = true;
            perm(v,distance+cal_d(tmp, customers[i]), depth+1, customers[i]);
            v[i] = false;
        }
    }

}

class location{
    int r, c;

    public location(int r, int c) {
        this.r = r;
        this.c = c;
    }
}