import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            nations = new int[n+1];
            List<Integer>[] schedule = new ArrayList[n+1];
            for (int i = 1; i <= n; i++) {
                nations[i] = i;
                schedule[i] = new ArrayList<>();
            }

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken());
                schedule[a].add(b); schedule[b].add(a);
            }

            int cnt = 0, connected = 0;
            Deque<Integer> q = new ArrayDeque<>();
            q.offer(1);
            while (!q.isEmpty()) {

                int curr = q.poll();

                for (int k : schedule[curr]){
                    if (!union(curr, k)) continue;
                    connected++; cnt++;
                    q.offer(k);
                }
                if (connected==n-1) break;
            }
            System.out.println(cnt);
        }


    }
    static int n, m;
    static int[] nations;

    public static boolean union(int x, int y) {

        int root_x = find(x);
        int root_y = find(y);

        if (root_x==root_y) return false;

        if (root_x < root_y) nations[y] = root_x;
        else nations[x] = root_y;

        return true;
    }

    public static int find(int node) {
        if (nations[node]==node) return node;
        return nations[node]=find(nations[node]);
    }
}