package DataStructure.SegmentTree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class BOJ_Gold1_11505_RangeProduct{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken()), k = Integer.parseInt(st.nextToken());
        long[] arr = new long[N];
        for (int i = 0; i < N; i++) arr[i] = Long.parseLong(br.readLine());
        SegmentTree_Product segmentree = new SegmentTree_Product(arr);
        for (int i = 0; i < m+k; i++) {
            st = new StringTokenizer(br.readLine());
            int option = Integer.parseInt(st.nextToken()), a = Integer.parseInt(st.nextToken());
            switch (option) {
                case 1:
                    long changes = Long.parseLong(st.nextToken());
                    segmentree.update(1, 0, N -1, a-1, changes);
                    arr[a-1] = changes;
                    break;
                case 2:
                    int b = Integer.parseInt(st.nextToken());
                    sb.append(segmentree.product(1, 0, N -1, a-1, b-1)).append('\n');
                    break;
            }
        }
        System.out.println(sb); // 1,000,000,007로 나눈 나머지를 출력한다 .. 주의
    }
    static int N;
}

class SegmentTree_Product {
    int n;
    long[] tree;
    long mod = 1000000007;

    public SegmentTree_Product(long[] arr) {
        n = arr.length;
        tree = new long[n*4];
        build(1, 0, n-1, arr);
    }

    public long build(int node,int start, int end, long[] arr) {

        if (start==end) return tree[node] = arr[start];

        int mid = (start+end)/2;

        return tree[node] = (build(node<<1, start, mid, arr) * build(node<<1|1, mid+1, end, arr))%mod;
    }

    public long product(int node, int start, int end, int left, int right) {

        if (start > right || end < left) return 1;

        if (left <= start && end <= right) return tree[node];

        int mid = (start+end)/2;

        return (product(node<<1, start, mid, left, right) * product(node<<1|1, mid+1, end, left, right))%mod;
    }

    public long update(int node, int start, int end, int idx, long changes) {

        if (start > idx || end < idx) return tree[node];

        if (start==end) return tree[node] = changes;

        int mid = (start+end)/2;
        return tree[node] = (update(node<<1, start, mid, idx, changes) * update(node<<1|1, mid+1, end, idx, changes))%mod;
    }
}