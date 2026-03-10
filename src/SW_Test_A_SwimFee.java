import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class SW_Test_A_SwimFee {
    /*

     */
    public static void main(String args[]) throws Exception {
        StringBuilder ans = new StringBuilder();
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
                plans[i] = Integer.parseInt(st.nextToken());

            int[] pay = new int[4];
            int[] sedal = new int[11];
            for (int i = 1; i <= 10; i++) {
                int cnt = 0;
                for (int j = i; j < i+3; j++)
                    if (plans[j]>0) cnt++;
                sedal[i] = cnt; // 세달이 2인거는 3달-1달-1일 비교
            }

            // Compare




            ans.append('\n');
        }
        System.out.print(ans);
    }

    static int[] fee, plans;
}