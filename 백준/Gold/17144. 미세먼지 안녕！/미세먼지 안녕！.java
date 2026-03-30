import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken()); C = Integer.parseInt(st.nextToken()); T = Integer.parseInt(st.nextToken());
        map = new int[R][C];
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }
        for (int i = 0; i < R; i++) { // 공기청정기 위치 저장
            if (map[i][0]==-1) { machine = i; break; }}

        for (int t = 0; t < T; t++) {
            spread();
            filter();
        }
        int sum = 0;
        for (int[] r : map) for (int k : r) if (k>-1) sum += k;
        System.out.println(sum);
    }
    static int R, C, T, machine;
    static int[][] map, next_state, dir = {{1,0,-1,0},{0,1,0,-1}};

    public static void spread() {   // 1. 확산
        next_state = new int[R][C];
        next_state[machine][0] = next_state[machine+1][0] = -1; // 다음 상태 공기청정기 위치 저장
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {

                if (map[r][c] <= 0) continue;

                int quantity = map[r][c] / 5;
                next_state[r][c] += map[r][c]; // 다음 먼지도 적용될 수 있게 +=, -= 적용

                for (int d = 0; d < 4; d++) {
                    int next_r = r + dir[0][d];
                    int next_c = c + dir[1][d];

                    if (next_r < 0 || next_r >= R || next_c < 0 || next_c >= C) continue;
                    if (map[next_r][next_c] == -1) continue;
                    next_state[next_r][next_c] += quantity;
                    next_state[r][c] -= quantity;   // 먼지 시발점 확산량만큼 감소
                }
            }
        }
        for (int i = 0; i < R; i++) map[i] = Arrays.copyOf(next_state[i], C);
    }

    public static void filter() {
        // Upside
        for (int i = machine-1; i > 0; i--) map[i][0] = map[i-1][0];
        for (int i = 0; i < C-1; i++) map[0][i] = map[0][i+1];
        for (int i = 0; i <= machine-1; i++) map[i][C-1] = map[i+1][C-1];   // 머신의 열 직전까지 포함
        for (int i = C-1; i > 1; i--) map[machine][i] = map[machine][i-1];
        map[machine][1] = 0;

        // Downside
        for (int i = machine+2; i < R-1; i++) map[i][0] = map[i+1][0];
        for (int i = 0; i < C-1; i++) map[R-1][i] = map[R-1][i+1];
        for (int i = R-1; i >= machine+2; i--) map[i][C-1] = map[i-1][C-1];
        for (int i = C-1; i > 1; i--) map[machine+1][i] = map[machine+1][i-1];
        map[machine+1][1] = 0;
    }

}