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

        TreeSet<Integer> treeSet = new TreeSet<>();
        
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int val = Integer.parseInt(st.nextToken());
            treeSet.add(val);
        }

        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < m; i++) {
            int key = Integer.parseInt(br.readLine());
            ans.append(treeSet.ceiling(key) == null ? -1 : treeSet.ceiling(key)).append('\n');
        }

        System.out.println(ans);
    }
    static int n, m;
}