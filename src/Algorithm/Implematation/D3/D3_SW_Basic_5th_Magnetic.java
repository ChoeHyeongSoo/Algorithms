package Algorithm.Implematation.D3;

import java.io.FileInputStream;
import java.util.Scanner;

class D3_SW_Basic_5th_Magnetic {
    /*
    자성체들은 성질에 따라 S극은 N극에 이끌리고, N극은 S극에 이끌리는 성질이 있다.
    테이블에 일정 간격을 두고 강한 자기장을 걸었을 때, 시간이 흐른 뒤에 자성체들이 서로 충돌하여 테이블 위에 남아있는 교착 상태의 개수를 구하라.
    [제약 사항]
    자성체는 테이블 앞뒤 쪽에 있는 N극 또는 S극에만 반응하며 자성체끼리는 전혀 반응하지 않는다.
    테이블의 크기는 100x100으로 주어진다.
    [입력]
    - 10개의 테스트 케이스가 주어진다.
    - 각 테스트 케이스의 첫 번째 줄에는 정사각형 테이블의 한 변의 길이가 주어진다. (이 값은 항상 100이다)
    - 그 다음 줄부터 100 x 100크기의 테이블의 초기 모습이 주어진다. 1은 N극 성질을 가지는 자성체를 2는 S극 성질을 가지는 자성체를 의미하며 테이블의 윗부분에 N극이 아래부분에 S극이 위치한다고 가정한다.
     */
    public static void main(String args[]) throws Exception {
        System.setIn(new FileInputStream("res/input_SW_Basic_5th_Magnetic.txt"));

        Scanner sc = new Scanner(System.in);
        int T;
//        T = sc.nextInt();
        T = 10;

        for (int test_case = 1; test_case <= T; test_case++) {
            int n = sc.nextInt();

            int[][] table = new int[n][n];
            for (int i = 0; i < n; i++) {       // n개의 List or SB 사용하여 받으면 나중에 계산이 편하다
                for (int j = 0; j < n; j++) {   // 하지만 비효율적이래
                    table[i][j] = sc.nextInt();
                }
            }

            int stuck = 0;  // 교착 상태
            stuck = getStuck(n, table);
//            stuck = getStuck_StringBuilder(n, table);

            System.out.println("#" + test_case + " " + stuck);
        }
    }

    private static int getStuck(int n, int[][] table) {
        int stuck = 0;
        for (int i = 0; i < n; i++) {   // 한 방향 선택 : N자성체 기준 0->100으로 탐색
            boolean state = false;      // 반복문에서 출력 오차가 적은 경우, 변수 초기화 위치 유의
            for (int j = 0; j < n; j++) {
                if (table[j][i]==0) continue;
                if (table[j][i]==1) state = true;
                if (state && table[j][i]==2) {
                    stuck++;
                    state = false;
                }
            }
        }
        return stuck;
    }

    private static int getStuck_StringBuilder(int n, int[][] table) {
        int stuck = 0;
        for (int i = 0; i < n; i++) {   // 한 방향 선택 : N자성체 기준 0->100으로 탐색
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < n; j++) {
                if (table[j][i] == 0) continue;
                sb.append(table[j][i]);
            }
            int idx = sb.indexOf("1");
            if (idx == -1) continue;

            // 1. 입력 받을 때, 100개의 sb에 각 index를 입력받았으면 편하게 계산 가능 (0 제거)
            while (idx < sb.length()) {
                sb = sb.delete(0, idx+1);   // 지우는 거 메모리 낭비
                int next = sb.indexOf("2");
                if (next == -1) break;
                stuck++;
                sb = sb.delete(0, next+1);
                idx = sb.indexOf("1");
                if (idx == -1) break;
            }

            // 2. 단순비교 - 굳이 문자열에 넣을 필요가 없어진다.
            while (idx < sb.length()-1) {
                if (sb.charAt(idx)=='1' && sb.charAt(idx+1)=='2') {
                    stuck++; idx+=2;
                }
                else idx++;
            }
        }
        return stuck;
    }


}