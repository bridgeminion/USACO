import java.io.*;
import java.util.*;

public class MST2 {

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
        int e = Integer.parseInt(st.nextToken());
        List<List<Point>> adj = new ArrayList<>(n+1);
        for (int i=0; i <= n; i++){
            adj.add(i, new ArrayList<>());
        }
        for (int i=0; i < e; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st1.nextToken());
            int b = Integer.parseInt(st1.nextToken());
            int c = Integer.parseInt(st1.nextToken());
            adj.get(a).add(new Point(b, c));
            adj.get(b).add(new Point(a, c));
        }
        TreeSet<Node> pq = new TreeSet<>(new Comparator<Node>() {
            @Override
            public int compare(Node node1, Node t1) {
                if (node1.key == t1.key){
                    return node1.index - t1.index;
                }
                return node1.key - t1.key;
            }
        });
        int[] key = new int[n+1];
        for (int i=2; i <= n; i++){
            pq.add(new Node(i, Integer.MAX_VALUE));
            key[i] = Integer.MAX_VALUE;
        }
        pq.add(new Node(1, 0));
        Set<Integer> visited = new HashSet<>();
        int ans = 0;
        while (!pq.isEmpty()){
            Node next = pq.pollFirst();
            while (next != null && visited.contains(next.index)){
                next = pq.pollFirst();
            }
            if (next == null){
                break;
            }
            int cur = next.index;
            ans += next.key;
            visited.add(cur);
            for (Point j : adj.get(cur)){
                if (!visited.contains(j.index) && j.dist < key[j.index]){
                    key[j.index] = j.dist;
                    pq.add(new Node(j.index, j.dist));
                }
            }
        }
        System.out.println(ans);
    }
}
