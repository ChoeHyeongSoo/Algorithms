import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int up = 1, down = 1;
        for (int i = n; i > n-k; i--) {
            up*=i; down*=(n+1-i);
        }
        System.out.println(up/down);
    }
    static int n;
}