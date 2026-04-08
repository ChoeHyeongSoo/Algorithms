import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        p = new int[n+1];
        adj = new ArrayList[n+1];
        int total = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            p[i] = Integer.parseInt(st.nextToken());
            total+=p[i];
            adj[i] = new ArrayList<>();
        }
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int len = Integer.parseInt(st.nextToken());
            for (int j = 0; j < len; j++) adj[i].add(Integer.parseInt(st.nextToken()));
        }

        int ans = Integer.MAX_VALUE;
        // 비트마스킹 : 부분 집합을 대체하라.
        for (int bit = 1; bit < (1<<n)-1; bit++) { // 0이거나 N인 경우는 선거구 조건 불충족
            // 연결 여부 체크
            boolean group_a = is_connected(bit), group_b = is_connected(((1<<n)-1)^bit);
            // 연결이라면 인구수 카운팅 - 전체 인구수 - 비트마스킹 구연 인구수 * 2
            if (!(group_a&&group_b)) continue;
            int population = 0;
            for (int i = 1; i <= n; i++) if ((bit&(1<<(i-1)))!=0) population+=p[i]; // bit와 인덱스-1의 위치 확인 (1: 0001, 2: 0010(1<<1))
            ans = Math.min(ans, Math.abs(total - 2*population));
        }
        System.out.println(ans!=Integer.MAX_VALUE ? ans : -1);
    }
    static int n;
    static int[] p;
    static List<Integer>[] adj;

    static boolean is_connected(int bit) {

        // Integer. 라이브러리 활용 : 비트 카운팅 / 0의 개수(LSB(최하위비트) 뒤 : bit & (-bit)
        int threshold = Integer.bitCount(bit), init = Integer.numberOfTrailingZeros(bit & (-bit))+1; // *bit<->index 간 1의 차이 주의!

        Deque<Integer> q = new ArrayDeque<>();
        boolean[] visit = new boolean[n+1];
        visit[init]=true; q.offer(init);
        int cnt = 1;    // 카운트도 1부터

        while (!q.isEmpty()) {
            int curr = q.poll();

            for (int k : adj[curr]) {

                if ((bit&(1<<(k-1)))==0) continue; // 인덱스 자리의 비트가 꺼져있다면 연결 x
                if (visit[k]) continue;

                visit[k] = true;
                q.offer(k);
                cnt++;
            }
        }
        return cnt == threshold;
    }
}