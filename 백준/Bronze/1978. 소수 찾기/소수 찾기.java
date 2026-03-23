import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        boolean[] l = new boolean[1001];
        l[0] = l[1] = true;
        for (int i = 2; i <= 1000; i++) {
            if (l[i]) continue;
            int k = 2;
            while (i*k <= 1000) l[i*k++] = true;
        }
        n = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int cnt = 0;
        for (int i = 0; i < n; i++) if(!l[Integer.parseInt(st.nextToken())]) cnt++;
        System.out.println(cnt);
    }
    static int n;
}