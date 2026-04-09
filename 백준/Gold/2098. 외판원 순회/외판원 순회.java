import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
public class Main{
    static final int INF = 10000000;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        cost = new int[n+1][n+1];   // 간선 비용 배열 : 간선만 주어지면 직접 인접리스트 혹은 인접행렬 생성
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) cost[i][j] = Integer.parseInt(st.nextToken());
        }

        dp = new int[n+1][1<<n]; // dp[Index][State] : 현재 위치를 방문 상태별로 정리
        for (int i = 1; i <= n; i++) Arrays.fill(dp[i], -1);

        System.out.println(tsp(1, 1<<0));
    }
    static int n;
    static int[][] cost;
    static int[][] dp;

    public static int tsp(int idx, int state) {

        if (state == (1<<n)-1) {
            if (cost[idx][1] == 0) return INF;
            return cost[idx][1];
        }

        if (dp[idx][state] != -1) return dp[idx][state];

        dp[idx][state] = INF;

        for (int next = 1; next <= n; next++) {
            if (cost[idx][next]==0 || (state & (1<<(next-1)))!=0) continue;

            int next_cost = tsp(next, state | (1<<(next-1)));
            dp[idx][state] = Math.min(dp[idx][state], cost[idx][next] + next_cost);
        }

        return dp[idx][state];
    }
}