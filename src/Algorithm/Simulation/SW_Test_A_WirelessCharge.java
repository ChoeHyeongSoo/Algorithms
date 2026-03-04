package Algorithm.Simulation;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class SW_Test_A_WirelessCharge { // 5644
    /*
    스마트폰을 무선 충전 할 때 최적의 BC (Battery Algorithm.Simulation.Charger)를 선택하는 알고리즘을 개발하고자 한다.
    매초마다 특정 BC의 충전 범위에 안에 들어오면 해당 BC에 접속이 가능하다. 따라서 T=5에 사용자 A는 BC 3에, 사용자 B는 BC 2에 접속할 수 있다. 이때, 접속한 BC의 성능(P)만큼 배터리를 충전 할 수 있다.
    만약 한 BC에 두 명의 사용자가 접속한 경우, 접속한 사용자의 수만큼 충전 양을 균등하게 분배한다.
    BC의 정보와 사용자의 이동 궤적이 주어졌을 때, 모든 사용자가 충전한 양의 합의 최댓값을 구하는 프로그램을 작성하라.
    [제약사항]
    1. 지도의 가로, 세로 크기는 10이다.
    2. 사용자는 총 2명이며, 사용자A는 지도의 (1, 1) 지점에서, 사용자B는 지도의 (10, 10) 지점에서 출발한다.
    3. 총 이동 시간 M은 20이상 100이하의 정수이다. (20 ≤ M ≤ 100)
    4. BC의 개수 A는 1이상 8이하의 정수이다. (1 ≤ A ≤ 8)
    5. BC의 충전 범위 C는 1이상 4이하의 정수이다. (1 ≤ C ≤ 4)
    6. BC의 성능 P는 10이상 500이하의 짝수이다. (10 ≤ P ≤ 500)
    7. 사용자의 초기 위치(0초)부터 충전을 할 수 있다.
    8. 같은 위치에 2개 이상의 BC가 설치된 경우는 없다. 그러나 사용자A, B가 동시에 같은 위치로 이동할 수는 있다. 사용자가 지도 밖으로 이동하는 경우는 없다.
    */
    static int[] dir_r = {0, 0, 1, 0, -1}, dir_c = {0, -1, 0, 1, 0};
    public static void main(String args[]) throws Exception {
        System.setIn(new FileInputStream("res/Algorithm/Simulation/input_SW_Test_A_WirelessCharge.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());   // 총 이동시간
            int c = Integer.parseInt(st.nextToken());   // BC 개수
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

            User[] users = new User[2];
            users[0] = new User(1, 1); users[1] = new User(10, 10);
            total_charging = 0;
            for (int i = 0; i <= m; i++) {    // 초단위 시뮬레이션, 각 충전량 합계
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