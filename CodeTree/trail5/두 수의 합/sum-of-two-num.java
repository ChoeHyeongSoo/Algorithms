import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        long k = Long.parseLong(st.nextToken());

        long[] nums = new long[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) nums[i] = Integer.parseInt(st.nextToken());

        Arrays.sort(nums);

        long ans = 0;
        int l = 0, r = n-1;
        while (l < r) {

            long sum = nums[l] + nums[r];

            if (sum == k) {
                if (nums[l]==nums[r]) {     // 런타임 발생 해결
                    ans += (long)(r-l+1)*(r-l)/2; // l, r의 숫자가 같아지면 종료조건 발생
                    break;
                }

                long l_cnt = 1, r_cnt = 1;

                while (nums[l]==nums[l+1]) {
                    l++; l_cnt++;
                }
                while (nums[r]==nums[r-1]) {
                    r--; r_cnt++;
                }

                ans += (l_cnt * r_cnt);

                l++; r--;
            } else if (sum < k) l++;
            else r--;
        }

        System.out.println(ans);
    }
    static int n;
}