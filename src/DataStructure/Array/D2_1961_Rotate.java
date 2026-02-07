package DataStructure.Array;

import java.io.FileInputStream;
import java.util.*;

class D2_1961_Rotate {

    public static void main(String args[]) throws Exception {

//         System.setIn(new FileInputStream("res/input.txt"));

        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {

            int n = sc.nextInt();
            int[][] arr = new int[n][n];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    arr[i][j] = sc.nextInt();
                }
            }

            int[][] arr_90 = new int[n][n];
            int[][] arr_180 = new int[n][n];
            int[][] arr_270 = new int[n][n];

            for (int i = 0; i < n; i++) {       // 인덱스 컨트롤 다양하게 해보기 - i는 arr의 row, j는 arr의 col에 접근
                for (int j = 0; j < n; j++) {
                    arr_90[i][j] = arr[n - 1 - j][i];
                    arr_180[i][j] = arr[n - 1 - i][n - 1 - j];
                    arr_270[i][j] = arr[j][n - 1 - i];
                }
            }

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n; i++) {
                StringBuilder sb1 = new StringBuilder();
                StringBuilder sb2 = new StringBuilder();
                StringBuilder sb3 = new StringBuilder();
                for (int j = 0; j < n; j++) {
                    sb1.append(arr_90[i][j]);
                    sb2.append(arr_180[i][j]);
                    sb3.append(arr_270[i][j]);
                }
                sb.append(sb1 + " " + sb2 + " " + sb3);
                sb.append("\n");
            }

            System.out.print("#" + test_case + "\n" + sb);

        }
    }

}
