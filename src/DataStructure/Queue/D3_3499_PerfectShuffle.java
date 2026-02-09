package DataStructure.Queue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class D3_3499_PerfectShuffle {
    /*
    카드를 퍼펙트 셔플 한다는 것은, 카드 덱을 정확히 절반으로 나누고 나눈 것들에서 교대로 카드를 뽑아 새로운 덱을 만드는 것을 의미
    만약 N이 홀수이면, 교대로 놓을 때 먼저 놓는 쪽에 한 장이 더 들어가게 하면 된다
     */
    public static void main(String args[]) throws Exception {
        //System.setIn(new FileInputStream("res/input.txt"));

        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {
            int n = sc.nextInt();
            String[] words = new String[n];
            Queue<String> top = new LinkedList<>();
            Queue<String> bot = new LinkedList<>();

            boolean odd = false;
            if (n % 2 == 1) odd = true;

            int cnt = 0;
//            if (odd) {
//                while (cnt < n / 2 + 1) {
//                    top.add(sc.next());
//                    cnt++;
//                }
//            }
//            else {
//                while (cnt < n / 2) {
//                    top.add(sc.next());
//                    cnt++;
//                }
//            }
            while (cnt < (n+1)/2) { // 짝수 홀수 모든 경우에 idx 범위 컨트롤 가능
                top.add(sc.next());
                cnt++;
            }
            while (cnt < n) {
                bot.add(sc.next());
                cnt++;
            }
            StringBuilder sb = new StringBuilder();
            while (!bot.isEmpty()) {
                sb.append(top.poll() + " ");    // 위에서 한 장
                sb.append(bot.poll() + " ");    // 아래서 한 장
            }
            if (odd)
                sb.append(top.poll());  // 홀수인 경우 마지막 한 장


            System.out.println("#" + test_case + " " + sb.toString());
        }
    }

}