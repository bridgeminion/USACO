import java.io.*;
import java.util.*;

public class Minesweeper {

    static boolean done = false;

    public static boolean checkGrid (int maxrow, int maxcol, int[][] grid, boolean[][] mine){
        for (int i=0; i < maxrow; i++){
            for (int j=0; j < maxcol; j++) {
                if (!checkSq(maxrow, maxcol, grid, i, j, mine)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean checkSq (int maxrow, int maxcol, int[][] grid, int row, int col, boolean[][] mine){
        int temp = 0;
        for (int dr = -1; dr <= 1; dr++) {
            for (int dc = -1; dc <=1; dc++) {
                if (row+dr >=0 && row+dr < maxrow && col+dc >= 0 && col+dc < maxcol){
                    if (mine[row+dr][col+dc]){
                        temp++;
                    }
                }
            }
        }
        return (temp == grid[row][col]);
    }

    public static void recurse (int row, int col, int[][] grid, int maxrow, int maxcol, int k, boolean[][] mine, int num){
        if (done){
            return;
        }
        if (row == maxrow-1 && col == maxcol-1) {
            if (checkGrid(maxrow, maxcol, grid, mine)) {
                for (int i=0; i < maxrow; i++) {
                    for (int j=0; j < maxcol; j++) {
                        if (mine[i][j]){
                            System.out.println((i+1) + " " + (j+1));
                        }
                    }
                }
                done = true;
            }
            return;
        }
        if (num > k){
            return;
        }
        if (row-1 > 0 && row-1 < maxrow && col-2 > 0 && col-2 < maxcol && !checkSq(maxrow, maxcol, grid, row-1, col-2, mine)){
            return;
        }
        mine[row][col] = true;
        num++;
        if (grid[row][col] != 0 && num <= k){
            if (row-1 >=0 && row-1 < maxrow && col-1 >=0 && col-1<maxcol){
                if (checkSq(maxrow, maxcol, grid, row, col, mine)){
                    if (col == maxcol-1){
                        recurse(row+1, 0, grid, maxrow, maxcol, k, mine, num);
                    }
                    else{
                        recurse(row, col+1, grid, maxrow, maxcol, k, mine, num);
                    }
                }
            }
            else{
                if (col == maxcol-1){
                    recurse(row+1, 0, grid, maxrow, maxcol, k, mine, num);
                }
                else{
                    recurse(row, col+1, grid, maxrow, maxcol, k, mine, num);
                }
            }
        }
        num--;
        mine[row][col] = false;
        if (col == maxcol-1){
            recurse(row+1, 0, grid, maxrow, maxcol, k, mine, num);
        }
        else{
            recurse(row, col+1, grid, maxrow, maxcol, k, mine, num);
        }
    }

    public static void main(String[] args) throws IOException {

//        BufferedReader br = new BufferedReader(new FileReader("measurement.in"));
//        PrintWriter pw = new PrintWriter(new FileWriter("measurement.out"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int num_rows = Integer.parseInt(st.nextToken());
        int num_cols = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int[][] grid = new int[num_rows][num_cols];
        for (int i=0; i < num_rows; i++){
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            for (int j=0; j < num_cols; j++){
                grid[i][j] = Integer.parseInt(st1.nextToken());
            }
        }
        boolean[][] mine = new boolean[num_rows][num_cols];
        recurse(0, 0, grid, num_rows, num_cols, n, mine, 0);
        pw.close();
    }
}
