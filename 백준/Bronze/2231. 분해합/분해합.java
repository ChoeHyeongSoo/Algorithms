import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = 0;
        for (int i = n-1; i > 0; i--) {
            int curr = i, sum = i;
            while (curr > 0) {
                sum+=(curr%10);
                curr/=10;
            }
            if (sum==n) m = i;
        }
        System.out.println(m);
    }
    static int n;
}