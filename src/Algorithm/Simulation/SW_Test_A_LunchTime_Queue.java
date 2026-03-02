package Algorithm.Simulation;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

class SW_Test_A_LunchTime_Queue { // 2383.
    static Stair_Math[] stairs_Queue = new Stair_Math[2];;
    public static void main(String args[]) throws Exception {
        System.setIn(new FileInputStream("res/Algorithm/Simulation/input_SW_Test_A_LunchTime.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            int n = Integer.parseInt(br.readLine()), s_idx = 0;
            StringTokenizer st;
            List<Person_Queue> people = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    int floor = Integer.parseInt(st.nextToken());
                    if(floor>1) stairs_Queue[s_idx++] = new Stair_Math(i,j,floor);
                    if(floor==1) people.add(new Person_Queue(i,j));
                }
            }
            time = Integer.MAX_VALUE;
            boolean[] v = new boolean[people.size()];
            subset(v, 0, people);
            System.out.println("#" + test_case + " " + time);
        }
    }

    static int time;

    private static void subset(boolean[] v, int idx, List<Person_Queue> people){

        if (idx==people.size()) {
            tiktaktok(v, people);
            return;
        }

        v[idx] = true;
        subset(v, idx+1, people);
        v[idx] = false;
        subset(v, idx+1, people);
    }
    private static void tiktaktok(boolean[] v, List<Person_Queue> people){

        PriorityQueue<Integer> first = new PriorityQueue<>();
        PriorityQueue<Integer> second = new PriorityQueue<>();
        for (int i = 0; i < v.length; i++) {
            Person_Queue p = people.get(i);
            int d = 0;
            if (v[i]) {
                d = Math.abs(p.r - stairs_Queue[0].r) + Math.abs(p.c - stairs_Queue[0].c);
                first.offer(d);
            }
            if (!v[i]) {
                d = Math.abs(p.r - stairs_Queue[1].r) + Math.abs(p.c - stairs_Queue[1].c);
                second.offer(d);
            }
        }

        int s1_time = goes_on(first, stairs_Queue[0].h);
        int s2_time = goes_on(second, stairs_Queue[1].h);
        int curr = Math.max(s1_time, s2_time);  // 이번 부분 집합의 시뮬레이션 타임
        time = Math.min(time, curr);
    }

    public static int goes_on(PriorityQueue<Integer> pq, int h){
        if (pq.isEmpty()) return 0;
        Queue<Integer> q= new LinkedList<>();
        int t = pq.peek()+1, done = 0, total = pq.size();   // 첫 사람 대기 끝난 시간으로 초기화
        while (done < total) {  // 전부 내려간 상태면 종료
            while (!q.isEmpty()&&q.peek()==t){  // 탈출: 계단 다 내려갔으면
                q.poll();
                done++;
            }

            while (q.size()<3&&!pq.isEmpty()&&pq.peek()+1<=t){  // 진입: 계단 인원 남고, 다음 사람 대기 끝이면
                pq.poll();
                q.offer(t+h);
            }

            if (done==total) return t;
            t++;

            if (t>=time) {
                while (!q.isEmpty()&&pq.isEmpty())
                    q.poll();pq.poll();
                return t;}
        }
        return t;
    }
}

class Person_Queue {
    int r, c;

    public Person_Queue(int r, int c) {
        this.r = r;
        this.c = c;
    }
}

class Stair_Queue {
    int r, c, h;

    public Stair_Queue(int r, int c, int h) {
        this.r = r;
        this.c = c;
        this.h = h;
    }
}