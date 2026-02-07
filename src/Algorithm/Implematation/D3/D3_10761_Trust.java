package Algorithm.Implematation.D3;

import java.io.FileInputStream;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Order {

    String name;
    int location;

    public Order() {
    }

    public Order(String name, int location) {
        this.name = name;
        this.location = location;

    }
}

class D3_10761_Trust {

    public static void main(String args[]) throws Exception {

        System.setIn(new FileInputStream("res/robo.txt"));

        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();

        for (int test_case = 1; test_case <= T; test_case++) {

            int iter = sc.nextInt();
            // 입력받을 구조 설정 중요
            Order[] orders = new Order[iter];
            Queue<Order> common = new LinkedList<>();
            Queue<Order> B = new LinkedList<>(), O = new LinkedList<>();
            Order[] temp = {new Order("B", 1), new Order("O", 1)};

            for (int i = 0; i < iter; i++) {
                String s = sc.next();
                orders[i] = new Order(s, sc.nextInt());
                common.add(orders[i]);
                if (s.equals("B")) B.add(orders[i]);
                else O.add(orders[i]);
            }
            // 조건1) 1 action/sec 조건2) 동시에 버튼 x, 동시에 이동 o
            // 큐 : 공통 명령 순서, B, O, 각각 순서 큐 -> 액션 ? || B와 O의 각각 병렬 액션은 Stack으로도 처리 가능
            int ans = 0;
            Order o_action = new Order(), b_action = new Order();
            while (!common.isEmpty()) {
                Order now = common.poll();
                int b_x = temp[0].location, o_x = temp[1].location, target_x = now.location;
                int next_o_x = o_action.location, next_b_x = b_action.location;

                if (now.name.equals("B")) {
                    B.poll();
                    if (!O.isEmpty())
                        o_action = O.element();

                    while (b_x != target_x) {          // 첫 명령 이동 수행
                        if (b_x < target_x) b_x++;
                        else b_x--;
                        if (o_x < next_o_x) o_x++;      // o도 다음 명령 수행을 위해 이동
                        else if (o_x > next_o_x) o_x--;
                        ans++;
                    }

                    if (o_x < next_o_x)         // 버튼 누르는 1초동안 o는 이동에 한해서 동작 가능
                        o_x++;
                    else if (o_x > next_o_x)
                        o_x--;
                    ans++; // push button
                }
                else {
                    O.poll();
                    if (!B.isEmpty())
                        b_action = B.element();

                    while (o_x != target_x) {
                        if (o_x < target_x) o_x++;
                        else o_x--;
                        if (b_x < next_b_x) b_x++;
                        else if (b_x > next_b_x) b_x--;
                        ans++;
                    }

                    if (b_x < next_b_x) b_x++;
                    else if (b_x > next_b_x) b_x--;
                    ans++; // push btn
                }
            }


            System.out.println("#" + test_case + " " + ans);

        }
    }

}
