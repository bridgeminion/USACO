import java.io.*;
import java.util.*;


public class Dijkstra {

    static class Point{
        int index;
        int dist;
        public Point (int index, int dist){
            this.index = index;
            this.dist = dist;
        }
    }

    static class Node implements Comparable<Node>{
        int index;
        int key;
        public Node (int index, int key){
            this.index = index;
            this.key = key;
        }
        @Override
        public int compareTo(Node x) {
            return this.key-x.key;
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
        Map<Integer, List<Point>> adj = new HashMap<>();
        for (int i=0; i < edges; i++){
            StringTokenizer st1  = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st1.nextToken());
            int b = Integer.parseInt(st1.nextToken());
            int d = Integer.parseInt(st1.nextToken());
            adj.putIfAbsent(a, new ArrayList<>());
            adj.putIfAbsent(b, new ArrayList<>());
            adj.get(a).add(new Point(b, d));
            adj.get(b).add(new Point(a, d));
        }
        Set<Integer> visited = new HashSet<>();
        int[] dist = new int[n+1];
        int[] key = new int[n+1];
        for (int i=1; i <= n; i++){
            dist[i] = -1;
            key[i] = Integer.MAX_VALUE;
        }
        key[start] = 0;
        for (int i=0; i < n; i++){
            int min = Integer.MAX_VALUE;
            int index = 0;
            for (int j=1; j <= n; j++){
                if (key[j] < min && !visited.contains(j)){
                    min = key[j];
                    index = j;
                }
            }
            visited.add(index);
            dist[index] = min;
            if (adj.containsKey(index)){
                for (Point j : adj.get(index)){
                    if (!visited.contains(j.index)){
                        if (key[j.index] > dist[index] + j.dist){
                            key[j.index] = dist[index] + j.dist;
                        }
                    }
                }
            }
        }
        for (int i=1; i <= n; i++){
            System.out.println(dist[i]);
        }
    }
}
