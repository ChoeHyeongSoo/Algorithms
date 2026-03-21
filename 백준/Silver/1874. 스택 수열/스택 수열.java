import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;
public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());

        Stack<Integer> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        int curr = 0; boolean can = true;
        stack.push(curr++);
        outer_loop:
        for (int i = 0; i < n; i++) {
            int x = Integer.parseInt(br.readLine());
            while (stack.peek()!=x) {
                if (stack.peek() < x) {
                    while (stack.peek()!=x) {
                        stack.push(curr++);
                        sb.append('+').append('\n');
                    }
                } else {
                    can = false; break outer_loop;
                }
            }
            stack.pop(); sb.append('-').append('\n');
        }
        System.out.println(can ? sb : "NO");
    }
    static int n;
}