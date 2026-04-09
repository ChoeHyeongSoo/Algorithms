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

    public static int tsp(int idx, int state) { // DFS와 동일

        if (state == (1<<n)-1) {   // 기저 조건 : 모두 방문 n개의 비트 활성화 = cnt==n
            if (cost[idx][1] == 0) return INF;
            return cost[idx][1];
        }

        if (dp[idx][state] != -1) return dp[idx][state]; // -1로 초기화 : 이미 처리된 곳은 재방문 x = 다익스트라 생각!

        dp[idx][state] = INF; // 최소값 갱신 위해 INF로 설정

        for (int next = 1; next <= n; next++) { // 간선을 받을 때, 1~N으로 배열에 받았다면 bit와 인덱스 꼬이는 걸 주의!
            if (cost[idx][next]==0 || (state & (1<<(next-1)))!=0) continue; // 연결이 안 되어 있거나, 방문(visit[next]==true)했으면 스킵

            int next_cost = tsp(next, state | (1<<(next-1))); // 1<<next로 쓰고 싶다면 1번 노드를 arr[0]에 할당, idx를 0부터 생각
            dp[idx][state] = Math.min(dp[idx][state], cost[idx][next] + next_cost);
        }

        return dp[idx][state];
    }
}