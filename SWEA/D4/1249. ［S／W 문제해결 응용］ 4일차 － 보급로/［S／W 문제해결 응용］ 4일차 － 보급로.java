import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

class Node implements Comparable<Node> {
    //    int[] position;
    int r, c;
    int cost;
    
    public Node(int r, int c, int cost) {
        this.r = r;
        this.c = c;
        this.cost = cost;
    }

    @Override
    public int compareTo(Node d) {
        return this.cost - d.cost;
    }
}
class Solution {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            int n = Integer.parseInt(br.readLine());
            int[][] map = new int[n][n];
            for (int i = 0; i < n; i++) {
                StringBuilder sb = new StringBuilder(br.readLine());
                for (int j = 0; j < n; j++) {
                    map[i][j] = sb.charAt(j) - '0';
                }
            }

            // Dijkstra!
            int[][] cost = new int[n][n];   // 비용 업데이트할 배열
            for (int[] r : cost) Arrays.fill(r, Integer.MAX_VALUE);
            cost[0][0] = map[0][0]; // 초기화

            PriorityQueue<Node> prior_queue = new PriorityQueue<>(); // 우선순위 큐로 자동정렬
            int[] dr = {1,0,-1,0}, dc = {0,1,0,-1};
//            prior_queue.add(new Node(new int[]{0,0}, map[0][0]));
            prior_queue.add(new Node(0, 0, map[0][0]));

            int ans = 0;
            while (!prior_queue.isEmpty()) {
                Node tmp = prior_queue.poll();
//                if (tmp.cost > cost[tmp.position[0]][tmp.position[1]]) continue;
                if (tmp.cost > cost[tmp.r][tmp.c]) continue;

                if (tmp.r == n-1 && tmp.c == n-1) {
                    ans = tmp.cost;
                    break;
                }

                for (int i = 0; i < 4; i++) {
                    int c = tmp.c + dc[i];
                    int r = tmp.r + dr[i];

                    if (c >= 0 && c < n && r >= 0 && r < n) {
                        // 진행 방향 비용 비교
                        if (cost[r][c] > cost[tmp.r][tmp.c] + map[r][c]) {
                            cost[r][c] = cost[tmp.r][tmp.c] + map[r][c];
                            prior_queue.add(new Node(r,c,cost[r][c]));
                        }
                    }
                }
            }
            System.out.println("#" + test_case + " " + ans);
        }
    }
}