import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;
public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int[] arr = new int[n+1];

        int total = 0;
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            total += arr[i];
        }

        if (total < s) System.out.println(0);
        else if (total==s) System.out.println(n);
        else {
            // 연속된 수 시작, 끝 인덱스 투 포인터
            int l = 0, r = 1, sum = arr[1];
            int min = Integer.MAX_VALUE;
            while (true) {
                if (sum >= s) {
                    min = Math.min(min, r-l);
                    sum -= arr[++l];
                }
                else if (r==n) break;
                else sum += arr[++r];
            }
            System.out.println(min);
        }
    }
    static int n;
}