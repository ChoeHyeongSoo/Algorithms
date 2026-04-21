import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Solution {
    public static void main(String args[]) throws Exception {
        StringBuilder ans = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = 10;
        for (int test_case = 1; test_case <= T; test_case++) {
            ans.append("#").append(test_case).append(" ");
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            adj = new ArrayList[101];
            for (int i = 1; i <= 100; i++) adj[i] = new ArrayList<>();

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n/2; i++) {
                int from = Integer.parseInt(st.nextToken()), to = Integer.parseInt(st.nextToken());
                adj[from].add(to);
            }

            int[] dist = new int[101];
            PriorityQueue<int[]> pq = new PriorityQueue<>((a, b)->a[1]-b[1]);
            pq.add(new int[]{s, 1});
            dist[s] = 1; int depth = 0;

            while(!pq.isEmpty()) {

                int[] curr = pq.poll();

                for (int next : adj[curr[0]]) {

                    if (dist[next] > 0) continue;
                    dist[next] = curr[1] + 1;
                    pq.offer(new int[]{next, dist[next]});
                    depth = Math.max(depth, dist[next]);
                }
            }

            for (int i = 100; i > 0; i--) {
             if (dist[i]==depth) { ans.append(i).append('\n'); break; }
            }
        }
        System.out.print(ans);
    }

    static List<Integer>[] adj;
}