import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        adj = new int[n+1][n+1];
        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken()), to = Integer.parseInt(st.nextToken()), dist = Integer.parseInt(st.nextToken());
            adj[from][to] = adj[to][from] = dist;
        }
        st = new StringTokenizer(br.readLine());
        v1 = Integer.parseInt(st.nextToken()); v2 = Integer.parseInt(st.nextToken());

        // 1. 다익스트라x3으로 각 거리 배열 받아 조건 삭제 *INF를 간선x가중치 최대값으로 설정하여 오버플로우 방지
        int[] dist_s = dijk_arr(1) ,dist_v1 = dijk_arr(v1), dist_v2 = dijk_arr(v2);
        int pos1 = dist_s[v1] + dist_v1[v2] + dist_v2[n];
        int pos2 = dist_s[v2] + dist_v2[v1] + dist_v1[n];
        int result = (Math.min(pos1, pos2) >= INF) ? -1 : Math.min(pos1, pos2);
//        System.out.println(result);

        //2. 비트마스킹 + 다익스트라 : 비트마스킹을 활용한 상태 처리
        System.out.println(dijk_bitmasking(1, n));
    }

    static int n, e, v1, v2;
    static final int INF = 200000 * 1000;
    static int[][] adj;

    static int[] dijk_arr(int start) {

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b)->a[1]-b[1]);
        int[] dist = new int[n+1];
        Arrays.fill(dist, INF);
        pq.offer(new int[]{start, 0});
        dist[start] = 0;

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            if (dist[curr[0]] < curr[1]) continue;

            for (int i = 1; i <= n; i++) {
                if (adj[curr[0]][i] == 0) continue;
                if (dist[i] <= curr[1]+adj[curr[0]][i]) continue;
                dist[i] = curr[1] + adj[curr[0]][i];
                pq.offer(new int[]{i, dist[i]});
            }
        }

        return dist;
    }

    static int dijk_bitmasking(int start, int end) {

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b)->a[1]-b[1]);
        int[][] dist = new int[n+1][1<<2]; // 거쳐야 할 노드가 2개 이므로 1<<2(4)
        for (int[] r : dist) Arrays.fill(r, INF);

        int init_state = (start==v1)?1:(start==v2)?2:0; // 1이 v1, v2에 포함되는 케이스 처리
        pq.offer(new int[]{start, 0, init_state});
        dist[start][init_state] = 0;

        while (!pq.isEmpty()) {
            int[] curr = pq.poll(); // 0: idx, 1: cost, 2: state
            
            if (curr[0]==end && curr[2]==3) return curr[1]; // end에서 state 모두 처리라면 반환
            if (dist[curr[0]][curr[2]] < curr[1]) continue;

            for (int i = 1; i <= n; i++) {
                if (adj[curr[0]][i] == 0) continue;
                int state = (curr[2] | (i==v1?1:i==v2?2:0));    // bitmasking
                if (dist[i][state] <= curr[1]+adj[curr[0]][i]) continue;
                dist[i][state] = curr[1] + adj[curr[0]][i];
                pq.offer(new int[]{i, dist[i][state], state});
            }
        }

        return -1;
    }
}