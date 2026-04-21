import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
 
public class Solution {
     
    public static void main(String[] args) throws Exception {
         
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder ans = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
 
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            adj = new int[n+1][n+1];
            dist = new int[n+1][n+1];
            for (int i = 1; i <= n; i++)
                for (int j = 1; j <= n; j++)
                    adj[i][j] = Integer.parseInt(st.nextToken());
             
            for (int[] r: dist) Arrays.fill(r, Integer.MAX_VALUE);
            pq = new PriorityQueue<>((a, b)->a[1]-b[1]);
             
            for (int i = 1; i <= n; i++) prim(i);
             
            int min = Integer.MAX_VALUE;
            for (int i = 1; i <= n; i++) {
                int tmp = 0;
                for (int j = 1; j <=n; j++) {
                    if (i==j) continue;
                    tmp+=dist[i][j];
                }
                min = Math.min(min, tmp);
            }
             
            ans.append("#" + tc + " ").append(min).append('\n');
        }
         
        System.out.println(ans);
    }
     
    static int n;
    static int[][] dist, adj;
    static PriorityQueue<int[]> pq;
     
    static void prim(int v) {
         
        pq.add(new int[] {v, 0});
        dist[v][v] = 0;
        int cnt = 1;
        while (!pq.isEmpty()) {
             
            int[] curr = pq.poll();
             
            if (dist[v][curr[0]] < curr[1]) continue;    // pq 내부에서 같은 값들은 처리
            if (cnt==n) return;
             
            for (int idx = 1; idx <= n; idx++) {
                 
                if (adj[curr[0]][idx]==0) continue;
                 
                if (dist[v][idx] <= curr[1] + adj[curr[0]][idx]) continue;   // 같은 경우도 갱신 x
                dist[v][idx] =  curr[1] + adj[curr[0]][idx];
                pq.offer(new int[] {idx, dist[v][idx]});
                cnt++;
            }
        }
    }
}