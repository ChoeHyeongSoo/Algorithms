import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(st.nextToken());
        int[] sorted = Arrays.copyOf(arr, n);
        Arrays.sort(sorted);

        Map<Integer, Integer> map = new HashMap<>();
        int rank = 0;
        for (int curr : sorted)
            if (!map.containsKey(curr)) map.put(curr, rank++);

        StringBuilder sb = new StringBuilder();
        for (int curr : arr) sb.append(map.get(curr)).append(" ");
        System.out.println(sb);
    }
    static int n;
}