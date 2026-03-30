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
        char[][] map = new char[n+2][m+2];
        for (char[] r : map) Arrays.fill(r, 'X');
        int init = 0;
        for (int i = 1; i <= n; i++) {
            String line = br.readLine();
            for (int j = 1; j <= m; j++) {
                map[i][j] = line.charAt(j-1);
                if (map[i][j]=='I') init = i*(m+2)+j;
            }
        }

        Deque<Integer> q = new ArrayDeque<>();
        boolean[][] v = new boolean[n+2][m+2];
        q.offer(init); v[init/(m+2)][init%(m+2)] = true;
        int cnt = 0;
        while (!q.isEmpty()) {

            int curr = q.poll();

            for (int d = 0; d < 4; d++) {
                int r = curr/(m+2) + dir[0][d];
                int c = curr%(m+2) + dir[1][d];

                if (v[r][c] || map[r][c]=='X') continue;
                v[r][c] = true;
                if (map[r][c]=='P') cnt++;
                q.offer(r*(m+2)+c);
            }
        }
        System.out.println(cnt==0?"TT":cnt);
    }
    static int n, m;
    static int[][] dir = {{1,0,-1,0},{0,1,0,-1}};
}