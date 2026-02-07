package Simulation.D2;

import java.util.*;

class D2_2805_Harvest {

    public static void main(String args[]) throws Exception {

        // System.setIn(new FileInputStream("res/test_input.txt"));

        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {

            int n = sc.nextInt();
            int[][] farm = new int[n][n];

            for (int i = 0; i < n; i++) {
                String line = sc.next();
                for (int j = 0; j < n; j++) {
                    farm[i][j] = line.charAt(j) - '0';  // 주의! (int)line.charAt(j)는 '0'==48 ++
                }
            }

            int mid = 0;
            for (int i = 0; i < n; i++) {
                mid+=farm[n/2][i];
            }
            int upside = 0, downside = 0;
            for (int i = 1; i <= n / 2; i++) {
                for (int j = i; j < n - i; j++) { // j = 0; j < n-2*i (횟수로 카운트)
                    upside+=farm[n/2- i][j];      // [i+j]로 설정 가능
                    downside+=farm[n/2+i][j];
                }
            }

            int ans = mid + upside + downside;
            System.out.println("#" + test_case + " " + ans);

        }
    }
}