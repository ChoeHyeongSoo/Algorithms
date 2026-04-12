import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        grid = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) grid[i][j] = Integer.parseInt(st.nextToken());
        }

        dp = new int[n][m];
        for (int[] r: dp) Arrays.fill(r, -1);
        dfs(0, 0, grid[0][0]);

        System.out.println(dp[0][0]);
    }
    static int n, m;
    static int[][] grid, dir = {{1,0,-1,0},{0,1,0,-1}}, dp;

    static int dfs(int r, int c, int h) {   // Top-Down DP

        if (r==n-1 && c==m-1) return 1; // 도착하면 1을 가지고 리턴 (가능한 케이스 +1을 위함)

        if (dp[r][c]!=-1) return dp[r][c];  // 이미 메모이제이션한 곳이라면 리턴

        dp[r][c] = 0;   // 메모이제이션 위한 초기화
        for (int d = 0; d < 4; d++) {   // 탐색

            int next_r = r + dir[0][d];
            int next_c = c + dir[1][d];

            if (next_r < 0 || next_r >= n || next_c < 0 || next_c >= m) continue;
            if (grid[next_r][next_c] >= h) continue;
            dp[r][c] += dfs(next_r, next_c, grid[next_r][next_c]);  // 현재 테이블에 가능한 경우의 수 합해지도록
        }
        return dp[r][c];
    }
}