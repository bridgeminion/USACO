import java.io.*;
import java.util.*;

class Path1{
    int end;
    int dist;
    public Path1(int end, int dist){
        this.end = end;
        this.dist = dist;
    }
}

public class Hurdles {

    static int ans = -1;

    public static void dfs (int cur, int dest, Map<Integer, List<Path1>> adj, int highest, boolean[] visited, int[] d){
        visited[cur] = true;
        if (cur == dest){
            if (ans == -1 || highest < ans){
                ans = highest;
            }
            return;
        }
        if (!adj.containsKey(cur)){
            return;
        }
        for (Path1 i : adj.get(cur)){
            if (highest == 0){
                d[i.end] = i.dist;
                dfs (i.end, dest, adj, Math.max(highest, i.dist), visited, d);
            }
            else if (!visited[i.end] || (visited[i.end] && highest < d[i.end])){
                d[i.end] = Math.max(highest, i.dist);
                dfs (i.end, dest, adj, Math.max(highest, i.dist), visited, d);
            }
        }
    }

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("paintbarn.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("paintbarn.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int num_stations = Integer.parseInt(st.nextToken());
        int num_paths = Integer.parseInt(st.nextToken());
        int num_tasks = Integer.parseInt(st.nextToken());
        Map<Integer, List<Path1>> adj = new HashMap<>();
        for (int i=0; i < num_paths; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st1.nextToken());
            int b = Integer.parseInt(st1.nextToken());
            int h = Integer.parseInt(st1.nextToken());
            adj.putIfAbsent(a, new ArrayList<>());
            adj.get(a).add(new Path1(b, h));
        }
        for (int i=0; i < num_tasks; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st1.nextToken());
            int b = Integer.parseInt(st1.nextToken());
            ans = -1;
            dfs(a, b, adj, 0, new boolean[num_stations+1], new int[num_stations+1]);
            System.out.println(ans);
        }
    }

}
