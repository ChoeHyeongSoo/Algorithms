package Algorithm.Implematation.D2;

import java.util.Scanner;

public class D2_1969_BruteForce {
    public static void main(String args[]) throws Exception {

//         System.setIn(new FileInputStream("res/input.txt"));

        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {

            int n = sc.nextInt();
            int m = sc.nextInt();

            int[] a = new int[n];
            int[] b = new int[m];

            for (int i = 0; i < n; i++) {
                a[i] = sc.nextInt();
            }
            for (int i = 0; i < m; i++) {
                b[i] = sc.nextInt();
            }

            int ans = Integer.MIN_VALUE;
            if (n > m) {
                int[] temp = a;
                int tmp = n;
                a = b; b = temp;
                n = m; m = tmp;
            }

            for (int i = 0; i <= m-n; i++) {
                int multiple_sum = 0;
                for (int j = 0; j < n; j++) {
                    multiple_sum += (a[j]*b[i+j]);
                }
                ans = Math.max(ans, multiple_sum);
            }

            System.out.println("#" + test_case + " " + ans);

        }
    }
}