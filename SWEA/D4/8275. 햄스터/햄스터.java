import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

class Solution {
    public static void main(String args[]) throws Exception {
        StringBuilder ans = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            ans.append("#").append(test_case).append(" ");
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            x = Integer.parseInt(st.nextToken());   // 각 우리 최대값
            m = Integer.parseInt(st.nextToken());   // 기록

            records = new Record[m];
            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                l = Integer.parseInt(st.nextToken());
                r = Integer.parseInt(st.nextToken());
                s = Integer.parseInt(st.nextToken());
                records[i] = new Record(l, r, s);
            }

            max = -1;
            group = new int[n+1];
            cases = new ArrayList<>();  // 정답 충족하는 배열 저장

            dfs(1, 0);

            if (!cases.isEmpty()) {
                Collections.sort(cases, new Comparator<int[]>() {   // 정렬보다 직접 비교가 편했으려나?
                    @Override
                    public int compare(int[] o1, int[] o2) {
                        for (int i = 1; i <= n; i++) {
                            if (o1[i] == o2[i]) continue;       // 같은 경우는 다음 자리 비교
                            return o1[i] - o2[i];
                        }
                        return -1;
                    }
                });
                int[] last = cases.get(0);
                for (int i = 1; i <= n; i++) {
                    ans.append(last[i]).append(" ");
                }
            } else {ans.append(-1);}    // cases.isEmpty ? 가능한 경우 x

            ans.append('\n');
        }
        System.out.print(ans);
    }

    static int n, m, x, l, r, s, max;
    static Record[] records;
    static int[] group;
//    static List<List<Integer>> cases;
    static List<int[]> cases;

    static void dfs(int idx, int sum) {

        for (Record cond : records) {
            if (idx <= cond.r) continue;    // 기록의 r 지점까지 idx가 왔으면 조건 판단
            int check = 0;
            for (int i = cond.l; i <= cond.r; i++) check+=group[i];
            if (check!=cond.s) return;
        }

        if (idx==n+1) { // 기저 조건
            if (max > sum) return;
            if (max < sum) {
                cases = new ArrayList<>();
                max = sum;
            }
            cases.add(Arrays.copyOf(group, group.length));
            return;
        }

        for (int i = x; i >= 0; i--) {  // 높은 수부터 진행하는 게 웬만하면 max 갱신이 일어나지 않을 것
            group[idx] = i;
            dfs(idx+1, sum+i);
        }
    }
}

class Record{
    int l, r, s;

    public Record(int l, int r, int s) {
        this.l = l;
        this.r = r;
        this.s = s;
    }
}