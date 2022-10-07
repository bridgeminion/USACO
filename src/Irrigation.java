import java.io.*;
import java.util.*;

public class Irrigation {

    public static int dist (Coordinate a, Coordinate b){
        return (a.x-b.x)*(a.x-b.x) + (a.y-b.y)*(a.y-b.y);
    }

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("paintbarn.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("paintbarn.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int min_cost = Integer.parseInt(st.nextToken());
        List<Coordinate> farms = new ArrayList<>();
        for (int i=0; i < n; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            farms.add(new Coordinate(Integer.parseInt(st1.nextToken()), Integer.parseInt(st1.nextToken())));
        }
        int[][] cost = new int[n][n];
        for (int i=0; i < n; i++){
            for (int j=0; j < n; j++){
                cost[i][j] = dist(farms.get(i), farms.get(j));
            }
        }
        Set<Integer> visited = new HashSet<>();
        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node node1, Node t1) {
                return node1.key - t1.key;
            }
        });
        for (int i=1; i < n; i++){
            pq.add(new Node(i, Integer.MAX_VALUE));
        }
        pq.add(new Node(0, min_cost));
        int ans = 0;
        while (visited.size() < n){
            Node next = pq.poll();
            int cur = next.index;
            if (next.key  == Integer.MAX_VALUE){
                System.out.println(-1);
                return;
            }
            ans += next.key;
            visited.add(cur);
            List<Node> temp = new ArrayList<>();
            int size = pq.size();
            for (int i=0; i < size; i++){
                Node x = pq.poll();
                if (cost[cur][x.index] >= min_cost && cost[cur][x.index] < x.key){
                    x.key = Math.min(x.key, cost[cur][x.index]);
                }
                temp.add(x);
            }
            pq.addAll(temp);
        }
        System.out.println(ans-min_cost);
    }
}
