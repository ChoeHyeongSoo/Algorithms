import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

class Solution {
    public static void main(String args[]) throws Exception {
        StringBuilder ans = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            ans.append("#").append(test_case).append(" ");
            StringTokenizer st = new StringTokenizer(br.readLine());

            n = Integer.parseInt(st.nextToken());
            connection = new boolean[n][n]; 	cores = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    int tmp = Integer.parseInt(st.nextToken());
                    if (tmp == 1) {
                        Core tmp_core = new Core(i, j);
                        if (i == 0 || i == n - 1 || j == 0 || j == n - 1) { // 거리 0인 애들 계산 x
                            connection[i][j] = true;
                            continue;
                        }   // 거리 미리 계산
                        tmp_core.each[0] = new Line(0, n - 1 - i);
                        tmp_core.each[1] = new Line(1, n - 1 - j);
                        tmp_core.each[2] = new Line(2, i);
                        tmp_core.each[3] = new Line(3, j);
                        cores.add(tmp_core);
                        connection[i][j] = true;
                    }
                }
            }

            max_cnt = 0; min_line = Integer.MAX_VALUE;
            subset(0, 0, 0);

            ans.append(min_line).append('\n');
        }
        System.out.print(ans);
    }

    static List<Core> cores;
    static boolean[][] connection;
    static int[] dir_r = {1, 0, -1, 0}, dir_c = {0, 1, 0, -1};
    static int n, min_line, max_cnt;


    public static void subset(int depth, int line, int cnt) {

        if (cnt + cores.size() - depth < max_cnt) return;  // 부분 집합 원소 포텐셜이 최대보다 낮을 때 중단

        if (depth == cores.size()) {    // 기저 조건
            if (cnt > max_cnt) {
                min_line = line;
                max_cnt = cnt;
            } else if (cnt == max_cnt) min_line = Math.min(min_line, line);
            return;
        }

        Core curr = cores.get(depth);
        Arrays.sort(curr.each); // 최단거리 순으로 탐색
        for (int d = 0; d < 4; d++) {
            int dir = curr.each[d].dir;   // curr,each[d].dir : 미리 저장해둔 방향 (정렬하면 index와 방향 불일치하므로)
            if (visit(dir, curr.r, curr.c)) {   //    	''			.distance:   ''        거리
                subset(depth + 1, line + curr.each[d].distance, cnt+1);   // Include
                check(dir, curr.r, curr.c, false);
            }
        }

        subset(depth+1, line, cnt);   // Exclude
    }

    public static boolean visit(int dir, int r, int c) { // 연결 가능 여부 반환: 가능하면 방문 처리까지

        int nextR = r + dir_r[dir], nextC = c + dir_c[dir];

        while (nextR >= 0 && nextR < n && nextC >= 0 && nextC < n) {

            if (connection[nextR][nextC]) return false;     // 불가능 반환

            nextR += dir_r[dir];
            nextC += dir_c[dir];
        }

        check(dir, r, c, true); // 방향 별 방문처리

        return true;
    }

    public static void check(int dir, int r, int c, boolean is) {   // 방문 처리, 복구 함수
        switch (dir) {
            case 0:
                for (int i = r + 1; i < n; i++) connection[i][c] = is;
                break;
            case 1:
                for (int i = c + 1; i < n; i++) connection[r][i] = is;
                break;
            case 2:
                for (int i = r - 1; i >= 0; i--) connection[i][c] = is;
                break;
            case 3:
                for (int i = c - 1; i >= 0; i--) connection[r][i] = is;
                break;
        }
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

class Line implements Comparable<Line> {
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