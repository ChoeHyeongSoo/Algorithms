import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Solution {
    public static void main(String args[]) throws Exception {
        StringBuilder ans = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            ans.append("#").append(test_case).append('\n');
            int n = Integer.parseInt(br.readLine());
            int[] money = new int[8];
            int div = 50000;
            money[0] = n/div;
            for (int i = 1; i < 8; i++) {
                n -= money[i-1]*div;
                if (i%2==1) div /= 5;
                else div /= 2;
                money[i] = n/div;
            }

            for (int k : money) ans.append(k).append(" ");

            ans.append('\n');
        }
        System.out.print(ans);
    }
}