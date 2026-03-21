import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        if (n==1) System.out.println(1);
        else {
            int layer = 1, range = 2;
            while (range<=n)
                range+=(6*layer++);
            System.out.println(layer);
        }
    }
    static int n;
}