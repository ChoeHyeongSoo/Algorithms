import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int tc = 0; tc < T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken()), t = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            s = Integer.parseInt(st.nextToken()); g = Integer.parseInt(st.nextToken()); h = Integer.parseInt(st.nextToken()); // 시작-(경로)
            adj = new ArrayList[n+1];
            int must = 0;
            for (int i = 1; i <= n; i++) adj[i] = new ArrayList<>();
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int curr = Integer.parseInt(st.nextToken()), next = Integer.parseInt(st.nextToken()), weight = Integer.parseInt(st.nextToken());
                if (curr==g&&next==h || curr==h&&next==g) must = weight;
                adj[curr].add(new Node(next, weight)); adj[next].add(new Node(curr, weight));
            }
            int[] candidate = new int[t];
            for (int i = 0; i < t; i++) candidate[i] = Integer.parseInt(br.readLine());
            Arrays.sort(candidate); // 오름차순 출력 위한 정렬

            int path_g2h = dijkstra(s, g) + must;
            int path_h2g = dijkstra(s, h) + must;
            for (int e : candidate) {
                min = dijkstra(s, e);
                if (path_g2h > min && path_h2g > min) {
                    sb.append(e).append(" ");
                    continue;
                }
                int compare1 = path_g2h + dijkstra(h, e);
                int compare2 = path_h2g + dijkstra(g, e);
                if (compare1==min || compare2==min) sb.append(e).append(" ");
            } sb.append('\n');
        }
        System.out.println(sb);
    }
    static int n, s, g, h;
    static List<Node>[] adj;
    static int min;
    static int[] costs_g;
    static int[] costs_h;


    public static int dijkstra(int s, int e) {
        PriorityQueue<Node> pq = new PriorityQueue<>(); // g,h 방문 안 한 상태로 후보지 들어오면 컨티뉴
        int[] costs = new int[n + 1];
        Arrays.fill(costs, Integer.MAX_VALUE);
        pq.add(new Node(s, 0));
        costs[s] = 0;
        while (!pq.isEmpty()) {
            Node curr = pq.poll();
            if (curr.idx==e) {
                return costs[curr.idx];
            }
            for (Node next : adj[curr.idx]) {
                if (costs[curr.idx]+ next.d <= costs[next.idx]) {
                    costs[next.idx] = costs[curr.idx]+ next.d;
                    pq.add(new Node(next.idx, costs[next.idx]));
                }
            }
        }
        return -1;
    }

    public static int dijkstra_fixed(int s, int mid) {
        PriorityQueue<Node> pq = new PriorityQueue<>(); // g,h 방문 안 한 상태로 후보지 들어오면 컨티뉴
        int[] costs = new int[n + 1];
        Arrays.fill(costs, Integer.MAX_VALUE);
        pq.add(new Node(s, 0));
        costs[s] = 0;
        int tmp_idx = 0;
        while (!pq.isEmpty()) {
            Node curr = pq.poll();
            if (curr.d > costs[curr.idx]) continue;
            if (curr.idx==g) {
                tmp_idx = g; break;
            }
            for (Node next : adj[curr.idx]) {
                if (costs[curr.idx]+ next.d <= costs[next.idx]) {
                    costs[next.idx] = costs[curr.idx]+ next.d;
                    pq.add(new Node(next.idx, costs[next.idx]));
                }
            }
        }
        return -1;
    }
}

class Node implements Comparable<Node>{
    int idx, d;
    boolean v_g, v_h;
    public Node(int idx, int d) {
        this.idx = idx;
        this.d = d;
    }

    @Override
    public int compareTo(Node o) {
        return this.d - o.d;
    }
}