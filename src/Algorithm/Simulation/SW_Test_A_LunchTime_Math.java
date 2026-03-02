package Algorithm.Simulation;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

class SW_Test_A_LunchTime_Math { // 2383.
    static Stair_Math[] staris_Math = new Stair_Math[2];
    public static void main(String args[]) throws Exception {
        System.setIn(new FileInputStream("res/Algorithm/Simulation/input_SW_Test_A_LunchTime.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int test_case = 1; test_case <= T; test_case++) {
            int n = Integer.parseInt(br.readLine()), s_idx = 0;
            StringTokenizer st;
            List<Person_Math> people = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    int floor = Integer.parseInt(st.nextToken());
                    if(floor>1) staris_Math[s_idx++] = new Stair_Math(i,j,floor);
                    if(floor==1) people.add(new Person_Math(i,j));
                }
            }
            time = Integer.MAX_VALUE;
            boolean[] v = new boolean[people.size()];
            subset(v, 0, people);
            System.out.println("#" + test_case + " " + time);
        }
    }

    static int time;

    private static void subset(boolean[] v, int idx, List<Person_Math> people){

        if (idx==people.size()) {
            tiktaktok(v, people);
            return;
        }

        v[idx] = true;
        subset(v, idx+1, people);
        v[idx] = false;
        subset(v, idx+1, people);
    }

    private static void tiktaktok(boolean[] v, List<Person_Math> people){

        ArrayList<Integer> first = new ArrayList<>();
        ArrayList<Integer> second = new ArrayList<>();
        int d = 0;
        for (int i = 0; i < v.length; i++) {
            Person_Math p = people.get(i);
            if (v[i]) {
                d = Math.abs(p.r - staris_Math[0].r) + Math.abs(p.c - staris_Math[0].c);
                first.add(d);
            }
            if (!v[i]) {
                d = Math.abs(p.r - staris_Math[1].r) + Math.abs(p.c - staris_Math[1].c);
                second.add(d);
            }
        }

        Collections.sort(first); Collections.sort(second);
        int case1 = 0, case2 = 0;
        for (int i = 0; i < first.size()-3; i++) {
            if (first.get(i)+ staris_Math[0].h + 1> first.get(i+3))
                first.set(i+3, first.get(i) + staris_Math[0].h);
        }
        for (int i = 0; i < second.size()-3; i++) {
            if (second.get(i)+ staris_Math[1].h + 1 > second.get(i+3))
                second.set(i+3, second.get(i) + staris_Math[1].h);
        }

        if (!first.isEmpty()) case1 = first.get(first.size()-1); // case1= first.getLast();
        if (!second.isEmpty()) case2 = second.get(second.size()-1); // case2= second.getLast();
        time = Math.min(time, Math.max(case1 +1+ staris_Math[0].h, case2 +1+ staris_Math[1].h));
    }

}

class Person_Math{
    int r, c;

    public Person_Math(int r, int c) {
        this.r = r;
        this.c = c;
    }
}

class Stair_Math {
    int r, c, h;

    public Stair_Math(int r, int c, int h) {
        this.r = r;
        this.c = c;
        this.h = h;
    }
}