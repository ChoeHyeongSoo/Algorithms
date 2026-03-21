import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        heap = new long[n];
        idx = 0; sb = new StringBuilder();
        for (int i = 0; i < n; i++)
            operation(Integer.parseInt(br.readLine()));
        System.out.println(sb);
    }
    static int n, idx;
    static long[] heap;
    static StringBuilder sb;

    public static void operation(long x) {
        if (x==0) {
            if (idx==0) sb.append(0).append('\n');
            else sb.append(heap[0]).append('\n');    // 부모 idx -1 / 2 | 자식 +1 * 2, '' -1
            out();
        } else in(x);
    }
    public static void in(long x) {
        heap[idx] = x;
        int curr_idx = idx++;
        while(heap[curr_idx] > heap[(curr_idx-1)/2] && curr_idx > 0) {
            swap(curr_idx, (curr_idx-1)/2);
            curr_idx = (curr_idx-1)/2;
        }
    }

    public static void out() {
        if (idx==0) return;
        int curr_idx = 0;
        heap[curr_idx] = heap[--idx];
        while(curr_idx*2+1 < idx) {
            int target_idx = curr_idx*2+1;
            if (target_idx+1 < idx && heap[target_idx] < heap[target_idx+1])
                target_idx++;

            if (heap[curr_idx] < heap[target_idx]) swap(curr_idx, target_idx);
            curr_idx = target_idx;
        }
    }

    public static void swap(int a, int b) {
        long tmp = heap[a];
        heap[a] = heap[b];
        heap[b] = tmp;
    }
}