package Algorithm.Greedy.D5;

import java.io.BufferedReader;
import java.io.InputStreamReader;

class D5_6782_SqrtGame {
    /*
    어느 날 현주는 제곱근 놀이를 만들기로 했는데, 룰은 다음과 같다.
    1) 2이상의 어떤 정수 N이 있다. 2) N을 N+1로 바꿀 수 있다. 3) root(N)이 정수일 때, N을 으로 바꿀 수 있다.
    게임의 목표는 N을 2로 만드는 것이다. N을 2로 만들기 위해 조작해야 하는 횟수의 최솟값을 구하는 프로그램을 작성하라.
     */
    public static void main(String args[]) throws Exception {
        StringBuilder ans = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            ans.append("#").append(test_case).append(" ");
            long n = Long.parseLong(br.readLine());
            long cnt = 0;
            while (n>2) {
                // 가장 가까운 제곱수 찾기 - 거기까지 도달 ~ 반복
                if (n%Math.sqrt(n)!=0) {
                    long target = (long)Math.pow((long)Math.sqrt(n) + 1, 2);
                    cnt += target - n;
                    n = target;
                    if (n == 2) break;
                }
                n = (long) Math.sqrt(n);
                cnt++;
            }
            if (n<2) cnt = 2-n;
            ans.append(cnt).append('\n');
        }
        System.out.print(ans);
    }
}