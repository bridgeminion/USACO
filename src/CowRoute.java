import java.io.*;
import java.util.*;

public class CowRoute {

    static class Route{
        long cost;
        int flights;
        public Route (int cost, int flights){
            this.cost = cost;
            this.flights = flights;
        }
    }

    static class Key{
        long key;
        int flights;
        public Key (long key, int flights){
            this.key = key;
            this.flights = flights;
        }
    }

    static class Node{
        int index;
        long key;
        int flights;
        public Node (int index, long key, int flights){
            this.index = index;
            this.key = key;
            this.flights = flights;
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
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        Route[][] flight = new Route[1001][1001];
        boolean[][] adj = new boolean[1001][1001];
        for (int i=0; i < n; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            int cost = Integer.parseInt(st1.nextToken());
            int m = Integer.parseInt(st1.nextToken());
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            int[] temp = new int[m];
            for (int j=0; j < m; j++){
                temp[j] = Integer.parseInt(st2.nextToken());
            }
            for (int j=0; j < m; j++){
                for (int k=j+1; k < m; k++){
                    int a = temp[j];
                    int b = temp[k];
                    if (adj[a][b]){
                        if (flight[a][b].cost > cost){
                            flight[a][b].cost = cost;
                            flight[a][b].flights = k-j;
                        }
                        else if (flight[a][b].cost == cost){
                            flight[a][b].flights = Math.min(flight[a][b].flights, k-j);
                        }
                    }
                    else {
                        flight[a][b] = new Route(cost, k-j);
                        adj[a][b] = true;
                    }
                }
            }
        }
        Set<Integer> visited = new HashSet<>();
        Key[] key = new Key[1001];
        TreeSet<Node> pq = new TreeSet<>(new Comparator<Node>() {
            @Override
            public int compare(Node node, Node t1) {
                if (node.key == t1.key){
                    if (node.flights == t1.flights) {
                        return node.index - t1.index;
                    }
                    return node.flights - t1.flights;
                }
                return Long.compare(node.key, t1.key);
            }
        });
        for (int i=1; i <= 1000; i++){
            key[i] = new Key(Long.MAX_VALUE, 0);
        }
        key[start] = new Key(0, 0);
        pq.add(new Node(start, 0, 0));
        while (!pq.isEmpty()){
            Node next = pq.pollFirst();
            while (next != null && visited.contains(next.index)){
                next = pq.pollFirst();
            }
            if (next == null){
                break;
            }
            long cur = next.key;
            int index = next.index;
            if (index == end){
                System.out.print(cur + " " + next.flights);
                return;
            }
            visited.add(index);
            for (int j=1; j <= 1000; j++){
                if (!visited.contains(j) && adj[index][j]) {
                    if (key[j].key > cur + flight[index][j].cost){
                        key[j].key = cur + flight[index][j].cost;
                        key[j].flights = flight[index][j].flights + key[index].flights;
                        pq.add(new Node(j, key[j].key, key[j].flights));
                    }
                    else if (key[j].key == cur + flight[index][j].cost){
                        key[j].flights = Math.min(key[j].flights, flight[index][j].flights + key[index].flights);
                        pq.add(new Node(j, key[j].key, key[j].flights));
                    }
                }
            }
        }
        System.out.println("-1 -1");
    }
}
