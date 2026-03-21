import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        Set<Long> set = new HashSet<>();
        for (int i = 0; i < n; i++) set.add(Long.parseLong(st.nextToken()));
        int m = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder(); st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) sb.append(set.contains(Long.parseLong(st.nextToken()))? 1 : 0).append('\n');
        System.out.println(sb);
    }
    static int n;
}