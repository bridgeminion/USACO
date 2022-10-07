import java.io.*;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Backbarn {

    static int ans = 0;
    static int rows;
    static int cols;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void recurse (int row, int col, boolean[][] grid, boolean[][] visited, int moves, int k, int endrow, int endcol){
//        System.out.println("row = " + row);
//        System.out.println("col = " + col);
//        System.out.println();
        if (row == endrow && col == endcol){
            if (moves <= k){
//                System.out.println(moves);
                ans++;
            }
            return;
        }
        if (moves == k){
            return;
        }
        for (int i=0; i < 4; i++){
            int nr = row + dr[i];
            int nc = col + dc[i];
            if (nr >= 0 && nr < rows && nc >= 0 && nc < cols && grid[nr][nc] && !visited[nr][nc]){
                visited[nr][nc] = true;
                recurse(nr, nc, grid, visited, moves+1, k, endrow, endcol);
                visited[nr][nc] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {

//        BufferedReader br = new BufferedReader(new FileReader("perimeter.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("perimeter.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        rows = Integer.parseInt(st.nextToken());
        cols = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        boolean[][] grid = new boolean[rows][cols];
        boolean[][] visited = new boolean[rows][cols];
        for (int i=0; i < rows; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            String temp = st1.nextToken();
            for (int j=0; j < cols; j++){
                if (temp.charAt(j) == '.'){
                    grid[i][j] = true;
                }
            }
        }
        visited[rows-1][0] = true;
        recurse(rows-1, 0, grid, visited, 1, k, 0, cols-1);
        pw.println(ans);
        pw.close();
    }
}
