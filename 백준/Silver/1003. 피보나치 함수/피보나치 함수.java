import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        int[] each = new int[n];
        for (int i = 0; i < n; i++) each[i] = Integer.parseInt(br.readLine());
        int idx = Arrays.stream(each).max().getAsInt();

        // 2: 1 0 (1번씩) 3: 2 1 (1 0) 1, 4: 3 (1 0 1) 2 (1 0)
        int[][] dp = new int[idx+1][2];
        dp[0][0] = 1; if(idx>0) dp[1][1] = 1;
        for (int i = 2; i <= idx; i++) {
            dp[i][0] = dp[i-1][0] + dp[i-2][0];
            dp[i][1] = dp[i-1][1] + dp[i-2][1];
        }
        StringBuilder sb = new StringBuilder();
        for (int k : each) sb.append(dp[k][0]).append(" ").append(dp[k][1]).append('\n');
        System.out.println(sb);
    }
    static int n;
}