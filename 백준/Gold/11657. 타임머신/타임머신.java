import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        edges = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken()), to = Integer.parseInt(st.nextToken()), w = Integer.parseInt(st.nextToken());
            edges.add(new int[]{from, to, w});
        }

        ans = new StringBuilder();
        System.out.println(bellman_ford(1) ? ans : -1);
    }

    static int n, m;
    static List<int[]> edges;   // 벨만-포드 : 간선 기준
    static StringBuilder ans;
    static boolean bellman_ford(int s) {
        long[] dist = new long[n+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[s] = 0;

        for (int i = 0; i < n-1; i++) { // V-1번 : 최소값 탐색
            for (int[] curr : edges) {
                if (dist[curr[0]]==Integer.MAX_VALUE) continue;
                if (dist[curr[1]] <= dist[curr[0]] + curr[2]) continue;
                dist[curr[1]] = dist[curr[0]] + curr[2];
            }
        }

        for (int[] curr : edges)    // V 번째 : 음수 사이클 판정
            if (dist[curr[1]]!=Integer.MAX_VALUE && dist[curr[1]] > dist[curr[0]] + curr[2]) return false;

        for (int i = 2; i <= n; i++) ans.append((dist[i]<Integer.MAX_VALUE) ? dist[i] : -1).append("\n");

        return true;
    }
}