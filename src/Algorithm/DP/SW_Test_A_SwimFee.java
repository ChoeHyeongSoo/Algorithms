package Algorithm.DP;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class SW_Test_A_SwimFee {
    /*
    수영장에서 판매하고 있는 이용권은 아래와 같이 4 종류이다.
       ① 1일 이용권 : 1일 이용이 가능하다.
       ② 1달 이용권 : 1달 동안 이용이 가능하다. 1달 이용권은 매달 1일부터 시작한다.
       ③ 3달 이용권 : 연속된 3달 동안 이용이 가능하다. 3달 이용권은 매달 1일부터 시작한다.
       (11월, 12월에도 3달 이용권을 사용할 수 있다 / 다음 해의 이용권만을 구매할 수 있기 때문에 3달 이용권은 11월, 12월, 1윌 이나 12월, 1월, 2월 동안 사용하도록 구매할 수는 없다.)
       ④ 1년 이용권 : 1년 동안 이용이 가능하다. 1년 이용권은 매년 1월 1일부터 시작한다.
    이용 계획에 나타나는 숫자는 해당 달에 수영장을 이용할 날의 수를 의미한다.
    각 이용권의 요금과 각 달의 이용 계획이 입력으로 주어질 때, 가장 적은 비용으로 수영장을 이용할 수 있는 방법을 찾고 그 비용을 정답으로 출력하는 프로그램을 작성하라.
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