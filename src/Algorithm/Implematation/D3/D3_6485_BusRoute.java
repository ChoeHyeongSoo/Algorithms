package Algorithm.Implematation.D3;

import java.io.FileInputStream;
import java.util.*;

class D3_6485_BusRoute {

    public static void main(String args[]) throws Exception {

        // System.setIn(new FileInputStream("res/test_input.txt"));

        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {

            int n = sc.nextInt();
            int[] a = new int[n];
            int[] b = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = sc.nextInt();
                b[i] = sc.nextInt();
            }
            int p = sc.nextInt();
            int[] c = new int[p];
            for (int i = 0; i < p; i++) {
                c[i] = sc.nextInt();
            }

            int[] ans = new int[p];

            for (int i = 0; i < p; i++) {
                for (int j = 0; j < n; j++) {
                    if (c[i] >= a[j] && c[i] <= b[j]) ans[i]++;
                }
            }

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < p; i++) {
                sb.append(ans[i] + " ");
            }
            System.out.println("#" + test_case + " " + sb.toString());
//            System.out.println("#" + test_case + " " + ans);

        }
    }
}