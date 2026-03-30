package Algorithm.Graph.ShortestPath;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_Gold3_1238_Party{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        edge = new ArrayList[n+1][2];   // 최단거리 계산에서 출발-도착 지점과 간선이 가지는 의미 이해
        for (int i = 1; i <= n; i++) {
            edge[i][0] = new ArrayList<>(); // 정방향
            edge[i][1] = new ArrayList<>(); // 역방향
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken()), e =Integer.parseInt(st.nextToken()), t = Integer.parseInt(st.nextToken());
            edge[s][0].add(new int[]{e, t});
            edge[e][1].add(new int[]{s, t});
        }
        int[] end2each = dijkstra(x, 0);    // 정방향 그래프 기준, X->each (귀가)
        int[] each2end = dijkstra(x, 1);    // 역방향 그래프 기준, X->each (출발) : X까지 가는 각 지점의 출발 최소값 계산 가능
        for (int i = 1; i <= n; i++) each2end[i]+=end2each[i];
        each2end[0] = 0;
        System.out.println(Arrays.stream(each2end).max().getAsInt());
    }
    static int n, x;
    static List<int[]>[][] edge;

    public static int[] dijkstra(int s, int option){

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b)->a[1]-b[1]); // target -> 외 모든 노드로 최단거리 구하기
        int[] costs = new int[n+1];
        Arrays.fill(costs, Integer.MAX_VALUE);
        costs[0] = 0;
        pq.offer(new int[]{x, 0}); costs[x] = 0;
        int cnt = 0;
        while(!pq.isEmpty()) {  // 2에서 각 지점 비용

            int[] curr = pq.poll();

            if (costs[curr[0]] < curr[1]) continue;
            if (++cnt==n) break;

            for (int[] next : edge[curr[0]][option]) {
                if (costs[next[0]] <= costs[curr[0]] + next[1]) continue;
                costs[next[0]] = costs[curr[0]] + next[1];
                pq.offer(new int[]{next[0], costs[next[0]]});
            }
        }

        return costs;
    }
}