import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main{
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[][] info = new int[n][2];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            info[i][0] = Integer.parseInt(st.nextToken());
            info[i][1] = Integer.parseInt(st.nextToken());
        }

        int[] table = new int[k+1];
        for (int idx = 0; idx < n; idx++) {

            int w = info[idx][0];
            int v = info[idx][1];

            for (int i = k; i >= w; i--) {
                table[i] = Math.max(table[i], table[i-w] + v);
            }
        }

        System.out.println(Arrays.stream(table).max().getAsInt());
    }
}