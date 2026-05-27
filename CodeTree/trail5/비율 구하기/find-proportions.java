import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());
        
        TreeMap<String, Double> treeMap = new TreeMap<>();
        int total = 0;
        for  (int i = 0; i < n; i++) {
            String key = br.readLine();
            treeMap.put(key, treeMap.getOrDefault(key, 0.0) + 1);
            total++;
        }

        StringBuilder ans = new StringBuilder();
        for (String k : treeMap.keySet())
            ans.append(k).append(" ")
                .append(String.format("%.4f", treeMap.get(k)/total*100))
                .append('\n');
            
        System.out.println(ans);
    }
}