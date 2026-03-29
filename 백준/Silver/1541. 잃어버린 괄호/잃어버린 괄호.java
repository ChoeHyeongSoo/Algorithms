import java.io.BufferedReader;
import java.io.InputStreamReader;
public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] sub = br.readLine().split("-");    // split 기준으로 분할
        int ans = 0;
        for (int i = 0; i < sub.length; i++) {
            int each = 0;
            String[] nums = sub[i].split("\\+");    // 허상문자는 \\ 필요
            for (String n : nums) each+=Integer.parseInt(n);
            if (i==0) ans+=each;
            else ans-=each;
        }
        System.out.println(ans);
    }
    static int n;
}