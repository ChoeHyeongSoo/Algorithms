import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());
        
        HashSet<Integer> set = new HashSet<>();
    
        StringBuilder ans = new StringBuilder();
        for  (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            char c = st.nextToken().charAt(0);
            int val = Integer.parseInt(st.nextToken());
            switch (c) {
                case 'a':
                    set.add(val);
                    break;
                case 'r':
                    set.remove(val);
                    break;
                case 'f':
                    ans.append(set.contains(val)).append('\n');
                    break;
            }
        }
            
        System.out.println(ans);
    }
}