package Algorithm.Graph.BFS;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

class SW_Test_A_GradSemester_TopologicalSort{

    public static void main(String[] args) throws Exception {

        System.setIn(new FileInputStream("res/Algorithm/Graph/BFS/input_SW_Test_A_GradSemester.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T;
        T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {

            int n = Integer.parseInt(br.readLine());
            List<Integer>[] graph = new ArrayList[n+1]; // 자식 노드를 담을 리스트 배열
            for (int i = 1; i <= n; i++) {
                graph[i] = new ArrayList<>();       // null 에러 방지를 위해 초기화
            }
            int[] degree = new int[n+1];    // 위상 배열 - idx 일치를 위해 0은 비움 (0도 0으로 세팅된 걸 주의)

            for (int i = 1; i <= n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int pre_cnt = Integer.parseInt(st.nextToken());
                degree[i] = pre_cnt;
                for (int p = 0; p < pre_cnt; p++) {
                    int parent = Integer.parseInt(st.nextToken());
                    graph[parent].add(i);
                }
            }

            Deque<Integer> q = new LinkedList<>();
            Deque<Integer> next = new LinkedList<>();

            for (int i = 1; i <= n; i++) {
                if (degree[i]==0) {
                    q.offer(i);
                }
            }

            int semester = 0;

            outer:
            while (!q.isEmpty()) {

                int curr = 0;

                while (!q.isEmpty()) {

                    int tmp = q.poll();

                    for (int child : graph[tmp]) {

                        degree[child]--;

                        if (degree[child] == 0) {
                            next.offer(child);
                        }
                    }
                }

                while(!next.isEmpty())
                    q.offer(next.poll());

                semester++;
            }

            for (int i = 1; i < n; i++) {
                if (degree[i] != 0) {
                    semester = -1;
                    break;
                }
            }

            System.out.println("#" + tc + " " + semester);
        }

    }

}