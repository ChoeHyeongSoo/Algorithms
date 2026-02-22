package Algorithm.Graph.DFS;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

class D6_SW_Application_10th_WorkOrder_DFS {
    /*
    해야 할 V개의 작업이 있다. 이들 중에 어떤 작업은 특정 작업이 끝나야 시작할 수 있으며, 이를 선행 관계라 하자.
    이런 작업의 선행 관계를 나타낸 그래프가 주어진다. 이 그래프에서 각 작업은 하나씩의 정점으로 표시되고 선행 관계는 방향성을 가진 간선으로 표현된다.
    단, 이 그래프에서 사이클은 존재하지 않는다 (사이클은 한 정점에서 시작해서 같은 정점으로 돌아오는 경로를 말한다).
    V개의 작업과 이들 간의 선행 관계가 주어질 때, 일을 끝낼 수 있는 작업 순서를 찾는 프로그램을 작성하라.
    [입력]
    10개의 테스트케이스가 주어진다.
    각 케이스의 첫번째 줄에는 그래프의 정점의 개수 V(3 ≤ V ≤ 1000)와 간선의 개수 E(2 ≤ E ≤ 3000)가 주어지고, 그 다음 줄에는 E개의 간선이 나열된다.
    간선은 간선을 이루는 두 정점으로 표기된다. 예를 들어, 정점 5에서 28로 연결되는 간선은 “5 28”로 표기된다.
     */
    public static void main(String args[]) throws Exception {
        System.setIn(new FileInputStream("res/Algorithm/Graph/input_D6_SW_Application_10th_WorkOrder.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = 10;

        for (int test_case = 1; test_case <= T; test_case++) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            int vertex = Integer.parseInt(st.nextToken());
            int edge = Integer.parseInt(st.nextToken());
            boolean[] visit = new boolean[vertex+1];
            List<Integer>[] graph = new ArrayList[vertex+1];

            for (int i = 1; i <= vertex; i++)
                graph[i] = new ArrayList<>();

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < edge; i++) {
                int v = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                graph[v].add(c);
            }

            Stack<Integer> s = new Stack<>();
            StringBuilder sb = new StringBuilder();

            for (int idx = 1; idx <= vertex; idx++) {
                if (!visit[idx]) dfs(idx, visit, s, graph);
            }

            while (!s.isEmpty())
                sb.append(s.pop() + " ");

            System.out.println("#" + test_case + " " + sb);
        }
    }

    public static void dfs(int idx, boolean[] visit, Stack<Integer> s, List<Integer>[] graph) {
        visit[idx] = true;

        for (int c : graph[idx]) {
            if (!visit[c])
                dfs(c, visit, s, graph);
        }

        s.push(idx);
    }

}