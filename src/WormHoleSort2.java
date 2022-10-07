import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class WormHoleSort2 {

    static class Worm {
        int a;
        int b;
        int c;

        public Worm(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }

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

    public static boolean check (int[] pos, int n, UnionFind cows, int x, List<Worm> list){
        for (Worm i : list){
            if (i.c >= x){
                cows.union(pos[i.a-1], pos[i.b-1]);
            }
        }
        for (int i=0; i < n; i++){
            if (!cows.find(pos[i], i+1)){
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("wormsort.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("wormsort.out"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] pos = new int[n];
        StringTokenizer s = new StringTokenizer(br.readLine());
        for (int i=0; i < n; i++) {
            pos[i] = Integer.parseInt(s.nextToken());
        }
        List<Worm> list = new ArrayList<>();
        for (int i=0; i < m; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st1.nextToken());
            int b = Integer.parseInt(st1.nextToken());
            int c = Integer.parseInt(st1.nextToken());
            list.add(new Worm (a, b, c));
        }
        if (check(pos, n, new UnionFind(n), Integer.MAX_VALUE, list)){
            pw.println(-1);
            pw.close();
            return;
        }
        int low = 0;
        int high = 1000000001;
        int ans = 0;
        while (low <= high) {
            int mid = (high + low) / 2;
            if (check(pos, n, new UnionFind(n), mid, list)) {
                ans = Math.max(ans, mid);
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        pw.println(ans);
        pw.close();
    }
}
