import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Tractor2 {

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
            if (root_a != root_b) {
                if (size[root_a] > size[root_b]){
                    par[root_b] = par[root_a];
                    size[root_a] += size[root_b];
                }
                else {
                    par[root_a] = par[root_b];
                    size[root_b] += size[root_a];
                }
            }
            num_components--;
        }

        public boolean find (int a, int b){
            return root (a) == root (b);
        }
    }

    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    static class Edge {
        int a;
        int b;
        int weight;

        public Edge(int a, int b, int weight) {
            this.a = a;
            this.b = b;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("tractor.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("tractor.out"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int[][] grid = new int[n][n];
        for (int i=0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j=0; j < n; j++){
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        UnionFind uf = new UnionFind (n*n);
        List<Edge> list = new ArrayList<>();
        for (int i=0; i < n; i++){
            for (int j=0; j < n; j++){
                for (int k=0; k < 2; k++){
                    int nr = i+dr[k];
                    int nc = i+dc[k];
                    if (nr >= 0 && nr < n && nc >= 0 && nc < n){
                        list.add (new Edge (i*n + j + 1, nr*n + nc + 1, Math.abs(grid[nr][nc] - grid[i][j])));
                    }
                }
            }
        }
        list.sort(new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return o1.weight - o2.weight;
            }
        });
        int target = (n*n+1)/2;
        for (Edge i : list){
            uf.union(i.a, i.b);
            if (uf.size[uf.root(i.a)] >= target || uf.size[uf.root(i.b)] >= target){
                pw.println(i.weight);
                pw.close();
                return;
            }
        }
    }
}
