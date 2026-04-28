import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {

        if (image[sr][sc] == color) return image;
        N = image.length;
        M = image[0].length;
        int origin = image[sr][sc];
        Deque<Integer> q = new ArrayDeque<>();
        image[sr][sc] = color;
        q.offer(sr*M + sc);

        while (!q.isEmpty()) {

            int curr = q.poll();

            for (int d = 0; d < 4; d++) {

                int nr = curr/M + dir[0][d];
                int nc = curr%M + dir[1][d];

                if (!isIn(nr, nc)) continue;
                if (image[nr][nc]!=origin) continue;

                image[nr][nc] = color;
                q.offer(nr*M + nc);
            }
        }
        return image;
    }

    static int N, M;
    static int[][] dir = {{1, 0, -1, 0}, {0, 1, 0, -1}};

    static boolean isIn(int r, int c) {
        return (r >= 0 && r < N && c >= 0 && c<M);
    }
}