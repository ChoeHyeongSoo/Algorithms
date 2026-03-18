import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main{
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(br.readLine());

        List<Node>[] adj = new ArrayList[v+1];
        for (int i = 1; i <= v; i++) adj[i] = new ArrayList<>();

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int tmp = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            adj[tmp].add(new Node(d, w));
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] distance = new int[v+1];
        for (int i = 1; i <= v; i++) distance[i] = Integer.MAX_VALUE;
        distance[k] = 0;
        pq.offer(new Node(k, 0));

        while (!pq.isEmpty()) {
            Node curr = pq.poll();
            int tmp = curr.link;
            int w = curr.w;

            if (distance[tmp]<w);

            for (Node next : adj[tmp]) {
                int d = distance[tmp] + next.w;

                if (d < distance[next.link]){
                    distance[next.link] = d;
                    pq.offer(new Node(next.link, d));
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= v; i++)
            sb.append((distance[i]!=Integer.MAX_VALUE) ? distance[i] : "INF").append('\n');

        System.out.println(sb);
    }
}

class Node implements Comparable<Node>{
    int link, w;

    public Node(int link, int w) {
        this.link = link;
        this.w = w;
    }

    @Override
    public int compareTo(Node o) {
        return this.w - o.w;
    }
}