import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Solution {
    public static void main(String args[]) throws Exception {
        StringBuilder ans = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            ans.append("#").append(test_case).append(" ");
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            List<Integer>[] friends = new ArrayList[n+1];
            for (int i = 1; i <= n; i++)
                friends[i] = new ArrayList<>();
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int me = Integer.parseInt(st.nextToken());
                int you = Integer.parseInt(st.nextToken());
                friends[me].add(you); friends[you].add(me);
            }

            int cnt = 0;
            boolean[] v = new boolean[n+1];
            if (!friends[1].isEmpty()) {

                Deque<Integer> q = new ArrayDeque<>();

                for (int f : friends[1]) q.offer(f);
                v[1] = true;

                while (!q.isEmpty()) {

                    int curr = q.poll();
                    v[curr] = true;

                    for (int f : friends[curr]) {
                        if (v[f]) continue;
                        v[f] = true;
                    }
                }
            }

            for (int i = 2; i <= n; i++)
                if (v[i]) cnt++;

            ans.append(cnt).append('\n');
        }
        System.out.print(ans);
    }
}