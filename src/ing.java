import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Solution {
    /*

     */
    public static void main(String args[]) throws Exception {
        StringBuilder ans = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            ans.append("#").append(test_case).append(" ");
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            connection = new boolean[n][n];
            cores = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    int tmp = Integer.parseInt(st.nextToken());
                    if (tmp==1) {
                        Core tmp_core = new Core(i, j);
                        if (i==0 || i==n-1 || j==0 || j==n-1) tmp_core.is_connected = true;
                        else {
                            tmp_core.each[0] = new Line(0, n - 1 - i);
                            tmp_core.each[1] = new Line(1, n - 1 - j);
                            tmp_core.each[2] = new Line(2, i);
                            tmp_core.each[3] = new Line(3, j);
                        }
                        cores.add(tmp_core);
                        connection[i][j] = true;
                    }
                }
            }

            // 리스트에 코어 번호, 방향, 거리 담아서 for i=0~n-1 / for (j=i+1)~ ?

            min_line = Integer.MAX_VALUE; max_core = Integer.MIN_VALUE;
            perm(0);

            ans.append('\n');
        }
        System.out.print(ans);
    }

    static List<Core> cores;
    static boolean[][] connection;
    static int min_line, max_core, n;
    static int[] dir_r = {1, 0, -1, 0}, dir_c = {0, 1, 0, -1};

    public static void perm(int line) {

        if (line > min_line) return;
//        if (depth==cores.size()) {min_line = Math.min(min_line, line); return;} // 연결 안 되는 케이스 존재

        for (int i = 0; i < cores.size(); i++) {

            if (cores.get(i).is_connected) continue;
            Arrays.sort(cores.get(i).each);
            Core tmp = cores.get(i);
            int r = tmp.r, c= tmp.c;
            for (int dir = 0; dir < 4; dir++) {
                if (visit(tmp.each[dir].dir, r, c, i)) line+=tmp.each[dir].distance;
            }

        }

    }

    public static boolean visit(int dir, int r, int c, int idx) {

        int nextR = r, nextC = c;

        if (nextR >= 0 && nextR < n && nextC >= 0 && nextC < n) {

            nextR += dir_r[dir];
            nextC += dir_c[dir];

            if (connection[nextR][nextC])
                return false;
        }

        switch (dir) {
            case 0:
                for (int i = r+1; i < n; i++) connection[i][c] = true;
                break;
            case 1:
                for (int i = c+1; i < n; i++) connection[r][i] = true;
                break;
            case 2:
                for (int i = r-1; i >= 0; i--) connection[i][c] = true;
                break;
            case 3:
                for (int i = c-1; i >= 0; i--) connection[r][i] = true;
                break;
        }

        return true;
    }
}

class Core {
    int r, c;
    boolean is_connected;
    Line[] each = new Line[4];

    public Core(int r, int c) {
        this.r = r;
        this.c = c;
    }
}

class Line implements Comparable<Line>{
    int dir, distance;

    public Line(int dir, int distance) {
        this.dir = dir;
        this.distance = distance;
    }

    @Override
    public int compareTo(Line o) {
        return this.distance - o.distance;
    }
}