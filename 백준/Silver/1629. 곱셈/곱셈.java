import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long a = Long.parseLong(st.nextToken());
        long b = Long.parseLong(st.nextToken());
        long c = Long.parseLong(st.nextToken());
        System.out.println(mul(a, b, c));
    }

    static long mul(long a, long b, long c) {
        if (b==1) return a % c;

        long curr = mul(a, b/2, c);

        if (b%2==0) return (curr * curr) % c;
        else return ((curr * curr % c) * a) % c;
    }
}