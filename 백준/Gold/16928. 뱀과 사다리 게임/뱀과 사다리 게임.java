import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;
public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[101];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()), y= Integer.parseInt(st.nextToken());
            map[x] = y;
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken()), v = Integer.parseInt(st.nextToken());
            map[u] = v;
        }

        // BFS
        int[] visit = new int[101];
        Arrays.fill(visit, 100);
        Deque<Integer> q = new ArrayDeque<>();
        Deque<Integer> in = new ArrayDeque<>();
        q.offer(1);
        int cnt = 0;
        main_loop:
        while (true) {
            while (!q.isEmpty()) {

                int curr = q.poll();
                if (curr == 100) break main_loop;   // 조기종료

                for (int i = 6; i >= 1; i--) {
                    int next = curr + i;

                    if (next > 100 || visit[next] < cnt) continue;  // 최단거리 보장
                    visit[next] = Math.min(visit[next], cnt);
                    if (map[next] > 0) {
                        next = map[next];
                        visit[next] = cnt;
                    }
                    in.offer(next);
                }
            }
            cnt++;
            while (!in.isEmpty()) q.offer(in.poll());   // 레벨 확인을 위해 이번 단계와 다음 단계 분리
        }
        System.out.println(cnt);
    }
    static int n, m;
    static int[] map;
}