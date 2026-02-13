package Algorithm.BackTracking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Hambugi {
    int favor;
    int cal;

    public Hambugi(int favor, int cal) {
        this.favor = favor;
        this.cal = cal;
    }
}

class D3_5215_HamburgerDiet {
    /*
    민기의 햄버거 재료에 대한 점수와 가게에서 제공하는 재료에 대한 칼로리가 주어졌을 때,
    민기가 좋아하는 햄버거를 먹으면서도 다이어트에 성공할 수 있도록 정해진 칼로리 이하의 조합 중에서 민기가 가장 선호하는 햄버거를 조합해주는 프로그램을 만들어보자.
    (단 여러 재료를 조합하였을 햄버거의 선호도는 조합된 재료들의 맛에 대한 점수의 합으로 결정되고, 같은 재료를 여러 번 사용할 수 없으며, 햄버거의 조합의 제한은 칼로리를 제외하고는 없다.)
     */
    public static void main(String args[]) throws Exception {
//        System.setIn(new FileInputStream("res/sample_input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

//        Scanner sc = new Scanner(System.in);
        int T = Integer.parseInt(br.readLine());

        for (int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());

            Hambugi[] hambugis = new Hambugi[n];
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                hambugis[i] = new Hambugi(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            }

            boolean[] v = new boolean[n];

            long startTime = System.nanoTime();
            int total_favor = recursive(n, l, 0, hambugis, v);
//            total_favor = dfs(n, l, 0, hambugis, 0, 0);
            long endTime = System.nanoTime();
            double duration = (endTime - startTime) / 1000000.0;
            System.out.println("실행 시간: " + duration + " ms (상세: " + (endTime - startTime) + " ns)");


            System.out.println("#" + test_case + " " + total_favor);
        }
    }

    // 비트마스킹 재귀, dfs 비교

    public static int recursive(int n, int l, int depth, Hambugi[] hambugis, boolean[] v) {

        int favor_sum = 0, cal_sum = 0;
//        if (depth==n) return favor_sum;   // * Fail Case Occur

        for (int i = 0; i < n; i++) {   // 자기 자신 넣는 것, 다른 거 탐색 하는 것, - 아래로 뻗으며 이거 적용?
            if (v[i]) {
                cal_sum+=hambugis[i].cal;
                if(cal_sum > l) {
                    favor_sum = 0; break;
                }
                favor_sum+=hambugis[i].favor;
            }
        }

        if (depth==n) return favor_sum; //  종료 조건, depth==n이면 다음 재귀 (d+1)로 가는 걸 막는데, 계산 자체가 막혀있는 상태였다

        v[depth] = false;   // 두 순서도 중요
        favor_sum = Math.max(favor_sum, recursive(n, l, depth+1, hambugis, v));

        v[depth] = true;
        favor_sum = Math.max(favor_sum, recursive(n, l, depth+1, hambugis, v));


        return favor_sum;
    }

    public static int dfs(int n, int l, int depth, Hambugi[] hambugis, int tmp_favor, int tmp_cal) {

        if (tmp_cal > l) return 0;
        if (depth==n) return tmp_favor; // favor, cal을 업데이트하면서 깊이를 내려갈 때 전달, 내려와서 조건 체크

        // 가지 형성 : 트리처럼 left, right 역할
        int include = dfs(n, l, depth+1, hambugis, tmp_favor+hambugis[depth].favor, tmp_cal+hambugis[depth].cal);
        int exclude = dfs(n, l, depth+1, hambugis, tmp_favor, tmp_cal);

        return Math.max(include, exclude);
    }


}

/*
1
5 1000
100 200
300 500
250 300
500 1000
400 400
 */
