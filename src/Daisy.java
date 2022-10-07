import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Daisy {

    static boolean[] visited = new boolean[251];


    public static void dfs (int cur, int nodes, boolean[][] adj, boolean[] visited){
        visited[cur] = true;
        for (int i=1; i <= nodes; i++){
            if (adj[i][cur] && !visited[i]){
                dfs (i, nodes, adj, visited);
            }
        }
    }

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("paintbarn.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("paintbarn.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int nodes = Integer.parseInt(st.nextToken());
        int edges = Integer.parseInt(st.nextToken());
        boolean[][] adj = new boolean[nodes+1][nodes+1];
        for (int i=0; i < edges; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st1.nextToken());
            int end = Integer.parseInt(st1.nextToken());
            adj[start][end] = true;
            adj[end][start] = true;
        }
        dfs (1, nodes, adj, visited);
        boolean found = false;
        for (int i=1; i <= nodes; i++){
            if (!visited[i]){
                found = true;
                System.out.println(i);
            }
        }
        if (!found){
            System.out.println(0);
        }
    }
}
