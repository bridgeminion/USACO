import java.io.*;
import java.util.*;

public class Dijkstra2 {

    static class Node{
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

    static class Point{
        int index;
        int dist;
        public Point (int index, int dist){
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


    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("paintbarn.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("paintbarn.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int edges = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(st.nextToken());
        List<List<Point>> adj = new ArrayList<>(n+1);
        for (int i=0; i <= n; i++){
            adj.add(i, new ArrayList<>());
        }
        for (int i=0; i < edges; i++){
            StringTokenizer st1  = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st1.nextToken());
            int b = Integer.parseInt(st1.nextToken());
            int d = Integer.parseInt(st1.nextToken());
            adj.get(a).add(new Point(b, d));
            adj.get(b).add(new Point(a, d));
        }
        Set<Integer> visited = new HashSet<>();
        int[] dist = new int[n+1];
        int[] key = new int[n+1];
        TreeSet<Node> pq = new TreeSet<>(new Comparator<Node>() {
            @Override
            public int compare(Node node, Node t1) {
                if (node.key == t1.key){
                    return node.index-t1.index;
                }
                return node.key - t1.key;
            }
        });
        for (int i=1; i <= n; i++){
            dist[i] = -1;
            key[i] = Integer.MAX_VALUE;
        }
        key[start] = 0;
        pq.add(new Node(start, 0));
        while (!pq.isEmpty()){
            Node next = pq.pollFirst();
            while (next != null && visited.contains(next.index)){
                next = pq.pollFirst();
            }
            if (next == null){
                break;
            }
            int cur = next.key;
            int index = next.index;
            visited.add(index);
            dist[index] = cur;
            for (Point j : adj.get(index)){
                if (!visited.contains(j.index)) {
                    if (key[j.index] > cur + j.dist){
                        key[j.index] = cur + j.dist;
                        pq.add(new Node(j.index, key[j.index]));
                    }
                }
            }
        }
        for (int i=1; i <= n; i++){
            System.out.println(dist[i]);
        }
    }
}
