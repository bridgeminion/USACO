import java.io.*;
import java.util.*;

public class PWalk {

    public static void dfs (int cur, int end,  Map<Integer, List<Integer>> adj, int[][] l, int dist, boolean[] visited){
        visited[cur] = true;
        if (cur == end){
            System.out.println(dist);
        }
        for (int i : adj.get(cur)){
            if (!visited[i]){
                dfs (i, end, adj, l, dist + l[cur][i], visited);
            }
        }
    }

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("paintbarn.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("paintbarn.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int num_cows = Integer.parseInt(st.nextToken());
        int num_q = Integer.parseInt(st.nextToken());
        int[][] num = new int[num_cows+1][num_cows+1];
        Map<Integer, List<Integer>> adj = new HashMap<>();
        for (int i=0; i < num_cows-1; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st1.nextToken());
            int e = Integer.parseInt(st1.nextToken());
            int l = Integer.parseInt(st1.nextToken());
            adj.putIfAbsent(s, new ArrayList<>());
            adj.putIfAbsent(e, new ArrayList<>());
            adj.get(s).add(e);
            adj.get(e).add(s);
            num[s][e] = l;
            num[e][s] = l;
        }
        for (int i=0; i < num_q; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st1.nextToken());
            int e = Integer.parseInt(st1.nextToken());
            dfs (s, e, adj, num, 0, new boolean[num_cows+1]);
        }
        pw.close();
    }
}
