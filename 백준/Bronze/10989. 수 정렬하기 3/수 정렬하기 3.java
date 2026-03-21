import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int[] arr = new int[10001];
        for (int i = 0; i < n; i++) arr[Integer.parseInt(br.readLine())]++;
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= 10000; i++) {
            if (arr[i]==0) continue;
            while (arr[i]-- > 0) sb.append(i).append('\n');
        }
        System.out.println(sb);
    }
    static int n;
}