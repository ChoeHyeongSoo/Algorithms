import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;
public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder ans = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            int l = Integer.parseInt(br.readLine());

            StringTokenizer st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()), c = Integer.parseInt(st.nextToken());
            Position curr = new Position(r, c, 0);
            st = new StringTokenizer(br.readLine());
            r = Integer.parseInt(st.nextToken()); c = Integer.parseInt(st.nextToken());

            boolean[][] visit = new boolean[l][l];
            Deque<Position> q = new ArrayDeque<>();
            visit[curr.r][curr.c] = true;
            q.offer(curr);

            while (!q.isEmpty()) {

                Position tmp = q.poll();

                if (tmp.r == r && tmp.c == c) {
                    ans.append(tmp.t).append('\n');
                    break;
                }

                for (int d = 0; d < 8; d++) {
                    int next_r = tmp.r + dir[0][d];
                    int next_c = tmp.c + dir[1][d];

                    if (next_r < 0 || next_r >= l || next_c < 0 || next_c >= l) continue;
                    if (visit[next_r][next_c]) continue;
                    visit[next_r][next_c] = true;
                    Position next = new Position(next_r, next_c, tmp.t+1);
                    q.offer(next);
                }
            }
        }
        System.out.println(ans);
    }
    static int[][] dir = {{2, 1, -1, -2, -2, -1, 1, 2}, {1, 2, 2, 1, -1, -2, -2, -1}};
}

class Position {
    int r, c, t;

    public Position(int r, int c, int t) {
        this.r = r;
        this.c = c;
        this.t = t;
    }
}