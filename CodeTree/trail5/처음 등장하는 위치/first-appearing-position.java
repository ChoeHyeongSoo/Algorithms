import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());
        
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
    
        StringTokenizer st = new StringTokenizer(br.readLine());
        for  (int i = 1; i <= n; i++) {
            int k = Integer.parseInt(st.nextToken());
            if (treeMap.getOrDefault(k, 0) == 0)
                treeMap.put(k, i);
        }

        StringBuilder ans = new StringBuilder();
        for (int k : treeMap.keySet())
            ans.append(k).append(" ").append(treeMap.get(k)).append('\n');
            
        System.out.println(ans);
    }
}