import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        int ans = 0;

        Cache cache = new Cache();

        for (String s : cities) {

            if (cache.isIn.getOrDefault(s.toLowerCase(), false)) {
                cache.update(s.toLowerCase());
                ans++;
            }
            else {
                cache.insert(new Node(s.toLowerCase()));
                ans+=5;
            }

            if (cache.capacity>cacheSize) cache.delete();
        }

        return ans;
    }

    public void main(String[] args) {
        System.out.println(solution(3, new String[]{"Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul"}));
    }

}

class Cache {

    Node head = new Node(), tail = new Node();
    int capacity = 0;
    Map<String, Boolean> isIn = new HashMap<>();

    public Cache() {
        head.next = tail;
        tail.prev = head;
    }

    public void insert(Node v) {

        v.prev = tail.prev;
        v.next = tail;
        tail.prev.next = v;
        tail.prev = v;
        isIn.put(v.city, true);
        capacity++;
    }

    public void delete() {

        Node target = head.next;
        target.next.prev = head;
        head.next = target.next;
        isIn.put(target.city, false);
        capacity--;
    }

    public void update(String s) {

        Node curr = head.next;

        while(!curr.city.equals(s)) curr=curr.next;

        curr.next.prev=curr.prev;
        curr.prev.next=curr.next;

        curr.prev = tail.prev;
        curr.next = tail;

        tail.prev.next = curr;
        tail.prev = curr;
    }
}

class Node {
    String city;
    Node prev, next;

    public Node() {
    }

    public Node(String city) {
        this.city = city;
    }
}