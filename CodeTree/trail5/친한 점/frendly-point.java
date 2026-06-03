import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        TreeSet<int[]> treeSet = new TreeSet<>((a, b)
                -> a[0]==b[0] ? a[1] - b[1] : a[0] - b[0]);

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()), y = Integer.parseInt(st.nextToken());
            treeSet.add(new int[]{x, y});
        }

        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()), y = Integer.parseInt(st.nextToken());
            int[] val = treeSet.ceiling(new int[]{x, y});
            ans.append(val == null ? "-1 -1" : val[0] + " " + val[1]).append('\n');
        }

        System.out.println(ans);
    }
    static int n, m;
}