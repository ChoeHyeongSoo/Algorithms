import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        String line = br.readLine();
        long pow = 1;
        long mod = 1234567891;
        long hash = 0;
        for (int i = 0; i < n; i++) {
            int curr = line.charAt(i)-'a'+1;
            hash = (hash + (curr*pow))%mod;
            pow = (pow*31) % mod;
        }
        System.out.println(hash);
    }
    static int n;
}