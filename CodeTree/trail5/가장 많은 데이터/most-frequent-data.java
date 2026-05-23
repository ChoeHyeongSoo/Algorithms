import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        Map<String, Integer> match = new HashMap<>();
        
        int max = 0;
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            int curr = match.getOrDefault(line, 0) + 1;
            match.put(line, curr);
            if (curr > max) max = curr;
        }
        
        System.out.println(max);
    }
}