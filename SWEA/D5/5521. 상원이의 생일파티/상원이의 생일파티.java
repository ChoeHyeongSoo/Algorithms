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
            List<Integer>[] friends = new ArrayList[n + 1];
            for (int i = 1; i <= n; i++)
                friends[i] = new ArrayList<>();
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int me = Integer.parseInt(st.nextToken());
                int you = Integer.parseInt(st.nextToken());
                friends[me].add(you); friends[you].add(me);
            }

            int cnt = 0;
            boolean[] v = new boolean[n + 1];
            if (!friends[1].isEmpty()) {
                v[1] = true;
                List<Integer> direct = friends[1];
                for (int f : direct) {
                    if (!v[f]) {
                        v[f] = true;
                        cnt++;
                    }
                    for (int ff : friends[f]) {
                        if (!v[ff]) {
                            v[ff] = true;
                            cnt++;
                        }
                    }
                }
            }
            ans.append(cnt).append('\n');
        }
        System.out.print(ans);
    }
}