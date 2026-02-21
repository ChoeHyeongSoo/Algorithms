package Algorithm.BackTracking.Permutation;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class SW_Test_A_MakeNumber {
    /*
    N개의 숫자가 적혀 있는 게임 판이 있고, +, -, x, / 의 연산자 카드를 숫자 사이에 끼워 넣어 다양한 결과 값을 구해보기로 했다.
    수식을 계산할 때 연산자의 우선 순위는 고려하지 않고 왼쪽에서 오른쪽으로 차례대로 계산한다.
    예를 들어 1, 2, 3 이 적힌 게임 판에 +와 x를 넣어 1 + 2 * 3을 만들면 1 + 2를 먼저 계산하고 그 뒤에 * 를 계산한다.
    즉 1+2*3의 결과는 9이다. 주어진 연산자 카드를 사용하여 수식을 계산했을 때 그 결과가 최대가 되는 수식과 최소가 되는 수식을 찾고, 두 값의 차이를 출력하시오.
     [제약사항]
    1. 시간 제한 : 최대 50 개 테스트 케이스를 모두 통과하는 데 C / C++ / Java 모두 3 초
    2. 게임 판에 적힌 숫자의 개수 N 은 3 이상 12 이하의 정수이다. ( 3 ≤ N ≤ 12 )
    3. 연산자 카드 개수의 총 합은 항상 N - 1 이다.
    4. 게임 판에 적힌 숫자는 1 이상 9 이하의 정수이다.
    5. 수식을 완성할 때 각 연산자 카드를 모두 사용해야 한다.
    6. 숫자와 숫자 사이에는 연산자가 1 개만 들어가야 한다.
    7. 완성된 수식을 계산할 때 연산자의 우선 순위는 고려하지 않고, 왼쪽에서 오른쪽으로 차례대로 계산한다.
    8. 나눗셈을 계산 할 때 소수점 이하는 버린다.
    9. 입력으로 주어지는 숫자의 순서는 변경할 수 없다.
    10. 연산 중의 값은 -100,000,000 이상 100,000,000 이하임이 보장된다.
     */
    public static void main(String args[]) throws Exception {
        System.setIn(new FileInputStream("res/Algorithm/BackTracking/Permutation/input_SW_Test_A_MakeNumber.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            int n = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            int[] operators = new int[4];
            int[] numbers = new int[n];

            for (int i = 0; i < 4; i++) {
                operators[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                numbers[i] = Integer.parseInt(st.nextToken());
            }

            int[] max_n_min = {Integer.MIN_VALUE, Integer.MAX_VALUE};

//            max_n_min = cal(numbers, operators, 0, 0, max_n_min);
            cal(numbers, operators, 0, numbers[0], max_n_min);

            System.out.println("#" + test_case + " " + (max_n_min[0] - max_n_min[1]));
        }
    }

    public static void cal(int[] numbers, int[] operator, int depth, int tmp, int[] max_n_min) {
        // 현재 수 기준으로 뒤에 올 연산자, 다음 수 계산이면 연산자의 수만큼 (n-1)
        // 깊이까지의 합을 기준으로 연산자와 현재 수를 계산할 거면 숫자의 위치만큼 (n) - 대신 처음 입력도 1부터 시작
        if (depth == numbers.length-1) {    // 연산자 수만큼 계산 중이니, n-1이 되어야 한다
            if (max_n_min[0] < tmp) max_n_min[0] = tmp;
            if (max_n_min[1] > tmp) max_n_min[1] = tmp;
//            max_n_min[0] = Math.max(max_n_min[0], tmp);
//            max_n_min[1] = Math.min(max_n_min[1], tmp);
            return;
        }

        for (int i = 0; i < 4; i++) {
            if (operator[i] > 0){
                operator[i]--;

                int curr = operation(tmp, numbers[depth+1], i);
                cal(numbers, operator, depth+1, curr, max_n_min);

                operator[i]++;
            }
        }
    }

    public static int operation(int x, int y, int op) {

        switch (op) {
            case 0:
                return x+y;
            case 1:
                return x - y;
            case 2:
                return x * y;
            case 3:
                return x / y;
        }

        return 0;
    }

}