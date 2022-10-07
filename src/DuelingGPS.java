import java.io.*;
import java.util.*;

public class DuelingGPS {

    static class Node {
        int index;
        int dist;
        public Node (int index, int dist){
            this.index = index;
            this.dist = dist;
        }
    }

    static class Point {
        int index;
        int key;
        public Point (int index, int key){
            this.index = index;
            this.key = key;
        }
    }

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("moop.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("moop.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        List<List<Node>> adj1 = new ArrayList<>(n+1);
        List<List<Node>> adj2 = new ArrayList<>(n+1);
        for (int i=0; i <= n; i++){
            adj1.add(i, new ArrayList<>());
            adj2.add(i, new ArrayList<>());
        }
        for (int i=0; i < m; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st1.nextToken());
            int b = Integer.parseInt(st1.nextToken());
            int dist1 = Integer.parseInt(st1.nextToken());
            int dist2 = Integer.parseInt(st1.nextToken());
            adj1.get(b).add(new Node (a, dist1));
            adj2.get(b).add(new Node (a, dist2));
        }
        int[] key1 = new int[n+1];
        for (int i=1; i < n; i++){
            key1[i] = Integer.MAX_VALUE;
        }
        PriorityQueue<Point> pq1 = new PriorityQueue<>(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                if (o1.key==o2.key){
                    return o1.index-o2.index;
                }
                return o1.key-o2.key;
            }
        });
        Set<Integer> visited1 = new HashSet<>();
        pq1.add(new Point (n, 0));
        for (int i=0; i < n-1; i++){
            Point cur = pq1.poll();
            while (cur != null && visited1.contains(cur.index)){
                cur = pq1.poll();
            }
            if (cur==null){
                break;
            }
            visited1.add(cur.index);
            for (Node j : adj1.get(cur.index)){
                if (!visited1.contains(j.index) && j.dist+cur.key < key1[j.index]){
                    key1[j.index] = j.dist+cur.key;
                    pq1.add(new Point(j.index, key1[j.index]));
                }
            }
        }
        int[] key2 = new int[n+1];
        for (int i=1; i < n; i++){
            key2[i] = Integer.MAX_VALUE;
        }
        PriorityQueue<Point> pq2 = new PriorityQueue<>(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                if (o1.key==o2.key){
                    return o1.index-o2.index;
                }
                return o1.key-o2.key;
            }
        });
        Set<Integer> visited2 = new HashSet<>();
        pq2.add(new Point (n, 0));
        for (int i=0; i < n-1; i++){
            Point cur = pq2.poll();
            while (cur != null && visited2.contains(cur.index)){
                cur = pq2.poll();
            }
            if (cur==null){
                break;
            }
            visited2.add(cur.index);
            for (Node j : adj2.get(cur.index)){
                if (!visited2.contains(j.index) && j.dist+cur.key < key2[j.index]){
                    key2[j.index] = j.dist+cur.key;
                    pq2.add(new Point(j.index, key2[j.index]));
                }
            }
        }
        List<List<Node>> adj3 = new ArrayList<>(n+1);
        adj3.add(new ArrayList<>());
        for (int i=0; i <= n; i++){
            adj3.add(new ArrayList<>());
        }
        for (int i=1; i <= n; i++){
            for (int j=0; j < adj1.get(i).size(); j++){
                int temp = 0;
                if (key1[adj1.get(i).get(j).index] != key1[i] + adj1.get(i).get(j).dist){
                    temp++;
                }
                if (key2[adj2.get(i).get(j).index] != key2[i] + adj2.get(i).get(j).dist){
                    temp++;
                }
                adj3.get(adj1.get(i).get(j).index).add(new Node(i, temp));
            }
        }
        int[] key3 = new int[n+1];
        for (int i=2; i <= n; i++){
            key3[i] = Integer.MAX_VALUE;
        }
        PriorityQueue<Point> pq3 = new PriorityQueue<>(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                if (o1.key==o2.key){
                    return o1.index-o2.index;
                }
                return o1.key-o2.key;
            }
        });
        Set<Integer> visited3 = new HashSet<>();
        pq3.add(new Point (1, 0));
        for (int i=0; i < n-1; i++){
            Point cur = pq3.poll();
            while (cur != null && visited3.contains(cur.index)){
                cur = pq3.poll();
            }
            if (cur==null){
                break;
            }
            visited3.add(cur.index);
            for (Node j : adj3.get(cur.index)){
                if (!visited3.contains(j.index) && j.dist+cur.key < key3[j.index]){
                    key3[j.index] = j.dist+cur.key;
                    pq3.add(new Point(j.index, key3[j.index]));
                }
            }
        }
        System.out.println(key3[n]);
    }
}
