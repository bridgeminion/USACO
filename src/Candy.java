//import java.io.*;
//import java.util.HashSet;
//import java.util.Set;
//import java.util.StringTokenizer;
//
//public class Main {
//    static boolean first = true;
//    static boolean cycle = false;
//    public static int dp (int cur, Set<Integer> f, int m, boolean[] visited, int[] val, int[] c, boolean[] p){
//        if (cycle){
//            return -1;
//        }
//        if (p[cur]){
//            cycle = true;
//            return -1;
//        }
//        p[cur] = true;
//        if (visited[cur]){
//            p[cur] = false;
//            return val[cur];
//        }
//        visited[cur] = true;
//        if (f.contains(cur) && !first){
//            val[cur] = Math.max(dp (cur+m, f, m, visited, val, c, p), val[cur]);
//            p[cur] = false;
//            val[cur] = Math.max(val[cur], dp (cur, f, m, visited, val, c, p));
//            return val[cur];
//        }
//        for (int i : c){
//            if (cur - i >= 0){
//                first = false;
//                val[cur] = Math.max(val[cur], dp (cur-i, f, m, visited, val, c, p) + i);
//            }
//        }
//        p[cur] = false;
//        return val[cur];
//    }
//
//    public static void main(String[] args) throws IOException {
////        BufferedReader br = new BufferedReader(new FileReader("mooyomooyo.in"));
////        PrintWriter pw = new PrintWriter(new FileWriter("mooyomooyo.out"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
//        StringTokenizer st = new StringTokenizer(br.readLine());
//        int n = Integer.parseInt(st.nextToken());
//        int choices = Integer.parseInt(st.nextToken());
//        int num_fav = Integer.parseInt(st.nextToken());
//        int m = Integer.parseInt(st.nextToken());
//        int[] c = new int[choices];
//        Set<Integer> f = new HashSet<>();
//        for (int i=0; i < choices; i++){
//            c[i] = Integer.parseInt(br.readLine());
//        }
//        for (int i=0; i < num_fav; i++){
//            f.add(Integer.parseInt(br.readLine()));
//        }
//        int ans = dp ( n, f, m, new boolean[50000], new int[50000], c, new boolean[50000]);
//        if (cycle){
//            System.out.println(-1);
//            return;
//        }
//        System.out.println(ans);
//    }
//}
