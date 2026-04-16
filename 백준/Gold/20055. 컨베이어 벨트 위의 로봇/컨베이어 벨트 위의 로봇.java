import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());   // 3<=n,m<=8

        int[] arr = new int[2 * n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 2 * n; i++) arr[i] = Integer.parseInt(st.nextToken());

        boolean[] robots = new boolean[n];
        int step = 1, cant = 0;
        while (true) {

            int tmp = arr[2 * n - 1];
            for (int i = 2 * n - 1; i > 0; i--) arr[i] = arr[i - 1];    // 컨베이어벨트 회전
            for (int i = n - 2; i > 0; i--) robots[i] = robots[i - 1];  // 로봇 이동
            arr[0] = tmp;
            robots[n-1] = false;    // N 도달시 바로 내리기
            robots[0] = false;      // 0은 한 사이클에서 반드시 비워진다. : 2 번 동작 위해서 false처리 필요

            for (int i = n - 1; i > 0; i--) {
                if (arr[i] >= 1 && !robots[i] && robots[i-1]) { // 전 단계에 로봇이 있어야 이동
                    robots[i] = robots[i - 1];
                    robots[i-1] = false;    // 이동한 칸은 비워두기 : 위 조건문 꼬임
                    if (--arr[i] == 0) cant++;
                }
            }
            robots[n-1] = false;    // n-1 도달한 건 바로 내리기

            if (arr[0] >= 1) {
                robots[0] = true;   // 올리기 수행
                if (--arr[0] == 0) cant++;
            }

            if (cant >= k) break;
            step++;
        }
        System.out.println(step);
    }

    static int n, k;
}