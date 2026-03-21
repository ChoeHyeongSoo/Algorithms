import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int ans = 0;
        boolean can = false;
        while (n>=0) {
            if (n%5==0) {
                ans+=(n/5);
                can = true; break;
            }
            n-=3;
            ans++;
        }
        System.out.println(can?ans:-1);
    }
    static int n;
}