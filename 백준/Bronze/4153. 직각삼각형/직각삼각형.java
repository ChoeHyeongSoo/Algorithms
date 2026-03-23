import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken()); if (c==0) break;
            if (Math.max(a, Math.max(b, c))==a) sb.append((a*a==b*b+c*c)?"right":"wrong");
            else if (Math.max(a, Math.max(b, c))==b) sb.append((b*b==a*a+c*c)?"right":"wrong");
            else sb.append((c*c==b*b+a*a)?"right":"wrong");
            sb.append('\n');
        }
        System.out.println(sb);
    }
    static int n;
}