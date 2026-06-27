import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        cur = new Node(br.readLine());
        n = Integer.parseInt(br.readLine());
        sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int op = Integer.parseInt(st.nextToken());
            Node tmp = null;
            if (st.hasMoreTokens())
                tmp = new Node(st.nextToken());
            switch (op) {
                case 1:
                    tmp.next = cur;
                    if ( cur.prev != null) {
                        cur.prev.next = tmp;
                        tmp.prev = cur.prev;
                    }
                    cur.prev = tmp;
                    printing();
                    break;
                case 2:
                    tmp.prev = cur;
                    if ( cur.next != null) {
                        cur.next.prev = tmp;
                        tmp.next = cur.next;
                    }
                    cur.next = tmp;
                    printing();
                    break;
                case 3:
                    if (cur.prev!=null) cur = cur.prev;
                    printing();
                    break;
                case 4:
                    if (cur.next!=null) cur = cur.next;
                    printing();
                    break;
            }
        }
        System.out.println(sb);

    }
    static int n;
    static StringBuilder sb;
    static Node cur;

    public static void printing() {
        sb.append((cur.prev==null ? "(Null)" : cur.prev.value) + " "
                + cur.value + " " + (cur.next==null ? "(Null)" : cur.next.value) + "\n");
    }
}

class Node {
    String value;
    Node prev, next;

    public Node(String value) {
        this.value = value;
    }
}