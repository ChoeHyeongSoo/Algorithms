package DataStructure.Array;

import java.util.Arrays;
import java.util.Scanner;
import java.io.FileInputStream;

class D2_1974_SudokuVerify {

    public static void main(String args[]) throws Exception {

        //  System.setIn(new FileInputStream("res/input.txt"));

        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {

            int n = 9;
            int[][] arr = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    arr[i][j] = sc.nextInt();
                }
            }

            int ans = 1;
            while (true) {
                for (int i = 0; i < n; i++) {        // verify row, col
                    int row = 0, col = 0;
                    for (int j = 0; j < n; j++) {
                        row += arr[i][j];
                        col += arr[j][i];
                    }
                    if (row != 45 || col != 45) {
                        ans = 0;
                        break;
                    }
                }
                for (int k = 0; k < 3; k++) {       // verify box
                    int box = 0;
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++) {
                            box += arr[3 * k + i][3 * k + j];
                        }
                    }
                    if (box != 45) { ans =0; break;
                    }
                }
                break;
            }


            System.out.println("#" + test_case + " " + ans);

        }
    }
}