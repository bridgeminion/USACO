import java.io.*;
import java.util.*;

public class MooTube {

    static class Point{
        int index;
        int dist;
        public Point (int index, int dist){
            this.index = index;
            this.dist = dist;
        }
    }

    public static int find (int start, int k, List<Point>[] map, int n){
        boolean[] visited = new boolean[n+1];
        int[] key = new int[n+1];
        visited[start] = true;
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        int ans = 0;
        key[start] = Integer.MAX_VALUE;
        while (!q.isEmpty()){
            int temp = q.remove();
            ans++;
            for (Point j : map[temp]){
                if (!visited[j.index] && j.dist >= k){
                    key[j.index] = Math.min(j.dist, key[temp]);
                    visited[j.index] = true;
                    q.add(j.index);
                }
            }
        }
        return ans-1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("mootube.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("mootube.out"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());
        List<Point>[] map = new List[n+1];
        for (int i=1; i <= n; i++){
            map[i] = new ArrayList<>();
        }
        for (int i=0; i < n-1; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st1.nextToken());
            int b = Integer.parseInt(st1.nextToken());
            int c = Integer.parseInt(st1.nextToken());
            map[a].add(new Point(b, c));
            map[b].add(new Point(a, c));
        }
        for (int i=0; i < q; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st1.nextToken());
            int start = Integer.parseInt(st1.nextToken());
            pw.println(find (start, k, map, n));
        }
        pw.close();
    }
}
