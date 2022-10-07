import java.io.*;
import java.util.*;

public class PackDel {

    static class Point {
        int index;
        int dist;

        public Point(int index, int dist) {
            this.index = index;
            this.dist = dist;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return index == point.index &&
                    dist == point.dist;
        }

        @Override
        public int hashCode() {
            return Objects.hash(index, dist);
        }
    }
    static class Node {
        int index;
        int key;
        public Node (int index, int key){
            this.index = index;
            this.key = key;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return index == node.index &&
                    key == node.key;
        }

        @Override
        public int hashCode() {
            return Objects.hash(index, key);
        }
    }

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("paintbarn.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("paintbarn.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        Map<Integer, List<Point>> adj = new HashMap<>();
        for (int i=0; i < m; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st1.nextToken());
            int b = Integer.parseInt(st1.nextToken());
            int d = Integer.parseInt(st1.nextToken());
            adj.putIfAbsent(a, new ArrayList<>());
            adj.putIfAbsent(b, new ArrayList<>());
            adj.get(a).add(new Point(b, d));
            adj.get(b).add(new Point(a, d));
        }
        int[] dist = new int[n+1];
        int[] key = new int[n+1];
        TreeSet<Node> pq = new TreeSet<>(new Comparator<Node>() {
            @Override
            public int compare(Node node, Node t1) {
                if (node.key == t1.key){
                    return node.index - t1.index;
                }
                return node.key - t1.key;
            }
        });
        Set<Integer> visited = new HashSet<>();
        for (int i=2; i <= n; i++){
            key[i] = Integer.MAX_VALUE;
        }
        key[1] = 0;
        pq.add(new Node(1, 0));
        for (int i=0; i < n; i++){
            Node next = pq.pollFirst();
            while (visited.contains(next.index)){
                next = pq.pollFirst();
            }
            int index = next.index;
            int cur = next.key;
            visited.add(index);
            dist[index] = cur;
            if (adj.containsKey(index)){
                for (Point j : adj.get(index)){
                    if (!visited.contains(j.index) && key[j.index] > dist[index] + j.dist){
                        key[j.index] = dist[index] + j.dist;
                        pq.add(new Node(j.index, key[j.index]));
                    }
                }
            }
        }
        System.out.println(dist[n]);
    }
}
