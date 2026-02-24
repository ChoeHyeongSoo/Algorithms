import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] card = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            card[i] = Integer.parseInt(st.nextToken());
        }

        int[] ans = new int[2];
        comb(ans, card, 0, 0, m);
        System.out.println(ans[1]);

    }

    public static void comb(int[] sum_max, int[] card, int cnt, int start, int m) {
        if (sum_max[0] > m) {
            return;
        }

        if (cnt == 3) {
            if (sum_max[0] > sum_max[1])
                sum_max[1] = sum_max[0];
            return;
        }

        for (int i = start; i < card.length; i++) {
            sum_max[0] += card[i];
            comb(sum_max, card, cnt + 1, i + 1, m);
            sum_max[0] -= card[i];
        }
    }

}