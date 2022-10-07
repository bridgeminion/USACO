import java.io.*;
import java.util.*;

class Node1{
    int index;
    Double key;
    public Node1 (int index, Double key){
        this.index = index;
        this.key = key;
    }
}

class Coordinate1{
    double x;
    double y;
    public Coordinate1(double x, double y){
        this.x = x;
        this.y = y;
    }
}


public class Roads {

    public static double dist (Coordinate1 a, Coordinate1 b){
        return Math.sqrt((a.x-b.x)*(a.x-b.x) + (a.y-b.y)*(a.y-b.y));
    }

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("paintbarn.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("paintbarn.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        List<Coordinate1> farms = new ArrayList<>();
        Map<Integer, Set<Integer>> adj = new HashMap<>();
        for (int i=0; i < n; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            farms.add(new Coordinate1(Double.parseDouble(st1.nextToken()), Double.parseDouble(st1.nextToken())));
        }
        for (int i=0; i < m; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st1.nextToken());
            int b = Integer.parseInt(st1.nextToken());
            adj.putIfAbsent(a-1, new HashSet<>());
            adj.get(a-1).add(b-1);
            adj.putIfAbsent(b-1, new HashSet<>());
            adj.get(b-1).add(a-1);
        }
        double[][] cost = new double[n][n];
        for (int i=0; i < n; i++){
            for (int j=0; j < n; j++){
                if (adj.containsKey(i) && adj.get(i).contains(j)){
                    cost[i][j] = 0;
                }
                else {
                    cost[i][j] = dist(farms.get(i), farms.get(j));
                }
            }
        }
        Set<Integer> visited = new HashSet<>();
        PriorityQueue<Node1> pq = new PriorityQueue<>(new Comparator<Node1>() {
            @Override
            public int compare(Node1 node1, Node1 t1) {
                return node1.key.compareTo(t1.key);
            }
        });
        for (int i=1; i < n; i++){
            pq.add(new Node1(i, (double) Integer.MAX_VALUE));
        }
        pq.add(new Node1(0, 0d));
        double ans = 0;
        while (visited.size() < n){
            Node1 next = pq.poll();
            int cur = next.index;
            ans += next.key;
            visited.add(cur);
            List<Node1> temp = new ArrayList<>();
            int size = pq.size();
            for (int i=0; i < size; i++){
                Node1 x = pq.poll();
                x.key = Math.min(x.key, cost[cur][x.index]);
                temp.add(x);
            }
            pq.addAll(temp);
        }



//        boolean[] visited = new boolean[n];
//        visited[0] = true;
//        double ans = 0;
//        Set<Integer> cur = new HashSet<>();
//        Set<Integer> done = new HashSet<>();
//        done.add(0);
//        cur.add(0);
//        while (cur.size() < n){
//            double min = 100001;
//            int index = 0;
//            Set<Integer> add = new HashSet<>();
//            for (int i : cur){
//                for (int j=0; j < n; j++) {
//                    if (adj.containsKey(i) && adj.get(i).contains(j)) {
//                        for (int k : adj.get(i)) {
//                            if (!visited[k]) {
//                                done.add(k);
//                                add.add(k);
//                                visited[k] = true;
//                            }
//                        }
//                        done.add(j);
//                        add.add(j);
//                        visited[j] = true;
//                    }
//                }
//                for (int j=0; j < n; j++){
//                    if (!visited[j] && !done.contains(j)){
//                        if (min > dist(farms.get(i), farms.get(j))){
//                            min = dist(farms.get(i), farms.get(j));
//                            index = j;
//                        }
//                    }
//                }
//            }
//            cur.addAll(add);
//            visited[index] = true;
//            cur.add(index);
//            done.add(index);
//            ans += min;
//        }
        System.out.println(String.format("%.2f", ans));
    }
}
