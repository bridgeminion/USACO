import java.io.*;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class LilypadPond {

    static class State {
        int row;
        int col;
        public State (int row, int col){
            this.row = row;
            this.col = col;
        }
    }

    static int[] dr = {1, 1, -1, -1, -2, -2, 2, 2};
    static int[] dc = {-2, 2, -2, 2, -1, 1, -1, 1};

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("moop.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("moop.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int[][] grid = new int[r][c];
        int startr = 0;
        int startc = 0;
        int endr = 0;
        int endc = 0;
        for (int i=0; i < r; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            for (int j=0; j < c; j++){
                int temp = Integer.parseInt(st1.nextToken());
                if (temp == 3){
                    startr = i;
                    startc = j;
                    grid[i][j] = 1;
                }
                else if (temp == 4){
                    endr = i;
                    endc = j;
                    grid[i][j] = 1;
                }
                else {
                    grid[i][j] = temp;
                }
            }
        }
        Deque<State> q = new LinkedList<>();
        q.add(new State (startr, startc));
        int[][] val = new int[r][c];
        for (int i=0; i < r; i++){
            for (int j=0; j < c; j++){
                val[i][j] = Integer.MAX_VALUE;
            }
        }
        val[startr][startc] = 0;
        while (!q.isEmpty()){
            State cur = q.remove();
            if (cur.row == endr && cur.col == endc){
                System.out.println(val[cur.row][cur.col]);
                return;
            }
            for (int i=0; i < 8; i++){
                int nr = cur.row + dr[i];
                int nc = cur.col + dc[i];
                if (nr >= 0 && nr < r && nc >= 0 && nc < c && grid[nr][nc] != 2){
                    if (grid[nr][nc] == 1){
                        if (val[nr][nc] > val[cur.row][cur.col]){
                            val[nr][nc] = val[cur.row][cur.col];
                            q.addFirst(new State (nr, nc));
                        }
                    }
                    else {
                        if (val[nr][nc] > val[cur.row][cur.col] + 1){
                            val[nr][nc] = val[cur.row][cur.col] + 1;
                            q.addLast(new State (nr, nc));
                        }
                    }
                }
            }
        }
        System.out.println(-1);
    }
}
