package Algorithm.Implementation.D4;

import java.util.Scanner;
import java.util.Stack;

class D4_5432_CutBars { // Stack의 예시 : boolean을 통해서 스위치 동작 가능

    public static void main(String args[]) throws Exception {

//         System.setIn(new FileInputStream("res/input_D3_SW_8th_Cipher.txt"));

        Scanner sc = new Scanner(System.in);
        int T;
        T = sc.nextInt();
        for (int test_case = 1; test_case <= T; test_case++) {
            String line = sc.next();
            int ans_stack_ver = stack_ver(line);
            int ans_non_stack_ver = non_stack_ver(line);
            System.out.println("#" + test_case + " " + ans_non_stack_ver);
        }
    }

    public static int stack_ver(String line) {
        int bars = 0;
        Stack<Integer> s = new Stack<>();
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (c == '(') {
                char next = line.charAt(i+1);
                if (next=='(') {
                    s.push(1);
                }
            } else if (c == ')') {
                char prev = line.charAt(i-1);
                if (prev=='('){bars+=s.size();}
                else {bars++;s.pop();}
            }
        }
        return bars;
    }

    public static int non_stack_ver(String line) {
        boolean open = false;
        boolean close = false;
        int temp = 0;
        int bars = 0;
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (c == '(') {
                if (!open) {
                    open = true;
                }
                else {
                    temp++;
                }
                close = false;
            } else if (c == ')') {
                if (!close) {
                    close = true;
                    bars+=temp;
                }
                else {
                    bars++; temp--;
                }
                open = false;
            }
        }
        return bars;
    }

}
