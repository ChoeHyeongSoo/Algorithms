import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());
        
        TreeMap<String, Integer> treeMap = new TreeMap<>();
    
        for  (int i = 1; i <= n; i++) {
            String key = br.readLine();
            treeMap.put(key, treeMap.getOrDefault(key, 0) + 1);
        }

        StringBuilder ans = new StringBuilder();
        for (String k : treeMap.keySet())
            ans.append(k).append(" ").append(treeMap.get(k)).append('\n');
            
        System.out.println(ans);
    }
}