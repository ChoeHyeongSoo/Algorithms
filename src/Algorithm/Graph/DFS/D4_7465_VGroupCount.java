package Algorithm.Graph.DFS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class D4_7465_VGroupCount {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            Set<Integer>[] relation = new HashSet[n+1];
            for (int i = 1; i <= n; i++)
                relation[i] = new HashSet<>();
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int me = Integer.parseInt(st.nextToken()), you = Integer.parseInt(st.nextToken());
                relation[me].add(you); relation[you].add(me);
            }

            int cnt = 0;
            boolean[] v = new boolean[n+1];
            for (int i = 1; i <= n; i++) {
                if (v[i]) continue;
                dfs(relation, v, i);
                cnt++;
            }

            System.out.println("#" + test_case + " " + cnt);
        }
    }

    public static void dfs(Set<Integer>[] r, boolean[] v, int tmp) {
        v[tmp] = true;

        if (r[tmp].isEmpty()) return;
        for (int k : r[tmp]) {
            if (v[k]) continue;
            dfs(r, v, k);
        }
    }
}