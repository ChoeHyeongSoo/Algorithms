import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int[] table = new int[n+1];
        Arrays.fill(table, Integer.MAX_VALUE);
        table[0] = table[1] = 0;
        for (int i = 2; i <= n; i++) {
            if (i%2==0) table[i] = Math.min(table[i], table[i/2] + 1);
            if (i%3==0) table[i] = Math.min(table[i], table[i/3] + 1);
            table[i] = Math.min(table[i], table[i-1] + 1);
        }
        System.out.println(table[n]);
    }
    static int n;
}