import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] degree = new int[n+1];
        List<Integer>[] adj = new ArrayList[n+1];
        for (int i = 1; i <= n; i++) adj[i] = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()), b =Integer.parseInt(st.nextToken());
            adj[a].add(b); degree[b]++;
        }

        StringBuilder sb = new StringBuilder();
        Deque<Integer> q = new ArrayDeque<>();
        for (int i = 1; i <= n; i++) {
            if (degree[i]==0) {
                q.offer(i);
                sb.append(i).append(" ");
            }
        }
        while (!q.isEmpty()){
            int curr = q.poll();

            for (int k : adj[curr]) {
                if (--degree[k]==0) {
                    q.offer(k);
                    sb.append(k).append(" ");
                }
            }
        }
        System.out.println(sb);
    }
    static int n;
}