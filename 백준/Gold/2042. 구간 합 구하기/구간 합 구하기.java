import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
public class Main{
    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        arr = new long[n];  // k번째처럼 인덱스 위주의 문제는 n+1로 설정하고, 트리 생성할 때도 1~n으로 범위 잡아주는 게 편리
        for (int i = 0; i < n; i++) arr[i] = Long.parseLong(br.readLine());
//        TopDown = new SegmentTree(arr);
//        BottomUp = new SegmentTree_BottomUp(arr);
        BIT = new FenwickTree(arr);

//        top_down();
//        bottom_up();
        Fenwick();
        System.out.println(sb);
    }

    static int n, m, k;
    static long[] arr;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static SegmentTree TopDown;
    static SegmentTree_BottomUp BottomUp;
    static FenwickTree BIT;

    public static void top_down() throws Exception{
        for (int i = 0; i < m+k; i++) {
            st = new StringTokenizer(br.readLine());
            int option = Integer.parseInt(st.nextToken()), target = Integer.parseInt(st.nextToken());
            switch (option) {
                case 1:
                    long change = Long.parseLong(st.nextToken()); // 주의! 변한값이 아니라 변화량을 반영해야 한다.
                    long diff = change - arr[target-1]; // 변화량 계산 (Top-Down)
                    arr[target-1] = change;     // *원본 배열에도 변한값 업데이트
                    TopDown.update(1, 0, n-1, target-1, diff); // x가 아니라 dx! - 트리의 값 변경 (Top-Down)
                    break;
                case 2:
                    int last = Integer.parseInt(st.nextToken());    // idx가 target-1 : arr이 [0]부터 시작이라 1->0을 의미하기 때문
                    sb.append(TopDown.sum(1, 0, n-1, target-1, last-1)).append('\n');
                    break;                                          // BIT에선 원본과 무관하게 1부터 시작하므로 상관 x
            }
        }
    }

    public static void bottom_up() throws Exception{
        for (int i = 0; i < m+k; i++) {
            st = new StringTokenizer(br.readLine());
            int option = Integer.parseInt(st.nextToken()), target = Integer.parseInt(st.nextToken());
            switch (option) {
                case 1:
                    long change = Long.parseLong(st.nextToken());
                    arr[target-1] = change;     // *원본 배열에도 변한값 업데이트
                    BottomUp.update(target-1, change);
                    break;
                case 2:
                    int last = Integer.parseInt(st.nextToken());
                    sb.append(BottomUp.sum(target-1, last-1)).append('\n');
                    break;
            }
        }
    }

    public static void Fenwick() throws Exception{
        for (int i = 0; i < m+k; i++) {
            st = new StringTokenizer(br.readLine());
            int option = Integer.parseInt(st.nextToken()), target = Integer.parseInt(st.nextToken());
            switch (option) {
                case 1:
                    long change = Long.parseLong(st.nextToken()); // *주의 변화량 반영
                    long diff = change - arr[target-1]; // 변화량 계산
                    arr[target-1] = change;     // *원본 배열에도 변한값 업데이트
                    BIT.update(target, diff);
                    break;
                case 2:
                    int last = Integer.parseInt(st.nextToken());
                    sb.append(BIT.range_sum(target, last)).append('\n');
                    break;
            }
        }
    }

}

class SegmentTree_BottomUp{

    long[] tree;
    int n;

    public SegmentTree_BottomUp(long[] arr){
        n = arr.length;
        tree = new long[n*2+1];
        build(arr);
    }

    public void build(long[] arr) {
        for (int i = 0; i < n; i++) tree[i+n] = arr[i]; // 리프 노드 먼저 할당

        for (int i = n-1; i >= 1; i--) tree[i] = tree[i << 1] + tree[i << 1 | 1]; // 부모 노드 채우기
    }

    public long sum(int left, int right) {
        long result = 0;
        left += n; right += n;

        while (left<=right) {
            if (left % 2 == 1) result += tree[left++];      // left 홀수 : 현재 노드의 부모 노드는 탐색 범위 밖의 값을 포함한 누적합
            if (right % 2 == 0) result += tree[right--];    // right 짝수 : ''

            left >>= 1;
            right >>= 1;
        } return result;
    }

    public void update(int idx, long value) {
        idx+=n;
        tree[idx] = value;

        while(idx>1) {  // 루트까지 업데이트값 전파 (Bottom-Up)
            idx >>= 1;
            tree[idx] = tree[idx << 1] + tree[idx << 1 | 1];
        }
    }

}

class SegmentTree {
    long[] tree;
    int n;

    public SegmentTree(long[] arr) {
        n = arr.length;
        tree = new long[n*4];
        build(arr, 1, 0, n-1);  // 분할 정복으로 트리 형성
    }

    public long build(long[] arr, int node, int start, int end) {

        if (start==end) return tree[node]=arr[start];   // 리프 노드 조건

        int mid = (start+end)/2;    // 분할 기준

        return tree[node] = build(arr, node * 2, start, mid)    // 좌/우 자식 노드 분할 정복 : 리턴하며 병합
                + build(arr, node * 2 + 1, mid+1, end);
    }

    public long sum(int node, int start, int end, int left, int right) {

        if (left > end || right < start) return 0;  // 구간 밖: 연산에 영향 없는 값 리턴

        if (left <= start && end <= right) return tree[node];  // 완전 포함: 노드 값 전체 리턴 (분할 x)

        int mid = (start+end)/2;

        return sum(node*2, start, mid, left, right) // 일부 포함: 분할 정복
                + sum(node*2+1, mid+1, end, left, right);
    }

    public void update(int node, int start, int end, int idx, long diff) {

        if (start > idx || end < idx) return;   // 구간 밖: 종료

        tree[node]+=diff;   // 구간에 포함되면 현재 노드부터 업데이트

        if (start==end) return; // 리프면 분할 x

        int mid = (start+end)/2;
        update(node*2, start, mid, idx, diff);  // 서브 트리 전체 업데이트
        update(node*2+1, mid+1, end, idx, diff);
    }
}

class FenwickTree {

    long[] tree;
    int n;

    public FenwickTree(long[] arr) {
        n = arr.length;
        tree = new long[n+1];
        build(n, arr);
    }

    public void build(int n, long[] arr) {
        for (int i = 1; i <= n; i++) {
            update(i, arr[i-1]);
        }
    }

    public void update(int idx, long diff) {  // *diff로 사용할 땐, 원본 배열도 메인 함수 내부에서 업데이트 주의
        while (idx <= n) {
            tree[idx] += diff;
            idx += (idx & -idx);    // LSB를 더해서 길이 +1의 현재 노드를 포함하는 구간도 업데이트
        }   // 3(110) : LSB(1), ([3] 구간합) -> LSB(4) : 001(4), [1, 2, 3, 4] 구간합
    }

    public long query(int idx) {
        long sum = 0;
        while (idx >= 1) {
            sum+=tree[idx];
            idx -= (idx & -idx); // LSB 빼서 길이 -1의 중복 없는 구간 접근 - 구간합에 반영
        }   // 3(110) : LSB(1), ([3] 구간합) -> LSB(2) : 010(2), [1, 2] 구간합
        return sum;
    }

    public long range_sum(int l, int r) {
        return query(r) - query(l-1);
    }
}