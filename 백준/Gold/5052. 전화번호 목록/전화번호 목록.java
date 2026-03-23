import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;
public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int tc = 0; tc < T; tc++) {
            n = Integer.parseInt(br.readLine());
            String[] numbs = new String[n];
            for (int i = 0; i < n; i++) numbs[i] = br.readLine();
            Arrays.sort(numbs);
            boolean consistency = true;
            for (int i = 1; i < n; i++) {
                if (numbs[i].charAt(0)!=numbs[i-1].charAt(0)) continue;
                if (numbs[i].length() > numbs[i-1].length()) {
                    if (numbs[i].startsWith(numbs[i - 1])) {
                        consistency = false;
                        break;
                    }
                } else {
                    if (numbs[i-1].startsWith(numbs[i])) {
                        consistency = false;
                        break;
                    }
                }
            }
            sb.append(consistency?"YES" : "NO").append('\n');
        }
        System.out.println(sb);
    }
    static int n;
}
