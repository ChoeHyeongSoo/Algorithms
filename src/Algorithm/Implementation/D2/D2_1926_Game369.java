package Algorithm.Implementation.D2;

import java.util.Scanner;

public class D2_1926_Game369 {

    public static int check(int i) {
        int cnt = 0;

        int temp = i;
        while (temp > 0) {
            int val = temp % 10;
            if (val == 3 || val == 9 || val == 6) cnt++;
            temp /= 10;
        }

        return cnt;
    }

    public static void main(String args[]) throws Exception {

//         System.setIn(new FileInputStream("res/input_D3_SW_8th_Cipher.txt"));

        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {

        int n = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            int cnt = check(i); // 자릿수마다 3, 6, 9 여부 체크
            if (cnt == 1) sb.append("- ");
            else if (cnt == 2) sb.append("-- ");
            else
                sb.append(i + " ");
        }

        String ans = sb.toString();
        System.out.println(ans);

        }
    }

}
