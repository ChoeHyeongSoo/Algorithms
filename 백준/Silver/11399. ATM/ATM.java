import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int[] line = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) line[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(line);
        int total = 0;
        for (int i = 1; i < n; i++) line[i]+=line[i-1];
        for (int i = 0; i < n; i++) total+=line[i];
        System.out.println(total);
    }
    static int n;
}