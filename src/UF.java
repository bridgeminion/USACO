import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class UF {

    public static class UnionFind {

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

        public boolean find (int a, int b){
            return root (a) == root (b);
        }
    }


    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("haybales.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("haybales.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        UnionFind uf = new UnionFind (n);
        for (int i=0; i < m; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st1.nextToken());
            int b = Integer.parseInt(st1.nextToken());
            uf.union(a, b);
            List<Integer> list = new ArrayList<>();
            boolean[] visited = new boolean[n+1];
            for (int j=1; j <= n; j++){
                int temp = uf.root(j);
                if (!visited[temp]){
                    visited[temp] = true;
                    list.add(uf.size[temp]);
                }
            }
            Collections.sort(list);
            for (int j : list){
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }
}
