package Algorithm.DivideConquer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class D3_12322_ColorPaper {
    /*
    여러개의 정사각형칸들로 이루어진 정사각형 모양의 종이가 주어져 있고, 각 정사각형들은 하얀색으로 칠해져 있거나 파란색으로 칠해져 있다.
    주어진 종이를 일정한 규칙에 따라 잘라서 다양한 크기를 가진 정사각형 모양의 하얀색 또는 파란색 색종이를 만들려고 한다.
    전체 종이의 크기가 N×N(N=2k, k는 1 이상 8 이하의 자연수) 이라면 종이를 자르는 규칙은 다음과 같다.
    - 전체 종이가 모두 같은 색으로 칠해져 있지 않으면 가로와 세로로 중간 부분을 잘라서 똑같은 크기의 네 개의 N/2 × N/2색종이로 나눈다.
    - 나누어진 종이 각각에 대해서도 앞에서와 마찬가지로 모두 같은 색으로 칠해져 있지 않으면 같은 방법으로 똑같은 크기의 네 개의 색종이로 나눈다.
    - 이와 같은 과정을 잘라진 종이가 모두 하얀색 또는 모두 파란색으로 칠해져 있거나, 하나의 정사각형 칸이 되어 더 이상 자를 수 없을 때까지 반복한다.
     */
    public static void main(String args[]) throws Exception {
//        System.setIn(new FileInputStream("res/sample_input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int[][] paper = new int[n][n];

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++)
                    paper[i][j] = Integer.parseInt(st.nextToken());
            }

            int[] cnt = new int[2]; // 0: White, 1: Blue
            cut(n, paper, cnt);
            System.out.println("#" + test_case + " " + cnt[0] + " " + cnt[1]);
        }
    }

    public static void cut (int w, int[][] paper, int[] cnt){
        // 기저 조건: 길이가 1이 되면 종료
        if (w == 1) {
            if (paper[0][0]==0) cnt[0]++;
            else cnt[1]++;
            return;
        }

        // 중단 조건 : 모두 파랑 or 모두 하양
        int all = 0;
        for (int[] r : paper)
            all += Arrays.stream(r).sum();

        if (all == 0) {cnt[0]++; return;}
        if (all == w*w) {cnt[1]++; return;}

        // 배열 넘겨 받기, 넘겨 받은 배열을 4등분
        int[][][] divided_paper = new int[4][w / 2][w / 2]; // 0~3에 하나씩

        for (int i = 0; i < w/2; i++) {
            for (int j = 0; j < w/2; j++) {
                divided_paper[0][i][j] = paper[i][j];
                divided_paper[1][i][j] = paper[i][w/2+j];
                divided_paper[2][i][j] = paper[w/2+i][j];
                divided_paper[3][i][j] = paper[w/2+i][w/2+j];
            }
        }

        for (int i = 0; i < 4; i++) {
            cut(w/2, divided_paper[i], cnt); // 4개 모두 재귀
        }
    }
}