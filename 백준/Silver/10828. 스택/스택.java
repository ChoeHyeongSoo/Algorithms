import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;
public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); idx = 0;
        stack = new LinkedList<>();
        sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            String op = st.nextToken();
            int x = 0;
            if (st.hasMoreTokens()) x = Integer.parseInt(st.nextToken());
            operation(op, x);
        }
        System.out.println(sb);
    }
    static int n, idx;
    static List<Integer> stack;
    static StringBuilder sb;
    public static void operation(String s, int x) {
        switch (s) {
            case "push":
                stack.add(idx++, x);
                break;
            case "pop":
                if (stack.isEmpty()) sb.append(-1).append('\n');
                else { sb.append(stack.get(idx-1)).append('\n');
                stack.remove(--idx);}
                break;
            case "size":
                sb.append(stack.size()).append('\n');
                break;
            case "empty":
                if (idx==0) sb.append(1).append('\n');
                else sb.append(0).append('\n');
                break;
            case "top":
                if (idx==0) sb.append(-1).append('\n');
                else sb.append(stack.get(idx-1)).append('\n');
                break;
        }
    }
}