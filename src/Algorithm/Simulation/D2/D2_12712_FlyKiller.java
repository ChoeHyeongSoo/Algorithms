package Algorithm.Simulation.D2;

import java.util.Scanner;

public class D2_12712_FlyKiller {

    public static void main(String args[]) throws Exception {

        //  System.setIn(new FileInputStream("res/input.txt"));

        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {

            int n = sc.nextInt();
            int m = sc.nextInt();
            int[][] arr = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    arr[i][j] = sc.nextInt();
                }
            }

            int ans = 0;

            // 각 방향 벡터 설정
            int[] cross_x = {0, 1, 0, -1}, cross_y = {1, 0, -1, 0};
            int[] x_x = {1, 1, -1, -1}, x_y = {1, -1, -1, 1};

            for (int i = 0; i < n; i++) {           // i, j range &&
                for (int j = 0; j < n; j++) {
                    int temp_cross = arr[i][j];
                    int temp_x = arr[i][j];
                    for (int k = 1; k < m; k++) {
                        for (int l = 0; l < 4; l++) {
                            int row_cross = i+cross_y[l]*k, col_cross = j+cross_x[l]*k;
                            int row_x = i+x_y[l]*k, col_x = j+x_x[l]*k;
                            if (col_x < n && col_x >= 0 && row_x >= 0 && row_x < n)
                                temp_x+=arr[row_x][col_x];
                            if (col_cross < n && col_cross >= 0 && row_cross >= 0 && row_cross < n)
                                temp_cross +=arr[row_cross][col_cross];
                        }
                    }
                    ans = Math.max(ans, Math.max(temp_x, temp_cross));
                }
            }

            System.out.println("#" + test_case + " " + ans);
        }
    }

}
