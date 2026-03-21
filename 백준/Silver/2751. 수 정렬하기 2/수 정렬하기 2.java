import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        List<Integer> arr = new ArrayList<>();
        for (int i = 0; i < n; i++)  arr.add(Integer.parseInt(br.readLine()));
        Collections.sort(arr);
        StringBuilder sb = new StringBuilder();
        for (int k : arr) sb.append(k).append('\n');
        System.out.println(sb);
    }
    static int n;
}