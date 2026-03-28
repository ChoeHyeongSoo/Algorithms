import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            Map<String, Integer> name2num = new HashMap<>();
            social = new ArrayList<>();
            cnt = new ArrayList<>();
            int n_count = 0;
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                String me = st.nextToken(), you = st.nextToken();
                if (name2num.getOrDefault(me, -1)==-1) {    // 비어있으면 각 맵, 리스트에 추가
                    name2num.put(me, n_count);
                    social.add(n_count++);
                    cnt.add(1);
                }
                if (name2num.getOrDefault(you, -1)==-1) {
                    name2num.put(you, n_count);
                    social.add(n_count++);
                    cnt.add(1);
                }
                int a = name2num.get(me);
                int b = name2num.get(you);
                sb.append(union(a, b)).append('\n');    // 유니온-파인드 : 리턴을 그룹 내 인원수로
            }

        }
        System.out.println(sb);
    }
    static int n;
    static List<Integer> social, cnt;
    public static int union(int x, int y) {
        int root_x = find(x);
        int root_y = find(y);

        if (root_x==root_y) return cnt.get(root_x); // 이미 같은 그룹이면 그 그룹 인원 리턴
        social.set(root_y, root_x);                 // 루트 획일화
        cnt.set(root_x, cnt.get(root_x)+cnt.get(root_y));   // 각 그룹의 수 병합
        return cnt.get(root_x);
    }

    public static int find(int v) {
        if (social.get(v)==v) return v;
        int root = find(social.get(v));
        social.set(v, root);
        return root;
    }
}