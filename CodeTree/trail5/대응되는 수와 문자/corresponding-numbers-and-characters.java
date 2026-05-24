import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken());

        Map<String, Integer> string2idx = new HashMap<>();
        String [] idx2String = new String[n+1];

        for (int i = 1; i <= n; i++) {
            String line = br.readLine();
            string2idx.put(line, i);
            idx2String[i] = line;
        }

        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < m; i++) {
            String line = br.readLine();
            char pre = line.charAt(0);
            if (pre >= '0' && pre <= '9')
                ans.append(idx2String[Integer.parseInt(line)]).append('\n');
            else
                ans.append(string2idx.get(line)).append('\n');
        }

        System.out.println(ans);
    }
}