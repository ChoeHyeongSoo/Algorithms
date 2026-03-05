import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
class Solution { // 5644
    static int[] dir_r = {0, 0, 1, 0, -1}, dir_c = {0, -1, 0, 1, 0};
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            timeline = new int[2][m+1];
            for (int x = 0; x < 2; x++){
                st = new StringTokenizer(br.readLine());
                for (int i = 1; i <= m; i++)
                    timeline[x][i] = Integer.parseInt(st.nextToken());}
            Charger[] chargers = new Charger[c];
            for (int i = 0; i < c; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken()), y = Integer.parseInt(st.nextToken());
                int coverage = Integer.parseInt(st.nextToken());
                int performance = Integer.parseInt(st.nextToken());
                chargers[i] = new Charger(x, y, coverage, performance, i);
            }

            // 초단위 시뮬레이션, 각 충전량 합계
            User[] users = new User[2];
            users[0] = new User(1, 1); users[1] = new User(10, 10);
            total_charging = 0;
            for (int i = 0; i <= m; i++) {
                for (int x = 0; x < 2; x++) {
                    users[x].r += dir_r[timeline[x][i]];
                    users[x].c += dir_c[timeline[x][i]];
                }
                update(chargers, users);
            }

            System.out.println("#" + test_case + " " + total_charging);
        }
    }

    static int[][] timeline;
    static int total_charging;
    
    public static boolean is_in(User x, Charger c){
        return Math.abs(x.r - c.r) + Math.abs(x.c - c.c) <= c.coverage;
    }
    
    public static void update(Charger[] chargers, User[] users) {

        List<Integer>[] available = new ArrayList[2];
        for (int i = 0; i < 2; i++)
            available[i] = new ArrayList<>();

        for (int i = 0; i < 2; i++)  // 충전: 모든 충전기에 대해서 현재 위치가 커버리지 내에 있는지
            for (Charger c: chargers)
                if (is_in(users[i], c)) available[i].add(c.idx);

        int max = 0; // a, b 같은 충전 선택된 경우 - 다른 거 선택하는 거에 대한 합산 비교 - 비교군 없으면 해당값 사용
        if (available[0].isEmpty())
            for (int charge_b : available[1])
                max = Math.max(max, chargers[charge_b].performance);
        else if (available[1].isEmpty())
            for (int charge_a : available[0])
                max = Math.max(max, chargers[charge_a].performance);
        else {
            for (int charge_a : available[0]) {
                for (int charge_b : available[1]) {
                    if (charge_a == charge_b) max = Math.max(max, chargers[charge_a].performance);
                    else max = Math.max(max, chargers[charge_a].performance + chargers[charge_b].performance);
                }
            }
        }
        total_charging += max;
    }
}

class User {
    int r, c;

    public User(int r, int c) {
        this.r = r;
        this.c = c;
    }
}

class Charger{
    int r, c, coverage, performance, idx;

    public Charger(int r, int c, int coverage, int performance, int idx) {
        this.r = r;
        this.c = c;
        this.coverage = coverage;
        this.performance = performance;
        this.idx = idx;
    }
}