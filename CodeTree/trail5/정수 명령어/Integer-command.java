import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder ans = new StringBuilder();
        TreeSet<Integer> treeSet = new TreeSet<>();

        int T = Integer.parseInt(st.nextToken());
        for (int tc = 0; tc < T; tc++) {

            int k = Integer.parseInt(br.readLine());
            for (int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine());
                char c = st.nextToken().charAt(0);
                int val = Integer.parseInt(st.nextToken());
                if (c == 'I')
                    treeSet.add(val);
                else if (!treeSet.isEmpty()) {
                    if (val > 0) treeSet.remove(treeSet.last());
                    else treeSet.remove(treeSet.first());
                }
            }
            
            ans.append(treeSet.isEmpty() ? "EMPTY" : treeSet.last() + " " + treeSet.first()).append('\n');
            treeSet.clear();
        }

        System.out.println(ans);
    }
}