import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        HashMap<Integer, Integer> map = new HashMap<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int key = Integer.parseInt(st.nextToken());
            map.put(key, map.getOrDefault(key, 0) + 1);
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> {
                int val_a = map.get(a);
                int val_b = map.get(b);
                return val_a == val_b ? b - a : val_b - val_a;
        });

        pq.addAll(map.keySet());

        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < k; i++)
            ans.append(pq.poll()).append(" ");

        System.out.println(ans);
    }
    static int n;
}