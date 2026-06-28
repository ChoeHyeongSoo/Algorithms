import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        int q = Integer.parseInt(br.readLine());

        HashMap<Integer, Node> hashMap = new HashMap<>();
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < q; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int op = Integer.parseInt(st.nextToken());
            int id = Integer.parseInt(st.nextToken());
            hashMap.computeIfAbsent(id, k -> new Node(id));
            Node tmp = hashMap.get(id), in = null;
            int target = 0;
            if (st.hasMoreTokens()) {
                target = Integer.parseInt(st.nextToken());
                if (!hashMap.containsKey(target)) hashMap.put(target, new Node(target));
                in = hashMap.get(target);
            }

            switch (op) {
                case 1:
                    if (tmp.prev != null) tmp.prev.next = tmp.next;
                    if (tmp.next != null) tmp.next.prev = tmp.prev;
                    tmp.next = null; tmp.prev = null;
                    break;
                case 2:
                    if (tmp.prev != null) {
                        in.prev = tmp.prev; tmp.prev.next = in;
                    }
                    in.next = tmp; tmp.prev = in;
                    break;
                case 3:
                    if (tmp.next != null) {
                        in.next = tmp.next; tmp.next.prev = in;
                    }
                    in.prev = tmp; tmp.next = in;
                    break;
                case 4:
                    ans.append((tmp.prev!=null?tmp.prev.idx:0) + " " + (tmp.next!=null?tmp.next.idx:0) + "\n");
            }
        }
        for (int i = 1; i <= n; i++) {
            Node v = hashMap.get(i);
            ans.append(((v==null || v.next==null)?0: v.next.idx) +  " ");
        }
        System.out.println(ans);
    }

    static int n;
}

class Node {
    int idx;
    Node prev, next;

    public Node(int idx) {
        this.idx = idx;
    }
}