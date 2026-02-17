package Algorithm.Simulation.D4;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class D3_2117_HomeSecurity_Coordinate {
    /*

     */
    public static void main(String args[]) throws Exception {
        System.setIn(new FileInputStream("res/TreeHeight.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

//        Scanner sc = new Scanner(System.in);
        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            ArrayList<int[]> home_location = new ArrayList<>();
            int cnt = 0;
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    int h = Integer.parseInt(st.nextToken());
                    if (h == 1) {
                        home_location.add(new int[]{i, j});
                        cnt++;
                    }
                }
            }

            int k = (int)((1 + Math.sqrt(1 - 2 * (1 - cnt * m))) / (double) 2);
            int ans = 0;
            outer_loop:
            while (k>=1) {
                int cost = k * k + (k - 1) * (k - 1);
                inner_loop:
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        int tmp = 0;
                        for (int check = 0; check < home_location.size(); check++) {
                            int d_y = home_location.get(check)[0] - i;
                            int d_x = home_location.get(check)[1] - j;
                            int distance = Math.abs(d_x) + Math.abs(d_y);
                            if (distance <= k-1) tmp++;
                        }
                        if (cost <= tmp*m)
                            ans = Math.max(ans, tmp);
                    }
                }
                k--;
            }

            System.out.println("#" + test_case + " " + ans);
        }
    }

}