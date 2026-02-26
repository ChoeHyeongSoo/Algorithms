import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class SW_Test_A_LunchTime {
    /*
    점심식사시간
     */
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            int n = Integer.parseInt(br.readLine());
            StringTokenizer st;
            int[][] floor = new int[n][n];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++)
                    floor[i][j] = Integer.parseInt(st.nextToken());
            }



//            System.out.println("#" + test_case + " " + );
        }
    }

}