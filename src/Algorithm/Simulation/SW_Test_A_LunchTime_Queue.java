package Algorithm.Simulation;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

class SW_Test_A_LunchTime_Queue { // 2383.
    /*
    N*N 크기의 정사각형 모양의 방에 사람들이 앉아 있다. 점심을 먹기 위해 아래 층으로 내려가야 하는데, 밥을 빨리 먹기 위해 최대한 빠른 시간 내에 내려가야 한다.
    이동 완료 시간은 모든 사람들이 계단을 내려가 아래 층으로 이동을 완료한 시간이다.
    사람들이 아래층으로 이동하는 시간은 계단 입구까지 이동 시간과 계단을 내려가는 시간이 포함된다.
    ① 계단 입구까지 이동 시간
        - 사람이 현재 위치에서 계단의 입구까지 이동하는데 걸리는 시간으로 다음과 같이 계산한다.
        - 이동 시간(분) = | PR - SR | + | PC - SC |
          (PR, PC : 사람 P의 세로위치, 가로위치, SR, SC : 계단 입구 S의 세로위치, 가로위치)
    ② 계단을 내려가는 시간
        - 계단을 내려가는 시간은 계단 입구에 도착한 후부터 계단을 완전히 내려갈 때까지의 시간이다.
        - 계단 입구에 도착하면, 1분 후 아래칸으로 내려 갈 수 있다.
        - 계단 위에는 동시에 최대 3명까지만 올라가 있을 수 있다.
        - 이미 계단을 3명이 내려가고 있는 경우, 그 중 한 명이 계단을 완전히 내려갈 때까지 계단 입구에서 대기해야 한다.
        - 계단마다 길이 K가 주어지며, 계단에 올라간 후 완전히 내려가는데 K 분이 걸린다.
    사람의 위치와 계단 입구의 위치 및 계단의 길이 정보가 표시된 N*N 크기의 지도가 주어진다.
    이때, 모든 사람들이 계단을 내려가 이동이 완료되는 시간이 최소가 되는 경우를 찾고, 그 때의 소요시간을 출력하는 프로그램을 작성하라.
    [제약 사항]
    1. 시간제한 : 최대 50개 테스트 케이스를 모두 통과하는데, C/C++/Java 모두 3초
    2. 방의 한 변의 길이 N은 4 이상 10 이하의 정수이다. (4 ≤ N ≤ 10)
    3. 사람의 수는 1 이상 10 이하의 정수이다. (1 ≤ 사람의 수 ≤ 10)
    4. 계단의 입구는 반드시 2개이며, 서로 위치가 겹치지 않는다.
    5. 계단의 길이는 2 이상 10 이하의 정수이다. (2 ≤ 계단의 길이 ≤ 10)
    6. 초기에 입력으로 주어지는 사람의 위치와 계단 입구의 위치는 서로 겹치지 않는다.
    */
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