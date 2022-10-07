import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Roller{
    Coordinate pos;
    boolean last;
    int r;
    int index;
    public Roller (Coordinate pos, boolean last, int r, int index){
        this.pos = pos;
        this.last = last;
        this.r = r;
        this.index = index;
    }
}

public class Baler {

    static boolean done = false;

    public static void dfs (int index, double speed, List<Roller> rollers, double ans, boolean[][] adj, boolean[] visited, int n){
        if (done){
            return;
        }
        Roller cur = rollers.get(index);
        if (cur.last){
            System.out.println((int)(ans));
            done = true;
            return;
        }
        visited[index] = true;
        for (int i=0; i < n; i++){
            if (!visited[i] && adj[index][i]){
                double nspeed = speed*cur.r/rollers.get(i).r;
                dfs (i, nspeed, rollers, ans + Math.abs(nspeed), adj, visited, n);
            }
        }
    }

    public static boolean adj (Roller a, Roller b){
        return (a.r+b.r)*(a.r+b.r) == (a.pos.x-b.pos.x)*(a.pos.x-b.pos.x) + (a.pos.y-b.pos.y)*(a.pos.y-b.pos.y);
    }

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("paintbarn.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("paintbarn.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        Coordinate last = new Coordinate(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        List<Roller> rollers = new ArrayList<>();
        int start = 0;
        for (int i=0; i < n; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st1.nextToken());
            int y = Integer.parseInt(st1.nextToken());
            int r = Integer.parseInt(st1.nextToken());
            Coordinate pos = new Coordinate(x, y);
            if (pos.equals(last)){
                rollers.add(new Roller(pos, true, r, i));
            }
            else {
                rollers.add(new Roller(pos, false, r, i));
            }
            if (x == 0 && y == 0){
                start = i;
            }
        }
        boolean[][] adj = new boolean[n][n];
        for (int i=0; i < n; i++){
            for (int j=0; j < n; j++){
                if (adj(rollers.get(i), rollers.get(j))){
                    adj[i][j] = true;
                }
            }
        }
        dfs(start, 10000, rollers, 10000, adj, new boolean[n], n);
    }
}
