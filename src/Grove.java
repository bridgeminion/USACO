import java.io.*;
import java.util.*;

public class Grove {

    static class State {
        int r;
        int c;
        int s;

        public State(int r, int c, int s) {
            this.r = r;
            this.c = c;
            this.s = s;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            State state = (State) o;
            return r == state.r &&
                    c == state.c &&
                    s == state.s;
        }

        @Override
        public int hashCode() {
            return Objects.hash(r, c, s);
        }
    }

    static int[] dr = {-1, 1, 0, 0, -1, 1, -1, 1};
    static int[] dc = {0, 0, -1, 1, -1, -1, 1, 1};


    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("moop.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("moop.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        boolean[][] grid = new boolean[r][c];
        int startr = 0;
        int startc = 0;
        int topr = r;
        int topc = 0;
        int botr = 0;
        for (int i=0; i < r; i++){
            String s = br.readLine();
            for (int j=0; j < c; j++){
                char temp = s.charAt(j);
                if (temp == '*'){
                    startr = i;
                    startc = j;
                }
                if (temp == 'X'){
                    grid[i][j] = true;
                    if (i < topr){
                        topr = i;
                        topc = j;
                    }
                    else if (i == topr){
                        topc = Math.max(topc, j);
                    }
                }
            }
        }
        for (int i=r-1; i >= 0; i--){
            if (grid[i][topc]){
                botr = i;
                break;
            }
        }
        Queue<State> q = new LinkedList<>();
        Set<State> visited = new HashSet<>();
        q.add(new State (startr, startc, 0));
        visited.add(new State (startr, startc, 0));
        int counter = 0;
        boolean first = false;
        while (!q.isEmpty()){
            int temp = q.size();
            for (int i=0; i < temp; i++){
                State cur = q.remove();
                if (cur.r == startr && cur.c == startc && cur.s == 2){
                    System.out.println(counter);
                }
//                if (cur.r == 5 && cur.c == 24){
//                    System.out.println(cur.s);
//                    System.out.println(counter);
//                    return;
//                }
//                if (!first && cur.s == 1){
//                    System.out.println(cur.r);
//                    System.out.println(cur.c);
//                    System.out.println(counter);
//                    first = true;
//                }
                if (cur.c == topc){
                    if (cur.r <= topr){
                        for (int j=0; j < 8; j++){
                            if (dc[j] == -1){
                                int nr = cur.r + dr[j];
                                int nc = cur.c + dc[j];
                                if (nr >= 0 && nr < r && nc >= 0 && nc < c && !grid[nr][nc] && !visited.contains(new State (nr, nc, cur.s + 1))){
                                    visited.add(new State (nr, nc, cur.s + 1));
                                    q.add(new State (nr, nc, cur.s + 1));
                                }
                            }
                        }
                    }
                    else if (cur.r >= botr){
                        for (int j=0; j < 8; j++){
                            if (dc[j] == 1){
                                int nr = cur.r + dr[j];
                                int nc = cur.c + dc[j];
                                if (nr >= 0 && nr < r && nc >= 0 && nc < c && !grid[nr][nc] && !visited.contains(new State (nr, nc, cur.s + 1))){
                                    visited.add(new State (nr, nc, cur.s + 1));
                                    q.add(new State (nr, nc, cur.s + 1));
                                }
                            }
                        }
                    }
                }
                else {
                    for (int j=0; j < 8; j++){
                        int nr = cur.r + dr[j];
                        int nc = cur.c + dc[j];
                        if (nr >= 0 && nr < r && nc >= 0 && nc < c && !grid[nr][nc]){
                            if (nc != topc && !visited.contains(new State (nr, nc, cur.s))){
                                visited.add(new State (nr, nc, cur.s));
                                q.add(new State (nr, nc, cur.s));
                            }
                            if (nc == topc){
                                if (nr < topr && (j == 0 || j == 1 || j == 2 || j == 4 || j == 5) && !visited.contains(new State (nr, nc, cur.s))){
                                    visited.add(new State (nr, nc, cur.s));
                                    q.add(new State (nr, nc, cur.s));
                                }
                                if (nr > botr && (j == 0 || j == 1 || j == 3 || j == 6 || j == 7) && !visited.contains(new State (nr, nc, cur.s))){
                                    visited.add(new State (nr, nc, cur.s));
                                    q.add(new State (nr, nc, cur.s));
                                }
                            }
                        }
                    }
                }
            }
            counter++;
        }
        System.out.println(-1);
    }
}
