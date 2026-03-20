import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        long[] dp = new long[n+1];
        dp[1] = 1; if (n>1) dp[2] = 2; // 피보나치 수열: 항상 2가 넘어야 가능 - 입력1에 대해 2는 인덱스 에러 - 이러한 예외 케이스 항상 주의!
        // 타일은 n이 증가함에 따라 가로 / 세로 붙이는 케이스 존재
        for (int i = 3; i <= n; i++) dp[i] = (dp[i-1] + dp[i-2])%10007; // 덧셈마다 나머지를 취해줘야 한다
        System.out.println(dp[n]);  // 세로: 1개 공간(dp[i-1]) 필요 / 가로: 2개 공간(dp[i-2]) 필요
    }
    static int n;
}