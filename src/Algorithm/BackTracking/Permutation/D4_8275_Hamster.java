package Algorithm.BackTracking.Permutation;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

class D4_8275_Hamster {
    /*
    정우는 햄스터 우리를 N개 가지고 있으며, 각 우리에 1번에서 N번까지의 번호를 붙여 일렬로 놔두고 있다.
    정우는 햄스터들에게 별 관심이 없지만, 각 우리에 0마리 이상 X마리 이하의 햄스터가 있다는 것은 알고 있다.
    어느 날 경근이가 정우 집에 놀러 왔다. 경근이는 바쁜 정우와 노는 대신 햄스터의 수를 세면서 놀았다.
    경근이는 M개의 기록을 남겼는데, 각 기록은 “l번 우리에서 r번 우리까지의 햄스터 수를 세었더니 s마리였다.” 하는 내용들이다.
    경근이가 남긴 기록을 모두 만족하는 햄스터 수 배치를 구하는 프로그램을 작성하라.
     */
    public static void main(String args[]) throws Exception {
        StringBuilder ans = new StringBuilder();
        System.setIn(new FileInputStream("res/Algorithm/BackTracking/Permutation/input_D4_8275_Hamster.txt"));
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