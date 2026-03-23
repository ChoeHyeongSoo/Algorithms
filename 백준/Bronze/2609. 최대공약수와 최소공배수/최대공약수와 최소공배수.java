import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int x = (n>m) ? hoje(n, m) : hoje(m, n);
        StringBuilder sb = new StringBuilder();
        sb.append(x).append('\n').append(n*m/x);
        System.out.println(sb);
    }

    public static int hoje(int a, int b) {
        if (a%b==0) return b;
        return hoje(b, a%b);
    }
}