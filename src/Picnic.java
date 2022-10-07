import java.io.*;
import java.util.*;

public class Picnic {

    public static void dfs (int cur, Map<Integer, Set<Integer>> adj, boolean[] visited, Set<Integer> cows, int[] times){
        visited[cur] = true;
        times[cur]++;
        if (adj.containsKey(cur)){
            Iterator it = adj.get(cur).iterator();
            while (it.hasNext()){
                int next = (int)it.next();
                if (!visited[next]){
                    cows.remove(next);
                    dfs (next, adj, visited, cows, times);
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
        int num_cows = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        Map<Integer, Set<Integer>> adj = new HashMap<>();
        Set<Integer> cows1 = new HashSet<>();
        List<Integer> cows2 = new ArrayList<>();
        for (int i=0; i < num_cows; i++){
            int temp = Integer.parseInt(br.readLine());
            cows1.add(temp);
            cows2.add(temp);
        }
        for (int i=0; i < m; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st1.nextToken());
            int b = Integer.parseInt(st1.nextToken());
            adj.putIfAbsent(a, new HashSet<>());
            adj.get(a).add(b);
        }
        int ans = 0;
        int[] times = new int[n+1];
        for (int i : cows2){
            dfs (i, adj, new boolean[n+1], cows1, times);
        }
        for (int i=1; i <= n; i++){
            if (!cows1.contains(i) && times[i] == num_cows){
                ans++;
            }
        }
        System.out.println(ans);
    }
}
