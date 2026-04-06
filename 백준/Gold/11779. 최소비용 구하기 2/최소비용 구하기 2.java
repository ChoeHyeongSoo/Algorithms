import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(br.readLine());
        List<Node>[] edges = new ArrayList[n+1];
        for (int i = 1; i <= n; i++) edges[i] = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()), v = Integer.parseInt(st.nextToken()), d = Integer.parseInt(st.nextToken());
            edges[u].add(new Node(v, d));
        }
        st = new StringTokenizer(br.readLine());
        s = Integer.parseInt(st.nextToken()); e = Integer.parseInt(st.nextToken());
        dist = new int[n+1]; Arrays.fill(dist, Integer.MAX_VALUE);
        parents = new int[n+1]; // 인덱스에 직전 경로 저장
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b)->a.dist - b.dist);
        Node init = new Node(s, 0);
        dist[s] = 0; pq.offer(init);

        ans = new StringBuilder();
        while (!pq.isEmpty()) {
            Node curr = pq.poll();

            if (curr.idx==e) {
                make_ans(curr);
                break;
            }

            if (dist[curr.idx] < curr.dist) continue; // 특수한 상황에서 현재까지 최단 경로로 온 게 여러 개면 모든 가능성 검토

            for (Node next : edges[curr.idx]) {

                if (dist[next.idx] <= dist[curr.idx]+next.dist) continue; // next까지 가는 거리 중 최소가 되는 건 어느 경로든 상관 x
                dist[next.idx] = dist[curr.idx]+next.dist;
                parents[next.idx] = curr.idx;   // 현재 지점의 최단 거리가 어느 지점에서 왔는지 인덱스 저장
                Node candidate = new Node(next.idx, dist[next.idx]);

                pq.offer(candidate);
            }
        }
        System.out.println(ans);
    }

    static int n, m, s, e;
    static StringBuilder ans;
    static int[] dist, parents;

    private static void make_ans(Node curr) {
        ans.append(dist[e]).append('\n');

        int idx = e;
        Stack<Integer> s = new Stack<>(); // s ~ e 를 순서대로 출력하기 위해 stack 사용
        while (idx>0) { // s까지 넣고 나면 s는 0이 저장되어 있으므로, 0이 조건
            s.push(idx);
            idx = parents[idx];
        }
        ans.append(s.size()).append('\n');
        while(!s.isEmpty()) ans.append(s.pop()).append(" ");
    }
}

class Node {
    int idx, dist;

    public Node(int idx, int dist) {
        this.idx = idx;
        this.dist = dist;
    }
}