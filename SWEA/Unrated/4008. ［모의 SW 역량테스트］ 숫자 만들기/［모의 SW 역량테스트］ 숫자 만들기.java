import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Solution {
  
    public static void main(String args[]) throws Exception {
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