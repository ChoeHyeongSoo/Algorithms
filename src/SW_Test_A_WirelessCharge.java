import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class SW_Test_A_WirelessCharge {
    /*

     */
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int[] timeline_A = new int[m+1], timeline_B = new int[m+1];
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= m; i++)
                timeline_A[i] = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= m; i++)
                timeline_B[i] = Integer.parseInt(st.nextToken());
            Charger[] chargers = new Charger[c];
            for (int i = 0; i < c; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken()), y = Integer.parseInt(st.nextToken());
                int coverage = Integer.parseInt(st.nextToken());
                int performance = Integer.parseInt(st.nextToken());
                chargers[i] = new Charger(x, y, coverage, performance);
            }

            int[] dir_r = {0, -1, 0, 1, 0}, dir_c = {0, 0, 1, 0, -1};

            // 초단위 시뮬레이션, 각 충전량 합계

            // 충전: 모든 충전기에 대해서 현재 위치가 퍼포먼스 내에 있는지
            // a, b 같은 충전 선택된 경우 - 다른 거 선택하는 거에 대한 합산 비교 - 비교군 없으면 해당값 사용


//            System.out.println("#" + test_case + " " + );
        }
    }

}

class Charger{
    int r, c, coverage, performance;

    public Charger(int r, int c, int coverage, int performance) {
        this.r = r;
        this.c = c;
        this.coverage = coverage;
        this.performance = performance;
    }
}