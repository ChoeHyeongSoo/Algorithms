import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        adj = new ArrayList[n+1];
        for (int i = 1; i <= n; i++) adj[i] = new ArrayList<>();
        for (int i = 0; i < n-1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken()), w = Integer.parseInt(st.nextToken());
            adj[a].add(new Node(b, w)); adj[b].add(new Node(a, w));
        }
        if (n>1) {
            max = 0;
            visit = new int[n + 1]; // 중복 생성 방지 : 2번 bfs니 각 1, 2로 방문여부 체크
            bfs(1, 1);  // 1. 루트에서 가장 먼 노드 탐색
            max = 0;
            bfs(vertex, 2); // 2. 가장 먼 노드 기준 가장 먼 노드 탐색
            System.out.println(max);
        } else System.out.println(0);
    }
    static int n, max, vertex;
    static List<Node>[] adj;
    static int[] visit;
    static Deque<Node> q = new ArrayDeque<>();

    static void bfs (int idx, int iter) {

        q.offer(new Node(idx, 0));
        visit[idx] = iter;
        while (!q.isEmpty()) {

            Node curr = q.poll();

            if (curr.weight > max) {
                max = curr.weight; vertex = curr.vertex;    // 거리와 노드 정보 저장
            }

            for (Node next : adj[curr.vertex]) {        // 인접 행렬 기준 탐색
               if (visit[next.vertex]>=iter) continue;
               visit[next.vertex] = iter;
               q.offer(new Node(next.vertex, curr.weight+ next.weight)); // 현재까지 + 간선의 가중치 반영
            }
        }
    }
}

class Node{
    int vertex, weight;

    public Node(int vertex, int weight) {
        this.vertex = vertex;
        this.weight = weight;
    }
}