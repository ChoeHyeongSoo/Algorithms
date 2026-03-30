import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
public class Main{
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] road = new int[100001];
        Arrays.fill(road, Integer.MAX_VALUE);

        PriorityQueue<int[]> pq = new PriorityQueue<>( (a, b) -> Integer.compare(a[1], b[1]));
        road[n] = 0; pq.offer(new int[]{n, 0});

        while (!pq.isEmpty()) {

            int[] curr = pq.poll();
            if (curr[0]==k) break;

            if (curr[0]+1 <= 100000 && road[curr[0]+1] > curr[1]+1) {  // +1 logic
                road[curr[0]+1] = curr[1]+1;
                pq.offer(new int[]{curr[0]+1, curr[1]+1});
            }
            if (curr[0]-1 >= 0 && road[curr[0]-1] > curr[1]+1) {   // -1 logic
                road[curr[0]-1] = curr[1]+1;
                pq.offer(new int[]{curr[0]-1, curr[1]+1});
            }
            if (curr[0]>0 && curr[0]*2 <= 100000 && road[curr[0]*2] > curr[1]) {  // *2 logic
                road[curr[0]*2] = curr[1];
                pq.offer(new int[]{curr[0]*2, curr[1]});
            }
        }
        System.out.println(road[k]);
    }
    static int n;
}