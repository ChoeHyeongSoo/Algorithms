import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n][n]; v = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < n; j++) arr[i][j] = line.charAt(j) - '0';
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (v[i][j] || arr[i][j]==0) continue;
                bfs(i, j);
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(group.size()).append('\n');
        Collections.sort(group);
        for (int k : group) sb.append(k).append('\n');
        System.out.println(sb);
    }

    static int n;
    static Deque<Integer> q = new ArrayDeque<>();
    static List<Integer> group = new ArrayList<>();
    static int[][] arr, dir = {{1,0,-1,0}, {0,1,0,-1}};
    static boolean[][] v;

    static void bfs(int i, int j) {

        v[i][j] = true;
        q.offer(i*n+j);
        int cnt = 1;
        while (!q.isEmpty()) {
            int curr = q.poll();

            for (int d = 0; d < 4; d++) {
                int r = curr/n + dir[0][d];
                int c = curr%n + dir[1][d];

                if (r<0 || r>=n || c<0 || c>=n) continue;
                if (v[r][c]||arr[r][c]==0) continue;
                v[r][c] = true;
                cnt++;
                q.offer(r*n+c);
            }
        }
        group.add(cnt);
    }
}