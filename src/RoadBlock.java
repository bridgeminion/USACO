import java.io.*;
import java.util.*;

public class RoadBlock {

    static class Point{
        int index;
        int dist;
        public Point (int index, int dist){
            this.index = index;
            this.dist = dist;
        }
    }

    static int ans = 0;

    public static void newShortest (int start, int end, List<Integer> shortest, boolean[][] adj, int[][] cost, int n, int cur){
        for (int i=0; i < shortest.size()-1; i++){
            cost[shortest.get(i)][shortest.get(i+1)] *= 2;
            cost[shortest.get(i+1)][shortest.get(i)] *= 2;
            calc (start, end, adj, cost, n, cur);
            cost[shortest.get(i)][shortest.get(i+1)] /= 2;
            cost[shortest.get(i+1)][shortest.get(i)] /= 2;
        }
    }

    public static void calc (int start, int end, boolean[][] adj, int[][] cost, int n, int cur){
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
                ans = Math.max(ans, min-cur);
            }
            for (int j=1; j <= n; j++){
                if (adj[index][j]){
                    if (key[j] > dist[index] + cost[index][j]){
                        key[j] = dist[index] + cost[index][j];
                    }
                }
            }
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
        int start = 1;
        int end = n;
//        Map<Integer, List<Point>> adj = new HashMap<>();
        int[][] cost = new int[n+1][n+1];
        boolean[][] adj = new boolean[n+1][n+1];
        for (int i=0; i < m; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st1.nextToken());
            int b = Integer.parseInt(st1.nextToken());
            int d = Integer.parseInt(st1.nextToken());
            cost[a][b] = d;
            cost[b][a] = d;
            adj[a][b] = true;
            adj[b][a] = true;
        }
        int[] dist = new int[n+1];
        int[] key = new int[n+1];
        int[] parent = new int[n+1];
        Set<Integer> visited = new HashSet<>();
        List<Integer> shortest = new ArrayList<>();
        for (int i=1; i <= n; i++){
            key[i] = Integer.MAX_VALUE;
            parent[i] = start;
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
//                System.out.println("length: " + min);
                shortest.add(index);
                int curparent = parent[index];
                while (curparent != start){
                    shortest.add(curparent);
                    curparent = parent[curparent];
                }
                shortest.add(start);
//                for (int j : shortest){
//                    System.out.println(j);
//                }
                newShortest(start, end, shortest, adj, cost, n, min);
                break;
            }
            for (int j=1; j <= n; j++){
                if (adj[index][j]){
                    if (key[j] > dist[index] + cost[index][j]){
                        key[j] = dist[index] + cost[index][j];
                        parent[j] = index;
                    }
                }
            }
        }
        System.out.println(ans);
    }
}
