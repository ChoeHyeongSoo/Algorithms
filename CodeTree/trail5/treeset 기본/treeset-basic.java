import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());

        TreeSet<Integer> treeSet = new TreeSet<>();
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            int value = st.hasMoreTokens() ? Integer.parseInt(st.nextToken()) : 0;

            switch (command.charAt(0)) {
                case 'a':
                    treeSet.add(value);
                    break;
                case 'f':
                    ans.append(treeSet.contains(value) ? "true" : "false").append('\n');
                    break;
                case 'l':
                    if (command.charAt(1) == 'a')
                        ans.append( treeSet.isEmpty() ? "None" : treeSet.last()).append('\n');
                    else
                        ans.append(treeSet.ceiling(value)==null ? "None" : treeSet.ceiling(value)).append('\n');
                    break;
                case 'u':
                    ans.append(treeSet.higher(value)==null ? "None" : treeSet.higher(value)).append('\n');
                    break;
                case 's':
                    ans.append( treeSet.isEmpty() ? "None" : treeSet.first()).append('\n');
                    break;
                case 'r':
                    treeSet.remove(value);
                    break;
            }
        }

        System.out.println(ans);
    }
    static int n;
}