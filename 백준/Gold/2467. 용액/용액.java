import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        samples = new long[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) samples[i] = Long.parseLong(st.nextToken());

        // 1. -부터 하나 고른 뒤 최소값에 가까운 값을 찾도록 이분탐색
        min = Long.MAX_VALUE; ans = new long[2];
        for (int i = 0; i < n-1; i++) {
            int left = i+1, right = n-1;
            long curr = samples[i];
            while (left <= right){          // while_loop : 시간 비교
                int mid = (left+right)/2;
                long target = samples[mid];

                if (Math.abs(curr+target) < Math.abs(min)){
                    min = curr+target;
                    ans[0] = curr; ans[1] = target;
                }

                if (curr+target == 0) break;
                else if (curr+target < 0) left = mid+1;
                else right = mid-1;
            }
        }
        System.out.println(ans[0] + " " + ans[1]);
    }
    static int n;
    static long min;
    static long[] samples, ans;
    
    public static void binary_search(int s, int e, long curr) { // recursive
        
        if (s>e) return;

        int mid = (s+e)/2;
        if (Math.abs(curr+samples[mid]) < Math.abs(min)){
            min = curr+samples[mid];
            ans[0] = curr; ans[1] = samples[mid];
        }

        if (curr+samples[mid]==0) return;
        else if (curr+samples[mid]<0) binary_search(mid+1, e, curr);
        else binary_search(s, mid-1, curr);
    }
}