import java.io.*;
import java.util.*;

public class MazeTicTacToe {

    static class Move {
        int r;
        int c;

        public Move(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    static class State {
        int r;
        int c;
        int[][] grid;

        public State(int r, int c, int[][] grid) {
            this.r = r;
            this.c = c;
            this.grid = grid;
        }
    }

    public static boolean equalGrid (State a, State b){
        for (int i=0; i < 3; i++){
            for (int j=0; j < 3; j++){
                if (a.grid[i][j] != b.grid[i][j]) return false;
            }
        }
        return true;
    }

    static int[] pow = new int[10];

    public static int convert (int[][] grid){
        int ans = 0;
        for (int i=0; i < 3; i++){
            for (int j=0; j < 3; j++){
                int factor = i*3+j;
                ans += grid[i][j] * pow[factor];
            }
        }
        return ans;
    }

    public static boolean won (int[][] grid){
        for (int i=0; i < 3; i++){
            if (grid[i][0] == 2 && grid[i][1] == 1 && grid[i][2] == 1) return true;
            if (grid[i][0] == 1 && grid[i][1] == 1 && grid[i][2] == 2) return true;

        }
        for (int j=0; j < 3; j++){
            if (grid[0][j] == 2 && grid[1][j] == 1 && grid[2][j] == 1) return true;
            if (grid[0][j] == 1 && grid[1][j] == 1 && grid[2][j] == 2) return true;
        }
        if (grid[0][0] == 2 && grid[1][1] == 1 && grid[2][2] == 1) return true;
        if (grid[0][0] == 1 && grid[1][1] == 1 && grid[2][2] == 2) return true;
        if (grid[0][2] == 2 && grid[1][1] == 1 && grid[2][0] == 1) return true;
        if (grid[0][2] == 1 && grid[1][1] == 1 && grid[2][0] == 2) return true;
        return false;
    }

    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        int n = Integer.parseInt(br.readLine());
        int[][] grid = new int[n][n]; // 1 = obstacle, 2 = bessie, 3 = O, 4 = M
        Map<Integer, Move> map = new HashMap<>();
        int startR = -1;
        int startC = -1;
        pow[0] = 1;
        for (int i=1; i < 10; i++){
            pow[i] = pow[i-1]*3;
        }
        for (int i=0; i < n; i++){
            String s = br.readLine();
            for (int j=0; j < n; j++){
                char c = s.charAt(j*3);
                if (c == '#'){
                    grid[i][j] = 1;
                }
                else if (c == 'B'){
                    grid[i][j] = 2;
                    startR = i;
                    startC = j;
                }
                else if (c == 'O') {
                    grid[i][j] = 3;
                    int uniqueID = i*n+j;
                    int row = s.charAt(j*3+1)-'1';
                    int col = s.charAt(j*3+2)-'1';
                    map.put(uniqueID, new Move (row, col));
                }
                else if (c == 'M'){
                    grid[i][j] = 4;
                    int uniqueID = i*n+j;
                    int row = s.charAt(j*3+1)-'1';
                    int col = s.charAt(j*3+2)-'1';
                    map.put(uniqueID, new Move (row, col));
                }
            }
        }
        boolean[][][] vis = new boolean[n][n][20000];
        Queue<State> q = new LinkedList<>();
        q.add(new State (startR, startC, new int[3][3]));
        vis[startR][startC][0] = true;
        Set<Integer> ans = new HashSet<>();
        while (!q.isEmpty()){
            State cur = q.remove();
            if (won (cur.grid)){
                ans.add(convert (cur.grid));
                continue;
            }
            int curState = convert(cur.grid);
            for (int i=0; i < 4; i++){
                int nr = cur.r+dr[i];
                int nc = cur.c+dc[i];
                if (grid[nr][nc] != 1){ // outside is all haybales
                    int id = nr*n+nc;
                    int newState = curState;
                    if (map.containsKey(id)){
                        int[][] newGrid = new int[3][3];
                        for (int j=0; j < 3; j++){
                            for (int k=0; k < 3; k++){
                                newGrid[j][k] = cur.grid[j][k];
                            }
                        }
                        Move next = map.get(id);
                        if (newGrid[next.r][next.c] == 0){
                            newGrid[next.r][next.c] = grid[nr][nc]-2;
                            newState += newGrid[next.r][next.c] * pow[next.r*3+next.c];
                        }
                        if (!vis[nr][nc][newState]){
                            vis[nr][nc][newState] = true;
                            q.add(new State (nr, nc, newGrid));
                        }
                    }
                    else {
                        if (!vis[nr][nc][newState]){
                            vis[nr][nc][newState] = true;
                            q.add(new State (nr, nc, cur.grid));
                        }
                    }
                }
            }
        }
        System.out.println(ans.size());
    }
}
