package Algorithm.BackTracking.D3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class D3_6808_CardBattle {
    /*
    규영이와 인영이는 1에서 18까지의 수가 적힌 18장의 카드로 게임을 하고 있다.
    한 번의 게임에 둘은 카드를 잘 섞어 9장씩 카드를 나눈다. 그리고 아홉 라운드에 걸쳐 게임을 진행한다.
    한 라운드에는 한 장씩 카드를 낸 다음 두 사람이 낸 카드에 적힌 수를 비교해서 점수를 계산한다.
    높은 수가 적힌 카드를 낸 사람은 두 카드에 적힌 수의 합만큼 점수를 얻고, 낮은 수가 적힌 카드를 낸 사람은 아무런 점수도 얻을 수 없다.
    이렇게 아홉 라운드를 끝내고 총점을 따졌을 때, 총점이 더 높은 사람이 이 게임의 승자가 된다.
    두 사람의 총점이 같으면 무승부이다. 이번 게임에 규영이가 받은 9장의 카드에 적힌 수가 주어진다.
    규영이가 내는 카드의 순서를 고정하면, 인영이가 어떻게 카드를 내는지에 따른 9!가지 순서에 따라 규영이의 승패가 정해질 것이다.
    이 때, 규영이가 이기는 경우와 지는 경우가 총 몇 가지 인지 구하는 프로그램을 작성하라.
    */
    public static void main(String args[]) throws Exception {
        StringBuilder ans = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            ans.append("#").append(test_case).append(" ");
            StringTokenizer st = new StringTokenizer(br.readLine());
            ky = new int[9]; iy = new int[9];
            boolean[] num = new boolean[19];
            for (int i = 0; i < 9; i++) {
                ky[i] = Integer.parseInt(st.nextToken());
                num[ky[i]] = true;
            }
            int iy_idx = 0;
            for (int i = 1; i <= 18; i++) { // 리스트로 만들면 이런 번거로운 거 안 해도 된다.
                if (num[i]) continue;
                iy[iy_idx++] = i;
            }

            win = 0; lose = 0; v = new boolean[9];

            game(0, 0, 0);

//            int total = 1;
//            for (int i = 9; i >= 2; i--)
//                total*=i;

            ans.append(win).append(" ").append(lose).append('\n');
//            ans.append(win).append(" ").append(total-win).append('\n');
        }
        System.out.print(ans);
    }

    static int[] ky, iy;
    static boolean[] v;
    static int win, lose;

    public static void game(int depth, int point_ky, int point_iy) {

        if (point_iy > 85.5) {
            int poss = 1;
            for (int facto = 9-depth; facto >= 2; facto--) {
                poss*=facto;   // 여기 depth를 계속 곱해서 오답..
            }
            lose+=poss; return;
        }
        if (point_ky > 85.5) {  // 백트래킹 유무로 속도 차이가 많이 난다.
            int poss = 1;
            for (int facto = 9-depth; facto >= 2; facto--) {
                poss*=facto;
            }
            win+=poss; return;}
        if (depth==9) { if (point_iy < point_ky) win++; return;}

        for (int i = 0; i < 9; i++) {

            if (v[i]) continue;
            v[i] = true;
            if (ky[depth] < iy[i])
                game(depth+1, point_ky, point_iy + ky[depth] + iy[i]);
            else
                game(depth+1, point_ky + ky[depth] + iy[i], point_iy);
            v[i] = false;
        }
    }
}