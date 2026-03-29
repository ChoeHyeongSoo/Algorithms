import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        curr = new int[m];
        combination(0, 1);
        System.out.println(sb);
    }
    static int n, m;
    static int[] curr;
    static StringBuilder sb = new StringBuilder();
    public static void combination(int depth, int idx) {

        if (depth==m) {
            for (int i : curr) sb.append(i).append(" ");
            sb.append('\n');
            return;
        }

        for (int i = idx; i <= n; i++) {
            curr[depth] = i;
            combination(depth+1, i+1);
        }
    }
}