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
        for (int i = 1; i <= m; i++)
            treeSet.add(i);

        st = new StringTokenizer(br.readLine());
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < n; i++) {
            int key = Integer.parseInt(st.nextToken());
            treeSet.remove(key);
            ans.append(treeSet.last()).append('\n');
        }

        System.out.println(ans);
    }
    static int n, m;
}