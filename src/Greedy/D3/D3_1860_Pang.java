package Greedy.D3;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Scanner;

class D3_1860_Pang {

    public static void main(String args[]) throws Exception {

        System.setIn(new FileInputStream("res/pang_input.txt"));

        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {

            int n = sc.nextInt();
            int m = sc.nextInt();   // m sec
            int k = sc.nextInt();   // k개
            int[] ETA = new int[n];

            for (int i = 0; i < n; i++) {
                ETA[i] = sc.nextInt();
            }

            String ans = "Impossible";
            Arrays.sort(ETA);

            int sold = 0, left = 0;
            while_loop:
            while (true) {
                if (ETA[0] < m) break;
                check_loop:
                for (int i = 0; i < n; i++) {
                    sold ++;
                    left = (ETA[i] / m * k) - sold;
                    if (left < 0) break while_loop;
                }
                ans = "Possible";
                break;
            }

            System.out.println("#" + test_case + " " + ans);

        }
    }
}