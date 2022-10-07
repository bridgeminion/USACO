import java.io.*;
import java.util.*;

public class TeaTime {

    static boolean found = false;
    static boolean done = false;

    public static void dfs (int a, int b, Map<Integer, Set<Integer>> adj, boolean[] visited){
        if (done){
            return;
        }
        visited[a] = true;
        if (a == b){
            found = true;
            done = true;
            return;
        }
        if (!adj.containsKey(a)){
            found = false;
            done = true;
            return;
        }
        for (int i : adj.get(a)){
            if (!visited[i]){
                dfs (i, b, adj, visited);
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
        int m = Integer.parseInt(st.nextToken());
        int num_q = Integer.parseInt(st.nextToken());
        Map<Integer, Set<Integer>> adj = new HashMap<>();
        for (int i=0; i < m; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st1.nextToken());
            int b = Integer.parseInt(st1.nextToken());
            adj.putIfAbsent(a, new HashSet<>());
            adj.putIfAbsent(b, new HashSet<>());
            adj.get(a).add(b);
            adj.get(b).add(a);
        }
        for (int i=0; i < num_q; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st1.nextToken());
            int b = Integer.parseInt(st1.nextToken());
            found = false;
            done = false;
            dfs (a, b, adj, new boolean[num_cows+1]);
            if (found){
                System.out.println("Y");
            }
            else {
                System.out.println("N");
            }
        }
    }
}
