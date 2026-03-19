import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        map = new int[n][3];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) map[i][j] = Integer.parseInt(st.nextToken());
        }

        int[][] prev = new int[3][2]; // 0: Max, 1: Min
        int[][] curr = new int[3][2];

        for (int i = 0; i < 3; i++) {   // Init
            prev[i][0] = map[0][i];
            prev[i][1] = map[0][i];
        }

        for (int f = 1; f < n; f++) {
            curr[0][0] = Math.max(prev[0][0], prev[1][0]) + map[f][0];
            curr[1][0] = Math.max(Math.max(prev[0][0], prev[1][0]), prev[2][0]) + map[f][1];
            curr[2][0] = Math.max(prev[1][0], prev[2][0]) + map[f][2];

            curr[0][1] = Math.min(prev[0][1], prev[1][1]) + map[f][0];
            curr[1][1] = Math.min(Math.min(prev[0][1], prev[1][1]), prev[2][1]) + map[f][1];
            curr[2][1] = Math.min(prev[1][1], prev[2][1]) + map[f][2];

            for (int i = 0; i < 3; i++)
                for (int j = 0; j < 2; j++)
                    prev[i][j] = curr[i][j];
        }

        int max = Math.max(Math.max(prev[0][0], prev[1][0]), prev[2][0]);
        int min = Math.min(Math.min(prev[0][1], prev[1][1]), prev[2][1]);

        System.out.println(max + " " + min);
    }

    static int[][] map;
}