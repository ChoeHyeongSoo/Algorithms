import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

class Solution {
    public static void main(String args[]) throws Exception {
        StringBuilder ans = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = 10;
        for (int test_case = 1; test_case <= T; test_case++) {
			ans.append("#").append(test_case).append(" ");
            StringTokenizer st = new StringTokenizer(br.readLine());
            int vertex = Integer.parseInt(st.nextToken());
            int edge = Integer.parseInt(st.nextToken());
            int[] in_degree = new int[vertex+1];
            List<Integer>[] graph = new ArrayList[vertex+1];

            for (int i = 1; i <= vertex; i++)
                graph[i] = new ArrayList<>();

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < edge; i++) {
                int v = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                in_degree[c]++;		// Topological Sort 
                graph[v].add(c);
            }

            Queue<Integer> q = new LinkedList<>();

            for (int idx = 1; idx <= vertex; idx++) {
                if (in_degree[idx]==0)		// BFS + Topological Sort
                    q.offer(idx);
            }

            while (!q.isEmpty()) {
                int tmp = q.poll();

                for (int c : graph[tmp]) {
                    in_degree[c]--;

                    if (in_degree[c]==0)
                        q.offer(c);
                }
                ans.append(tmp + " ");
            }
			ans.append('\n');
        }
        System.out.print(ans);
    }
}