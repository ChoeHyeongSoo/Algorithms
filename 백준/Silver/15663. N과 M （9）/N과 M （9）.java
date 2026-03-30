import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        v = new boolean[n];
        arr = new int[n];
        curr = new int[m];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(st.nextToken());
        Arrays.sort(arr);
        combination(0);
        System.out.println(sb);
    }
    static int n, m;
    static boolean[] v;
    static int[] curr;
    static int[] arr;
    static StringBuilder sb = new StringBuilder();
    public static void combination(int depth) {

        if (depth==m) {
            for (int i : curr) sb.append(i).append(" ");
            sb.append('\n');
            return;
        }

        int prev = 0;
        for (int i = 0; i < n; i++) {
            if (v[i]) continue;
            if (prev == arr[i]) continue;
            v[i] = true;
            prev = arr[i];
            curr[depth] = arr[i];
            combination(depth+1);
            v[i] = false;
        }
    }
}