import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        v = new boolean[n+1];
        curr = new int[m];
        combination(0);
        System.out.println(sb);
    }
    static int n, m;
    static boolean[] v;
    static int[] curr;
    static StringBuilder sb = new StringBuilder();
    public static void combination(int depth) {

        if (depth==m) {
            for (int i : curr) sb.append(i).append(" ");
            sb.append('\n');
            return;
        }

        for (int i = 1; i <= n; i++) {
            if (depth>0 && curr[depth-1] > i) continue;
            curr[depth] = i;
            combination(depth+1);
        }
    }
}