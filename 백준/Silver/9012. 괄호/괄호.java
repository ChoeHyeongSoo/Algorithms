import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;
public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        Stack<Character> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < line.length(); j++) {
                char c = line.charAt(j);
                if (c=='(') stack.push(c);
                else {
                    if (stack.isEmpty()) {
                        stack.push(c); break;
                    } else stack.pop();
                }
            }
            sb.append(stack.isEmpty()?"YES":"NO").append('\n');
            stack.clear();
        }
        System.out.println(sb);
    }
    static int n;
}