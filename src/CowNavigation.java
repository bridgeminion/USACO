import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class CowNavigation {

    static class State {
        int r1;
        int c1;
        int d1;
        int r2;
        int c2;
        int d2;
        boolean done1;
        boolean done2;
        public State(int r1, int c1, int d1, int r2, int c2, int d2, boolean done1, boolean done2) {
            this.r1 = r1;
            this.c1 = c1;
            this.d1 = d1;
            this.r2 = r2;
            this.c2 = c2;
            this.d2 = d2;
            this.done1 = done1;
            this.done2 = done2;
        }
    }

    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static boolean inBounds (int nr1, int nc1, int n){
        return nr1 >= 0 && nr1 < n && nc1 >= 0 && nc1 < n;
    }

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("mooyomooyo.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("mooyomooyo.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        boolean[][] grid = new boolean[n][n];
        for (int i=0; i < n; i++){
            String temp = br.readLine();
            for (int j=0; j < n; j++){
                grid[i][j] = temp.charAt(j) == 'H';
            }
        }
        Queue<State> q = new LinkedList<>();
        boolean[][][][][][] visited = new boolean[n][n][4][n][n][4];
        q.add(new State (n-1, 0, 0, n-1, 0, 1, false, false));
        visited[n-1][0][0][n-1][0][1] = true;
        int counter = 0;
        while (!q.isEmpty()){
            int temp = q.size();
            for (int i=0; i < temp; i++){
                State cur = q.remove();
                if (cur.r1 == 0 && cur.c1 == n-1){
                    cur.done1 = true;
                }
                if (cur.r2 == 0 && cur.c2 == n-1){
                    cur.done2 = true;
                }
                if (cur.done1 && cur.done2){
                    System.out.println(counter);
                    return;
                }
                int nr1 = cur.r1;
                int nc1 = cur.c1;
                int nd1 = cur.d1;
                int nr2 = cur.r2;
                int nc2 = cur.c2;
                int nd2 = cur.d2;
                // going forward
                nr1 += dr[cur.d1];
                nc1 += dc[cur.d1];
                nr2 += dr[cur.d2];
                nc2 += dc[cur.d2];
                if (!inBounds(nr1, nc1, n)){
                    nr1 = cur.r1;
                    nc1 = cur.c1;
                }
                else if (grid[nr1][nc1]){
                    nr1 = cur.r1;
                    nc1 = cur.c1;
                }
                if (!inBounds(nr2, nc2, n)){
                    nr2 = cur.r2;
                    nc2 = cur.c2;
                }
                else if (grid[nr2][nc2]){
                    nr2 = cur.r2;
                    nc2 = cur.c2;
                }
//                if (!visited[nr1][nc1][nd1][nr2][nc2][nd2]){
//                    visited[nr1][nc1][nd1][nr2][nc2][nd2] = true;
                    q.add(new State (nr1, nc1, nd1, nr2, nc2, nd2, cur.done1, cur.done2));
//                }
                // add 1 to dir
//                if (!visited[cur.r1][cur.c1][(cur.d1+1)%4][cur.r2][cur.c2][(cur.d2+1)%4]){
//                    visited[cur.r1][cur.c1][(cur.d1+1)%4][cur.r2][cur.c2][(cur.d2+1)%4] = true;
                    q.add(new State (cur.r1, cur.c1, (cur.d1+1)%4, cur.r2, cur.c2, (cur.d2+1)%4, cur.done1, cur.done2));
//                }
                // subtract 1 from dir
//                if (!visited[cur.r1][cur.c1][(cur.d1+3)%4][cur.r2][cur.c2][(cur.d2+3)%4]){
//                    visited[cur.r1][cur.c1][(cur.d1+3)%4][cur.r2][cur.c2][(cur.d2+3)%4] = true;
                    q.add(new State (cur.r1, cur.c1, (cur.d1+3)%4, cur.r2, cur.c2, (cur.d2+3)%4, cur.done1, cur.done2));
//                }
            }
            counter++;
        }
    }
}
