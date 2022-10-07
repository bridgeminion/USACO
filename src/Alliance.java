import java.io.*;
import java.util.*;

public class Alliance {

    static class Nodes{
        List<Integer> list;
        public Nodes (List<Integer> list){
            this.list = list;
        }
    }

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("moop.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("moop.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        Nodes[] adj = new Nodes[n+1];
        for (int i=1; i <= n; i++){
            adj[i] = new Nodes(new ArrayList<>());
        }
        for (int i=0; i < m; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st1.nextToken());
            int b = Integer.parseInt(st1.nextToken());
            adj[a].list.add(b);
            adj[b].list.add(a);
        }
        int counter = 0;
        int[] num_nodes = new int[n+1];
        int[] num_edges = new int[n+1];
        Set<Integer> visited = new HashSet<>();
        for (int i=1; i <= n; i++){
            if (!visited.contains(i)){
                Queue<Integer> q = new LinkedList<>();
                q.add(i);
                visited.add(i);
                num_nodes[counter] = 1;
                while (!q.isEmpty()){
                    int temp = q.remove();
                    for (int j : adj[temp].list){
                        if (!visited.contains(j)){
                            q.add(j);
                            visited.add(j);
                            num_nodes[counter]++;
                        }
                        num_edges[counter]++;
                    }
                }
                counter++;
            }
        }
        long ans = 1;
        int mod = 1000000007;
        for (int i=0; i < counter; i++){
            if (num_nodes[i] < num_edges[i]/2){
                System.out.println(0);
                return;
            }
            if (num_nodes[i] == num_edges[i]/2){
                ans = (ans * 2)%mod;
            }
            else {
                ans = (ans * num_nodes[i])%mod;
            }
        }
        System.out.println(ans);
    }
}
