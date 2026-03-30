import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        sb = new StringBuilder();
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        arr = new int[n+2][n+2];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) arr[i][j] = Integer.parseInt(st.nextToken()) + arr[i][j-1];
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken()), y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken()), y2 = Integer.parseInt(st.nextToken());
            action(x1, y1, x2, y2);
        }
        System.out.println(sb);
    }

    static int n;
    static int[][] arr;
    static StringBuilder sb;

    private static void action(int x1, int y1, int x2, int y2) {
        int sum = 0;
        for (int i = x1; i <= x2; i++) sum+=(arr[i][y2]-arr[i][y1-1]);
        sb.append(sum).append('\n');
    }

}