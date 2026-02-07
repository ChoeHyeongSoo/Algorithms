package DataStructure.String;

import java.util.Scanner;

public class D1_21936_PalindromeLength {

    public static void main(String args[]) throws Exception {

        //  System.setIn(new FileInputStream("res/sadari_input.txt"));

        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {

            int n = sc.nextInt();
            int m = sc.nextInt();


            String ans = "NONE";
            String line = sc.next();
            for (int i = 0; i <= line.length()-m; i++) {
                StringBuilder sb = new StringBuilder(line.substring(i, i+m));
                String check = sb.toString(), palindrome = sb.reverse().toString();
                if (check.equals(palindrome)) {ans = check; break;}
//                if (sb.toString().equals(sb.reverse().toString())) {ans = sb.toString(); break;}
            }

            System.out.println("#" + test_case + " " + ans);

        }
    }

}
