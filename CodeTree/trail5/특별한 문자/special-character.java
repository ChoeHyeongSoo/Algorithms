import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;

public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String line = br.readLine();

        LinkedHashMap<Character, Integer> map = new LinkedHashMap<>();
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        boolean isIn = false;
        char ans = ' ';
        for (char key : map.keySet()) {
            if (map.get(key) == 1) {
                isIn = true;
                ans = key;
                break;
            }
        }

        System.out.println(isIn ? ans : "None");
    }
    static int n;
}