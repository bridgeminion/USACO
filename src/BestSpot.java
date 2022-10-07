import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BestSpot {

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("paintbarn.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("paintbarn.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int num_favs = Integer.parseInt(st.nextToken());
        int num_paths = Integer.parseInt(st.nextToken());
        List<Integer> favs = new ArrayList<>();
        for (int i=0; i < num_favs; i++){
            favs.add(Integer.parseInt(br.readLine()));
        }
        boolean[][] adj = new boolean[n+1][n+1];
        int[][] cost = new int[n+1][n+1];
        for (int i=0; i < num_paths; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st1.nextToken());
            int b = Integer.parseInt(st1.nextToken());
            int c = Integer.parseInt(st1.nextToken());
            adj[a][b] = true;
            adj[b][a] = true;
            cost[a][b] = c;
            cost[b][a] = c;
        }
        for (int k=1; k <= n; k++){
            for (int i=1; i <= n; i++){
                for (int j=1; j <= n; j++){
                    if (i != j && j != k && i != k && adj[i][k] && adj[k][j]){
                        if (cost[i][j] == 0 || cost[i][j] > cost[i][k] + cost[k][j]){
                            cost[i][j] = cost[i][k] + cost[k][j];
                            adj[i][j] = true;
                            adj[j][i] = true;
                        }
                    }
                }
            }
        }
        int best = Integer.MAX_VALUE;
        int ans = 0;
        for (int i=1; i <= n; i++){
            int cur = 0;
            for (int j : favs){
                cur += cost[i][j];
            }
            if (cur < best){
                best = cur;
                ans = i;
            }
            if (cur == best){
                ans = Math.min(ans, i);
            }
        }
        System.out.println(ans);
    }
}
