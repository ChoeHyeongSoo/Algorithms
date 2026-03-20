import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        long[] dp = new long[n+1];
        dp[1] = 1; if (n>1) dp[2] = 3;
        for (int i = 3; i <= n; i++) dp[i] = (dp[i-1] + 2*dp[i-2])%10007;
        System.out.println(dp[n]);
    }
    static int n;
}