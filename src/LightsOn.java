import java.io.*;
import java.util.*;

public class LightsOn {

    static class Point {
        int r;
        int c;
        public Point (int r, int c){
            this.r = r;
            this.c = c;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return r == point.r &&
                    c == point.c;
        }

        @Override
        public int hashCode() {
            return Objects.hash(r, c);
        }
    }

    static class Nodes {
        List<Point> list = new ArrayList<>();
        public Nodes (){
        }
    }

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1 ,1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("lightson.in"));
        PrintWriter pw = new PrintWriter(new FileWriter("lightson.out"));
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        Nodes[][] adj = new Nodes[n+1][n+1];
        for (int i=1; i <= n; i++){
            for (int j=1; j <= n; j++){
                adj[i][j] = new Nodes();
            }
        }
        for (int i=0; i < m; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st1.nextToken());
            int c = Integer.parseInt(st1.nextToken());
            int nr = Integer.parseInt(st1.nextToken());
            int nc = Integer.parseInt(st1.nextToken());
            adj[r][c].list.add(new Point (nr, nc));
        }
        boolean[][] on = new boolean[n+1][n+1];
        boolean[][] visited = new boolean[n+1][n+1];
        Queue<Point> q = new LinkedList<>();
        q.add(new Point (1, 1));
        visited[1][1] = true;
        on[1][1] = true;
        while (!q.isEmpty()){
            Point cur = q.remove();
            on[cur.r][cur.c] = true;
            for (Point i : adj[cur.r][cur.c].list){
                if (!visited[i.r][i.c]){
                    on[i.r][i.c] = true;
                    boolean hasVisitedNeighbor = false;
                    for (int j=0; j < 4; j++){
                        int nr = i.r + dr[j];
                        int nc = i.c + dc[j];
                        if (nr > 0 && nr <= n && nc > 0 && nc <= n && visited[nr][nc]){
                            hasVisitedNeighbor = true;
                            break;
                        }
                    }
                    if (hasVisitedNeighbor){
                        visited[i.r][i.c] = true;
                        q.add(i);
                    }
                }
            }
            for (int i=0; i < 4; i++){
                int nr = cur.r + dr[i];
                int nc = cur.c + dc[i];
                if (nr > 0 && nr <= n && nc > 0 && nc <= n && on[nr][nc] && !visited[nr][nc]){
                    visited[nr][nc] = true;
                    q.add(new Point (nr, nc));
                }
            }
        }
        int ans = 0;
        for (int i=1; i <= n; i++){
            for (int j=1; j <= n; j++){
                if (on[i][j]) ans++;
            }
        }
        pw.println(ans);
        pw.close();
    }
}
