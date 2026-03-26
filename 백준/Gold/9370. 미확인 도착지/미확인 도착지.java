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

            int[] path_s = get_cost(s), path_h = get_cost(h), path_g = get_cost(g);
            for (int e : candidate) {
                int path_g2h = path_s[g] + must + path_h[e];    // s-g-h-e 표현식
                int path_h2g = path_s[h] + must + path_g[e];
                if (path_s[e]==path_g2h || path_s[e]==path_h2g) sb.append(e).append(" ");
            } sb.append('\n');
        }
        System.out.println(sb);
    }
    static int n, s, g, h;
    static List<Node>[] adj;


    public static int[] get_cost(int s) {
        PriorityQueue<Node> pq = new PriorityQueue<>(); // g,h 방문 안 한 상태로 후보지 들어오면 컨티뉴
        int[] costs = new int[n + 1];
        Arrays.fill(costs, Integer.MAX_VALUE);
        pq.add(new Node(s, 0));
        costs[s] = 0;
        int cnt = 0;
        while (!pq.isEmpty()) {     // 다익스트라 : 목적지가 아닌, 각 노드로 가는 모든 최단 거리 정보를 가진 배열 반환
            Node curr = pq.poll();
            if (cnt==n) {
                return costs;
            }
            for (Node next : adj[curr.idx]) {
                if (costs[curr.idx]+ next.d < costs[next.idx]) {
                    if (costs[next.idx]==Integer.MAX_VALUE) cnt++;  // 최초 한 번(노드 n에 대한 최단거리) 카운트
                    costs[next.idx] = costs[curr.idx]+ next.d;
                    pq.add(new Node(next.idx, costs[next.idx]));
                }
            }
        }
        return costs;
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