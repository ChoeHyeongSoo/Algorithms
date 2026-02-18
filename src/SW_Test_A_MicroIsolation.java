import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

class micro_crowd {
    int r, c, number, dir, idx;

    public micro_crowd(int r, int c, int number, int dir, int idx) {
        this.r = r;
        this.c = c;
        this.number = number;
        this.dir = dir;
        this.idx = idx;
    }

    public micro_crowd(int number, int dir, int idx) {
        this.number = number;
        this.dir = dir;
        this.idx = idx;
    }
}

class SW_Test_A_MicroIsolation {
    /*

     */
    public static void main(String args[]) throws Exception {
        System.setIn(new FileInputStream("res/input_SW_Test_Isolation.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

//        Scanner sc = new Scanner(System.in);
        int T = Integer.parseInt(br.readLine());
        int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};

        for (int test_case = 1; test_case <= T; test_case++) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

//            ArrayList<micro_crowd> micro_crowds = new ArrayList<>();
            micro_crowd[][] map = new micro_crowd[n][n];
            micro_crowd[] micro_crowds = new micro_crowd[k];

            for (int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine());
                int input_r = Integer.parseInt(st.nextToken()), input_c = Integer.parseInt(st.nextToken());
                int input_cnt = Integer.parseInt(st.nextToken()), input_dir = Integer.parseInt(st.nextToken());
                micro_crowd c = new micro_crowd(input_r, input_c, input_cnt, input_dir, i);
//                micro_crowds.add(c); map[input_r][input_c] = c;
                micro_crowds[i] = c;
            }
            // M 시간 이후
            for (int i = 0; i < m; i++) {
                action(micro_crowds, map);
            }
            int ans = 0;
            for (int i = 0; i < micro_crowds.length; i++) {
                ans += micro_crowds[i].number;
            }

            System.out.println("#" + test_case + " " + ans);
        }
    }

    // 군집 이동/시간 - cell[0 or N][~], cell[~][0 or N] -> 군집 수 /= 2 , 방향 반전 ((dir+2)%4)
    // cond2) 군집 결합 - 수 많은 쪽의 dir로 this.dir 변경
    public static void action(micro_crowd[] micro_crowds, micro_crowd[][] map) {
        int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};   // dir+2)%4 update
        micro_crowd[] next_state = new micro_crowd[micro_crowds.length];
        int next_k = 0;
        micro_crowd[][] next_map = new micro_crowd[map.length][map.length];
        ArrayList<micro_crowd> conflict = new ArrayList<>();
        int[] idx = new int[micro_crowds.length];
        for (int i = 0; i < micro_crowds.length; i++) {
            if (micro_crowds[i]==null) continue;
            micro_crowd next = micro_crowds[i];
//            map[next.r][next.c] = null;
            next.r = next.r + dr[next.dir - 1];
            next.c = next.c + dc[next.dir - 1];
            if (next.r == 0 || next.r == map.length - 1 ||
                    next.c == 0 || next.c == map.length - 1) {      // 약품 도달 시, 방향 전환
                next.dir = change_dir(next.dir);
                next.number /= 2;
                if (next.number==0) continue;
            }
            if (next_map[next.r][next.c] != null) {
                next_map[next.r][next.c].number+=next.number;
                conflict.add();
            }
            next_state[i] = next;
            next_k++;
            map[next.r][next.c] = next;
        }
        micro_crowds = Arrays.copyOf(next_state, next_k);
        for (int i = 0; i < map.length; i++) {
            map[i] = Arrays.copyOf(next_map[i], map.length);
        }
    }

    public static int change_dir(int dir) {
        switch (dir) {
            case 1:
                return 2;
            case 2:
                return 1;
            case 3:
                return 4;
            case 4:
                return 3;
        }
        return 0;
    }


}