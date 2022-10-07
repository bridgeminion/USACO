import java.io.*;
import java.util.*;

public class Planting {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("planting.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("planting.out"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        Map<Integer, Set<Integer>> adj = new HashMap<>();
        for (int i=0; i < n-1; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st1.nextToken());
            int b = Integer.parseInt(st1.nextToken());
            adj.putIfAbsent(a, new HashSet<>());
            adj.putIfAbsent(b, new HashSet<>());
            adj.get(a).add(b);
            adj.get(b).add(a);
        }
        boolean[] visited = new boolean[n+1];
        int ans = 0;
        for (int i : adj.keySet()){
            ans = Math.max(ans, adj.get(i).size()+1);
        }
        pw.println(ans);
        pw.close();
    }
}
