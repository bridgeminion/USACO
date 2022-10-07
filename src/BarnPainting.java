import java.io.*;
import java.util.*;

public class BarnPainting {

    static long mod = 1000000007;

    static class Nodes{
        List<Integer> list;
        public Nodes (List<Integer> list){
            this.list = list;
        }
    }

    public static long dp (int index, int color, Nodes[] adj, boolean[][] visited, long[][] val, int parent, int[] c){
        if (c[index] > 0 && c[index] != color){
            return 0;
        }
        if (visited[index][color]){
            return val[index][color];
        }
        visited[index][color] = true;
        long ans = 1;
        for (int i : adj[index].list){
            if (i != parent){
                long num = 0;
                for (int j=1; j <= 3; j++){
                    if (j != color){
                        num += dp (i, j, adj, visited, val, index, c);
                        num %= mod;
                    }
                }
                ans *= num;
                ans %= mod;
            }
        }
        val[index][color] = ans;
        return ans;
    }

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("mooyomooyo.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("mooyomooyo.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        Nodes[] adj = new Nodes[n+1];
        for (int i=1; i <= n; i++){
            adj[i] = new Nodes (new ArrayList<>());
        }
        for (int i=0; i < n-1; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st1.nextToken());
            int b = Integer.parseInt(st1.nextToken());
            adj[a].list.add(b);
            adj[b].list.add(a);
        }
        boolean[][] visited = new boolean[n+1][4];
        long[][] val = new long[n+1][4];
        int[] color = new int[n+1];
        for (int i=0; i < k; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st1.nextToken());
            int b = Integer.parseInt(st1.nextToken());
            color[a] = b;
        }
        System.out.println((dp (1, 1, adj, visited, val, -1, color) +  dp (1, 2, adj, visited, val, -1, color) + dp (1, 3, adj, visited, val, -1, color))%mod);
    }
}
