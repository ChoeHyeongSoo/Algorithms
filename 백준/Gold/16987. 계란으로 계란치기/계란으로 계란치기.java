import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        eggs = new Egg[n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            eggs[i] = new Egg(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        max = 0;
        dfs(0, 0);
        System.out.print(max);
    }

    static int n, max;
    static Egg[] eggs;

    public static void dfs(int idx, int broken) {      // 다 깨져있으면 종료
        if (idx==n) { max = Math.max(broken, max); return; }

        Egg curr = eggs[idx];
        if (curr.g <= 0 || broken==n-1) {
            dfs(idx+1, broken);
            return;     // 여기 return 안 걸어서 디버깅 30분... 재귀에서 돌아오고 아래 for문을 한 번 더 돌리므로 값이 더 커졌다
        }

        for (int target = 0; target < n; target++) {
            if (target ==idx || eggs[target].g<=0) continue;
            eggs[target].g -= curr.w; curr.g -= eggs[target].w;
            int tmp_break = 0;
            if (eggs[target].g <= 0) tmp_break++;
            if (curr.g <= 0) tmp_break++;
            dfs(idx+1, broken+tmp_break);
            eggs[target].g += curr.w; curr.g += eggs[target].w;
        }
    }
}
class Egg {
    int g, w;

    public Egg(int g, int w) {
        this.g = g;
        this.w = w;
    }
}