import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());
        check = new boolean[v+1];
        adj = new ArrayList<>();
        for (int i = 0; i < v+1; i++) adj.add(new ArrayList<>());
        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int tmp = Integer.parseInt(st.nextToken());
            int link = Integer.parseInt(st.nextToken());
            adj.get(tmp).add(link); adj.get(link).add(tmp); // 어떻게 그래프 형성하는지 - 자료구조부터
        }

        int idx = 0, ans = 0;
        Deque<Integer> q = new ArrayDeque<>();
        while (idx < v) {

            idx++;
            if (check[idx]) continue;
            if ( adj.get(idx).isEmpty()) continue;
            q.offer(idx);

            while (!q.isEmpty()) {

                int tmp = q.poll();

                for (int k : adj.get(tmp)) {
                    if (check[k]) continue;
                    check[k] = true;
                    q.offer(k);
                }
            }

            ans++;
        }

        for (int i = 1; i <= v; i++) {
            if (!check[i]) ans++;
        }

        System.out.println(ans);
    }

    static boolean[] check;
    static List<List<Integer>> adj;
}