package Algorithm.Graph.BFS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class D5_5521_BirthdayInvitation {
    /*
    상원이는 반 친구들에게 생일 파티 초대장을 주려고 한다.
    그러나 파티가 어색하게 되는 것을 원치 않는 상원이는 모든 친구들에게 초대장을 줄 생각이 없다.
    같은 반에 있지만, 서로 친한 친구가 아닐 수도 있기 때문이다.
    상원이는 우선 자신과 친한 친구들에게는 모두 초대장을 주기로 했다.
    여기에 더해서 친한 친구의 친한 친구인 경우에도 초대장을 주기로 했다.
    총 몇 명의 친구들에게 초대장을 주어야 하는지 구하는 프로그램을 작성하라.
    상원이의 반에는 N명이 있으며, 각 학생들은 1번에서 N번까지의 번호가 붙어 있다.
    여기서 1번 학생이 상원이다.
    */
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