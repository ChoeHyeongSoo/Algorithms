package DataStructure.Stack.D4;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

class Calculator {
    StringBuilder postfix;
    Stack<Character> operator;
    Stack<Integer> result;

    public Calculator(StringBuilder postfix, Stack<Character> operator, Stack<Integer> result) {
        this.postfix = postfix;
        this.operator = operator;
        this.result = result;
    }
}

public class D4_1224_Calculator3 {

    public static void main(String args[]) throws Exception {

        System.setIn(new FileInputStream("res/Algorithm/DFS/input_D4_1224_Calculator3_recursive.txt"));

        Scanner sc = new Scanner(System.in);
        int T;
//        T=sc.nextInt();
        T = 10;

        Map<Character, Integer> priority = new HashMap<>();
        priority.put('(', 0);
        priority.put('*', 2);
        priority.put('/', 2);
        priority.put('+', 1);
        priority.put('-', 1);

        for(int test_case = 1; test_case <= T; test_case++)
        {
            int n = sc.nextInt();
            String line = sc.next();

            Calculator calculator = new Calculator(new StringBuilder(), new Stack<>(), new Stack<>());

            convert_to_postfix(n, line, priority, calculator);

            calculation(n, line, calculator);

            int ans = calculator.result.pop();

            System.out.println("#" + test_case + " " + ans);

        }
    }

    public static void convert_to_postfix(int n, String line, Map<Character, Integer> priority, Calculator calculator) {
        for (int i = 0; i < n; i++) {
            char c = line.charAt(i);
            if (c >= '0' && c <= '9')
                calculator.postfix.append(c);
            else if (c=='(')
                calculator.operator.push(c);
            else if (c==')') {
                while (calculator.operator.peek() != '('){
                    calculator.postfix.append(calculator.operator.pop());
                }
                calculator.operator.pop();
            }
            else {
                if (calculator.operator.isEmpty()) calculator.operator.push(c);
                else {
                    while (!calculator.operator.isEmpty() && (priority.get(calculator.operator.peek())) >= priority.get(c))
                        calculator.postfix.append(calculator.operator.pop());
                    calculator.operator.push(c);
                }
            }
        }
        while (!calculator.operator.isEmpty())
            calculator.postfix.append(calculator.operator.pop());
    }

    private static void calculation(int n, String line, Calculator calculator) {

        for (int i = 0; i < calculator.postfix.length(); i++) {
            char c = calculator.postfix.charAt(i);
            if (c >= '0' && c <= '9')
                calculator.result.push(c - '0');
            else {
                int y = calculator.result.pop();
                int x = calculator.result.pop();

                switch (c) {
                    case '+':
                        calculator.result.push(x+y);
                        break;
                    case '*':
                        calculator.result.push(x*y);
                        break;
                    case '/':
                        calculator.result.push(x/y);
                        break;
                    case '-':
                        calculator.result.push(x-y);
                        break;
                }
            }
        }

    }

}
