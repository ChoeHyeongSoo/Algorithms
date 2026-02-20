package Algorithm.Graph.DFS;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

class D4_1224_Calculator3_recursive {

    static int idx = 0;

    public static void main(String args[]) throws Exception
    {
        System.setIn(new FileInputStream("res/Algorithm/DFS/input_D4_1224_Calculator3_recursive.txt"));

        Scanner sc = new Scanner(System.in);
        int T;
//        T=sc.nextInt();
        T = 10;

        for(int test_case = 1; test_case <= T; test_case++)
        {
            int n = sc.nextInt();
            String line = sc.next();

            idx = 0;
            long ans = cal(n, line);

            System.out.println("#" + test_case + " " + ans);

        }
    }

    public static long cal(int n, String line) {
        Stack<Long> number = new Stack<>();
        Stack<Character> operator = new Stack<>();
        while (idx < n) {
            char c = line.charAt(idx++);
            if (c >= '0' && c <= '9') number.push((long) c - '0');
            else {
                if (c == '*' || c == '/') {   // *, /는 number에서 pop한 것과 다음 숫자를 연산 후 다시 push
                    char nextChar = line.charAt(idx++);
                    long tmp = number.pop();
                    if (nextChar == '(') {    // 재귀 구현: '('에서 스택 생성 후, ')' 만날 때까지 재귀
                        number.push(tmp * cal(n, line));
                    } else {
                        if (c == '*')
                            number.push(tmp * ((long) nextChar - '0'));
                        if (c == '/')
                            number.push(tmp / ((long) nextChar - '0'));
                    }
                } else if (c == '+' || c == '-') {
                    if (operator.isEmpty())
                        operator.push(c);
                    else {
                        char saved_operation = operator.pop();
                        long tmp = number.pop();
                        switch (saved_operation) {
                            case '*':
                                tmp *= number.pop();
                                break;
                            case '/':
                                tmp /= number.pop();
                                break;
                            case '+':
                                operator.push(saved_operation);
                                break;
                            case '-':
                                operator.push(saved_operation);
                                break;
                        }
                        number.push(tmp);
                        operator.push(c);
                    }
                } else if (c == ')') {  // ')'는 재귀 종료 조건 : 스택 비우며 연산
                    long add = number.pop();
                    while (!operator.isEmpty()) {
                        char next_operation = operator.pop();
                        if (next_operation == '*') add *= number.pop();
                        if (next_operation == '/') add /= number.pop();
                        if (next_operation == '+') add += number.pop();
                        if (next_operation == '-') add -= number.pop();
                    }
                    return add;
                } else number.push(cal(n, line));
            }
        }
        long ans = number.pop();
        while (!number.isEmpty()) ans+=number.pop();

        return ans;
    }

}