package Algorithm.DP;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class SW_Test_A_SwimFee {
    /*

     */
    public static void main(String args[]) throws Exception {
        StringBuilder ans = new StringBuilder();
        System.setIn(new FileInputStream("Algorithm/DP/input_SW_Test_A_SwimFee.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            ans.append("#").append(test_case).append(" ");
            StringTokenizer st = new StringTokenizer(br.readLine());
            fee = new int[4];
            for (int i = 0; i < 4; i++) // 1일 / 1달 / 3달 / 1년
                fee[i] = Integer.parseInt(st.nextToken());
            st =  new StringTokenizer(br.readLine());
            plans = new int[13];
            for (int i = 1; i <= 12; i++)
                plans[i] = Integer.parseInt(st.nextToken());  // 큐에 입력받기?

            // Compare
            int[] dp = new int[13];
            dp[1] = Math.min(plans[1]*fee[0], fee[1]);
            dp[2] = dp[1] + Math.min(plans[2]*fee[0], fee[1]);
            for (int i = 3; i <= 12; i++) {

                int a = dp[i-1] + Math.min(plans[i]*fee[0], fee[1]);
                int b = dp[i-3] + fee[2];
                dp[i] = Math.min(a, b);
            }

            int pay = Math.min(fee[3], dp[12]);
            ans.append(pay).append('\n');
        }
        System.out.print(ans);
    }

    static int[] fee, plans;
}