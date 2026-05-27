import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder ans = new StringBuilder();
    
        HashSet<Integer> set = new HashSet<>();
        
        int n = Integer.parseInt(br.readLine());
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++)
            set.add(Integer.parseInt(st.nextToken()));
        
        int m = Integer.parseInt(br.readLine());
        
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= m; i++)
            ans.append(set.contains(Integer.parseInt(st.nextToken()))?1:0).append(" ");
            
        System.out.println(ans);
    }
}