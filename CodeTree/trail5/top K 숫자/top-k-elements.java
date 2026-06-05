import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        TreeSet<Integer> treeSet = new TreeSet<>((a, b) -> b - a);

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int key = Integer.parseInt(st.nextToken());
            treeSet.add(key);
        }

        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < k; i++) {
            ans.append(treeSet.first()).append(" ");
            treeSet.remove(treeSet.first());
        }

        System.out.println(ans);
    }
    static int n, k;
}