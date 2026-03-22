import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());
        int[][][] box = new int[N][M][H];
        List<Position> init = new ArrayList<>();
        int yet = 0;
        for (int k = 0; k < H; k++) {
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    box[i][j][k] = Integer.parseInt(st.nextToken());
                    if (box[i][j][k]==1) init.add(new Position(i, j, k));
                    else if (box[i][j][k]==0) yet++;
                }
            }
        }

        if (init.isEmpty()) System.out.println(-1);
        else {
            if (yet==0) System.out.println(0);
            else {
                int[][] dir = {{1, 0, -1, 0, 0, 0}, {0, 1, 0, -1, 0, 0}, {0, 0, 0, 0, 1, -1}};
                int day = 0;
                Deque<Position> q = new ArrayDeque<>();
                Deque<Position> next = new ArrayDeque<>();
                for (Position p : init) q.offer(p);
                while (yet>0) {
                    while (!q.isEmpty()) {
                        Position curr = q.poll();

                        for (int i = 0; i < 6; i++) {
                            int r = curr.r + dir[0][i];
                            int c = curr.c + dir[1][i];
                            int h = curr.h + dir[2][i];

                            if (r < 0 || r >= N || c < 0 || c >= M || h < 0 || h >= H) continue;
                            if (box[r][c][h] == 0) {
                                next.offer(new Position(r, c, h));
                                box[r][c][h] = 1; yet--;
                            }
                        }
                    }

                    while (!next.isEmpty()) q.offer(next.poll());
                    day++;
                    if (q.isEmpty() && yet>0) {day = -1; break;}
                }
                System.out.println(day);
            }
        }


    }
    static int N;
}

class Position {
    int r, c, h;

    public Position(int r, int c, int h) {
        this.r = r;
        this.c = c;
        this.h = h;
    }
}