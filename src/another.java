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
            int s = Integer.parseInt(st.nextToken()), g = Integer.parseInt(st.nextToken()), h = Integer.parseInt(st.nextToken()); // 시작-(경로)
            List<Node>[] adj = new ArrayList[n+1];
            for (int i = 1; i <= n; i++) adj[i] = new ArrayList<>();
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int curr = Integer.parseInt(st.nextToken()), next = Integer.parseInt(st.nextToken()), weight = Integer.parseInt(st.nextToken());
                adj[curr].add(new Node(next, weight)); adj[next].add(new Node(curr, weight));
            }
            int[] candidate = new int[t];
            for (int i = 0; i < t; i++) candidate[i] = Integer.parseInt(br.readLine());
            // g-h 거쳐서 간 거리의 가중치 < 가장 적은 노드를 거치고 간 가중치 이면 가능 djikstra --- 적은 노드가 아니라 최단거리 (g,h)방문없이 체크되면 제외
            PriorityQueue<Node> pq = new PriorityQueue<>(); // g,h 방문 안 한 상태로 후보지 들어오면 컨티뉴
            int[] costs = new int[n+1];
            boolean[][] possible = new boolean[n+1][2];
            Arrays.fill(costs, Integer.MAX_VALUE); Arrays.sort(candidate); // 오름차순 출력 위한 정렬
            pq.add(new Node(s, 0)); costs[s] = 0;
            int cnt = 0;
            while_loop:
            while (!pq.isEmpty()) {
                Node curr = pq.poll();
                if (curr.d > costs[curr.idx]) continue;
                for (Node i : adj[curr.idx]){
                    if (costs[i.idx] >= costs[curr.idx] + i.d) {
                        costs[i.idx] = costs[curr.idx] + i.d;
                        Node next = new Node(i.idx, costs[i.idx]);
                        if (i.idx == g || curr.idx==g || curr.v_g) next.v_g = true; else next.v_g = false;
                        if (i.idx == h || curr.idx==h || curr.v_h) next.v_h = true; else next.v_h = false;
                        possible[i.idx][0] = (costs[i.idx] > costs[curr.idx] + i.d)? next.v_g : next.v_g || possible[i.idx][0];
                        possible[i.idx][1] = (costs[i.idx] > costs[curr.idx] + i.d)? next.v_h : next.v_h || possible[i.idx][1];
                        pq.add(next);
                    }
                }
            }
            for (int k : candidate) // g, h를 거치지 않은 상태로 와서 갱신될 수 있다. (예외)
                if (possible[k][0]&&possible[k][1]) sb.append(k).append(" ");
            sb.append('\n');
        }
        System.out.println(sb);
    }
    static int n;
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
