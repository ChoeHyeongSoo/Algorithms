import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;
public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        queue = new LinkedList<>();
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
    static int n;
    static List<Integer> queue;
    static StringBuilder sb;
    public static void operation(String s, int x) {
        switch (s) {
            case "push":
                queue.add(x);
                break;
            case "pop":
                if (queue.isEmpty()) sb.append(-1).append('\n');
                else {sb.append(queue.get(0)).append('\n');
                queue.remove(0);}
                break;
            case "size":
                sb.append(queue.size()).append('\n');
                break;
            case "empty":
                if (queue.isEmpty()) sb.append(1).append('\n');
                else sb.append(0).append('\n');
                break;
            case "front":
                if (queue.isEmpty()) sb.append(-1).append('\n');
                else sb.append(queue.get(0)).append('\n');
                break;
            case "back":
                if (queue.isEmpty()) sb.append(-1).append('\n');
                else sb.append(queue.get(queue.size()-1)).append('\n');
                break;
        }
    }
}