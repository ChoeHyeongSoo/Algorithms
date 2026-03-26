import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        n = Integer.parseInt(st.nextToken()); int m = Integer.parseInt(st.nextToken());
        long[] arr = new long[n];
        for (int i = 0; i < n; i++) arr[i] = Long.parseLong(br.readLine());
        SegmentTree min_tree = new SegmentTree(arr);
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken());
            sb.append(min_tree.min(a-1, b-1)).append('\n'); // Index임을 주의!
        }
        System.out.println(sb);
    }
    static int n;
}

class SegmentTree{
    int n;
    long[] tree;

    public SegmentTree(long[] arr) {
        n = arr.length;
        tree = new long[n*2];
        build(arr);
    }

    public void build(long[] arr) {

        for (int i = 0; i < n; i++) tree[i+n] = arr[i]; // 리프 노드 우선 할당

        for (int i = n-1; i > 0; i--) tree[i] = Math.min(tree[i<<1], tree[i<<1|1]); // 최소값 계산
    }

    public long min(int left, int right) {

        long result = Integer.MAX_VALUE;
        left+=n; right+=n;
        while (left<=right) {
            if (left % 2 == 1) result = Math.min(result, tree[left++]);     // 최소값 계산후, 올바른 부모 노드 찾아가도록
            if (right % 2 == 0) result =  Math.min(result, tree[right--]);

            left >>= 1;
            right >>= 1;

        } return result;
    }
}