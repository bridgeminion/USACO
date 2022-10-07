import java.io.*;
import java.util.*;

public class Dream {

    static class State {
        int row;
        int col;
        boolean orange;
        int dir;
        public State (int row, int col, boolean orange, int dir){
            this.row = row;
            this.col = col;
            this.orange = orange;
            this.dir = dir;
        }

    }

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader("dream.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("dream.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int[][] grid = new int[r][c];
        for (int i=0; i < r; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            for (int j=0; j < c; j++){
                grid[i][j] = Integer.parseInt(st1.nextToken());
            }
        }
        Queue<State> q = new LinkedList<>();
        boolean[][][][] visited = new boolean[r][c][2][5];
        q.add(new State (0, 0, false, -1));
        visited[0][0][0][0] = true;
        int counter = 0;
        while (!q.isEmpty()){
            int temp = q.size();
            for (int i=0; i < temp; i++){
                State cur = q.remove();
                int orange = 0;
                if (cur.orange){
                    orange = 1;
                }
                if (cur.row == r-1 && cur.col == c-1){
                    pw.println(counter);
                    pw.close();
                    return;
                }
                boolean added = false;
                if (grid[cur.row][cur.col] == 4){
                    int nr = cur.row + dr[cur.dir];
                    int nc = cur.col + dc[cur.dir];
                    if (nr >= 0 && nr < r && nc >= 0 && nc < c && grid[nr][nc] != 0){
                        if (grid[nr][nc] == 1){
                            if (!visited[nr][nc][orange][0]){
                                visited[nr][nc][orange][0] = true;
                                q.add(new State(nr, nc, cur.orange, -1));
                            }
                            added = true;
                        }
                        else if (grid[nr][nc] == 2){
                            if (!visited[nr][nc][1][0]){
                                visited[nr][nc][1][0] = true;
                                q.add(new State(nr, nc, true, -1));
                            }
                            added = true;
                        }
                        else if (grid[nr][nc] == 3 && cur.orange){
                            if (!visited[nr][nc][1][0]){
                                visited[nr][nc][1][0] = true;
                                q.add(new State (nr, nc, true, -1));
                            }
                            added = true;
                        }
                        else if (grid[nr][nc] == 4){
                            if (!visited[nr][nc][0][cur.dir+1]){
                                visited[nr][nc][0][cur.dir+1] = true;
                                q.add(new State(nr, nc, false, cur.dir));
                            }
                            added = true;
                        }
                    }
                }
                if (grid[cur.row][cur.col] == 1 || grid[cur.row][cur.col] == 2 || grid[cur.row][cur.col] == 3 || (grid[cur.row][cur.col] == 4 && !added)){
                    for (int j=0; j < 4; j++){
                        int nr = cur.row + dr[j];
                        int nc = cur.col + dc[j];
                        if (nr >= 0 && nr < r && nc >= 0 && nc < c && grid[nr][nc] != 0) {
                            if (grid[nr][nc] == 1 && !visited[nr][nc][orange][0]){
                                visited[nr][nc][orange][0] = true;
                                q.add(new State(nr, nc, cur.orange, -1));
                            }
                            else if (grid[nr][nc] == 2 && !visited[nr][nc][1][0]){
                                visited[nr][nc][1][0] = true;
                                q.add(new State(nr, nc, true, -1));
                            }
                            else if (grid[nr][nc] == 3 && cur.orange && !visited[nr][nc][1][0]) {
                                visited[nr][nc][1][0] = true;
                                q.add(new State(nr, nc, true, -1));
                            }
                            else if (grid[nr][nc] == 4 && !visited[nr][nc][0][j+1]){
                                visited[nr][nc][0][j+1] = true;
                                q.add(new State(nr, nc, false, j));
                            }
                        }
                    }
                }
            }
            counter++;
        }
        pw.println(-1);
        pw.close();
    }
}
