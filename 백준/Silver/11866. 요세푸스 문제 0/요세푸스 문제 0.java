import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder(); sb.append('<');
        n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        List<Integer> circle = new ArrayList<>();
        for (int i = 1; i <= n; i++) circle.add(i);

        int idx = k-1;
        while (circle.size() > 1){
            sb.append(circle.get(idx)).append(", ");
            circle.remove(idx);
            idx = (idx+k-1)%circle.size();
        }
        sb.append(circle.get(0)).append('>');
        System.out.println(sb);
    }
    static int n;
}