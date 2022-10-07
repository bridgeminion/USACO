import java.io.*;
import java.util.*;

public class FencePlan {

    static class Coordinate {
        int x;
        int y;
        public Coordinate(int x, int y){
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Coordinate that = (Coordinate) o;
            return x == that.x &&
                    y == that.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    static class Nodes{
        List<Integer> list;
        public Nodes (List<Integer> list){
            this.list = list;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("fenceplan.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("fenceplan.out"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        Coordinate[] points = new Coordinate[n+1];
        Nodes[] adj = new Nodes[n+1];
        for (int i=1; i <= n; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st1.nextToken());
            int y = Integer.parseInt(st1.nextToken());
            points[i] = new Coordinate(x, y);
            adj[i] = new Nodes (new ArrayList<>());
        }
        for (int i=0; i < m; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st1.nextToken());
            int b = Integer.parseInt(st1.nextToken());
            adj[a].list.add(b);
            adj[b].list.add(a);
        }
        int[] index = new int[n+1];
        boolean[] visited = new boolean[n+1];
        int counter = 0;
        for (int i=1; i <= n; i++){
            if (!visited[i]){
                Queue<Integer> q = new LinkedList<>();
                visited[i] = true;
                q.add(i);
                while (!q.isEmpty()){
                    int temp = q.remove();
                    index[temp] = counter;
                    for (int j : adj[temp].list){
                        if (!visited[j]){
                            visited[j] = true;
                            q.add(j);
                        }
                    }
                }
                counter++;
            }
        }
        int[] minx = new int[counter];
        int[] maxx = new int[counter];
        int[] miny = new int[counter];
        int[] maxy = new int[counter];
        for (int i=0; i < counter; i++){
            minx[i] = 1000000000;
            miny[i] = 1000000000;
        }
        for (int i=1; i <= n; i++){
            Coordinate cur = points[i];
            minx[index[i]] = Math.min(minx[index[i]], cur.x);
            maxx[index[i]] = Math.max(maxx[index[i]], cur.x);
            miny[index[i]] = Math.min(miny[index[i]], cur.y);
            maxy[index[i]] = Math.max(maxy[index[i]], cur.y);
        }
        long ans = Long.MAX_VALUE;
        for (int i=0; i < counter; i++){
            long temp = 2*((maxx[i] - minx[i])+(maxy[i] - miny[i]));
            ans = Math.min(ans, temp);
        }
        pw.println(ans);
        pw.close();
    }
}
