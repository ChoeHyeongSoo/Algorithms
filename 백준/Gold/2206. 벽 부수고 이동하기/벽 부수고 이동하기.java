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
        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) map[i][j] = line.charAt(j) - '0';
        }

        int[][][] dist = new int[n][m][2];
        boolean[][][] visit = new boolean[n][m][2];
        Deque<Node> q = new ArrayDeque<>();
        dist[n-1][m-1][0] = dist[n-1][m-1][1] = Integer.MAX_VALUE;
        dist[0][0][0] = dist[0][0][1] = 1;
        q.offer(new Node(0, 0,  false));
        while (!q.isEmpty()) {
            Node curr = q.poll();

            for (int d = 0; d < 4; d++) {
                int r = curr.r + dir[0][d];
                int c = curr.c + dir[1][d];

                if (r<0 || r>=n || c<0 || c>=m) continue;

                if (map[r][c]==0) {
                    int idx = curr.use ? 1 : 0;
                    if (!visit[r][c][idx]) {
                        visit[r][c][idx] = true;
                        dist[r][c][idx] = dist[curr.r][curr.c][idx]+1;
                        q.offer(new Node(r, c, curr.use));
                    }
                } else {
                    if (curr.use || visit[r][c][1]) continue;
                    visit[r][c][1] = true;
                    dist[r][c][1] = dist[curr.r][curr.c][0] + 1;
                    q.offer(new Node(r, c, true));
                }
            }
        }
        int ans = Math.min(dist[n-1][m-1][0], dist[n-1][m-1][1]);
        System.out.println(ans==Integer.MAX_VALUE?-1:ans);
    }
    static int n, m;
    static int[][] map, dir = {{1,0,-1,0},{0,1,0,-1}};
}

class Node {
    int r, c;
    boolean use;

    public Node(int r, int c, boolean use) {
        this.r = r;
        this.c = c;
        this.use = use;
    }
}