import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

class Solution {
    public int solution(int[][] maps) {

        int w = maps[0].length, h = maps.length;

        int[][] dir = {{1, 0, -1, 0}, {0, 1, 0, -1}};

        boolean[][] visit = new boolean[h][w];
        Deque<int[]> q = new ArrayDeque<>();
        visit[0][0] = true;
        q.offer(new int[]{0, 1});

        int ans = 0;
        while (!q.isEmpty()) {

            int[] curr = q.poll();

            if (curr[0] / w == h-1 && curr[0] % w == w - 1) {
                ans = curr[1]; break;
            }

            for (int d = 0; d < 4; d++) {

                int r = curr[0] / w + dir[0][d];
                int c = curr[0] % w + dir[1][d];

                if (r < 0 || c < 0 || r >= h || c >= w) continue;
                if (visit[r][c] || maps[r][c] == 0) continue;

                visit[r][c] = true;
                q.offer(new int[]{r * w + c, curr[1] + 1});
            }
        }

        return visit[h-1][w-1] ? ans : -1;
    }
}