import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        samples = new long[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) samples[i] = Long.parseLong(st.nextToken());

        // 1. 투 포인터 이분 탐색을 위한 정렬
        Arrays.sort(samples);

        // 2. 첫 원소부터 뒤의 부분 배열 내부 이분 탐색 진행
        min = Long.MAX_VALUE; ans = new long[3];
        for_loop:
        for (int i = 0; i < n-2; i++) {
            int l = i+1, r = n-1;
            long curr = samples[i];
            while (l < r){
                long left = samples[l], right = samples[r];
                long sum = curr+left+right;

                if (Math.abs(sum) < Math.abs(min)){
                    min = sum;
                    ans[0] = curr; ans[1] = left; ans[2] = right;
                }

                if (sum == 0) break for_loop;   // 0이면 정답 중 하나, 탈출
                else if (sum < 0) l++;
                else r--;
            }
        }
        System.out.println(ans[0] + " " + ans[1] + " " + ans[2]);
    }
    static int n;
    static long min;
    static long[] samples, ans;
}