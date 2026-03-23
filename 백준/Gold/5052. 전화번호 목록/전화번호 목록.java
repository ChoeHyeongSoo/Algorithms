import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int tc = 0; tc < T; tc++) {
            n = Integer.parseInt(br.readLine());
            boolean consistency = true;
            numbs = new node[10];
            for (int iter = 0; iter < n; iter++) {
                line = br.readLine();
                int s = line.charAt(0) - '0';
                if (numbs[s]==null)numbs[s] = new node();
                node curr = numbs[s];
                for (int idx = 1; idx < line.length(); idx++) {
                    int c = line.charAt(idx) - '0';
                    if (curr.is_leaf) {consistency = false; break;}
                    if (curr.child[c]==null)curr.child[c] = new node();
                    curr = curr.child[c];
                }
                curr.is_leaf = true;
                for (int idx = 0; idx < 10; idx++) {
                    if (curr.child[idx] != null) {
                        consistency = false;
                        break;
                    }
                }
            }
            sb.append(consistency?"YES" : "NO").append('\n');
        }
        System.out.println(sb);
    }
    static int n;
    static node[] numbs;
    static String line;
}

class node {
    boolean is_leaf;
    node[] child = new node[10];
}