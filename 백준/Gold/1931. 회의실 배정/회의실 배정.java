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
        int n = Integer.parseInt(st.nextToken());
        long[][] info = new long[n][2];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            info[i][0] = Integer.parseInt(st.nextToken());
            info[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(info, (a, b)-> a[1]==b[1] ? Math.toIntExact(a[0]-b[0]) : Math.toIntExact(a[1]-b[1]));

        int cnt = 0;
        long last = -1;
        for (int i = 0; i < n; i++) {
            if (info[i][0] < last) continue;
            last = info[i][1]; cnt++;
        }

        System.out.println(cnt);
    }
}