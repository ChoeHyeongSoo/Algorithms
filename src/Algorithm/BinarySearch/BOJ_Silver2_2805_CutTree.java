package Algorithm.BinarySearch;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class BOJ_Silver2_2805_CutTree {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());   // (1 ≤ N ≤ 1,000,000, 1 ≤ M ≤ 2,000,000,000)
        int[] trees = new int[n];
        st = new StringTokenizer(br.readLine());
        int min= 0, max = 0;
        for (int i = 0; i < n; i++) {
            trees[i] = Integer.parseInt(st.nextToken());    // M의 사이즈가 2x10^9 N도 10^6 - logN 만 허용 - 트리기반!
            max = Math.max(trees[i], max);
        }
        int ans = 0;
        while (min <= max) {        // 타겟 높이 기준으로 이분 탐색
            int mid = (min+max)/2;
            long sum = 0;

            for (int t : trees)
                if (t > mid) sum+=(t-mid);   // 트리 전체에 대해 합 찾기 - 카운트로 누적합 계산 가능할까?

            if (sum>=m) {
                ans = mid; min = mid+1; // m보다 합이 크면 값을 높인다 - left를 mid+1로, 우측 절반 탐색
            } else max = mid-1;

        }
        System.out.println(ans);
    }
    static int n;
}