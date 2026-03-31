import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        String line = br.readLine();
        int hash = 0;
        for (int i = 0; i < n; i++) {
            int curr = line.charAt(i)-'a'+1;
            hash += curr*(int)Math.pow(31, i)%1234567891;
        }
        System.out.println(hash);
    }
    static int n;
}