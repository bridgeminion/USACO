import java.io.*;
import java.util.*;

public class Training {

    static class Pair {
        int a;
        int b;

        public Pair(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return a == pair.a && b == pair.b;
        }

        @Override
        public int hashCode() {
            return Objects.hash(a, b);
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] cost = new int[n+1][n+1];
        Map<Integer, Set<Integer>> roads = new HashMap<>();
        for (int i=1; i <= n; i++){
            roads.put(i, new HashSet<>());
        }
        for (int i=0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            if (c != 0){
                cost[a][b] = c;
                cost[b][a] = c;
            }
            roads.get(a).add(b);
            roads.get(b).add(a);
        }
//        Set<Pair> all = new HashSet<>();
//        for (int i=1; i <= n; i++){
//            Queue<Integer> q = new LinkedList<>();
//            boolean[] vis = new boolean[n+1];
//            q.add(i);
//            vis[i] = true;
//            int dist = 0;
//            while (!q.isEmpty()){
//                int size = q.size();
//                for (int k=0; k < size; k++){
//                    int cur = q.remove();
//                    if (dist%2 == 1){
//                        all.add(new Pair (Math.min(i, cur), Math.max(i, cur)));
//                    }
//                    for (int j : roads.get(cur)){
//                        if (!vis[j]){
//                            vis[j] = true;
//                            q.add(j);
//                        }
//                    }
//                }
//                dist++;
//            }
//        }
//        int ans = 0;
//        for (Pair i : all){
//            ans += cost[i.a][i.b];
//        }
//        System.out.println(ans);
    }
}
