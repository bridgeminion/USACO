import java.io.*;
import java.util.*;

public class HeatWave {

    static class Point{
        int index;
        int dist;
        public Point (int index, int dist){
            this.index = index;
            this.dist = dist;
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
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());
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
        Set<Integer> visited = new HashSet<>();
        for (int i=1; i <= n; i++){
            key[i] = Integer.MAX_VALUE;
        }
        key[start] = 0;
        for (int i=0; i < n; i++){
            int min = Integer.MAX_VALUE;
            int index = 0;
            for (int j=1; j < n+1; j++){
                if (!visited.contains(j) && key[j] < min){
                    min = key[j];
                    index = j;
                }
            }
            visited.add(index);
            dist[index] = min;
            if (index == end){
                System.out.println(min);
                return;
            }
            if (adj.containsKey(index)){
                for (Point j : adj.get(index)){
                    if (key[j.index] > dist[index] + j.dist){
                        key[j.index] = dist[index] + j.dist;
                    }
                }
            }
        }
    }
}
