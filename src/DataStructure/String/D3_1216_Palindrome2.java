package DataStructure.String;

import java.util.*;

class D3_1216_Palindrome2 {

    public static void main(String args[]) throws Exception {

//          System.setIn(new FileInputStream("res/input.txt"));

        Scanner sc = new Scanner(System.in);
        int T;
        T = 10;

        for (int test_case = 1; test_case <= T; test_case++) {
            int tc = sc.nextInt();
            int n = 100;
            char[][] arr = new char[n][n];
            String[] row = new String[n], col = new String[n];
            for (int i = 0; i < n; i++) {
                String line = sc.next();
                for (int j = 0; j < n; j++) {
                    arr[i][j] = line.charAt(j);
                }
                row[i] = line;
            }

            int ans = 0;
            for (int i = 0; i < n; i++) {
                StringBuilder sb = new StringBuilder();
                for (int j = 0; j < n; j++) {
                    sb.append(arr[j][i]);
                }
                col[i] = sb.toString();
            }

            String r, c;
            main_loop:
            for (int i = n; i > 0; i--) {
                for (int k = 0; k < n; k++) {
                    for (int j = 0; j <= n - i; j++) {
                        r = row[k].substring(j, j + i);
                        c = col[k].substring(j, j + i);
                        StringBuilder sb_r = new StringBuilder(r),  sb_c = new StringBuilder(c);
                        String r_palindrome = sb_r.reverse().toString(), c_palindrome = sb_c.reverse().toString();
                        if (r.contains(r_palindrome) || c.contains(c_palindrome)) {
                            ans = i; break main_loop;
                        }
                    }
                }
            }

            System.out.println("#" + tc + " " + ans);

        }
    }
}