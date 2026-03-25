import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken()), k = Integer.parseInt(st.nextToken());
        arr = new long[n];  // k번째처럼 인덱스 위주의 문제는 n+1로 설정하고, 트리 생성할 때도 1~n으로 범위 잡아주는 게 편리
        for (int i = 0; i < n; i++) arr[i] = Long.parseLong(br.readLine());
        SegmentTree prefix_sum = new SegmentTree(arr);
        for (int i = 0; i < m+k; i++) {
            st = new StringTokenizer(br.readLine());
            int option = Integer.parseInt(st.nextToken()), target = Integer.parseInt(st.nextToken());
            switch (option) {
                case 1:
                    long change = Long.parseLong(st.nextToken()); // 주의! 변한값이 아니라 변화량을 반영해야 한다.
                    long diff = change - arr[target-1]; // 변화량 계산
                    arr[target-1] = change;     // 원본 배열에도 변한값 할당
                    prefix_sum.update(1, 0, n-1, target-1, diff); // x가 아니라 dx! - 트리의 값 변경
                    break;
                case 2:
                    int last = Integer.parseInt(st.nextToken());
                    sb.append(prefix_sum.sum(1, 0, n-1, target-1, last-1)).append('\n');
                    break;
            }
        }
        System.out.println(sb);
    }
    static int n;
    static long[] arr;
}

class SegmentTree_BottomUp{

}

class SegmentTree {
    long[] tree;
    int n;

    public SegmentTree(long[] arr) {
        n = arr.length;
        tree = new long[n*4];
        build(arr, 1, 0, n-1);
    }

    public long build(long[] arr, int node, int start, int end) {

        if (start==end) return tree[node]=arr[start];

        int mid = (start+end)/2;

        return tree[node] = build(arr, node * 2, start, mid) + build(arr, node * 2 + 1, mid+1, end);
    }

    public long sum(int node, int start, int end, int left, int right) {

        if (left > end || right < start) return 0;

        if (left <= start && end <= right) return tree[node];

        int mid = (start+end)/2;

        return sum(node*2, start, mid, left, right)
                + sum(node*2+1, mid+1, end, left, right);
    }

    public void update(int node, int start, int end, int idx, long diff) {

        if (start > idx || end < idx) return;

        tree[node]+=diff;

        if (start==end) return;

        int mid = (start+end)/2;
        update(node*2, start, mid, idx, diff);
        update(node*2+1, mid+1, end, idx, diff);
    }
}