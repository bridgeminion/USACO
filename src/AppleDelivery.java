import java.io.*;
import java.util.*;

public class AppleDelivery {

    static class Point {
        int end;
        int dist;

        public Point(int end, int dist) {
            this.end = end;
            this.dist = dist;
        }
    }

    static class Nodes {
        List<Point> list;
        public Nodes (List<Point> list){
            this.list = list;
        }
    }

    static class Node {
        int index;
        int key;

        public Node(int index, int key) {
            this.index = index;
            this.key = key;
        }
    }

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("haybales.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("haybales.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int num_paths = Integer.parseInt(st.nextToken());
        int num_pastures = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(st.nextToken());
        int end1 = Integer.parseInt(st.nextToken());
        int end2 = Integer.parseInt(st.nextToken());
        Nodes[] adj = new Nodes[num_pastures+1];
        for (int i=1; i <= num_pastures; i++){
            adj[i] = new Nodes (new ArrayList<>());
        }
        for (int i=0; i < num_paths; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st1.nextToken());
            int b = Integer.parseInt(st1.nextToken());
            int d = Integer.parseInt(st1.nextToken());
            adj[a].list.add(new Point(b, d));
            adj[b].list.add(new Point(a, d));
        }
        PriorityQueue<Node> pq1 = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.key-o2.key;
            }
        });
        Set<Integer> visited1 = new HashSet<>();
        pq1.add(new Node (start, 0));
        int[] key1 = new int[num_pastures+1];
        for (int i=1; i <= num_pastures; i++){
            key1[i] = Integer.MAX_VALUE;
        }
        key1[start] = 0;
        while (visited1.size() < num_pastures){
            Node cur = pq1.poll();
            while (visited1.contains(cur.index)){
                cur = pq1.poll();
            }
            visited1.add(cur.index);
            if (visited1.contains(end1) && visited1.contains(end2)){
                break;
            }
            for (Point i : adj[cur.index].list){
                if (!visited1.contains(i.end) && key1[i.end] > cur.key+i.dist){
                    key1[i.end] = cur.key+i.dist;
                    pq1.add(new Node (i.end, key1[i.end]));
                }
            }
        }
        PriorityQueue<Node> pq2 = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.key-o2.key;
            }
        });
        Set<Integer> visited2 = new HashSet<>();
        pq2.add(new Node (end1, 0));
        int[] key2 = new int[num_pastures+1];
        for (int i=1; i <= num_pastures; i++){
            key2[i] = Integer.MAX_VALUE;
        }
        key2[end1] = 0;
        while (visited2.size() < num_pastures){
            Node cur = pq2.poll();
            while (visited2.contains(cur.index)){
                cur = pq2.poll();
            }
            visited2.add(cur.index);
            if (visited2.contains(end2)){
                break;
            }
            for (Point i : adj[cur.index].list){
                if (!visited2.contains(i.end) && key2[i.end] > cur.key+i.dist){
                    key2[i.end] = cur.key+i.dist;
                    pq2.add(new Node (i.end, key2[i.end]));
                }
            }
        }
//        PriorityQueue<Node> pq3 = new PriorityQueue<>(new Comparator<Node>() {
//            @Override
//            public int compare(Node o1, Node o2) {
//                return o1.key-o2.key;
//            }
//        });
//        Set<Integer> visited3 = new HashSet<>();
//        pq3.add(new Node (end2, 0));
//        int[] key3 = new int[num_pastures+1];
//        for (int i=1; i <= num_pastures; i++){
//            key3[i] = Integer.MAX_VALUE;
//        }
//        key3[end2] = 0;
//        while (visited3.size() < num_pastures){
//            Node cur = pq3.poll();
//            while (visited3.contains(cur.index)){
//                cur = pq3.poll();
//            }
//            visited3.add(cur.index);
//            for (Point i : adj[cur.index].list){
//                if (!visited3.contains(i.end) && key3[i.end] > cur.key+i.dist){
//                    key3[i.end] = cur.key+i.dist;
//                    pq3.add(new Node (i.end, key3[i.end]));
//                }
//            }
//        }
        int ans = Math.min(key1[end1] + key2[end2], key1[end2] + key2[end2]);
        System.out.println(ans);
    }
}
