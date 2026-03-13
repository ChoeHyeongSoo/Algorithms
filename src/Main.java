//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.util.Arrays;
//import java.util.StringTokenizer;
//
//public class Main{
//
//    private static String br;
//
//    static void main(String[] args) throws Exception {
//
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        int n = Integer.parseInt(br.readLine());
//        Don[] days = new Don[n+1];
//        int[] profit = new int[n+1];
//        for (int i = 1; i <= n; i++) {
//            StringTokenizer st = new StringTokenizer(br.readLine());
//            int t = Integer.parseInt(st.nextToken());
//            int p = Integer.parseInt(st.nextToken());
//            days[i] = new Don(t, p);
//        }
//
//        for (int i = 1; i <= n; i++) {
//            if (i+days[i].t > n) {continue;};
//            profit[i+days[i].t] = Math.max(profit[i+days[i].t], profit[i] + days[i].p);
//        }
//
//        System.out.println(Arrays.stream(profit).max());
//    }
//
//}
//
//class Don {
//    int t, p;
//
//    public Don(int t, int p) {
//        this.t = t;
//        this.p = p;
//    }
//}