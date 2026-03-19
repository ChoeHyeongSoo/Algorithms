import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        int[][] prev = new int[5][2]; // 0: Max, 1: Min
        int[][] curr = new int[5][2];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= 3; i++) {              // DP 위한 초기화
            int k = Integer.parseInt(st.nextToken());
            for (int j = 0; j < 2; j++) prev[i][j] = k;
        }
        prev[0][0] = prev[4][0] = -1;
        prev[0][1] = prev[4][1] = Integer.MAX_VALUE;

        for (int i = 1; i < n; i++) {
            st = new StringTokenizer(br.readLine());    // 각 층 점화식
            for (int idx = 1; idx <= 3; idx++) {
                int k = Integer.parseInt(st.nextToken());
                curr[idx][0] = k + Math.max(prev[idx][0], Math.max(prev[idx+1][0], prev[idx-1][0]));
                curr[idx][1] = k + Math.min(prev[idx][1], Math.min(prev[idx+1][1], prev[idx-1][1]));
            }
            for (int idx = 1; idx <= 3; idx++) {
                prev[idx][0] = curr[idx][0];
                prev[idx][1] = curr[idx][1];
            }
        }
        
        int max = Math.max(Math.max(prev[1][0], prev[2][0]), prev[3][0]);
        int min = Math.min(Math.min(prev[1][1], prev[2][1]), prev[3][1]);
        System.out.println(max + " " + min);
    }
}