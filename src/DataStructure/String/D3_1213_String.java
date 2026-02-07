package DataStructure.String;

import java.io.FileInputStream;
import java.util.*;

class D3_1213_String {

    public static void main(String args[]) throws Exception {

        // System.setIn(new FileInputStream("res/test_input.txt"));

        Scanner sc = new Scanner(System.in);
        int T;
        T = 10;

        for (int test_case = 1; test_case <= T; test_case++) {

            int tc = sc.nextInt();
            String target = sc.next();
            String line = sc.next();
            int ans = 0;

            for (int i = 0; i <= line.length() - target.length(); i++) {
                if (line.substring(i, i+target.length()).equals(target)) ans++;
            }

            System.out.println("#" + test_case + " " + ans);

        }
    }
}