import java.io.*;
import java.util.*;

public class Closing {

    static class UnionFind {

        int num_nodes;
        public int[] par;
        public int[] size;
        public int num_components;

        public UnionFind (int nodes) {
            num_nodes = nodes;
            par = new int[num_nodes+1];
            size = new int[num_nodes+1];
            for (int i=1; i <= nodes; i++){
                par[i] = i;
                size[i] = 1;
            }
            num_components = num_nodes;
        }


        public int root (int i) {
            while (par[i] != i){
                par[i] = par[par[i]];
                i = par[i];
            }
            return i;
        }

        public void union (int a, int b){
            int root_a = root (a);
            int root_b = root (b);
            if (root_a != root_b){
                if (size[root_a] > size[root_b]){
                    par[root_b] = par[root_a];
                    size[root_a] += size[root_b];
                }
                else {
                    par[root_a] = par[root_b];
                    size[root_b] += size[root_a];
                }
                num_components--;
            }
        }

        public boolean find (int a, int b){
            return root (a) == root (b);
        }

    }

    static class Nodes{
        List<Integer> list;
        public Nodes (List<Integer> list){
            this.list = list;
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("closing.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("closing.out"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        UnionFind uf = new UnionFind(n);
        Nodes[] adj = new Nodes[n+1];
        for (int i=1; i <= n; i++){
            adj[i] = new Nodes (new ArrayList<>());
        }
        for (int i=0; i < m; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st1.nextToken());
            int b = Integer.parseInt(st1.nextToken());
            adj[a].list.add(b);
            adj[b].list.add(a);
        }
        int[] input = new int[n];
        for (int i=0; i < n; i++){
            input[i] = Integer.parseInt(br.readLine());
        }
        Set<Integer> cur = new HashSet<>();
        boolean[] ans = new boolean[n];
        for (int i=n-1; i >= 0; i--){
            int temp = input[i];
            cur.add(temp);
            for (int j : adj[temp].list){
                if (cur.contains(j)){
                    uf.union(temp, j);
                }
            }
            ans[i] = uf.size[uf.root(temp)] == cur.size();
        }
        for (int i=0; i < n; i++){
            if (ans[i]){
                pw.println("YES");
            }
            else {
                pw.println("NO");
            }
        }
        pw.close();
    }
}
