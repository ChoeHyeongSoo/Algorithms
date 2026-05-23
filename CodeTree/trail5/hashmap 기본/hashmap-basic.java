import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());

        Map<Integer, Integer> hashmap = new HashMap<>();
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            
            String command = st.nextToken();
            int key = Integer.parseInt(st.nextToken());
            
            if (command.charAt(0)=='a') {
                int value = Integer.parseInt(st.nextToken());
                hashmap.put(key, value);
            } else if (command.charAt(0)=='r') {
                hashmap.remove(key);
            } else {
                int val = hashmap.getOrDefault(key, 0);
                ans.append(val>0 ? val : "None").append('\n');
            }
        }
        System.out.println(ans);
    }
    static int n;
}