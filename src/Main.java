import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        sb = new StringBuilder();
        Mission[] operators = new Mission[m];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            operators[i] = new Mission(st.nextToken(), Integer.parseInt(st.nextToken()));
        }



    }
    static StringBuilder sb;
    public static void operation(Mission tmp) {

        switch (tmp.op) {
            case "add":
                sb.append(tmp.n);
                break;
            case "remove":

                break;
            case "add":
                sb.append(tmp.n);
                break;
        }

    }

}

class Mission {
    String op;
    int n;

    public Mission(String op, int n) {
        this.op = op;
        this.n = n;
    }
}