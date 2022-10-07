import java.io.*;
import java.util.*;

public class SlideDP {

    static class Point implements Comparable<Point>{
        int index;
        Long fun;
        public Point (int index, Long fun){
            this.index = index;
            this.fun = fun;
        }

        @Override
        public int compareTo(Point point) {
            return this.fun.compareTo(point.fun);
        }
    }

    public static long dp (Point pos, int num, Map<Integer, List<Point>> adj, boolean[][] visited, boolean[] vis2, long[][] val, int k, int v){
        if (num == k){
            if (pos.index == v){
                return 0;
            }
            if (visited[pos.index][num]){
                return val[pos.index][num];
            }
            visited[pos.index][num] = true;
            long max = 0;
            for (Point i : adj.get(pos.index)){
                if (!vis2[i.index]){
                    vis2[i.index] = true;
                    max = Math.max(max, i.fun + dp (i, num, adj, visited, vis2, val, k, v));
                    vis2[i.index] = false;
                }
            }
            val[pos.index][num] = max;
            return max;
        }
        if (pos.index == v){
            return 0;
        }
        if (visited[pos.index][num]){
            return val[pos.index][num];
        }
        visited[pos.index][num] = true;
        long max = 0;
        long min = Long.MAX_VALUE;
        for (Point i : adj.get(pos.index)){
            if (!vis2[i.index]){
                vis2[i.index] = true;
                max = Math.max(max, i.fun + dp (i, num, adj, visited, vis2, val, k, v));
                min = Math.min(min, i.fun + dp (i, num+1, adj, visited, vis2, val, k, v));
                vis2[i.index] = false;
            }
        }
        if (num < k-1){
            val[pos.index][num+1] = min;
            visited[pos.index][num+1] = true;
        }
        val[pos.index][num] = Math.min(max, min);
        return val[pos.index][num];
    }

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("berries.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("berries.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        Map<Integer, List<Point>> adj = new HashMap<>();
        for (int i=0; i < e; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st1.nextToken());
            int b = Integer.parseInt(st1.nextToken());
            long fun = Integer.parseInt(st1.nextToken());
            adj.putIfAbsent(a, new ArrayList<>());
            adj.get(a).add(new Point(b, fun));
        }
        boolean[][] visited = new boolean[v+1][k+1];
        boolean[] vis2 = new boolean[v+1];
        long[][] val = new long[v+1][k+1];
        vis2[1] = true;
        System.out.println(dp (new Point(1, 0L), 0, adj, visited, vis2, val, k, v));
    }
}
