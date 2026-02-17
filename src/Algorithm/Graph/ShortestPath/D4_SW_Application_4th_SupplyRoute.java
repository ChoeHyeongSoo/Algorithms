package Algorithm.Graph.ShortestPath;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

class Node implements Comparable<Node> {
    //    int[] position;
    int r, c;
    int cost;

//    public Node(int[] position, int cost) {
//        this.position = position;
//        this.cost = cost;
//    }

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
class D4_SW_Application_4th_SupplyRoute {
    /*
    공병대는 출발지(S) 에서 도착지(G)까지 가기 위한 도로 복구 작업을 빠른 시간 내에 수행하려고 한다.
    도로가 파여진 깊이에 비례해서 복구 시간은 증가한다. 깊이가 1이라면 복구에 드는 시간이 1이라고 가정한다.
    출발지는 좌상단의 칸(S)이고 도착지는 우하단의 칸(G)가 된다. 이동 경로는 상하좌우 방향으로 진행할 수 있으며, 한 칸씩 움직일 수 있다.
    지도 정보에는 각 칸마다 파여진 도로의 깊이가 주어진다. 현재 위치한 칸의 도로를 복구해야만 다른 곳으로 이동할 수 있다.
    출발지에서 도착지까지 가는 경로 중에 복구 시간이 가장 짧은 경로에 대한 총 복구 시간을 구하시오.
     */
    public static void main(String args[]) throws Exception {
        System.setIn(new FileInputStream("res/Algorithm/Graph/ShortestPath/input_D4_SW_Application_4th_SupplyRoute.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

//        Scanner sc = new Scanner(System.in);
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

            // (0,0) -> (n-1,n-1) 까지 최소비용 거리 구하기 : 누적합으로 비용 갱신, 현재 상황에서 가장 최소비용인 방향으로 진행
            // r, y는 증가하는 방향으로 진행해야 함. 감소하게 될 경우, 누적합으로 돌아갔을 때의 비용 비교
            // r+k, c+k에서 돌아간 비용과 차선책을 선택해서 진행한 것을 비교해서 업데이트
            // 최선을 선택해서 나아갈 떄, 차선책으로 간 비용보다 비싸지면 가지치기 : dx, dy 모두 증가하는 경우는 패쓰

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