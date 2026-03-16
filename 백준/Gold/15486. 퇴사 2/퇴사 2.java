import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main{

    private static String br;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] t = new int[n+1];
        int[] p = new int[n+1];
        int[] profit = new int[n+2];
        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            t[i] = Integer.parseInt(st.nextToken());
            p[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = n; i >= 1; i--) {
            if (i+t[i] > n+1) {profit[i] = profit[i+1]; continue;} // 퇴사일 초과: 일 불가
            profit[i] = Math.max(profit[i+1], profit[i+t[i]]+p[i]); // 당일 일을 하는 것과, 이후 일을 하는 것중 최고액
        }

        System.out.println(profit[1]);
    }
}