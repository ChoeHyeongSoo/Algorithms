package DataStructure.Array;

import java.util.Scanner;

public class Test_0209_Array_ZigZagLoop {
    /*
    2차원 배열의 행의 길이 N과 열의 길이 M이 주어진다.
    가장 왼쪽 위에서부터 오른쪽 방향으로 출발하여 지그재그 형태로 배열을 탐색하며 2부터 시작하여 2의 승수를 하나씩 증가시키며 채워 넣어라.
    [제약 조건]
    • 1 ≤ N, M ≤ 6
     */
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for (int tc = 1; tc <= T; tc++) {

            int n = sc.nextInt();
            int m = sc.nextInt();

            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < n*m; i+=m) {
                for (int j = 0; j < m; j++) {
                    long tmp = 0 ;      // 제약조건, n=m=6인 케이스, long 타입 필요
                    if ((i/m)%2 == 0) {
                        tmp = 2<<i+j;
                    } else {
                        tmp = 2<<i+m-1-j;
                    }
                    sb.append(tmp + " ");
                }
                if (i > n*m - m - 1) break; // 출력 형태로 인해 마지막 줄은 공백 추가 x
                sb.append("\n");
            }

            System.out.println("#" + tc + "\n" + sb.toString());

        }
    }

}
