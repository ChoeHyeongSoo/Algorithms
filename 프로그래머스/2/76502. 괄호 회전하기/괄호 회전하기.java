import java.util.Deque;
import java.util.Stack;

class Solution {
    public int solution(String s) {
        int answer = 0;

        for (int i = 0; i < s.length(); i++) {
            if (check(s)) answer++;
            s = s.substring(1) + s.charAt(0);
        }

        return answer;
    }

    public boolean check(String s) {

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {

            char c = s.charAt(i);

            if (c == '[' || c == '{' || c == '(')
                stack.push(c);
            else
                if (stack.isEmpty()) return false;

            switch (c) {
                case ']':
                    if (stack.pop()!='[')
                        return false;
                    break;
                case '}':
                    if (stack.pop()!='{')
                        return false;
                    break;
                case ')':
                    if (stack.pop()!='(')
                        return false;
                    break;
            }
        }

        return stack.isEmpty();
    }
}