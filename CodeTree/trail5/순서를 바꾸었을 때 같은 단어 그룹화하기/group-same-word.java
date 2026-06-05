import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());

        Map<String, Integer> map = new HashMap<>();

        int max = 0;
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            char[] arr = line.toCharArray();
            Arrays.sort(arr);
            String key = new String(arr);
            int tmp = map.getOrDefault(key, 0) + 1;
            map.put(key, tmp);
            max = Math.max(max, tmp);
        }

        System.out.println(max);
    }
    static int n;
}