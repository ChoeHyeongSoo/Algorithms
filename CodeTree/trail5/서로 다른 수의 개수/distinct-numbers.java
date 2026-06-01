import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());

        HashSet<Integer> set = new HashSet<>();
        
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++)
            set.add(Integer.parseInt(st.nextToken()));
        
        System.out.println(set.size());
    }
    static int n;
}