import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        Member[] arr = new Member[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i] = new Member();
            arr[i].age = Integer.parseInt(st.nextToken());
            arr[i].name = st.nextToken();
        }
        Arrays.sort(arr);
        StringBuilder sb = new StringBuilder();
        for (Member m : arr) sb.append(m.age).append(" ").append(m.name).append('\n');
        System.out.println(sb);
    }
    static int n;
}
class Member implements Comparable<Member>{
    int age;
    String name;

    @Override
    public int compareTo(Member o) {
        return this.age-o.age;
    }
}