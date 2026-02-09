package DataStructure.Queue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class D3_1225_PWGenerator {
    /*
    - 8개의 숫자를 입력 받는다.
    - 첫 번째 숫자를 1 감소한 뒤, 맨 뒤로 보낸다.
    다음 첫 번째 수는 2 감소한 뒤 맨 뒤로, 그 다음 첫 번째 수는 3을 감소하고 맨 뒤로, 그 다음 수는 4, 그 다음 수는 5를 감소한다.
    이와 같은 작업을 한 사이클이라 한다.
    - 숫자가 감소할 때 0보다 작아지는 경우 0으로 유지되며, 프로그램은 종료된다. 이 때의 8자리의 숫자 값이 암호가 된다.
    [제약 사항]
    주어지는 각 수는 integer 범위를 넘지 않는다.
    마지막 암호 배열은 모두 한 자리 수로 구성되어 있다.
     */
    public static void main(String args[]) throws Exception {
        //System.setIn(new FileInputStream("res/input.txt"));

        Scanner sc = new Scanner(System.in);
        int T;
//        T = sc.nextInt();
        T = 10;

        for (int test_case = 1; test_case <= T; test_case++) {
            int tc = sc.nextInt();
            int n = 8;
            Queue<Integer> pw = new LinkedList<>();

            for (int i = 0; i < n; i++) {
                pw.add(sc.nextInt());
            }

            int diff = 0, target = 0;
            while (true) {
                diff = diff % 5 + 1;
                target = pw.poll();
                if (target - diff <= 0) {   // 마지막 0이 나오면 탈출
                    target = 0;
                    pw.add(target);         // 꼭 큐에 추가 안 하고 정답 끝에 붙여도 상관 없다
                    break;
                }
                target -= diff;
                pw.add(target);
            }

            StringBuilder sb = new StringBuilder();

            while (!pw.isEmpty())
                sb.append(pw.poll() + " ");

            System.out.println("#" + test_case + " " + sb.toString());
        }
    }

}