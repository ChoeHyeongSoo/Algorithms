import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder ans = new StringBuilder();
        n = Integer.parseInt(st.nextToken());

        TreeSet<Integer> treeSet = new TreeSet<>();
        treeSet.add(0);
        
        st = new StringTokenizer(br.readLine());
        int min = INF;
        for (int i = 0; i < n; i++) {
            int key = Integer.parseInt(st.nextToken());
            treeSet.add(key);
            int lower = treeSet.lower(key) == null ? INF : key - treeSet.lower(key);
            int higher = treeSet.higher(key) == null ? INF : treeSet.higher(key) - key;
            min = Math.min(min, Math.min(lower, higher));
            ans.append(min).append('\n');
        }

        System.out.println(ans);
    }
    static int n;
    static final int INF = Integer.MAX_VALUE;
}