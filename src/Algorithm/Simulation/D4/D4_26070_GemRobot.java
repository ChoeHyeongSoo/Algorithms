package Algorithm.Simulation.D4;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

class D4_26070_GemRobot {
    /*

     */
    public static void main(String args[]) throws Exception {
        System.setIn(new FileInputStream("res/input_D4_26070_GemRobot.txt"));
        StringBuilder ans = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            ans.append("#").append(test_case).append(" ");
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            gems = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    int tmp = Integer.parseInt(st.nextToken());
                    if (tmp>0) gems.add(new Gem(i, j, tmp));
                }
            }

            // 보석 좌표 리스트로 받기 - 상대좌표로 사분면 파악 - 회전수 고려
            robo = new Robot(0, 0, 0);
            int cnt = 0;
            Collections.sort(gems); // 1번 보석부터 찾도록 정렬
            for (Gem key : gems)
                cnt = next(key, cnt);

            ans.append(cnt).append('\n');
        }
        System.out.print(ans);
        System.out.println(Math.atan2(1, 1-10000)/ Math.PI);
    }

    public static int[] dir_r = {0, 1, 0, -1}, dir_c = {1, 0, -1, 0};
    public static int n;
    public static List<Gem> gems;
    public static Robot robo;

    public static int next(Gem key, int cnt) {

        // 좌표-방향 기준으로 상대위치 - 0: 우 1: 하 2: 좌 3: 상
        double angle = Math.atan2(key.r - robo.r, key.c - robo.c) / Math.PI;
        if (angle < 0) angle+=2;

        // 사분면 판단: atan의 결과에 현재 방향d로 가중치 부여 : 0 (+0.5) / 1 (+1) / 2(+1.5) / 3 (+0)
        switch (robo.d) {                              // 배열과 평면좌표는 상하가 반대... 적용도 반대..,
            case 0:
                angle += 0.5;
                break;
            case 2:
                angle += 1.5;
                break;
            case 3:
                angle += 1;
                break;
        }
        if (angle >= 2) angle-=2;

        // 보석은 테두리에 생기지 않음 - 좌측 보석에 대해 최스==3회 보장
        if (angle>=1 && angle < 1.5) {
            cnt+=2;
            robo.d = (robo.d + 2) % 4;
        } else if (angle > 0.5 && angle < 1) {
            cnt+=1;
            robo.d = (robo.d + 1) % 4;
        } else if (angle < 0.5 || angle >= 1.5) {
            cnt+=3;
            robo.d = (robo.d == 0) ? 3 : robo.d - 1;
        }

        robo.r = key.r; robo.c = key.c;

        return cnt;
    }
}

class Robot {
    int r, c, d;

    public Robot(int r, int c, int d) {
        this.r = r;
        this.c = c;
        this.d = d;
    }
}

class Gem implements Comparable<Gem> {
    int r, c, no;

    public Gem(int r, int c, int no) {
        this.r = r;
        this.c = c;
        this.no = no;
    }

    @Override
    public int compareTo(Gem o) {
        return this.no - o.no;
    }
}