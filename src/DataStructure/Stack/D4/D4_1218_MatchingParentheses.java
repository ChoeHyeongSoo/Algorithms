package DataStructure.Stack.D4;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

class D4_1218_MatchingParentheses {

    public static void main(String args[]) throws Exception {

//         System.setIn(new FileInputStream("res/1218_input.txt"));

        Scanner sc = new Scanner(System.in);
        int T;
//        T = sc.nextInt();
        T = 10;
        Map<Character, Character> moonza = new HashMap<>();
        moonza.put(')', '(');
        moonza.put('}', '{');
        moonza.put(']', '[');
        moonza.put('>', '<');
        for (int test_case = 1; test_case <= T; test_case++) {

            int n = sc.nextInt();   // 문자열 길이와 일치
            String line = sc.next();
            Stack<Character> op = new Stack<>();
            int ans = 1;
            for (int i = 0; i < n; i++) {
                char c = line.charAt(i);
                if (moonza.containsValue(c)) op.push(c);
                else if (moonza.containsKey(c)) {
                    if (!(op.pop()==moonza.get(c))) {ans=0; break;}
                }
//                else {ans=0; break;}          // 예외 케이스 존재x
//                if (!(moonza.containsValue(c)) || !(moonza.containsKey(c))) {ans=0; break;}   // c=='{' || c=='[' || c=='(' || c=='<'
            }
//            if (op.isEmpty()) ans = 1;        // ans = 1로 초기화하면 안 해도 됨

            System.out.println("#" + test_case + " " + ans);
        }
    }


}
