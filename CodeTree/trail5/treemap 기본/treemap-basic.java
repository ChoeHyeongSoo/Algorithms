import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());

        TreeMap<Integer, Integer> treeMap = new TreeMap<>();

        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            char c = st.nextToken().charAt(0);
            if (c=='p') {
                Collection<Integer> result = treeMap.values();
                if (result.isEmpty()) ans.append("None");
                else
                    for (int k : result) ans.append(k).append(" ");
                ans.append('\n');
            } else {
                int key = Integer.parseInt(st.nextToken());
                if (c=='a') {
                    int value = Integer.parseInt(st.nextToken());
                    treeMap.put(key, value);
                } else if (c=='r')
                    treeMap.remove(key);
                else {
                    int result = treeMap.getOrDefault(key, 0);
                    ans.append(result > 0 ? result : "None").append('\n');
                }
            }
        }
        System.out.println(ans);
    }
    static int n;
}