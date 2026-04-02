import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;
public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken()); b = Integer.parseInt(st.nextToken());


            Deque<Node> q = new ArrayDeque<>();
            boolean[] visit = new boolean[10001];
            visit[a] = true; q.offer(new Node(a));

            while (!q.isEmpty()) {
                Node curr = q.poll();
                if (curr.num==b) { sb.append(curr.orders); break; }
                for (int order = 1; order <= 4; order++) {
                    int next = opertaion(order, curr.num);
                    if (visit[next]) continue;
                    visit[next] = true;
                    Node candidate = new Node(next);
                    candidate.orders.append(curr.orders).append(transform(order));
                    q.offer(candidate);
                }
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }
    static int a, b;

    static int opertaion(int order, int curr) {

        switch (order) {
            case 1:
                return (curr * 2)%10000;
            case 2:
                return (curr>0) ? curr-1 : 9999;
            case 3:
                int d1 = curr/1000; curr%=1000;
                int d2 = curr/100; curr%=100;
                int d3 = curr/10; curr%=10;
                int d4 = curr;
                return d2*1000+d3*100+d4*10+d1;
            case 4:
                int b1 = curr/1000; curr%=1000;
                int b2 = curr/100; curr%=100;
                int b3 = curr/10; curr%=10;
                int b4 = curr;
                return b4*1000+b1*100+b2*10+b3;
        }
        return 0;
    }

    public static char transform(int order){
        switch (order){
            case 1: return 'D';
            case 2: return 'S';
            case 3: return 'L';
            case 4: return 'R';
        }
        return '0';
    }

}
class Node{
    int num;
    StringBuilder orders = new StringBuilder();

    public Node(int num) {
        this.num = num;
    }
}