import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        v = Integer.parseInt(st.nextToken());
        adj = new ArrayList[v+1];
        for (int i = 1; i <= v; i++) adj[i] = new ArrayList<>();
        for (int i = 0; i < v; i++) {
            st = new StringTokenizer(br.readLine());
            int curr = Integer.parseInt(st.nextToken());
            int target = 0;
           while (true) {
               target = Integer.parseInt(st.nextToken());
               if (target==-1) break;
               int dist = Integer.parseInt(st.nextToken());
               adj[curr].add(new Node(target, dist));
           }
        }
//        dist = new int[v+1];  // 아무 노드에서나 가장 먼 노드 탐색
//        Arrays.fill(dist, -1);
//        l = 0; idx = 0;
//        bfs(1);
//        l = 0;
//        dist = new int[v+1];  // 거리 초기화
//        Arrays.fill(dist, -1);
//        bfs(idx);             // 먼 녿 기준으로 거리 계산
//        System.out.println(l);
        visit = new boolean[v+1];   // 아무 노드에서나 가장 먼
        dfs(1, 0);
        l = 0;
        visit = new boolean[v+1];
        dfs(idx, 0);
        System.out.println(l);
    }
    static int v, l, idx;
    static int[] dist;
    static boolean[] visit;
    static List<Node>[] adj;

    static void dfs(int curr, int d) {

        visit[curr] = true;

        if (d > l) {
            l = d;
            idx = curr;
        }

        for (Node next : adj[curr]) {
            if (visit[next.v]) continue;
            dfs(next.v, d + next.dist);
        }
    }

    static void bfs(int tmp) {  // 740ms

        Deque<Integer> q = new ArrayDeque<>();
        q.offer(tmp); dist[tmp] = 0;
        while (!q.isEmpty()) {

            int curr = q.poll();

            for (Node next : adj[curr]) {
                if (dist[next.v]>-1) continue;
                dist[next.v] = dist[curr] + next.dist;
                if (l < dist[curr] + next.dist) {
                    l = dist[curr] + next.dist;
                    idx = next.v;
                }
                q.offer(next.v);
            }
        }
    }
}

class Node {
    int v, dist;

    public Node(int v, int dist) {
        this.v = v;
        this.dist = dist;
    }
}