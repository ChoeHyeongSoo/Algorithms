import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int[] size = new int[6];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 6; i++) size[i] = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int t = Integer.parseInt(st.nextToken()), p = Integer.parseInt(st.nextToken()), cnt = 0;
        for (int i = 0; i < 6; i++) {
            if(size[i]%t==0) cnt+=size[i]/t;
            else cnt+=size[i]/t+1;
        }
        System.out.println(cnt + "\n" + n/p + " " + n%p);
    }
    static int n;
}